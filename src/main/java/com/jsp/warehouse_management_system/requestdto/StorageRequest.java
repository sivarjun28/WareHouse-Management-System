package com.jsp.warehouse_management_system.requestdto;

import java.util.List;

import com.jsp.warehouse_management_system.enums.MaterialType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StorageRequest {
	
	
	private String blockName;
	private String section;

	List<MaterialType> materialTypes;
	
}
