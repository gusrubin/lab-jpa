package com.gusrubin.lab.jpa.repository.simpleentity;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gusrubin.lab.jpa.repository.simpleentity.entity.CustomerEntity;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, UUID> {
	
	Optional<CustomerEntity> findByName(String name);
}
