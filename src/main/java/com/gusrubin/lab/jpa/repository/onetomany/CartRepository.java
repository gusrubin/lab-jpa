package com.gusrubin.lab.jpa.repository.onetomany;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gusrubin.lab.jpa.repository.onetomany.entity.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, UUID>{

}
