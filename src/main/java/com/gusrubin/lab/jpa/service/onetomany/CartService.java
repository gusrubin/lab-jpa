package com.gusrubin.lab.jpa.service.onetomany;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.gusrubin.lab.jpa.api.onetomany.dto.CartDTO;
import com.gusrubin.lab.jpa.repository.onetomany.CartRepository;
import com.gusrubin.lab.jpa.repository.onetomany.entity.Cart;
import com.gusrubin.lab.jpa.service.onetomany.mapper.CartMapper;

@Service
public class CartService {
	
	private final CartRepository repository;
	
	private final CartMapper mapper;

	public CartService(CartRepository cartRepository, CartMapper cartMapper) {
		this.repository = cartRepository;
		this.mapper = cartMapper;
	}
	
	public List<CartDTO> findAll() {
		return repository.findAll().stream().map(mapper::toDTO).collect(Collectors.toList());
	}
	
	public CartDTO findById(UUID id) {
		Optional<Cart> result = repository.findById(id);
		return result.isPresent() ? mapper.toDTO(result.get()) : null;
	}
	
	public CartDTO save() {
		return mapper.toDTO(repository.save(new Cart()));
	}
	
	public void delete(UUID id) {
		Optional<Cart> result = repository.findById(id);
		if (result.isEmpty()) {
			throw new IllegalStateException("Cart ID not registered");
		}
		repository.delete(result.get());
	}	

}
