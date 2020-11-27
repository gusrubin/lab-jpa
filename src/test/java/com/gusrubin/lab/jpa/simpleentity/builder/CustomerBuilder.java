package com.gusrubin.lab.jpa.simpleentity.builder;

import java.util.UUID;

import com.gusrubin.lab.jpa.api.simpleentity.dto.CustomerDTO;
import com.gusrubin.lab.jpa.repository.simpleentity.entity.CustomerEntity;

public class CustomerBuilder {
	
	private static final UUID NEW_ID = UUID.randomUUID();
	
	private static final String VALID_NAME_A = "CustomerA";
	
	private static final String VALID_NAME_B = "CustomerB";
	
	public static CustomerDTO buildValidDTOCustomerA() {
		return CustomerDTO.builder()
				.id(null)
				.name(VALID_NAME_A)
				.build();
	}
	
	public static CustomerEntity buildValidEntityCustomerA() {
		return CustomerEntity.builder()
				.id(NEW_ID)
				.name(VALID_NAME_A)
				.build();
	}
	
	public static CustomerDTO buildValidDTOCustomerB() {
		return CustomerDTO.builder()
				.id(null)
				.name(VALID_NAME_B)
				.build();
	}
	
	public static CustomerEntity buildValidEntityCustomerB() {
		return CustomerEntity.builder()
				.id(NEW_ID)
				.name(VALID_NAME_B)
				.build();
	}
	
	public static CustomerDTO buildDTOWithCustomerNameNull() {
		return CustomerDTO.builder()
				.id(null)
				.name(null)
				.build();
	}	

}
