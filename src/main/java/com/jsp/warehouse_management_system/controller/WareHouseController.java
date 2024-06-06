package com.jsp.warehouse_management_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.warehouse_management_system.requestdto.WareHouseRequest;
import com.jsp.warehouse_management_system.responsedto.WareHouseResponse;
import com.jsp.warehouse_management_system.service.WareHouseService;
import com.jsp.warehouse_management_system.util.ResponseStructure;


@Controller
@RestController 
@RequestMapping("/api/v2")
public class WareHouseController {

	@Autowired
	private WareHouseService wareHouseservice;

	@PostMapping("/warehouse")
	public String createWareHouse(@RequestBody WareHouseRequest warehouseRequest){

		return "WareHouse Created";   


	}
}

