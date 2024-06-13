package com.jsp.warehouse_management_system.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class WareHouse {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int wareHouseId;
	private String name;
	private double totalCapacity;
	

	@OneToOne
	private Admin admin;
	
	
	@OneToMany(mappedBy = "wareHouse")
	private List<Storage> storages;

	

}
