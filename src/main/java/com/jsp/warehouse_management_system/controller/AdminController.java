 package com.jsp.warehouse_management_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.jsp.warehouse_management_system.requestdto.AdminRequest;
import com.jsp.warehouse_management_system.responsedto.AdminResponse;
import com.jsp.warehouse_management_system.service.AdminService;

import com.jsp.warehouse_management_system.util.ResponseStructure;


import jakarta.validation.Valid;



@RestController
@Controller
@RequestMapping("/api/v2")
public class AdminController {
	
@Autowired
private AdminService userService;
@PostMapping("/superadmin")
public ResponseEntity<ResponseStructure<AdminResponse>> createSuperAdmin(@RequestBody @Valid AdminRequest adminRequest){
	return userService.createSuperAdmin(adminRequest);	
	}
 
	
}