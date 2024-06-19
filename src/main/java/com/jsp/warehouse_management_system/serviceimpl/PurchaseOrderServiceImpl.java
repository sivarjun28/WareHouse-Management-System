package com.jsp.warehouse_management_system.serviceimpl;

import org.springframework.http.ResponseEntity;

import com.jsp.warehouse_management_system.requestdto.PurchaseOrderRequest;
import com.jsp.warehouse_management_system.responsedto.PurchaseOrderResponse;
import com.jsp.warehouse_management_system.service.PurchaseOrderService;
import com.jsp.warehouse_management_system.util.ResponseStructure;

public class PurchaseOrderServiceImpl implements PurchaseOrderService{

	@Override
	public ResponseEntity<ResponseStructure<PurchaseOrderResponse>> createPurchaseOrder(
			PurchaseOrderRequest purchaseRequest) {
		
		return null;
	}

}
