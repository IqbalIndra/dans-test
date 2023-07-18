package com.dans.test.demo.service.dans.impl;

import com.dans.test.demo.service.dans.DansApiService;
import com.dans.test.demo.config.dans.DansApiConfig;
import com.dans.test.demo.response.dans.PositionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DansApiServiceImpl implements DansApiService {
    private final RestTemplate restTemplate;
    private final DansApiConfig dansApiConfig;
    @Override
    public List<PositionResponse> getPositions() {
        String resourceUrl = dansApiConfig.getBaseUrl()+"/api/recruitment/positions.json";

        return restTemplate.exchange(
                        resourceUrl,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<PositionResponse>>() {}
                ).getBody();
    }

    @Override
    public PositionResponse getPositionsById(String id) {
        String resourceUrl = dansApiConfig.getBaseUrl()+"/api/recruitment/positions/{ID}";

        Map<String, String> uriParam = new HashMap<>();
        uriParam.put("ID", id);

        return restTemplate.exchange(
                resourceUrl,
                HttpMethod.GET,
                null,
                PositionResponse.class,
                uriParam
            ).getBody();
    }

}
