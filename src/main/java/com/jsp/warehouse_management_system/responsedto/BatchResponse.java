package com.jsp.warehouse_management_system.responsedto;

import com.jsp.warehouse_management_system.entity.Storage;

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
public class BatchResponse {

	private int batchId;
	private int quantity;
	
	private StorageResponse storage;
	
}
