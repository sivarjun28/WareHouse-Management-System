package com.jsp.warehouse_management_system.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.warehouse_management_system.entity.Storage;

public interface StorageRepository extends JpaRepository<Storage, Integer>{

	
//	Optional<Storage> findFirstByCapacityInWeightAndLengthInMetersAndBreadthInMetersAndHeightInMeters
//	(double capacityInWeight, double lengthInMeters , double breadthInMeters , double heightInMeters);
//	
//	
//	List<Storage> findAllTypesByCapacityInWeightAndLengthInMetersAndBreadthInMetersAndHeightInMeters
//	(double capacityInWeight, double lengthInMeters , double breadthInMeters , double heightInMeters);
	
}
