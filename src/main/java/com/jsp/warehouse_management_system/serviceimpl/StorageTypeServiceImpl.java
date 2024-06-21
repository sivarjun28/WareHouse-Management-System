package com.jsp.warehouse_management_system.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import com.jsp.warehouse_management_system.entity.StorageType;
import com.jsp.warehouse_management_system.exception.StorageTypeNotFoundByIdException;
import com.jsp.warehouse_management_system.mapper.StorageTypeMapper;
import com.jsp.warehouse_management_system.repository.StorageTypeRepository;
import com.jsp.warehouse_management_system.requestdto.StorageTypeRequest;
import com.jsp.warehouse_management_system.responsedto.StorageTypeResponse;
import com.jsp.warehouse_management_system.service.StorageTypeService;
import com.jsp.warehouse_management_system.util.ResponseStructure;


@Service
public class StorageTypeServiceImpl implements StorageTypeService{
	
@Autowired
private StorageTypeMapper storageTypeMapper;
@Autowired
private StorageTypeRepository storageTypeRepository;
	@Override
	public ResponseEntity<ResponseStructure<StorageTypeResponse>> createStorageType(
			StorageTypeRequest storageTypeRequest) {
		
		
		StorageType storageType =  storageTypeRepository.save(storageTypeMapper.mapToStorageType(storageTypeRequest,
				new StorageType()));
		
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseStructure<StorageTypeResponse>()
						.setStatus(HttpStatus.CREATED.value())
						.setMessage("Storage Type Created")
						.setData(storageTypeMapper.mapToStorageTypeResponse(storageType)));
		
	}
	@Override
	public ResponseEntity<ResponseStructure<StorageTypeResponse>> updateStorageType(int storageTypeId,
			StorageTypeRequest storageTypeRequest) {
		
		return storageTypeRepository.findById(storageTypeId).map(existingStorageType -> {
			
			existingStorageType = storageTypeMapper.mapToStorageType(storageTypeRequest, existingStorageType);
			 
			existingStorageType = storageTypeRepository.save(existingStorageType);
			
			
					return ResponseEntity.status(HttpStatus.CREATED)
							.body(new ResponseStructure<StorageTypeResponse>()
									.setStatus(HttpStatus.CREATED.value())
									.setMessage("Storage Type Created")
									.setData(storageTypeMapper.mapToStorageTypeResponse(existingStorageType)));	
					
		}).orElseThrow(()-> new StorageTypeNotFoundByIdException("storage not found"));
	}
	@Override
	public ResponseEntity<ResponseStructure<List<StorageTypeResponse>>> findStorageTypes() {
		
		
		List<StorageTypeResponse>  storageTypeResponses = storageTypeRepository.findAll()
				.stream().map(storageTypes -> storageTypeMapper.mapToStorageTypeResponse(storageTypes))
				.toList();
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseStructure<List<StorageTypeResponse>>()
						.setStatus(HttpStatus.CREATED.value())
						.setMessage("Storage Type Created")
						.setData(storageTypeResponses));	
	}

	



}
