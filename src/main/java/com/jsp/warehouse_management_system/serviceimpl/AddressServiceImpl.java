package com.jsp.warehouse_management_system.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;

import com.jsp.warehouse_management_system.entity.Address;
import com.jsp.warehouse_management_system.entity.WareHouse;
import com.jsp.warehouse_management_system.exception.AddressNotFoundByIdException;
import com.jsp.warehouse_management_system.exception.WareHouseNotFoundByIdException;
import com.jsp.warehouse_management_system.mapper.AddressMapper;
import com.jsp.warehouse_management_system.repository.AddressRepository;
import com.jsp.warehouse_management_system.repository.WareHouseRespository;
import com.jsp.warehouse_management_system.requestdto.AddressRequest;
import com.jsp.warehouse_management_system.responsedto.AddressResponse;
import com.jsp.warehouse_management_system.service.AddressService;
import com.jsp.warehouse_management_system.util.ResponseStructure;

@Service
public class AddressServiceImpl implements AddressService{

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private AddressMapper addressMapper;

	@Autowired
	private WareHouseRespository wareHouseRespository;

	@Override
	public ResponseEntity<ResponseStructure<AddressResponse>> addAddress(int wareHouseId, AddressRequest addressRequest) {

		WareHouse wareHouse = wareHouseRespository.findById(wareHouseId)
				.orElseThrow(() -> new  WareHouseNotFoundByIdException("WareHouse not Found"));


		Address address = addressRepository.save(addressMapper.mapToAddress(addressRequest, new Address()));
		address.setWareHouse(wareHouse);
		wareHouseRespository.save(wareHouse);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body( new ResponseStructure<AddressResponse>()
						.setStatus(HttpStatus.CREATED.value())
						.setMessage("Address added ")
						.setData(addressMapper.mapToAddressResponse(address)));
	}
	@Override
	public ResponseEntity<ResponseStructure<AddressResponse>> updateAddress(int addressId, AddressRequest addressRequest) {
	    return addressRepository.findById(addressId).map(existingAddress -> {
	        
	        existingAddress = addressMapper.mapToAddress(addressRequest, existingAddress);
	        existingAddress = addressRepository.save(existingAddress);
	        
	        AddressResponse addressResponse = addressMapper.mapToAddressResponse(existingAddress);
	        return ResponseEntity.status(HttpStatus.CREATED)
	                .body(new ResponseStructure<AddressResponse>()
	                        .setStatus(HttpStatus.CREATED.value())
	                        .setMessage("Address added ")
	                        .setData(addressMapper.mapToAddressResponse(existingAddress))
	                );
	                
	    }).orElseThrow(() -> new AddressNotFoundByIdException("Address with ID not found"));
	}

	
	
	@Override
	public ResponseEntity<ResponseStructure<AddressResponse>> findAddress(int addressId) {
		
		return addressRepository.findById(addressId).map(address -> {
			
			return ResponseEntity.status(HttpStatus.FOUND)
					.body(new ResponseStructure<AddressResponse>()
							.setStatus(HttpStatus.FOUND.value())
							.setMessage("Address found ")
							.setData(addressMapper.mapToAddressResponse(address)));
					
		}).orElseThrow(() -> new AddressNotFoundByIdException("Address with ID not found"));

		
	}

	



}
