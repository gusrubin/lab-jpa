package com.gusrubin.lab.jpa.api.onetomany;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gusrubin.lab.jpa.api.onetomany.dto.ItemDTO;
import com.gusrubin.lab.jpa.service.onetomany.ItemService;

@RestController
@RequestMapping("/cart-items")
public class ItemApi {
	
private final ItemService service;
	
	@Autowired	
	public ItemApi(ItemService service) {
		this.service = service;
	}
	
	@GetMapping
	public ResponseEntity<List<ItemDTO>> findAll() {
		return ResponseEntity.ok(service.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ItemDTO> findById(@PathVariable UUID id) {
		return ResponseEntity.ok(service.findById(id));
	}
	
	@GetMapping("/cart/{cartId}")
	public ResponseEntity<List<ItemDTO>> findByCartId(@PathVariable UUID cartId) {
		return ResponseEntity.ok(service.findByCartId(cartId));
	}
	
	@PostMapping
	public ResponseEntity<ItemDTO> create(@RequestBody ItemDTO requestBody) {
		return ResponseEntity.ok(service.save(requestBody));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable UUID id) {
		service.delete(id);		
		return ResponseEntity.noContent().build();
	}

}
