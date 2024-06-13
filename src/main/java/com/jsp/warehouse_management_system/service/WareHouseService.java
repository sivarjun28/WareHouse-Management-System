package com.jsp.warehouse_management_system.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.jsp.warehouse_management_system.requestdto.WareHouseRequest;
import com.jsp.warehouse_management_system.responsedto.AdminResponse;
import com.jsp.warehouse_management_system.responsedto.WareHouseResponse;
import com.jsp.warehouse_management_system.util.ResponseStructure;

public interface WareHouseService {

	public ResponseEntity<ResponseStructure<WareHouseResponse>> createWareHouse(WareHouseRequest wareHouseRequest);

	public ResponseEntity<ResponseStructure<WareHouseResponse>> updateWareHouse(int wareHouseId,WareHouseRequest wareHouseRequest);

	public ResponseEntity<ResponseStructure<WareHouseResponse>> findWareHouse(int wareHouseId);

	public ResponseEntity<ResponseStructure<List<WareHouseResponse>>> findWareHouses();
	
	
		

}
