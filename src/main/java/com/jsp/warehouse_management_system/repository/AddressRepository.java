package com.jsp.warehouse_management_system.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.warehouse_management_system.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Integer>{

	List<Address> findAllByCity(String city);

}
