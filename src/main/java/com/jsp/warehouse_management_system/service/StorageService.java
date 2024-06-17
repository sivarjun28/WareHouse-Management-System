package com.jsp.warehouse_management_system.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.jsp.warehouse_management_system.requestdto.StorageRequest;
import com.jsp.warehouse_management_system.responsedto.AllStorageTypesResponse;
import com.jsp.warehouse_management_system.responsedto.StorageResponse;
import com.jsp.warehouse_management_system.util.ResponseStructure;
import com.jsp.warehouse_management_system.util.SimpleStructure;

public interface StorageService {

	
	public ResponseEntity<ResponseStructure<StorageResponse>> updateStorage(int storageId,
			StorageRequest storageRequest);



	public ResponseEntity<SimpleStructure<String>> createStorage(StorageRequest storageRequest, int wareHouseId,
			int noOfStorageUnits, int storageTypeId);

}
