package com.jsp.warehouse_management_system.serviceimpl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.jsp.warehouse_management_system.entity.Admin;
import com.jsp.warehouse_management_system.enums.AdminType;
import com.jsp.warehouse_management_system.enums.Privilage;
import com.jsp.warehouse_management_system.exception.IllegalOperationException;
import com.jsp.warehouse_management_system.mapper.AdminMapper;
import com.jsp.warehouse_management_system.repository.AdminRepository;

import com.jsp.warehouse_management_system.requestdto.AdminRequest;
import com.jsp.warehouse_management_system.responsedto.AdminResponse;
import com.jsp.warehouse_management_system.service.AdminService;
import com.jsp.warehouse_management_system.util.ResponseStructure;

@Service
public class AdminServiceImpl implements AdminService{

	@Autowired
	private AdminRepository adminRepository;

	@Autowired
	private AdminMapper adminMapper;

	@Override
	public ResponseEntity<ResponseStructure<AdminResponse>> createSuperAdmin(AdminRequest adminRequest) {
 
		if (adminRepository.existsByAdminType(AdminType.SUPER_ADMIN)){

			throw new IllegalOperationException("Super Admin already Exists");
		}
		Admin admin = adminMapper.mapToAdmin(adminRequest, new Admin());
		admin.setAdminType(AdminType.SUPER_ADMIN);
		admin = adminRepository.save(admin);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseStructure<AdminResponse>()
						.setStatus(HttpStatus.CREATED.value())
						.setMessage("Super admin Created")
						.setData(adminMapper.mapToAdminResponse(admin)));
	} 
}
