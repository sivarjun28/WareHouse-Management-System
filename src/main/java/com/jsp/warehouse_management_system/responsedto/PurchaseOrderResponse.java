package com.jsp.warehouse_management_system.responsedto;

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
public class PurchaseOrderResponse {
	
	private int orderId;
	private int orderQuantity;
	private String invoiceLink;
	private int customerId;
}
