package com.gusrubin.lab.jpa.api.onetomany.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemDTO {
	
	private UUID id;
	
	private String name;

	private UUID cartId;

}
