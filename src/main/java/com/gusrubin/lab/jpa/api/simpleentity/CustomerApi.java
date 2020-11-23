package com.gusrubin.lab.jpa.api.simpleentity;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gusrubin.lab.jpa.api.simpleentity.dto.CustomerDTO;
import com.gusrubin.lab.jpa.service.simpleentity.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerApi {
	
	private final CustomerService service;
	
	public CustomerApi(CustomerService customerService) {
		this.service = customerService;
	}
	
	@GetMapping
	public ResponseEntity<List<CustomerDTO>> findAll() {
		return ResponseEntity.ok(service.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CustomerDTO> findById(@PathVariable UUID id) {
		return ResponseEntity.ok(service.findById(id));
	}
	
	@PostMapping
	public ResponseEntity<CustomerDTO> create(@RequestBody CustomerDTO requestBody) {
		return ResponseEntity.ok(service.save(requestBody));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable UUID id) {
		service.delete(id);		
		return ResponseEntity.noContent().build();
	}

}
