package com.deliveryapp.deliveryservice.service;

import com.deliveryapp.deliveryservice.dto.DeliveryDto;
import com.deliveryapp.deliveryservice.dto.DeliveryMapper;
import com.deliveryapp.deliveryservice.model.Delivery;
import com.deliveryapp.deliveryservice.repo.DeliveryRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DeliveryServiceImpl implements DeliveryService{
    private final DeliveryRepository deliveryRepository;
    private final DeliveryMapper deliveryMapper;
    private final EmailService emailService;

    @Override
    public long generateOrderNumber() {
        return (long) (Math.random() * 5000) + 1000;
    }
    @Override
    public DeliveryDto createDelivery(DeliveryDto deliveryDto) {
        Delivery delivery = deliveryMapper.convertToEntity(deliveryDto);
        return deliveryMapper.convertToDto(deliveryRepository.save(delivery));
    }

    @Override
    public DeliveryDto getDelivery(long id) {
        Optional<Delivery> deliveryOptional = deliveryRepository.findById(id);
        if (deliveryOptional.isPresent()) {
            return deliveryMapper.convertToDto(deliveryOptional.get());
        } else {
            throw new EntityNotFoundException("User not found");
        }
    }
    @Override
    public boolean notifyRecipientOfArrival(long deliveryId) {
        DeliveryDto deliveryDto = getDelivery(deliveryId);
        if (deliveryDto != null) {
            LocalDate arrivalDate = deliveryDto.getDate();
            if (arrivalDate != null) {
                LocalDate deadline = arrivalDate.plusDays(7);
                String emailBody = buildEmailBody(deliveryDto, deadline);
                emailService.send(deliveryDto.getRecipientEmail(), "Уведомление о прибытии доставки", emailBody);
                return true;
            } else {
                throw new RuntimeException("Человек с таким ID не найден: " + deliveryId);
            }
        }
        return false;
    }
    @Override
    public String buildEmailBody(DeliveryDto deliveryDto, LocalDate deadline) {
        return "Уважаемый " + deliveryDto.getFullName() + ", ваш заказ №" + deliveryDto.getId() +
                " прибудет " + deliveryDto.getDate() + ". Пожалуйста, заберите его до " + deadline + ".";
    }

    @Override
    public void delete(long id) {
        deliveryRepository.deleteById(id);
    }
}
