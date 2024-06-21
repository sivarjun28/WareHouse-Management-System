package com.jsp.warehouse_management_system.entity;

import java.time.LocalDate;
import java.util.List;

import com.jsp.warehouse_management_system.enums.MaterialType;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productId;
    private String productTitle;
    private double lengthInMeters;
    private double breadthInMeters;
    private double heightInMeters;
    private double weightInKg;

    
    @Enumerated(EnumType.STRING)
    private List<MaterialType> materialTypes;

    private LocalDate restockedAt;
    private int sellerId;

    @OneToMany(mappedBy = "inventory")
    private List<Batch> batches;

    @ManyToOne
    private Client client;

    @ManyToMany(mappedBy = "inventories")
    private List<PurchaseOrder> purchaseOrders;
}
