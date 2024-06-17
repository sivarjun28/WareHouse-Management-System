package com.jsp.warehouse_management_system.mapper;

import org.springframework.stereotype.Component;

import com.jsp.warehouse_management_system.entity.Storage;
import com.jsp.warehouse_management_system.entity.StorageType;

import com.jsp.warehouse_management_system.requestdto.StorageTypeRequest;
import com.jsp.warehouse_management_system.responsedto.AllStorageTypesResponse;
import com.jsp.warehouse_management_system.responsedto.StorageTypeResponse;




@Component
public class StorageTypeMapper {


	public StorageType mapToStorageType(StorageTypeRequest storageTypeRequest , StorageType storageType) {

		storageType.setLengthInMeters(storageTypeRequest.getLengthInMeters());
		storageType.setBreadthInMeters(storageTypeRequest.getBreadthInMeters());
		storageType.setHeightInMeters(storageTypeRequest.getHeightInMeters());
		storageType.setCapacityInWeight(storageTypeRequest.getCapacityInWeight());

		return storageType;

	}

	public StorageTypeResponse mapToStorageTypeResponse(StorageType storageType) {
		
		return StorageTypeResponse.builder()
				.storageTypeId(storageType.getStorageTypeId())
				.lengthInMeters(storageType.getLengthInMeters())
				.breadthInMeters(storageType.getBreadthInMeters())
				.heightInMeters(storageType.getHeightInMeters())
				.capacityInWeight(storageType.getCapacityInWeight())
				.build();

	}
	public StorageTypeResponse mapToAllStorageResponse(StorageType storageType) {
		return StorageTypeResponse.builder()
				.capacityInWeight(storageType.getCapacityInWeight())
				.lengthInMeters(storageType.getLengthInMeters())
				.breadthInMeters(storageType.getBreadthInMeters())
				.heightInMeters(storageType.getHeightInMeters())
				.unitsAvailable(storageType.getUnitsAvailable())
				
				.build();
	}

}
