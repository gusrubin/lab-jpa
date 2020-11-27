package com.gusrubin.lab.jpa.service.onetomany.mapper;

import org.springframework.stereotype.Component;

import com.gusrubin.lab.jpa.api.onetomany.dto.CartDTO;
import com.gusrubin.lab.jpa.repository.onetomany.entity.Cart;

@Component
public class CartMapper {
	
	public CartDTO toDTO(Cart entity) {
		return CartDTO.builder()
				.id(entity.getId())
				.items(entity.getItems())
				.build();
	}
	
	public Cart toEntity(CartDTO dto) {
		return Cart.builder()
				.id(dto.getId() != null ? dto.getId() : null)
				.items(dto.getItems() != null ? dto.getItems() : null)
				.build();
	}

}
