package com.jsp.warehouse_management_system.requestdto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AddressRequest {
	
	@NotNull(message = "addressLine cannot be null")
	@NotBlank(message = "addressLine cannot be Blank")
	private String addressLine;
	@NotNull(message = "addressLine cannot be null")
	@NotBlank(message = "addressLine cannot be Blank")
	private String city;
	@NotNull(message = "city cannot be null")
	@NotBlank(message = "city cannot be Blank")
	private String state;
	@NotNull(message = "state cannot be null")
	@NotBlank(message = "state cannot be Blank")
	private String country;
	@NotNull(message = "country cannot be null")
	@NotBlank(message = "country cannot be Blank")
	private int pincode;
	private String latitude;
	private String longitude;
}
