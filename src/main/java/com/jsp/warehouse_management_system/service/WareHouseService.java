package com.jsp.warehouse_management_system.service;

import org.springframework.http.ResponseEntity;


import com.jsp.warehouse_management_system.requestdto.WareHouseRequest;
import com.jsp.warehouse_management_system.responsedto.WareHouseResponse;
import com.jsp.warehouse_management_system.util.ResponseStructure;

public interface WareHouseService {

	

	public ResponseEntity<ResponseStructure<WareHouseResponse>> createWareHouse(WareHouseRequest warehouseRequest);
	

	
	
}
