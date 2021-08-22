package com.resource.impl;

import com.exceptions.TokopediaException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.models.ServiceResponse;
import com.models.requests.CreateVisitRequest;
import com.resource.VisitWriterResource;
import com.service.IVisitManagementService;
import com.utils.Constants;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Visit Writer Resource")
public class VisitWriterResourceImpl implements VisitWriterResource {
    private final Logger logger = LoggerFactory.getLogger(VisitWriterResourceImpl.class);

    private final IVisitManagementService visitManagementService;

    public VisitWriterResourceImpl(@Autowired IVisitManagementService visitManagementService) {
        this.visitManagementService = visitManagementService;
    }

    @Tag(name = "Visit Writer Resource")
    @Override
    public ResponseEntity<ServiceResponse> storeVisit(HttpHeaders httpHeaders, CreateVisitRequest createVisitRequest) {
        try {
            return ResponseEntity.accepted().body(ServiceResponse.builder()
                    .status(Constants.RESPONSE_STATUS.SUCCESS.name())
                    .data(visitManagementService.storeVisit(createVisitRequest))
                    .build());
        } catch (TokopediaException tokopediaException) {
            logger.error("Error while storing visitor's visit.", tokopediaException);
            return new ResponseEntity<>(ServiceResponse.builder()
                    .status(Constants.RESPONSE_STATUS.FAILURE.name())
                    .data(tokopediaException.getMessage())
                    .build(), tokopediaException.getHttpStatusCode());
        } catch (JsonProcessingException e) {
            logger.error("Error while storing visitor's visit.", e);
            return new ResponseEntity<>(ServiceResponse.builder()
                    .status(Constants.RESPONSE_STATUS.FAILURE.name())
                    .data(e.getMessage())
                    .build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
