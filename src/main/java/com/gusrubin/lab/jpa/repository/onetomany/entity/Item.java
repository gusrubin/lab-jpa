package com.gusrubin.lab.jpa.repository.onetomany.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "ITEM")
public class Item {
	
	@Id
    @GenericGenerator(name = "UUIDStringGenerator", strategy = "com.gusrubin.lab.jpa.config.UUIDStringGenerator")
    @GeneratedValue(generator = "UUIDStringGenerator")
    @Column(name = "ID")
	private UUID id;
	
	@ManyToOne
	@JoinColumn(name="CART_ID", nullable=false)
	private Cart cart;

}
