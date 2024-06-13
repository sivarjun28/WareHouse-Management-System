package com.jsp.warehouse_management_system.mapper;

import org.hibernate.annotations.Comment;
import org.springframework.stereotype.Component;

import com.jsp.warehouse_management_system.entity.Admin;
import com.jsp.warehouse_management_system.entity.WareHouse;
import com.jsp.warehouse_management_system.requestdto.AdminRequest;
import com.jsp.warehouse_management_system.requestdto.WareHouseRequest;
import com.jsp.warehouse_management_system.responsedto.AdminResponse;
import com.jsp.warehouse_management_system.responsedto.WareHouseResponse;

@Component
public class WareHouseMapper {

	
	public WareHouse mapToWareHouse(WareHouseRequest wareHouseRequest, WareHouse wareHouse) {
		wareHouse.setName(wareHouseRequest.getName());
		
		
		return wareHouse;
		
		
	}
	
	public WareHouseResponse mapToWareHouseResponse(WareHouse wareHouse) {
		return WareHouseResponse.builder()
				.wareHouseId(wareHouse.getWareHouseId())
				.name(wareHouse.getName())
				.totalCapacityInKg(0)
				.build();
				
				
		
		
}
}
