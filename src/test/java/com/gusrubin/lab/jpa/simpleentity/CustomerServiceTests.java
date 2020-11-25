package com.gusrubin.lab.jpa.simpleentity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.hibernate.service.spi.InjectService;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
//import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import com.gusrubin.lab.jpa.api.simpleentity.dto.CustomerDTO;
import com.gusrubin.lab.jpa.repository.simpleentity.CustomerRepository;
import com.gusrubin.lab.jpa.repository.simpleentity.entity.CustomerEntity;
import com.gusrubin.lab.jpa.service.simpleentity.CustomerService;
import com.gusrubin.lab.jpa.service.simpleentity.mapper.CustomerMapper;
import com.gusrubin.lab.jpa.simpleentity.builder.CustomerBuilder;

import lombok.extern.slf4j.Slf4j;

//@RunWith(MockitoJUnitRunner.class)
//@ActiveProfiles("test")
@Slf4j
//@RunWith(JUnitPlatform.class)
@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)

class CustomerServiceTests {
	
	@Mock
	private CustomerRepository repository;
	
	@Mock
	private CustomerMapper mapper;
	
	@Spy
	@InjectMocks
	private CustomerService serviceSpy;
	
	@BeforeEach
	public void resetInvocations() {
		//Mockito.reset(serviceSpy);
		MockitoAnnotations.openMocks(this);//.initMocks(this);
		log.info("Starting tests");
	}
	
	@Test
	void shouldSaveCustomer() {		
		CustomerDTO expected = CustomerBuilder.buildValidDTO();
		
		Mockito.when(repository.save(Mockito.any())).thenReturn(expected);		
		
		CustomerDTO result = serviceSpy.save(expected);
		
		log.info(expected.getName());
		
		log.info(result.getName());
		
		assertEquals(expected.getName(), result.getName());
	}
	
//	@Test
//	void shouldUpdateCustomer() {
//		CustomerEntity registeredCustomer = CustomerBuilder.buildValidEntity();
//		Optional<CustomerEntity> customerResult = Optional.of(registeredCustomer);
//		Mockito.when(repository.findByName(registeredCustomer.getName())).thenReturn(customerResult);
//		
//		CustomerEntity updatedCustomer = registeredCustomer;
//		updatedCustomer.setName("CustomerB");
//		
//		Mockito.when(repository.save(Mockito.any())).thenReturn(updatedCustomer);
//		
//		CustomerEntity customer = serviceSpy.update(updatedCustomer);
//		
//		assertEquals(updatedCustomer.getName(), customer.getName());
//	}

}
