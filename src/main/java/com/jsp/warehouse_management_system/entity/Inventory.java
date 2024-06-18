package com.jsp.warehouse_management_system.entity;
import java.time.LocalDate;
import java.util.List;

import com.jsp.warehouse_management_system.enums.MaterialType;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Inventory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int productId;
	private double lengthInMeters;
	private double breadthInMeters;
	private double heightInMeters;
	private double weightInKg;
	private int quantity;
	List<MaterialType> materialTypes;
	private LocalDate restockedAt;
	private int sellerId;
	
	
	@ManyToMany(mappedBy = "inventories")
	private List<Storage> storages;
	
	@ManyToOne
	private Client client;
	
	
	
}
