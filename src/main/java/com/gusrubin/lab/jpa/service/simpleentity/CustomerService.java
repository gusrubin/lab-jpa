package com.gusrubin.lab.jpa.service.simpleentity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.gusrubin.lab.jpa.api.simpleentity.dto.CustomerDTO;
import com.gusrubin.lab.jpa.repository.simpleentity.CustomerRepository;
import com.gusrubin.lab.jpa.repository.simpleentity.entity.CustomerEntity;
import com.gusrubin.lab.jpa.service.simpleentity.mapper.CustomerMapper;

@Service
public class CustomerService {
	
	private final CustomerRepository repository;
	
	private final CustomerMapper mapper;
	
	public CustomerService(CustomerRepository customerRepository, CustomerMapper customerMapper) {
		this.repository = customerRepository;
		this.mapper = customerMapper;
	}
	
	public List<CustomerDTO> findAll() {
		return repository.findAll().stream().map(mapper::toDTO).collect(Collectors.toList());
	}
	
	public CustomerDTO findById(UUID id) {
		Optional<CustomerEntity> customerResult = repository.findById(id);
		
		return customerResult.isPresent() ? mapper.toDTO(customerResult.get()) : null;		
	}	
	
	public CustomerDTO save(CustomerDTO customer) {
		
		// Validate customer
		if (!StringUtils.hasText(customer.getName())) {
			throw new IllegalArgumentException("Invalid customer name");
		}
		
		CustomerEntity entity = mapper.toEntity(customer);
		
		return mapper.toDTO(repository.save(entity));
	}
	
	public CustomerDTO update(CustomerDTO customer) {
		
		// Validate customer
		if (!StringUtils.hasText(customer.getName())) {
			throw new IllegalArgumentException("Invalid customer name");
		}
		
		// Validate if there's another customer with same name
		Optional<CustomerEntity> customerResult = repository.findByName(customer.getName());
		CustomerEntity registeredCustomer = customerResult.isPresent() ? customerResult.get() : null;
		
		if (registeredCustomer != null && !registeredCustomer.getId().equals(customer.getId())) {
			throw new IllegalStateException("Customer name already registered with other id");
		}
		
		CustomerEntity entity = mapper.toEntity(customer);
		
		return mapper.toDTO(repository.save(entity));
	}
	
	public void delete(UUID id) {
		Optional<CustomerEntity> customerResult = repository.findById(id);
		CustomerEntity customer = customerResult.isPresent() ? customerResult.get() : null;
		
		if (customer == null) {
			throw new IllegalStateException("There's no customer registered with this id");
		}
		
		repository.delete(customer);
	}

}
