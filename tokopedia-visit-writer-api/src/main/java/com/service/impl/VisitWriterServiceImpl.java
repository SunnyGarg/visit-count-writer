package com.service.impl;

import com.exceptions.TokopediaException;
import com.models.Visit;
import com.service.IVisitWriterService;
import com.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class VisitWriterServiceImpl implements IVisitWriterService {
    private RedisTemplate<String, Object> redisTemplate;

    private static final String UNDER_SCORE = "_";
    private static final String VISIT_SUFFIX = "visit";
    private static final String VISIT_COUNT_TEXT = "visit_count";


    public VisitWriterServiceImpl(@Autowired RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void writeVisitorRequest(Visit visit) throws TokopediaException {
        String hash = new StringBuffer(visit.getPageId()).append(UNDER_SCORE)
                .append(CommonUtils.convertDateToYearAndMonth(visit.getVisitedAt())).append(UNDER_SCORE).append(VISIT_SUFFIX).toString();
        String hashKey = VISIT_COUNT_TEXT;
        Long hashValue;
        if (redisTemplate.opsForHash().hasKey(hash, hashKey)) {
            hashValue = (Long) redisTemplate.opsForHash().get(hash, hashKey) + 1;
        } else {
            hashValue = 1L;
        }
        redisTemplate.opsForHash().put(hash, hashKey, hashValue);

        //This expiry time dependes on business use case. We may archive this to some persistent storage for future use.
        redisTemplate.expire(hash, 365, TimeUnit.DAYS);
    }
}
