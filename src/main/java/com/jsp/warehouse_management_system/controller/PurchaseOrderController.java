package com.jsp.warehouse_management_system.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.warehouse_management_system.requestdto.PurchaseOrderRequest;
import com.jsp.warehouse_management_system.responsedto.PurchaseOrderResponse;
import com.jsp.warehouse_management_system.service.PurchaseOrderService;
import com.jsp.warehouse_management_system.util.ResponseStructure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("api/v1")

public class PurchaseOrderController {
	
	@Autowired
	private PurchaseOrderService purchaseOrderService;

	@PostMapping("inventories/{inventoryId}/purchaseorders")
	public ResponseEntity<ResponseStructure<PurchaseOrderResponse>> createPurchaseOrder(@RequestBody PurchaseOrderRequest purchaseRequest) {
		
		return purchaseOrderService.createPurchaseOrder(purchaseRequest);
	}
	
}
