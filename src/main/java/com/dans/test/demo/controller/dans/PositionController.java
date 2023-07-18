package com.dans.test.demo.controller.dans;

import com.dans.test.demo.response.dans.PositionResponse;
import com.dans.test.demo.service.dans.DansApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dans")
@RequiredArgsConstructor
public class PositionController {
    private final DansApiService dansApiService;

    @GetMapping("/jobs")
    public ResponseEntity<?> getJobs(){
        List<PositionResponse> positions = dansApiService.getPositions();
        return ResponseEntity.ok(positions);
    }

    @GetMapping("/jobs/{id}")
    public ResponseEntity<?> getJobsById(@PathVariable("id") String id){
        PositionResponse position = dansApiService.getPositionsById(id);
        return ResponseEntity.ok(position);
    }
}
