 package com.jsp.warehouse_management_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.warehouse_management_system.entity.WareHouse;
import com.jsp.warehouse_management_system.requestdto.AdminRequest;
import com.jsp.warehouse_management_system.requestdto.WareHouseRequest;
import com.jsp.warehouse_management_system.responsedto.AdminResponse;
import com.jsp.warehouse_management_system.responsedto.WareHouseResponse;
import com.jsp.warehouse_management_system.service.WareHouseService;
import com.jsp.warehouse_management_system.util.ResponseStructure;

import jakarta.validation.Valid;



@RestController 
@RequestMapping("/api/v1")
public class WareHouseController {
@Autowired
private WareHouseService wareHouseService;

		
	@PreAuthorize("hasAuthority('CREATE_WAREHOUSE')")
	@PostMapping("/warehouses")
	public ResponseEntity<ResponseStructure<WareHouseResponse>> createWareHouse(@RequestBody @Valid WareHouseRequest wareHouseRequest){
		return wareHouseService.createWareHouse(wareHouseRequest);
	}
	

@PreAuthorize("hasAuthority('UPDATE_WAREHOUSE')")
@PutMapping("/warehouses/{wareHouseId}")
public ResponseEntity<ResponseStructure<WareHouseResponse>> updateWareHouse(@PathVariable int wareHouseId,@RequestBody @Valid WareHouseRequest wareHouseRequest){
	return wareHouseService.updateWareHouse(wareHouseId,wareHouseRequest);
}


@PreAuthorize("hasAuthority('READ')")
@GetMapping("/warehouses/{wareHouseId}")
public ResponseEntity<ResponseStructure<WareHouseResponse>> findWareHouse(@PathVariable int wareHouseId){
	return wareHouseService.findWareHouse(wareHouseId);
	
}

}

