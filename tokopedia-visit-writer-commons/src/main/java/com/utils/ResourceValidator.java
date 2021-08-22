package com.utils;

import com.exceptions.TokopediaException;
import com.models.requests.CreateVisitRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Date;

@Component
public class ResourceValidator {

    public void validateValue(String key, String value) {
        if (Boolean.FALSE.equals(StringUtils.hasText(value))) {
            throw new TokopediaException(key + " is required.", HttpStatus.BAD_REQUEST);
        }
    }

    public void validateValue(String key, Date value) {
        if (value == null) {
            throw new TokopediaException(key + " is required.", HttpStatus.BAD_REQUEST);
        }
    }

    public void validateCreateVisitRequest(CreateVisitRequest createVisitRequest) {
        validateValue("Page Id", createVisitRequest.getPageId());
        validateValue("Visit Timestamp", createVisitRequest.getVisitTimestamp());
    }
}
