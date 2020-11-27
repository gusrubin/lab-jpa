package com.gusrubin.lab.jpa.repository.onetomany.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	
	private String name;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Cart cart;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Item))
			return false;
		return id != null && id.equals(((Item) obj).getId());
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}

	@JsonIgnore
	public Cart getCart() {
		return cart;
	}

}
