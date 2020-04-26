package com.aveco.awsproxy;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;


@Data
@Component
@ConfigurationProperties("aws-configuration")
public class AWSConfiguration {

    private String accessKey;
    private String secretKey;

}