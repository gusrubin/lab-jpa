package com.gusrubin.lab.jpa.repository.simpleentity.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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
public class CustomerEntity {
	
	@Id
    @GenericGenerator(name = "UUIDStringGenerator", strategy = "com.gusrubin.lab.jpa.config.UUIDStringGenerator")
    @GeneratedValue(generator = "UUIDStringGenerator")
    @Column(name = "ID")
	private UUID id;
	
	private String name;

}
