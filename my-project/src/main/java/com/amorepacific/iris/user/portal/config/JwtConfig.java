package com.amorepacific.iris.user.portal.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtConfig {

    @Value("${jwt.secret.key}")
    private String secretKeyString;

    private static String secretKey;

    @PostConstruct
    public void init() {
        JwtConfig.secretKey = this.secretKeyString;
    }

    public static String getSecretKey() {
        return secretKey;
    }
}