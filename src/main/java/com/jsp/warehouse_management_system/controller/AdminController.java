 package com.jsp.warehouse_management_system.controller;

import java.util.List;

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

import com.jsp.warehouse_management_system.entity.Admin;
import com.jsp.warehouse_management_system.requestdto.AdminRequest;
import com.jsp.warehouse_management_system.responsedto.AdminResponse;
import com.jsp.warehouse_management_system.service.AdminService;

import com.jsp.warehouse_management_system.util.ResponseStructure;


import jakarta.validation.Valid;



@RestController
@RequestMapping("/api/v1")
public class AdminController {
	
@Autowired
private AdminService adminService;
@PostMapping("/register")
public ResponseEntity<ResponseStructure<AdminResponse>> createSuperAdmin(@RequestBody @Valid AdminRequest adminRequest){
	return adminService.createSuperAdmin(adminRequest);	
	}
 

@PreAuthorize("hasAuthority('CREATE_WAREHOUSE')")
@PostMapping("/{wareHouseId}/admins")
public ResponseEntity<ResponseStructure<AdminResponse>> createAdmin(@PathVariable int  wareHouseId, @RequestBody AdminRequest adminRequest){
	return adminService.createAdmin(wareHouseId,adminRequest);
	  
}

@PreAuthorize("hasAuthority('UPDATE_ADMIN')")
@PutMapping("/admins")
public ResponseEntity<ResponseStructure<AdminResponse>> updateAdmin(@RequestBody AdminRequest adminRequest){
	return adminService.updateAdmin(adminRequest);
}

@PreAuthorize("hasAuthority('UPDATE_ADMIN')")
@PutMapping("/admins/{adminId}")
public ResponseEntity<ResponseStructure<AdminResponse>> updateAdminBySuperAdmin(@PathVariable int adminId, @RequestBody AdminRequest adminRequest){
	
	return adminService.updateAdminBySuperAdmin(adminId, adminRequest);
	
}

@PreAuthorize("hasAuthority('READ')")
@GetMapping("/admins/{adminId}")
public ResponseEntity<ResponseStructure<AdminResponse>> findAdmin(@PathVariable int adminId){
	return adminService.findAdmin(adminId);
	
}

@PreAuthorize("hasAuthority('READ')")
@GetMapping("/admins")
public ResponseEntity<ResponseStructure<List<AdminResponse>>> findAdmins(){
	return adminService.findAdmins();
	
}


}