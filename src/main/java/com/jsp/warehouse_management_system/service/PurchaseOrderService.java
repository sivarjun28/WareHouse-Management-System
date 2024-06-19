package com.jsp.warehouse_management_system.service;

import org.springframework.http.ResponseEntity;

import com.jsp.warehouse_management_system.requestdto.PurchaseOrderRequest;
import com.jsp.warehouse_management_system.responsedto.PurchaseOrderResponse;
import com.jsp.warehouse_management_system.util.ResponseStructure;

public interface PurchaseOrderService {

	ResponseEntity<ResponseStructure<PurchaseOrderResponse>> createPurchaseOrder(PurchaseOrderRequest purchaseRequest);

	
}
