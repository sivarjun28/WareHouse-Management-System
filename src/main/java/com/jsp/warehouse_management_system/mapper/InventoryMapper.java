package com.jsp.warehouse_management_system.mapper;

import org.springframework.stereotype.Component;

import com.jsp.warehouse_management_system.entity.Inventory;
import com.jsp.warehouse_management_system.requestdto.InventoryRequest;
import com.jsp.warehouse_management_system.responsedto.InventoryResponse;

@Component
public class InventoryMapper {

	public Inventory mapToInventory(InventoryRequest inventoryRequest, Inventory inventory) {
		
		inventory.setLengthInMeters(inventoryRequest.getLengthInMeters());
		inventory.setBreadthInMeters(inventoryRequest.getBreadthInMeters());
		inventory.setHeightInMeters(inventoryRequest.getHeightInMeters());
		inventory.setWeightInKg(inventoryRequest.getWeightInKg());
		inventory.setQuantity(inventoryRequest.getQuantity());
		
		inventory.setMaterialTypes(inventoryRequest.getMaterialTypes());
		inventory.setSellerId(inventoryRequest.getSellerId());
		return inventory;
		
	}
	
	public InventoryResponse mapToInventoryResponse(Inventory inventory) {
		return InventoryResponse.builder()
				.productId(inventory.getProductId())
				.quantity(inventory.getQuantity())
				.restockedAt(inventory.getRestockedAt())
				.materialTypes(inventory.getMaterialTypes())
				.sellerId(inventory.getSellerId())
				.build();
				

	}
}
