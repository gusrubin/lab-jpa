package com.gusrubin.lab.jpa.service.simpleentity.mapper;

import org.springframework.stereotype.Component;

import com.gusrubin.lab.jpa.api.simpleentity.dto.CustomerDTO;
import com.gusrubin.lab.jpa.repository.simpleentity.entity.CustomerEntity;

@Component
public class CustomerMapper {
	
	public CustomerDTO toDTO(CustomerEntity entity) {
		return CustomerDTO.builder()
				.id(entity.getId())
				.name(entity.getName())
				.build();
	}
	
	public CustomerEntity toEntity(CustomerDTO dto) {
		return CustomerEntity.builder()
				.id(dto.getId() != null ? dto.getId() : null)
				.name(dto.getName())
				.build();
	}
}
