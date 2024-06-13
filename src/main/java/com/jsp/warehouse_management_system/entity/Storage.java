package com.jsp.warehouse_management_system.entity;

import java.util.List;

import com.jsp.warehouse_management_system.enums.MaterialType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Storage {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int storageId;
	private String blockName;
	private String section;
	private double lengthInMeters;
	private double breadthInMeters;
	private double heightInMeters;
	private double capacityInWeight;
	@Enumerated(EnumType.STRING)
	List<MaterialType> materialTypes;
	private double maxAdditionalWeight;
	private double availableArea;

	@ManyToOne
	private WareHouse wareHouse;
}
