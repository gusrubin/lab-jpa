package com.gusrubin.lab.jpa.onetomany.builder;

import java.util.UUID;

import com.gusrubin.lab.jpa.api.onetomany.dto.ItemDTO;
import com.gusrubin.lab.jpa.repository.onetomany.entity.Item;

public class ItemBuilder {
	
	private static final UUID NEW_ID = UUID.randomUUID();
	
	private static final String ITEM_NAME_A = "Item A";
	
	private static final String ITEM_NAME_B = "Item B";
	
	public static Item buildItemA() {
		return Item.builder().id(NEW_ID).name(ITEM_NAME_A).build();
	}
	
	public static Item buildItemB() {
		return Item.builder().id(NEW_ID).name(ITEM_NAME_B).build();
	}
	
	public static ItemDTO buildItemDTOA() {
		return ItemDTO.builder().id(NEW_ID).name(ITEM_NAME_A).build();
	}
	
	public static ItemDTO buildItemDTOB() {
		return ItemDTO.builder().id(NEW_ID).name(ITEM_NAME_B).build();
	}

}
