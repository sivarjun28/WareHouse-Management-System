package com.jsp.warehouse_management_system.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Client {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int clientId;
private String businessName;
private String email;
private long contactNumber;
private String apiKey;
}
