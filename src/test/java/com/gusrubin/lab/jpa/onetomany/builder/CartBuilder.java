package com.gusrubin.lab.jpa.onetomany.builder;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.gusrubin.lab.jpa.api.onetomany.dto.CartDTO;
import com.gusrubin.lab.jpa.repository.onetomany.entity.Cart;

public class CartBuilder {
	
	private static final UUID NEW_ID = UUID.randomUUID();	
	
	public static Cart buildCartWithoutItems() {
		return Cart.builder().id(NEW_ID).items(new ArrayList<>()).build();
	}
	
	public static CartDTO buildCartDTOWithoutItems() {
		return CartDTO.builder().items(new ArrayList<>()).build();
	}
	
	public static Cart buildCartWithOneItem() {
		return Cart.builder().id(NEW_ID).items(List.of(ItemBuilder.buildItemA())).build();
	}
	
	public static CartDTO buildCartDTOWithOneItem() {
		return CartDTO.builder().items(List.of(ItemBuilder.buildItemDTOA())).build();
	}
	
	public static Cart buildCartWithTwoItems() {
		return Cart.builder().id(NEW_ID)
				.items(List.of(ItemBuilder.buildItemA(), ItemBuilder.buildItemB())).build();
	}
	
	public static CartDTO buildCartDTOWithTwoItem() {
		return CartDTO.builder().items(List.of(ItemBuilder.buildItemDTOA(), ItemBuilder.buildItemDTOB())).build();
	}

}
