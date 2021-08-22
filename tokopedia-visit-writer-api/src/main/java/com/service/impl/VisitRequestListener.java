package com.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.models.Visit;
import com.service.IVisitWriterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class VisitRequestListener {

    private IVisitWriterService visitWriterService;
    private ObjectMapper objectMapper;

    public VisitRequestListener(@Autowired IVisitWriterService visitWriterService
            , @Autowired ObjectMapper objectMapper) {
        this.visitWriterService = visitWriterService;
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "${kafka.visit.inbound.topic}", groupId = "${consumer.group.id}")
    public void listen(String message) {
        try {
            visitWriterService.writeVisitorRequest(objectMapper.readValue(message, Visit.class));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
