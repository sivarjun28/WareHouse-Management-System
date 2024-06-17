package com.jsp.warehouse_management_system.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.warehouse_management_system.entity.StorageType;
import com.jsp.warehouse_management_system.entity.WareHouse;

public interface StorageTypeRepository extends JpaRepository<StorageType, Integer>{

	

}
