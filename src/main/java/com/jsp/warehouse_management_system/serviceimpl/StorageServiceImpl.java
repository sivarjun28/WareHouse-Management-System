package com.jsp.warehouse_management_system.serviceimpl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.warehouse_management_system.entity.Storage;
import com.jsp.warehouse_management_system.entity.WareHouse;
import com.jsp.warehouse_management_system.exception.StorageNotFoundByIdException;
import com.jsp.warehouse_management_system.exception.WareHouseNotFoundByIdException;
import com.jsp.warehouse_management_system.mapper.AdminMapper;
import com.jsp.warehouse_management_system.mapper.StorageMapper;
import com.jsp.warehouse_management_system.repository.StorageRepository;
import com.jsp.warehouse_management_system.repository.WareHouseRespository;
import com.jsp.warehouse_management_system.requestdto.StorageRequest;
import com.jsp.warehouse_management_system.responsedto.StorageResponse;
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

	@Override
	public ResponseEntity<SimpleStructure<String>> createStorage(StorageRequest storageRequest,
			int wareHouseId, int noOfStorageUnits) {

		WareHouse wareHouse =  wareHouseRespository.findById(wareHouseId).orElseThrow(()-> new WareHouseNotFoundByIdException(""));

			List<Storage> storages = new ArrayList<Storage>();
			
			int count = 0;

			while(noOfStorageUnits > 0) {
			
			Storage storage  = storageMapper.mapToStorage(storageRequest, new Storage());
			
			
			
			storage.setMaxAdditionalWeight(storageRequest.getCapacityInWeight());
			storage.setAvailableArea(storageRequest.getLengthInMeters() * storageRequest.getBreadthInMeters() * storageRequest.getHeightInMeters());
			storage.setWareHouse(wareHouse);
			
			storages.add(storage);
			count++;
			noOfStorageUnits --;
		}
		
		storageRepository.saveAll(storages);
//		wareHouseRespository.save(wareHouse);
		
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
