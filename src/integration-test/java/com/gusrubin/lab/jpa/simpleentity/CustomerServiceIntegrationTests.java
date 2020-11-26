package com.gusrubin.lab.jpa.simpleentity;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import com.gusrubin.lab.jpa.SpringJpaApplication;
import com.gusrubin.lab.jpa.api.simpleentity.dto.CustomerDTO;
import com.gusrubin.lab.jpa.simpleentity.builder.CustomerBuilder;
import com.gusrubin.lab.jpa.utils.ObjectConverter;

@RunWith(SpringRunner.class)
//@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = { SpringJpaApplication.class })
@ActiveProfiles("it")
class CustomerServiceIntegrationTests {

	@Autowired
	private WebApplicationContext wac;

	MockMvc mockMvc;
	
	private static final String BASE_PATH = "/customers";

	@BeforeEach
	public void setup() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	@Test
	void shouldSaveCustomer() throws Exception {
		CustomerDTO expectedDTO = CustomerBuilder.buildValidDTOCustomerA();

		this.mockMvc.perform(post(BASE_PATH).contentType(MediaType.APPLICATION_JSON)
								.content(ObjectConverter.objectToJson(expectedDTO)))
				.andDo(print())
				.andExpect(status().isOk())
		 		.andExpect(content().contentType(MediaType.APPLICATION_JSON))
		 		.andExpect(jsonPath("$.id").value(notNullValue()))
		 		.andExpect(jsonPath("$.name").value(expectedDTO.getName()));
	}
	
	@Test
	void shouldFailOnSaveCustomerWithNameNull() throws Exception {
		CustomerDTO expectedDTO = CustomerBuilder.buildDTOWithCustomerNameNull();
				
		MvcResult result = this.mockMvc.perform(post(BASE_PATH).contentType(MediaType.APPLICATION_JSON)
			.content(ObjectConverter.objectToJson(expectedDTO)))
			.andDo(print())
			.andExpect(status().is5xxServerError())
			.andReturn();
		
		String content = result.getResponse().getContentAsString();
		
		assertEquals("Invalid customer name", content);
	}	

}
