package com.jsp.warehouse_management_system.serviceimpl;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.warehouse_management_system.requestdto.WareHouseRequest;
import com.jsp.warehouse_management_system.responsedto.WareHouseResponse;
import com.jsp.warehouse_management_system.service.WareHouseService;
import com.jsp.warehouse_management_system.util.ResponseStructure;

@Service
public class WareHouseSeviceImpl implements WareHouseService{

	@Override
	public ResponseEntity<ResponseStructure<WareHouseResponse>> createWareHouse(WareHouseRequest warehouseRequest) {
		// TODO Auto-generated method stub
		return null;
	}

}
