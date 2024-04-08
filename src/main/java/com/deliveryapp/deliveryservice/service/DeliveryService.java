package com.deliveryapp.deliveryservice.service;

import com.deliveryapp.deliveryservice.dto.DeliveryDto;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public interface DeliveryService {
    long generateOrderNumber();

    DeliveryDto createDelivery(DeliveryDto deliveryDto);

    DeliveryDto getDelivery(long id);

    boolean notifyRecipientOfArrival(long deliveryId);

    String buildEmailBody(DeliveryDto deliveryDto, LocalDate deadline);

    void delete(long id);
}
