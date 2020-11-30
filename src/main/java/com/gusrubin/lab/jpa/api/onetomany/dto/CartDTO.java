package com.gusrubin.lab.jpa.api.onetomany.dto;

import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartDTO {
	
	private UUID id;
	
	private List<ItemDTO> items;

}
