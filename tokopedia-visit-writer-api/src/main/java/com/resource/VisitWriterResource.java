package com.resource;

import com.models.ServiceResponse;
import com.models.requests.CreateVisitRequest;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v1/api")
public interface VisitWriterResource {
    @ApiOperation(value = "Store visitor's visit to page.", response = ResponseEntity.class)
    @PostMapping(value = "/visit", produces = "application/json")
    ResponseEntity<ServiceResponse> storeVisit(@RequestHeader HttpHeaders httpHeaders
            , @RequestBody CreateVisitRequest createVisitRequest);
}

