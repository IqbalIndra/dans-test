package com.dans.test.demo.config.dans;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class DansApiConfig {
    @Value("${dns.api.base-url}")
    private String baseUrl;
}
