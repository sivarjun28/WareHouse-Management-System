package com.jsp.warehouse_management_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.jsp.warehouse_management_system.entity.Admin;
import com.jsp.warehouse_management_system.enums.AdminType;
import com.jsp.warehouse_management_system.requestdto.AdminRequest;
import com.jsp.warehouse_management_system.responsedto.AdminResponse;

import com.jsp.warehouse_management_system.util.ResponseStructure;

public interface AdminService {

	
	

	public ResponseEntity<ResponseStructure<AdminResponse>> createSuperAdmin(AdminRequest adminRequest) ;
	public ResponseEntity<ResponseStructure<AdminResponse>> createAdmin(int wareHouseId,AdminRequest adminRequest ) ;
	
	

}
