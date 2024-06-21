package com.jsp.warehouse_management_system.requestdto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseOrderRequest {

	
	private int orderQuantity;
	private String invoiceLink;
	private int customerId;
}
