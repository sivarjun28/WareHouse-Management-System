package com.jsp.warehouse_management_system.requestdto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StorageTypeRequest {
	private double lengthInMeters;
	private double breadthInMeters;
	private double heightInMeters;
	private double capacityInWeight;
	private int unitsAvailable;
}
