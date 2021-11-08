package org.palms.first.microservice.dto;

import lombok.Data;

@Data
public class UserInfo {
    private String name;
    private Long age;
    private String message;
}
