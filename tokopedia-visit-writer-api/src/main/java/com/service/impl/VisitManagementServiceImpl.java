package com.service.impl;

import com.config.KafkaConfig;
import com.convertor.ModelConvertor;
import com.exceptions.TokopediaException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.models.Visit;
import com.models.requests.CreateVisitRequest;
import com.service.IVisitManagementService;
import com.utils.ResourceValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class VisitManagementServiceImpl implements IVisitManagementService {
    private final ModelConvertor modelConvertor;
    private final ResourceValidator resourceValidator;
    private KafkaConfig kafkaConfig;
    private ObjectMapper objectMapper;
    private KafkaTemplate<String, String> kafkaTemplate;

    public VisitManagementServiceImpl(@Autowired ModelConvertor modelConvertor
            , @Autowired ResourceValidator resourceValidator
            , @Autowired KafkaConfig kafkaConfig
            , @Autowired ObjectMapper objectMapper
            , @Autowired KafkaTemplate<String, String> kafkaTemplate) {
        this.modelConvertor = modelConvertor;
        this.resourceValidator = resourceValidator;
        this.kafkaConfig = kafkaConfig;
        this.objectMapper = objectMapper;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public Visit storeVisit(CreateVisitRequest createVisitRequest) throws TokopediaException, JsonProcessingException {
        resourceValidator.validateCreateVisitRequest(createVisitRequest);
        Visit visit = modelConvertor.toVisit(createVisitRequest);
        kafkaTemplate.send(kafkaConfig.getVisitInboundTopic(), objectMapper.writeValueAsString(visit));
        return visit;
    }
}
