package com.jsp.warehouse_management_system.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.warehouse_management_system.entity.Client;

public interface ClientRepository extends JpaRepository<Client, Integer>{

	Optional<Client> findByEmail(String email);

}
