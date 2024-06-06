package com.jsp.warehouse_management_system.responsedto;

import java.util.List;

import com.jsp.warehouse_management_system.enums.AdminType;
import com.jsp.warehouse_management_system.enums.Privilage;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdminResponse {
	
	private int adminId;
	private String name;
	private String email;
	AdminType adminType;
	List<Privilage> privilages;
	
	
}
