package com.adapter;

import com.exceptions.TokopediaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

@Service
public class RestAPIClientAdapter {
    private final RestTemplate restTemplate;

    public RestAPIClientAdapter(@Autowired RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Retryable(value = {TokopediaException.class}, maxAttempts = 3, backoff = @Backoff(delay = 2000))
    public ResponseEntity<String> callAPI(String endPoint, HttpMethod methodType, String payload
            , MultiValueMap<String, String> headers) throws TokopediaException {

        if (Boolean.FALSE.equals(StringUtils.hasText(endPoint))) {
            throw new TokopediaException("Rest API end point can not be null.", HttpStatus.BAD_REQUEST);
        }

        if (methodType == null) {
            throw new TokopediaException("Rest API method type can not be null.", HttpStatus.BAD_REQUEST);
        }

        if ((methodType.equals(HttpMethod.POST)
                || methodType.equals(HttpMethod.PUT)
                || methodType.equals(HttpMethod.PATCH)) && payload == null) {
            throw new TokopediaException("Payload can not be null for Rest API method " + methodType, HttpStatus.BAD_REQUEST);
        }

        try {
            return restTemplate.exchange(endPoint, methodType, new HttpEntity<>(payload, headers), String.class);
        } catch (Exception e) {
            throw new TokopediaException("Failed in calling the API " + endPoint, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Recover
    public ResponseEntity<String> getBackendResponseFallback() {
        return null;
    }
}
