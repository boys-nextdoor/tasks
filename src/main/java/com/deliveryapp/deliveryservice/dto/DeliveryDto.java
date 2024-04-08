package com.deliveryapp.deliveryservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DeliveryDto {
    private long id;
    private String fullName;
    private String recipientCity;
    private String recipientAddress;
    private String recipientEmail;
    private String senderCity;
    private String senderAddress;
    private double weight;
    private LocalDate date;
    private long orderNumber;
}
