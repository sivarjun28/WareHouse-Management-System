package com.jsp.warehouse_management_system.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.jsp.warehouse_management_system.requestdto.StorageTypeRequest;
import com.jsp.warehouse_management_system.responsedto.StorageTypeResponse;
import com.jsp.warehouse_management_system.util.ResponseStructure;

public interface StorageTypeService {

	ResponseEntity<ResponseStructure<StorageTypeResponse>> createStorageType(StorageTypeRequest storageTypeRequest);

	ResponseEntity<ResponseStructure<StorageTypeResponse>> updateStorageType(int storageTypeId,
			StorageTypeRequest storageTypeRequest);

	ResponseEntity<ResponseStructure<List<StorageTypeResponse>>> findStorageTypes();

}
