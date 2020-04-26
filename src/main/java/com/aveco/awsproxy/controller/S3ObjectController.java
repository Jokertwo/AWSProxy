package com.aveco.awsproxy.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.services.s3.model.ObjectListing;
import com.aveco.awsproxy.services.S3ObjectService;
import com.aveco.awsproxy.shared.io.response.object.ObjectResponse;
import com.aveco.awsproxy.shared.util.IDProvider;
import com.aveco.awsproxy.shared.util.TimestampProvider;


@RestController
@RequestMapping("/object")
public class S3ObjectController {

    private final S3ObjectService s3ObjectService;
    private final IDProvider idProvider;
    private final TimestampProvider timestampProvider;

    public S3ObjectController(S3ObjectService s3ObjectService,
                              IDProvider idProvider,
                              TimestampProvider timestampProvider) {
        this.s3ObjectService = s3ObjectService;
        this.idProvider = idProvider;
        this.timestampProvider = timestampProvider;
    }


    @GetMapping("/{bucketName}")
    public ObjectResponse listObjects(@PathVariable String bucketName) {
        ObjectListing list = s3ObjectService.listObjects(bucketName);
        ObjectResponse response = new ObjectResponse();
        response.setTimestamp(timestampProvider.createTimestamp());
        response.setResponseId(idProvider.createID());
        response.setObjects(list);
        return response;
    }
}
