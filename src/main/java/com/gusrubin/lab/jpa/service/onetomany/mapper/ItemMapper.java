package com.gusrubin.lab.jpa.service.onetomany.mapper;

import org.springframework.stereotype.Component;

import com.gusrubin.lab.jpa.api.onetomany.dto.ItemDTO;
import com.gusrubin.lab.jpa.repository.onetomany.entity.Cart;
import com.gusrubin.lab.jpa.repository.onetomany.entity.Item;

@Component
public class ItemMapper {
	
	public ItemDTO toDTO(Item entity) {
		return ItemDTO.builder()
				.id(entity.getId())
				.name(entity.getName())
				.cartId(entity.getCart().getId())
				.build();
	}
	
	public Item toEntity(ItemDTO dto) {
		return Item.builder()
				.id(dto.getId() != null ? dto.getId() : null)
				.name(dto.getName())
				.cart(dto.getCartId() != null ? Cart.builder().id(dto.getId()).build() : null)
				.build();
	}

}
