package com.dans.test.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/ping")
public class PingController {

    @GetMapping
    public Map<String, Object> ping(){
        Map<String,Object> map = new HashMap<>();
        map.put("status","up");

        return map;
    }
}
