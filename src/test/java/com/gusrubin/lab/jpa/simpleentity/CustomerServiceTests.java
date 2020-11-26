package com.gusrubin.lab.jpa.simpleentity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import com.gusrubin.lab.jpa.api.simpleentity.dto.CustomerDTO;
import com.gusrubin.lab.jpa.repository.simpleentity.CustomerRepository;
import com.gusrubin.lab.jpa.repository.simpleentity.entity.CustomerEntity;
import com.gusrubin.lab.jpa.service.simpleentity.CustomerService;
import com.gusrubin.lab.jpa.service.simpleentity.mapper.CustomerMapper;
import com.gusrubin.lab.jpa.simpleentity.builder.CustomerBuilder;


@ExtendWith(MockitoExtension.class)
class CustomerServiceTests {
	
	@Mock
	private CustomerRepository repository;
	
	@Spy
	private CustomerMapper mapper;
	
	@Spy
	@InjectMocks
	private CustomerService serviceSpy;
	
	@Test
	void shouldSaveCustomerSuccessfully() {
		CustomerDTO expectedDTO = CustomerBuilder.buildValidDTOCustomerA();
		CustomerEntity expectedEntity = CustomerBuilder.buildValidEntityCustomerA();
		
		Mockito.when(repository.save(Mockito.any())).thenReturn(expectedEntity);
		
		CustomerDTO actualDTO = serviceSpy.save(expectedDTO);
		
		assertEquals(expectedDTO.getName(), actualDTO.getName());
	}
	
	@Test
	void shouldFailOnSaveCustomerWithNameNull() {
		CustomerDTO expectedDTO = CustomerBuilder.buildDTOWithCustomerNameNull();
		
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			serviceSpy.save(expectedDTO);
	    });		
	 
	    String expectedMessage = "Invalid customer name";
	    String actualMessage = exception.getMessage();
	    
	    assertTrue(actualMessage.contains(expectedMessage));
	}

}
