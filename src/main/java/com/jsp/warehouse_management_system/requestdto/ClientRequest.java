package com.jsp.warehouse_management_system.requestdto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientRequest {

	private String businessName;
	private String email;
	private long contactNumber;
	
}
