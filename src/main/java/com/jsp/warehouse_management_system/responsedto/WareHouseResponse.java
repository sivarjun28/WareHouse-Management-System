package com.jsp.warehouse_management_system.responsedto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WareHouseResponse {

	private int wareHouseId;
	private String name;
	private double totalCapacityInKg;
	
	private AddressResponse addressResponse;
	
}
