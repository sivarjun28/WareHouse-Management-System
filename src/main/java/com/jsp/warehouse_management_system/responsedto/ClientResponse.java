package com.jsp.warehouse_management_system.responsedto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientResponse {
	private int clientId;
	private String businessName;
	private String email;
	private long contactNumber;
}
