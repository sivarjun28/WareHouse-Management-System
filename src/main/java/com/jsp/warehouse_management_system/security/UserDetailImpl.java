package com.jsp.warehouse_management_system.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.jsp.warehouse_management_system.entity.Admin;
import com.jsp.warehouse_management_system.enums.Privilage;

import lombok.AllArgsConstructor;


@AllArgsConstructor
public class UserDetailImpl implements UserDetails{

	
	private Admin admin;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		
		return admin.getAdminType()
				.getPrivilages()
			.stream().map(privilage -> new SimpleGrantedAuthority(privilage.name())).toList();
	}

	@Override
	public String getPassword() {
		
		return admin.getPassword();
	}

	@Override
	public String getUsername() {
		
		
		return admin.getEmail();
	}

}
