package com.deliveryapp.deliveryservice.dto;

import com.deliveryapp.deliveryservice.model.Delivery;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DeliveryMapper {
    private final ModelMapper modelMapper;

    public DeliveryDto convertToDto(Delivery delivery) {
        return modelMapper.map(delivery, DeliveryDto.class);
    }

    public Delivery convertToEntity(DeliveryDto deliveryDto) {
        return modelMapper.map(deliveryDto, Delivery.class);
    }
}
