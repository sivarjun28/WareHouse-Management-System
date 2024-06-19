package com.jsp.warehouse_management_system.serviceimpl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.warehouse_management_system.entity.Client;
import com.jsp.warehouse_management_system.entity.Inventory;
import com.jsp.warehouse_management_system.entity.Storage;
import com.jsp.warehouse_management_system.exception.ClientNotFoundByIdException;
import com.jsp.warehouse_management_system.exception.InventoryNotFoundByIdException;
import com.jsp.warehouse_management_system.exception.SpaceOrWeightNotAvailableException;
import com.jsp.warehouse_management_system.exception.StorageNotFoundByIdException;
import com.jsp.warehouse_management_system.mapper.InventoryMapper;
import com.jsp.warehouse_management_system.repository.ClientRepository;
import com.jsp.warehouse_management_system.repository.InventoryRepository;
import com.jsp.warehouse_management_system.repository.StorageRepository;
import com.jsp.warehouse_management_system.requestdto.InventoryRequest;
import com.jsp.warehouse_management_system.responsedto.InventoryResponse;
import com.jsp.warehouse_management_system.service.InventoryService;
import com.jsp.warehouse_management_system.util.ResponseStructure;



@Service
public class InventoryServiceImpl implements InventoryService{

	@Autowired
	private StorageRepository storageRepository;
	
	@Autowired
	private InventoryRepository inventoryRepository;
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private InventoryMapper inventoryMapper;

	@Override
	public ResponseEntity<ResponseStructure<InventoryResponse>> createInventory(int storageId, int clientId,
			InventoryRequest inventoryRequest) {
		
		Storage storage = storageRepository.findById(storageId).orElseThrow(()-> new StorageNotFoundByIdException("storage Not Found"));
		
		Inventory inventory = inventoryRepository.save(inventoryMapper.mapToInventory(inventoryRequest, new Inventory()));
		
		Client client = clientRepository.findById(clientId).orElseThrow(()-> new ClientNotFoundByIdException("Client Not Found"));
		
		storage.getInventories().add(inventory);
		inventory.setRestockedAt(LocalDate.now());
		
		
		storage.setMaxAdditionalWeight(storage.getMaxAdditionalWeight() * inventoryRequest.getQuantity() - inventoryRequest.getWeightInKg());
		storage.setAvailableArea(inventory.getLengthInMeters() * inventory.getBreadthInMeters() * inventory.getHeightInMeters());
		storage.setSelletId(inventoryRequest.getSellerId());
		
		inventory = inventoryRepository.save(inventory);
		storageRepository.save(storage);
		clientRepository.save(client);
		
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseStructure<InventoryResponse>()
						.setStatus(HttpStatus.CREATED.value())
						.setMessage("Inventory created")
						.setData(inventoryMapper.mapToInventoryResponse(inventory)));
						
	}

	@Override 
	public ResponseEntity<ResponseStructure<InventoryResponse>> findInventory(int productId) {
		
		return inventoryRepository.findById(productId).map(inventory ->{
			return ResponseEntity.status(HttpStatus.FOUND)
					.body(new ResponseStructure<InventoryResponse>()
							.setStatus(HttpStatus.FOUND.value())
							.setMessage("Inventory created")
							.setData(inventoryMapper.mapToInventoryResponse(inventory)));
		}).orElseThrow(()-> new InventoryNotFoundByIdException("Inventory not found "));
				
		}

	@Override
	public ResponseEntity<ResponseStructure<List<InventoryResponse>>> findAllInventories() {
		
	List<InventoryResponse> inventoryResponses	=inventoryRepository.findAll().stream()
					.map(inventories -> inventoryMapper.mapToInventoryResponse(inventories))
					.toList();
	return ResponseEntity.status(HttpStatus.FOUND)
			.body(new ResponseStructure<List<InventoryResponse>>()
					.setStatus(HttpStatus.FOUND.value())
					.setMessage("Inventories found")
					.setData(inventoryResponses));
	}

	@Override
	public ResponseEntity<ResponseStructure<InventoryResponse>> updateInventory(int productId, InventoryRequest inventoryRequest) {

	    return inventoryRepository.findById(productId)
	        .map(existingInventory -> {
	            
	            int oldQuantity = existingInventory.getQuantity();
	            double originalWeight = existingInventory.getWeightInKg() * existingInventory.getQuantity();
	            double originalArea = existingInventory.getLengthInMeters() *
	                                 existingInventory.getBreadthInMeters() * existingInventory.getHeightInMeters();
	            
	            existingInventory = inventoryMapper.mapToInventory(inventoryRequest, existingInventory);
	            
	            if (oldQuantity != existingInventory.getQuantity())
	                existingInventory.setRestockedAt(LocalDate.now());
	            
	            double newWeight = existingInventory.getWeightInKg() * existingInventory.getQuantity();
	            double newArea = existingInventory.getLengthInMeters() * existingInventory.getBreadthInMeters() 
	                            * existingInventory.getHeightInMeters();
	            
	            existingInventory.getStorages().forEach(storage -> {
	                if (storage.getAvailableArea() > 0 && storage.getMaxAdditionalWeight() > 0) {
	                    storage.setMaxAdditionalWeight(storage.getMaxAdditionalWeight() + originalWeight - newWeight);
	                    storage.setAvailableArea(storage.getAvailableArea() + originalArea - newArea);
	                } else {
	                    throw new SpaceOrWeightNotAvailableException("No available area or capacity of storage full");
	                }
	            });
	            
	            existingInventory = inventoryRepository.save(existingInventory);
	            existingInventory.getStorages().forEach(storageRepository::save);
	           
	            return ResponseEntity.status(HttpStatus.OK)
	                .body(new ResponseStructure<InventoryResponse>()
	                    .setStatus(HttpStatus.OK.value())
	                    .setMessage("Inventory updates")
	                    .setData(inventoryMapper.mapToInventoryResponse(existingInventory)));
	        }).orElseThrow(() -> new InventoryNotFoundByIdException("Product not found"));
	}



		
		
	
}


