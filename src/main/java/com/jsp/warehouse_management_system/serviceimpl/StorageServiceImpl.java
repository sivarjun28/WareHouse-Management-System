package com.jsp.warehouse_management_system.serviceimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.warehouse_management_system.entity.Storage;
import com.jsp.warehouse_management_system.entity.StorageType;
import com.jsp.warehouse_management_system.entity.WareHouse;
import com.jsp.warehouse_management_system.exception.StorageNotFoundByIdException;
import com.jsp.warehouse_management_system.exception.StorageTypeNotFoundByIdException;
import com.jsp.warehouse_management_system.exception.WareHouseNotFoundByIdException;
import com.jsp.warehouse_management_system.mapper.AdminMapper;
import com.jsp.warehouse_management_system.mapper.StorageMapper;
import com.jsp.warehouse_management_system.repository.StorageRepository;
import com.jsp.warehouse_management_system.repository.StorageTypeRepository;
import com.jsp.warehouse_management_system.repository.WareHouseRespository;
import com.jsp.warehouse_management_system.requestdto.StorageRequest;
import com.jsp.warehouse_management_system.requestdto.StorageTypeRequest;
import com.jsp.warehouse_management_system.responsedto.AllStorageTypesResponse;
import com.jsp.warehouse_management_system.responsedto.StorageResponse;
import com.jsp.warehouse_management_system.responsedto.WareHouseResponse;
import com.jsp.warehouse_management_system.service.StorageService;
import com.jsp.warehouse_management_system.util.ResponseStructure;
import com.jsp.warehouse_management_system.util.SimpleStructure;

@Service
public class StorageServiceImpl implements StorageService{


	@Autowired
	private StorageRepository storageRepository;

	@Autowired
	private WareHouseRespository wareHouseRespository;

	@Autowired
	private StorageMapper storageMapper;
	
	@Autowired
	private StorageTypeRepository storageTypeRepository;
	

	@Override
	public ResponseEntity<SimpleStructure<String>> createStorage(StorageRequest storageRequest,
			int wareHouseId, int noOfStorageUnits, int storageTypeId) {

		WareHouse wareHouse =  wareHouseRespository.findById(wareHouseId).orElseThrow(()-> 
		new WareHouseNotFoundByIdException("No wareHouse Found by the given Id"));

		StorageType storageType = storageTypeRepository.findById(storageTypeId).orElseThrow(() ->
		new StorageTypeNotFoundByIdException("No storage Type found"));
		
		List<Storage> storages = new ArrayList<Storage>();

		int count = 0;

		while(noOfStorageUnits > 0) {

			Storage storage  = storageMapper.mapToStorage(storageRequest, new Storage());
			
			storage.setWareHouse(wareHouse);
			storage.setStorageType(storageType);
			
			storage.setMaxAdditionalWeight(storageType.getCapacityInWeight());
			storageType.setUnitsAvailable(storageType.getUnitsAvailable() + noOfStorageUnits);
			storages.add(storage);
			count++;
			noOfStorageUnits --;
		}

		storageRepository.saveAll(storages);
		wareHouseRespository.save(wareHouse);
		storageTypeRepository.save(storageType);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(new SimpleStructure<String>()
				.setStatus(HttpStatus.CREATED.value())
				.setMesssage(""+count + " Storages created"));

	}

	@Override
	public ResponseEntity<ResponseStructure<StorageResponse>> updateStorage(int storageId,
			StorageRequest storageRequest) {
		
		
		return storageRepository.findById(storageId).map(existingStorage -> {
			
			existingStorage = storageMapper.mapToStorage(storageRequest, existingStorage);
			storageRepository.save(existingStorage);
			
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseStructure<StorageResponse>()
					.setStatus(HttpStatus.OK.value())
					.setMessage("Storage updsates")
					.setData(storageMapper.mapToStorageResponse(existingStorage)));
					
			
		}).orElseThrow(()-> new StorageNotFoundByIdException("storage Not Found"));
	}


					
			
			
} 


