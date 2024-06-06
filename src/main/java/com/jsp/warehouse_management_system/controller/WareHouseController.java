package com.jsp.warehouse_management_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.warehouse_management_system.requestdto.WareHouseRequest;
import com.jsp.warehouse_management_system.responsedto.WareHouseResponse;

import com.jsp.warehouse_management_system.util.ResponseStructure;



@RestController 
@RequestMapping("/api/v2")
public class WareHouseController {

	

	@GetMapping("/warehouses")
	public String createWareHouse(){

		return "WareHouse found";   


	}
}

