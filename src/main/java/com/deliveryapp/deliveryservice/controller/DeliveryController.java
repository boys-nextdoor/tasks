package com.deliveryapp.deliveryservice.controller;

import com.deliveryapp.deliveryservice.dto.DeliveryDto;
import com.deliveryapp.deliveryservice.service.DeliveryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/delivery")
@AllArgsConstructor
public class DeliveryController {
    private final DeliveryService deliveryService;

    @PostMapping("/create")
    public DeliveryDto create(@RequestBody DeliveryDto deliveryDto) {
        deliveryDto.setOrderNumber(deliveryService.generateOrderNumber());
        return deliveryService.createDelivery(deliveryDto);
    }

    @GetMapping("/get/{id}")
    public DeliveryDto get(@PathVariable long id) {
        return deliveryService.getDelivery(id);
    }

    @PostMapping("/notifyArrival/{deliveryId}")
    public ResponseEntity<String> notifyRecipientOfArrival(@PathVariable long deliveryId) {
        boolean notified = deliveryService.notifyRecipientOfArrival(deliveryId);
        if (notified) {
            return ResponseEntity.ok("Success");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable long id) {
        deliveryService.delete(id);
    }

}
