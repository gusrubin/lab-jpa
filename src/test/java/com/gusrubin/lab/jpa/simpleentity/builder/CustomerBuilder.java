package com.gusrubin.lab.jpa.simpleentity.builder;

import java.util.UUID;

import com.gusrubin.lab.jpa.api.simpleentity.dto.CustomerDTO;
import com.gusrubin.lab.jpa.repository.simpleentity.entity.CustomerEntity;

public class CustomerBuilder {
	
	private static final UUID ID = UUID.randomUUID();
	
	private static final String VALID_NAME_A = "CustomerA";
	
	private static final String VALID_NAME_B = "CustomerB";
	
	public static CustomerEntity buildValidEntity() {
		return CustomerEntity.builder()
				.id(ID)
				.name(VALID_NAME_A)
				.build();
	}
	
	public static CustomerDTO buildValidDTO() {
		return CustomerDTO.builder()
				.id(null)
				.name(VALID_NAME_B)
				.build();
	}

}
