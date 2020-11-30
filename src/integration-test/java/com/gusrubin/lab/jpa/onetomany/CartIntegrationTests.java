package com.gusrubin.lab.jpa.onetomany;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
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

import com.gusrubin.lab.jpa.api.onetomany.dto.CartDTO;
import com.gusrubin.lab.jpa.api.onetomany.dto.ItemDTO;
import com.gusrubin.lab.jpa.repository.onetomany.CartRepository;
import com.gusrubin.lab.jpa.repository.onetomany.ItemRepository;
import com.gusrubin.lab.jpa.service.onetomany.CartService;
import com.gusrubin.lab.jpa.service.onetomany.ItemService;
import io.vertx.core.json.Json;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@ActiveProfiles("it")
class CartIntegrationTests {
	
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private ItemService itemService;
	
	private static final String BASE_PATH = "/carts";

	@Autowired
	private WebApplicationContext wac;

	MockMvc mockMvc;
	
	@BeforeEach
	public void setup() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}
	
	/**
     * Add a new CartDTO.
     * @return CartDTO
     */
    protected CartDTO addNewCustomer() {
      return cartService.save();
    }
    
    /**
     * Add a new Item to Cart
     * @return ItemDTO
     */
    protected ItemDTO addNewItemToCart(ItemDTO itemDTO) {
    	return itemService.save(itemDTO);
    }
	
	/**
     * List of Cart Id´s to delete.
     */
    protected List<UUID> cartIdsToDelete;
	
	/**
     * Clean database.
     */
    @AfterEach
    public void cleanDatabase() {
        if (cartIdsToDelete != null) {
        	cartIdsToDelete.forEach(id -> cartService.delete(id));
        }
    }
    
    @Test
	void shouldSaveACart() throws Exception {
		MvcResult mvcResult = this.mockMvc.perform(post(BASE_PATH).contentType(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
		 		.andExpect(content().contentType(MediaType.APPLICATION_JSON))
		 		.andExpect(jsonPath("$.id").value(notNullValue()))
		 		.andReturn();
		
		CartDTO response = Json.decodeValue(mvcResult.getResponse().getContentAsString(), CartDTO.class);
        cartIdsToDelete = List.of(response.getId());
	}

}
