package com.gusrubin.lab.jpa.service.onetomany;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gusrubin.lab.jpa.api.onetomany.dto.ItemDTO;
import com.gusrubin.lab.jpa.repository.onetomany.CartRepository;
import com.gusrubin.lab.jpa.repository.onetomany.ItemRepository;
import com.gusrubin.lab.jpa.repository.onetomany.entity.Cart;
import com.gusrubin.lab.jpa.repository.onetomany.entity.Item;
import com.gusrubin.lab.jpa.service.onetomany.mapper.ItemMapper;

@Service
public class ItemService {	
	
	private final ItemRepository repository;
	
	private final ItemMapper mapper;
	
	private final CartRepository cartRepository;

	@Autowired
	public ItemService(ItemRepository itemRepository, ItemMapper itemMapper, CartRepository cartRepository) {
		super();
		this.repository = itemRepository;
		this.mapper = itemMapper;
		this.cartRepository = cartRepository;
	}
	
	public List<ItemDTO> findAll() {
		return repository.findAll().stream().map(mapper::toDTO).collect(Collectors.toList());
	}
	
	public ItemDTO findById(UUID id) {
		Optional<Item> result = repository.findById(id);
		return result.isPresent() ? mapper.toDTO(result.get()) : null;
	}
	
	public ItemDTO save(ItemDTO itemDTO) {
		if (itemDTO.getCartId() == null) {
			throw new IllegalStateException("Missing Cart ID");
		}
		Optional<Cart> cartResult = cartRepository.findById(itemDTO.getCartId());
		if (cartResult.isEmpty()) {
			throw new IllegalStateException("Cart not registered");
		}
		return mapper.toDTO(repository.save(Item.builder().cart(cartResult.get()).build()));
	}
	
	public void delete(UUID id) {
		Optional<Item> result = repository.findById(id);
		if (result.isEmpty()) {
			throw new IllegalStateException("Item ID not registered");
		}
		repository.delete(result.get());
	}

}
