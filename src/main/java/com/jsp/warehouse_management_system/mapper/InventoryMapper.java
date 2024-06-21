package com.jsp.warehouse_management_system.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jsp.warehouse_management_system.entity.Batch;
import com.jsp.warehouse_management_system.entity.Inventory;
import com.jsp.warehouse_management_system.requestdto.InventoryRequest;
import com.jsp.warehouse_management_system.responsedto.InventoryResponse;

@Component
public class InventoryMapper {
	
	@Autowired
	private BatchMapper batchMapper;

	public Inventory mapToInventory(InventoryRequest inventoryRequest, Inventory inventory) {
		
		inventory.setLengthInMeters(inventoryRequest.getLengthInMeters());
		inventory.setBreadthInMeters(inventoryRequest.getBreadthInMeters());
		inventory.setHeightInMeters(inventoryRequest.getHeightInMeters());
		inventory.setWeightInKg(inventoryRequest.getWeightInKg());
		inventory.setProductTitle(inventory.getProductTitle());
		inventory.setMaterialTypes(inventoryRequest.getMaterialTypes());
		inventory.setSellerId(inventoryRequest.getSellerId());
		return inventory;
		
	}
	
	public InventoryResponse mapToInventoryResponse(Inventory inventory) {
		return InventoryResponse.builder()
				.productId(inventory.getProductId())
				.productTitle(inventory.getProductTitle())
				.restockedAt(inventory.getRestockedAt())
				.materialTypes(inventory.getMaterialTypes())
				.sellerId(inventory.getSellerId())
				.build();
				

	}
	
	public InventoryResponse mapToInventoryResponse(Inventory inventory, Batch batch) {
		return InventoryResponse.builder()
				.productId(inventory.getProductId())
				.restockedAt(inventory.getRestockedAt())
				.productTitle(inventory.getProductTitle())
				.materialTypes(inventory.getMaterialTypes())
				.sellerId(inventory.getSellerId())
				.batch(batchMapper.mapToBatchResponse(batch))
				.build();
				

	}
}
