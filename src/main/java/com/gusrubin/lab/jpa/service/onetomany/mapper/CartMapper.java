package com.gusrubin.lab.jpa.service.onetomany.mapper;

import java.util.ArrayList;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.gusrubin.lab.jpa.api.onetomany.dto.CartDTO;
import com.gusrubin.lab.jpa.api.onetomany.dto.ItemDTO;
import com.gusrubin.lab.jpa.repository.onetomany.entity.Cart;
import com.gusrubin.lab.jpa.repository.onetomany.entity.Item;

@Component
public class CartMapper {

	@Autowired
	private ItemMapper itemMapper;

	public CartDTO toDTO(Cart entity) {
		return CartDTO.builder().id(entity.getId())
				.items(!CollectionUtils.isEmpty(entity.getItems())
						? entity.getItems().stream().map(itemMapper::toDTO).collect(Collectors.toList())
						: new ArrayList<>())
				.build();
	}

	public Cart toEntity(CartDTO dto) {
		return Cart.builder().id(dto.getId() != null ? dto.getId() : null)
				.items(dto.getItems() != null
						? dto.getItems().stream().map(itemMapper::toEntity).collect(Collectors.toList())
						: new ArrayList<>())
				.build();
	}

}
