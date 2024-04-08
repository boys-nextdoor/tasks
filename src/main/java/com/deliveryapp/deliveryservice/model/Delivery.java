package com.deliveryapp.deliveryservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "products")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "Full name",nullable = false)
    private String fullName;

    @Column(name = "Recipient city",nullable = false)
    private String recipientCity;

    @Column(name = "Address of the recipient",nullable = false)
    private String recipientAddress;

    @Column(name = "Recipient's email",nullable = false)
    private String recipientEmail;

    @Column(name = "Sender's city",nullable = false)
    private String senderCity;

    @Column(name = "Sender's address",nullable = false)
    private String senderAddress;

    @Column(name = "Cargo weight",nullable = false)
    private double weight;

    @Column(name = "Cargo pickup date",nullable = false)
    private LocalDate date;

    private long orderNumber;
}
