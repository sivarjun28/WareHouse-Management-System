package com.jsp.warehouse_management_system.responsedto;

import java.time.LocalDate;
import java.util.List;

import com.jsp.warehouse_management_system.enums.MaterialType;

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
public class InventoryResponse {
	
	private int productId;
	private String productTitle;
	List<MaterialType> materialTypes;
	private LocalDate restockedAt;
	private int sellerId;
	
	private BatchResponse batch;
}
