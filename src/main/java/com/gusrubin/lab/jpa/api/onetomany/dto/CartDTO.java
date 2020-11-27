package com.gusrubin.lab.jpa.api.onetomany.dto;

import java.util.List;
import java.util.UUID;

import com.gusrubin.lab.jpa.repository.onetomany.entity.Item;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CartDTO {
	
	private UUID id;
	
	private List<Item> items;

}
