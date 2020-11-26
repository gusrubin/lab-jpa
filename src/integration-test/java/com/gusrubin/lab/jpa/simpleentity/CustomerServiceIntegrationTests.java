package com.gusrubin.lab.jpa.simpleentity;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import com.gusrubin.lab.jpa.api.simpleentity.dto.CustomerDTO;
import com.gusrubin.lab.jpa.repository.simpleentity.CustomerRepository;
import com.gusrubin.lab.jpa.service.simpleentity.CustomerService;
import com.gusrubin.lab.jpa.simpleentity.builder.CustomerBuilder;
import com.gusrubin.lab.jpa.utils.ObjectConverter;

import io.vertx.core.json.Json;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@ActiveProfiles("it")
class CustomerServiceIntegrationTests {
	
	@Autowired
	private CustomerRepository repository;
	
	@Autowired
	private CustomerService service;
	
	private static final String BASE_PATH = "/customers";

	@Autowired
	private WebApplicationContext wac;

	MockMvc mockMvc;
	
	@BeforeEach
	public void setup() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}
	
	/**
     * Add a new CustomerDTO.
     * @return CustomerDTO
     */
    protected CustomerDTO addNewCustomer(CustomerDTO customerDTO) {
      return service.save(customerDTO);
    }
    
    /**
     * List of Id´s to delete.
     */
    protected List<UUID> idsToDelete;
	
	/**
     * Clean database.
     */
    @AfterEach
    public void cleanDatabase() {
        if (idsToDelete != null) {
            idsToDelete.forEach(id -> service.delete(id));
        }
    }    

	@Test
	void shouldSaveACustomer() throws Exception {
		CustomerDTO dto = CustomerBuilder.buildValidDTOCustomerA();

		MvcResult mvcResult = this.mockMvc.perform(post(BASE_PATH).contentType(MediaType.APPLICATION_JSON)
								.content(ObjectConverter.objectToJson(dto)))
				.andDo(print())
				.andExpect(status().isOk())
		 		.andExpect(content().contentType(MediaType.APPLICATION_JSON))
		 		.andExpect(jsonPath("$.id").value(notNullValue()))
		 		.andExpect(jsonPath("$.name").value(dto.getName()))
		 		.andReturn();
		
		CustomerDTO response = Json.decodeValue(mvcResult.getResponse().getContentAsString(), CustomerDTO.class);
        idsToDelete = List.of(response.getId());
	}
	
	@Test
	void shouldFailOnSaveACustomerWithNameNull() throws Exception {
		CustomerDTO dto = CustomerBuilder.buildDTOWithCustomerNameNull();
				
		MvcResult result = this.mockMvc.perform(post(BASE_PATH).contentType(MediaType.APPLICATION_JSON)
			.content(ObjectConverter.objectToJson(dto)))
			.andDo(print())
			.andExpect(status().is5xxServerError())
			.andReturn();
		
		String content = result.getResponse().getContentAsString();
		
		assertEquals("Invalid customer name", content);
	}
	
	@Test
	void shouldGetACustomer() throws Exception {		
		CustomerDTO dto = addNewCustomer(CustomerBuilder.buildValidDTOCustomerA());
		idsToDelete = List.of(dto.getId());

		this.mockMvc.perform(get(BASE_PATH + "/" + dto.getId()))
				.andDo(print())
				.andExpect(status().isOk())
		 		.andExpect(content().contentType(MediaType.APPLICATION_JSON))
		 		.andExpect(jsonPath("$.id").value(notNullValue()))
		 		.andExpect(jsonPath("$.name").value(dto.getName()));
	}	
	
	@Test
	void shouldDeleteACustomer() throws Exception {
		CustomerDTO dto = addNewCustomer(CustomerBuilder.buildValidDTOCustomerA());
		
		this.mockMvc.perform(delete(BASE_PATH + "/" + dto.getId()))
		.andDo(print())
		.andExpect(status().isNoContent());
		
		assertThat(repository.findById(dto.getId()), equalTo(Optional.empty()));
        idsToDelete = null;
	}

}
