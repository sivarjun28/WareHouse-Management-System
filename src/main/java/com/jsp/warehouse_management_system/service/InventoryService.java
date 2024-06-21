package com.jsp.warehouse_management_system.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.jsp.warehouse_management_system.requestdto.InventoryRequest;
import com.jsp.warehouse_management_system.responsedto.BatchResponse;
import com.jsp.warehouse_management_system.responsedto.InventoryResponse;
import com.jsp.warehouse_management_system.util.ResponseStructure;

public interface InventoryService {

	ResponseEntity<ResponseStructure<InventoryResponse>> createInventory(int storageId,int clientId,int quantity, InventoryRequest inventoryRequest);

	ResponseEntity<ResponseStructure<InventoryResponse>> findInventory(int productId);

	ResponseEntity<ResponseStructure<List<InventoryResponse>>> findAllInventories();

	ResponseEntity<ResponseStructure<InventoryResponse>> updateInventory(int productId,
			 InventoryRequest inventoryRequest);
	
	
	 ResponseEntity<ResponseStructure<List<BatchResponse>>> updateQuantity(int storageId, int productId, int quantity);
	
}
