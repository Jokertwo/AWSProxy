package com.aveco.awsproxy.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Data;


@Data
@Component
public class AWSConfiguration {

    @Value("${aws-configuration.accessKey}")
    private String accessKey;
    @Value("${aws-configuration.secretKey}")
    private String secretKey;

}
