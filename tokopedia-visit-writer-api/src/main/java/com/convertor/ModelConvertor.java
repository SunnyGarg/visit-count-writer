package com.convertor;

import com.models.Visit;
import com.models.requests.CreateVisitRequest;
import com.utils.CommonUtils;
import org.springframework.stereotype.Component;

@Component
public class ModelConvertor {
    public Visit toVisit(CreateVisitRequest createVisitRequest) {
        return Visit.builder()
                .pageId(createVisitRequest.getPageId())
                .visitedAt(createVisitRequest.getVisitTimestamp())
                .build();
    }
}

