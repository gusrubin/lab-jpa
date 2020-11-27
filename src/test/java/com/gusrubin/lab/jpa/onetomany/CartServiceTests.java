package com.gusrubin.lab.jpa.onetomany;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import com.gusrubin.lab.jpa.api.onetomany.dto.CartDTO;
import com.gusrubin.lab.jpa.api.simpleentity.dto.CustomerDTO;
import com.gusrubin.lab.jpa.onetomany.builder.CartBuilder;
import com.gusrubin.lab.jpa.repository.onetomany.CartRepository;
import com.gusrubin.lab.jpa.repository.onetomany.entity.Cart;
import com.gusrubin.lab.jpa.repository.simpleentity.CustomerRepository;
import com.gusrubin.lab.jpa.repository.simpleentity.entity.CustomerEntity;
import com.gusrubin.lab.jpa.service.onetomany.CartService;
import com.gusrubin.lab.jpa.service.onetomany.mapper.CartMapper;
import com.gusrubin.lab.jpa.service.simpleentity.CustomerService;
import com.gusrubin.lab.jpa.service.simpleentity.mapper.CustomerMapper;
import com.gusrubin.lab.jpa.simpleentity.builder.CustomerBuilder;

@ExtendWith(MockitoExtension.class)
class CartServiceTests {

	@Mock
	private CartRepository repository;
	
	@Spy
	private CartMapper mapper;
	
	@Spy
	@InjectMocks
	private CartService serviceSpy;
	
	@Test
	void shouldSaveCartSuccessfully() {
		Cart expectedEntity = CartBuilder.buildCartWithoutItems();
		
		Mockito.when(repository.save(Mockito.any())).thenReturn(expectedEntity);
		
		CartDTO dto = serviceSpy.save();
		
		verify(repository).save(Mockito.any());
		
		assertEquals(expectedEntity.getId(), dto.getId());
	}

}
