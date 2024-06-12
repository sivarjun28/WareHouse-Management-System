package com.jsp.warehouse_management_system.service;

import org.springframework.http.ResponseEntity;

import com.jsp.warehouse_management_system.requestdto.AddressRequest;
import com.jsp.warehouse_management_system.responsedto.AddressResponse;
import com.jsp.warehouse_management_system.util.ResponseStructure;

public interface AddressService {

	public ResponseEntity<ResponseStructure<AddressResponse>> addAddress(int wareHouseId, AddressRequest addressRequest) ;

	public ResponseEntity<ResponseStructure<AddressResponse>> updateAddress(int addressId,
			AddressRequest addressRequest);

	public ResponseEntity<ResponseStructure<AddressResponse>> findAddress(int addressId);



}
