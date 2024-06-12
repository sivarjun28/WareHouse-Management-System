package com.jsp.warehouse_management_system.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.jsp.warehouse_management_system.entity.Admin;
import com.jsp.warehouse_management_system.entity.WareHouse;
import com.jsp.warehouse_management_system.enums.AdminType;
import com.jsp.warehouse_management_system.security.UserDetailImpl;


public interface AdminRepository extends JpaRepository<Admin,Integer >{

	boolean existsByAdminType(AdminType adminType);

	
Optional<Admin>	findByEmail(String username);

List<Admin> findAllByAdminType(AdminType adminType);


}
