package com.service;

import com.exceptions.TokopediaException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.models.Visit;
import com.models.requests.CreateVisitRequest;

public interface IVisitWriterService {
    void writeVisitorRequest(Visit visit) throws TokopediaException;
}
