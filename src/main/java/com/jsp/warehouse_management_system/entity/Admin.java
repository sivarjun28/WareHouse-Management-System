package com.jsp.warehouse_management_system.entity;

import java.util.List;

import com.jsp.warehouse_management_system.enums.AdminType;
import com.jsp.warehouse_management_system.enums.Privilege;


import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Admin {
	
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int adminId;
private String name;
private String email;
private String password;
@Enumerated(EnumType.STRING)
AdminType adminType;

}
