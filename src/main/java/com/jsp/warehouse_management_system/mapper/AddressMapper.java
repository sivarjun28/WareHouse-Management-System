package com.jsp.warehouse_management_system.mapper;

import org.springframework.stereotype.Component;

import com.jsp.warehouse_management_system.entity.Address;
import com.jsp.warehouse_management_system.requestdto.AddressRequest;
import com.jsp.warehouse_management_system.responsedto.AddressResponse;

@Component
public class AddressMapper {

	public Address mapToAddress(AddressRequest addressRequest , Address address ) {
		address.setAddressLine(addressRequest.getAddressLine());
		address.setCity(addressRequest.getCity());
		address.setState(addressRequest.getState());
		address.setCountry(addressRequest.getCountry());
		address.setPincode(addressRequest.getPincode());
		address.setLatitude(addressRequest.getLatitude());
		address.setLongitude(addressRequest.getLongitude());

		return address;
	}

	public AddressResponse mapToAddressResponse(Address address) {
		return AddressResponse.builder()
				.addressId(address.getAddressId())
				.addressLine(address.getAddressLine())
				.city(address.getCity())
				.state(address.getState())
				.country(address.getCountry())
				.pincode(address.getPincode())
				.latitude(address.getLatitude())
				.longitude(address.getLongitude())
				.build();
	}
}
