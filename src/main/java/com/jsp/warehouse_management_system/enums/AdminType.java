package com.jsp.warehouse_management_system.enums;

import java.util.List;

public enum AdminType {
	SUPER_ADMIN(List.of(Privilage.CREATE_ADMIN,Privilage.CREATE_WAREHOUSE,Privilage.CREATE_ADDRESS,Privilage.CREATE_STORAGE
	,Privilage.READ, Privilage.UPADTE_ADMIN, Privilage.UPDATE_WAREHOUSE,Privilage.UPDATE_STORAGE,Privilage.UPDATE_ADDRESS
	,Privilage.DELETE_ADMIN,Privilage.DELETE_WAREHOUSE,Privilage.DELETE_ADDRESS,Privilage.DELETE_STORAGE)),
	
	ADMIN(List.of(Privilage.CREATE_STORAGE,Privilage.UPADTE_ADMIN,Privilage.UPADTE_ADMIN,Privilage.UPDATE_STORAGE,Privilage .DELETE_STORAGE));

	private List<Privilage> privlages;

	private AdminType(List<Privilage> privlages) {
		this.privlages = privlages;
	}

	public List<Privilage> getPrivilages(){
		return this.privlages;
	}



}
