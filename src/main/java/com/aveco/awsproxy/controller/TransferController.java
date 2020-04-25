package com.aveco.awsproxy.controller;

import org.springframework.web.bind.annotation.RestController;

import com.aveco.awsproxy.shared.util.IDProvider;
import com.aveco.awsproxy.shared.util.configuration.AWSConfiguration;


@RestController
public class TransferController {

    private IDProvider idProvider;
    private AWSConfiguration configuration;

    public TransferController(IDProvider idProvider, AWSConfiguration configuration) {
        this.idProvider = idProvider;
        this.configuration = configuration;

    }

}
