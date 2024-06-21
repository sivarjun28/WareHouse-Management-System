package com.jsp.warehouse_management_system.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jsp.warehouse_management_system.entity.Batch;
import com.jsp.warehouse_management_system.responsedto.BatchResponse;
import com.jsp.warehouse_management_system.responsedto.StorageResponse;

@Component
public class BatchMapper {
    
    @Autowired
    private StorageMapper storageMapper;

    public BatchResponse mapToBatchResponse(Batch batch) {
        return BatchResponse.builder()
                .batchId(batch.getBatchId())
                .quantity(batch.getQuantity())
                .storage(storageMapper.mapToStorageResponse(batch.getStorage()))
                .build();
    }
}


