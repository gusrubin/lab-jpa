package com.gusrubin.lab.jpa.repository.onetomany;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gusrubin.lab.jpa.repository.onetomany.entity.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, UUID>{
	
	List<Item> findByCartId(UUID cartId);

}
