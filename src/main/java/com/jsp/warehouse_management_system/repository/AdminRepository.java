package com.jsp.warehouse_management_system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.warehouse_management_system.entity.Admin;
import com.jsp.warehouse_management_system.enums.AdminType;


public interface AdminRepository extends JpaRepository<Admin,Integer >{

	boolean existsByAdminType(AdminType adminType);

}
