package com.jsp.warehouse_management_system.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.warehouse_management_system.entity.StorageType;
import com.jsp.warehouse_management_system.requestdto.StorageTypeRequest;
import com.jsp.warehouse_management_system.responsedto.StorageTypeResponse;
import com.jsp.warehouse_management_system.service.StorageTypeService;
import com.jsp.warehouse_management_system.util.ResponseStructure;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;




@RestController
@RequestMapping("api/v1")
public class StorageTypeController {
@Autowired
private StorageTypeService storageTypeService;
	@PostMapping("/storagetypes")
	public ResponseEntity<ResponseStructure<StorageTypeResponse>> createStorageType(@RequestBody StorageTypeRequest storageTypeRequest ) {
		
		return storageTypeService.createStorageType(storageTypeRequest);
	}
	
@PutMapping("storagetypes/{storageId}")
public ResponseEntity<ResponseStructure<StorageTypeResponse>> updateStorageType(@PathVariable int storageTypeId, @RequestBody StorageTypeRequest storageTypeRequest) {
   
    
    return storageTypeService.updateStorageType(storageTypeId,storageTypeRequest);
}

@GetMapping("/storagetypes")
public ResponseEntity<ResponseStructure <List<StorageTypeResponse>>> findAllStorageTypes() {
    return storageTypeService.findStorageTypes();
}

}
