package com.gusrubin.lab.jpa.api.onetomany;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gusrubin.lab.jpa.api.onetomany.dto.CartDTO;
import com.gusrubin.lab.jpa.service.onetomany.CartService;

@RestController
@RequestMapping("/carts")
public class CartApi {
	
	private final CartService service;
	
	@Autowired	
	public CartApi(CartService service) {
		this.service = service;
	}
	
	@GetMapping
	public ResponseEntity<List<CartDTO>> findAll() {
		return ResponseEntity.ok(service.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CartDTO> findById(@PathVariable UUID id) {
		return ResponseEntity.ok(service.findById(id));
	}
	
	@PostMapping
	public ResponseEntity<CartDTO> create() {
		return ResponseEntity.ok(service.save());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable UUID id) {
		service.delete(id);		
		return ResponseEntity.noContent().build();
	}

}
