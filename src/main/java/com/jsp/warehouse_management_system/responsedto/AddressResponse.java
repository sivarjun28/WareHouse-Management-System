package com.jsp.warehouse_management_system.responsedto;

import com.jsp.warehouse_management_system.entity.WareHouse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressResponse {
	
	
	private int addressId;
	private String addressLine;
	private String city;
	private String state;
	private String country;
	private int pincode;
	private String latitude;
	private String longitude;
}
