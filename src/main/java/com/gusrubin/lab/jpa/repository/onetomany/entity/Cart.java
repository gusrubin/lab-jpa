package com.gusrubin.lab.jpa.repository.onetomany.entity;

import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "CART")
public class Cart {
	
	@Id
    @GenericGenerator(name = "UUIDStringGenerator", strategy = "com.gusrubin.lab.jpa.config.UUIDStringGenerator")
    @GeneratedValue(generator = "UUIDStringGenerator")
    @Column(name = "ID")
	private UUID id;
	
	@OneToMany(mappedBy="cart")
    private List<Item> items;

}
