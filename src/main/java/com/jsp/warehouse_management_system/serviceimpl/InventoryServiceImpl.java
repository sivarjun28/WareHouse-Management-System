package com.jsp.warehouse_management_system.serviceimpl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.warehouse_management_system.entity.Batch;
import com.jsp.warehouse_management_system.entity.Client;
import com.jsp.warehouse_management_system.entity.Inventory;
import com.jsp.warehouse_management_system.entity.Storage;
import com.jsp.warehouse_management_system.exception.BatchNotFoundException;
import com.jsp.warehouse_management_system.exception.ClientNotFoundByIdException;
import com.jsp.warehouse_management_system.exception.InventoryNotFoundByIdException;
import com.jsp.warehouse_management_system.exception.SpaceOrWeightNotAvailableException;
import com.jsp.warehouse_management_system.exception.StorageNotFoundByIdException;
import com.jsp.warehouse_management_system.mapper.BatchMapper;
import com.jsp.warehouse_management_system.mapper.InventoryMapper;
import com.jsp.warehouse_management_system.repository.BatchRepository;
import com.jsp.warehouse_management_system.repository.ClientRepository;
import com.jsp.warehouse_management_system.repository.InventoryRepository;
import com.jsp.warehouse_management_system.repository.StorageRepository;
import com.jsp.warehouse_management_system.requestdto.InventoryRequest;
import com.jsp.warehouse_management_system.responsedto.BatchResponse;
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
	
	@Autowired
	private BatchRepository batchRepository;
	
	@Autowired
	private BatchMapper batchMapper;

	@Override
	public ResponseEntity<ResponseStructure<InventoryResponse>> createInventory(int storageId, int clientId,
			int quantity, InventoryRequest inventoryRequest) {

		Storage storage = storageRepository.findById(storageId).orElseThrow(() -> new StorageNotFoundByIdException("Storage Not Found"));

		Inventory inventory = inventoryMapper.mapToInventory(inventoryRequest, new Inventory());
		inventory.setRestockedAt(LocalDate.now());

		storage.setMaxAdditionalWeight(storage.getMaxAdditionalWeight() - inventoryRequest.getWeightInKg()* quantity);
		storage.setAvailableArea(inventoryRequest.getLengthInMeters()*inventoryRequest.getBreadthInMeters()*inventoryRequest.getHeightInMeters());

		Batch batch = new Batch();

		batch.setInventory(inventory);
		batch.setStorage(storage);
		batch.setQuantity(quantity);

		storageRepository.save(storage);
		inventory = inventoryRepository.save(inventory);
		batch = batchRepository.save(batch);


		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseStructure<InventoryResponse>()
						.setStatus(HttpStatus.CREATED.value())
						.setMessage("Inventory Created")
						.setData(inventoryMapper.mapToInventoryResponse(inventory , batch)));
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
	public ResponseEntity<ResponseStructure<InventoryResponse>> updateInventory(
	        int productId, InventoryRequest inventoryRequest) {

	    return inventoryRepository.findById(productId).map(inventory -> {
	        Batch batch = batchRepository.findByInventory_ProductId(productId)
	                .orElseThrow(() -> new BatchNotFoundException("Stock not found"));

	        int oldQuantity = batch.getQuantity();
	        double oldLength = inventory.getLengthInMeters();
	        double oldBreadth = inventory.getBreadthInMeters();
	        double oldHeight = inventory.getHeightInMeters();

	        double originalWeight = inventory.getWeightInKg() * oldQuantity;
	        double originalArea = inventory.getBreadthInMeters() 
	                            * inventory.getHeightInMeters() 
	                            * inventory.getLengthInMeters();

	        Storage storage = batch.getStorage();

	        Inventory updatedInventory = inventoryMapper.mapToInventory(inventoryRequest, inventory);

	        double newWeight = updatedInventory.getWeightInKg() * oldQuantity;
	        double newArea = updatedInventory.getBreadthInMeters() 
	                       * updatedInventory.getHeightInMeters() 
	                       * updatedInventory.getLengthInMeters();

	        if ((oldLength != updatedInventory.getLengthInMeters() 
	                || oldBreadth != updatedInventory.getBreadthInMeters() 
	                || oldHeight != updatedInventory.getHeightInMeters())
	                || originalWeight != newWeight) {
	            
	            if (storage.getAvailableArea() > 0 && storage.getMaxAdditionalWeight() > 0) {
	                storage.setMaxAdditionalWeight(storage.getMaxAdditionalWeight() 
	                                             + originalWeight - newWeight);
	                storage.setAvailableArea(storage.getAvailableArea() 
	                                       + originalArea - newArea);
	            } else {
	                throw new SpaceOrWeightNotAvailableException("No Available Area or Capacity of Storage Full");
	            }
	        }

	        updatedInventory = inventoryRepository.save(updatedInventory);
	        batch.setInventory(updatedInventory);
	        batch.setStorage(storage);
	        batchRepository.save(batch);
	        storageRepository.save(storage);

	        return ResponseEntity.status(HttpStatus.OK)
	                .body(new ResponseStructure<InventoryResponse>()
	                        .setData(inventoryMapper.mapToInventoryResponse(updatedInventory, batch))
	                        .setMessage("Inventory updated")
	                        .setStatus(HttpStatus.OK.value()));
	    }).orElseThrow(() -> new InventoryNotFoundByIdException("Inventory not found"));
	}

	

	@Override
	public ResponseEntity<ResponseStructure<List<BatchResponse>>> updateQuantity(int storageId, int productId, int quantity) {
	    Inventory inventory = inventoryRepository.findById(productId)
	            .orElseThrow(() -> new InventoryNotFoundByIdException("Inventory not found"));

	    Storage storage = storageRepository.findById(storageId)
	            .orElseThrow(() -> new StorageNotFoundByIdException("Storage not found"));

	    List<Batch> batches = batchRepository.findByStorageAndInventory(storage, inventory);
	    if (batches.isEmpty()) {
	        throw new BatchNotFoundException("Batches not found");
	    }

	    for (Batch batch : batches) {
	        batch.setQuantity(quantity);
	        inventory.setRestockedAt(LocalDate.now());
	        batchRepository.save(batch);
	    }

	    List<BatchResponse> batchResponses = batches.stream()
	            .map(batchMapper::mapToBatchResponse)
	            .collect(Collectors.toList());
	    
	    return ResponseEntity.status(HttpStatus.OK)
	    		.body(new ResponseStructure<List<BatchResponse>>()
	    				.setStatus(HttpStatus.OK.value())
	    				.setMessage("quantity updated")
	    				.setData(batchResponses));
	    			
	}
		
		
	
}


