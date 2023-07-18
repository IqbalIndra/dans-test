package com.dans.test.demo.service.dans;

import com.dans.test.demo.response.dans.PositionResponse;

import java.util.List;

public interface DansApiService {
    List<PositionResponse> getPositions();
    PositionResponse getPositionsById(String id);
}
