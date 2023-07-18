package com.dans.test.demo.response.dans;

import com.dans.test.demo.config.dans.CustomDansDateDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PositionResponse {
    private String id;
    private String type;
    private String url;

    @JsonDeserialize(using = CustomDansDateDeserializer.class)
    private Date createdAt;

    private String company;
    private String companyUrl;
    private String location;
    private String description;
    private String title;
    private String howToApply;
    private String companyLogo;
}
