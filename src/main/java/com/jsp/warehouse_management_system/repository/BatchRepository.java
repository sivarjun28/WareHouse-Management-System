package com.jsp.warehouse_management_system.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.warehouse_management_system.entity.Batch;
import com.jsp.warehouse_management_system.entity.Inventory;
import com.jsp.warehouse_management_system.entity.Storage;

public interface BatchRepository extends JpaRepository<Batch, Integer>{

	Optional<Batch> findByInventory_ProductId(int productId);

	List<Batch> findByStorageAndInventory(Storage storage, Inventory inventory);

}
