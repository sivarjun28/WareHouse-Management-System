package com.jsp.warehouse_management_system.mapper;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


import com.jsp.warehouse_management_system.entity.Admin;
import com.jsp.warehouse_management_system.enums.AdminType;
import com.jsp.warehouse_management_system.requestdto.AdminRequest;
import com.jsp.warehouse_management_system.responsedto.AdminResponse;

@Component
public class AdminMapper {
	
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
		public Admin mapToAdmin(AdminRequest adminRequest, Admin admin) {
			admin.setName(adminRequest.getName());
			admin.setEmail(adminRequest.getEmail());
			admin.setPassword(passwordEncoder.encode(adminRequest.getPassword()));
			
			return admin;
			
			
		}
		
		public AdminResponse mapToAdminResponse(Admin admin) {
			return AdminResponse.builder()
			.adminId(admin.getAdminId())
			.name(admin.getName())
			.email(admin.getEmail())
			.adminType(admin.getAdminType())
			.build();
		}
		
		
		
}
