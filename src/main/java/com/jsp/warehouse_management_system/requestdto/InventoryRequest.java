package com.jsp.warehouse_management_system.requestdto;

import java.time.LocalDate;
import java.util.List;

import com.jsp.warehouse_management_system.enums.MaterialType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class InventoryRequest {

	private double lengthInMeters;
private String productTitle;
	private double breadthInMeters;
	private double heightInMeters;
	private double weightInKg;
	private int quantity;
	List<MaterialType> materialTypes;
	
	private int sellerId;
	
}
