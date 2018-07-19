package com.crud.tasks.config;

import lombok.Getter;
import lombok.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class AdminConfig {

    @org.springframework.beans.factory.annotation.Value("${admin.mail}")
    private String adminMail;
    @org.springframework.beans.factory.annotation.Value("${admin.name}")
    private String adminName;
}
