package com.aveco.awsproxy.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.aveco.awsproxy.services.S3ObjectService;
import com.aveco.awsproxy.shared.io.request.UploadFileRequest;
import com.aveco.awsproxy.shared.io.response.object.ObjectResponse;
import com.aveco.awsproxy.shared.io.response.object.UploadResponse;
import com.aveco.awsproxy.shared.util.Utils;

import lombok.extern.log4j.Log4j2;


@RestController
@RequestMapping("/object")
@Log4j2
public class S3ObjectController {

    private final S3ObjectService s3ObjectService;
    private final Utils utils;

    public S3ObjectController(S3ObjectService s3ObjectService,
                              Utils utils) {
        this.s3ObjectService = s3ObjectService;
        this.utils = utils;
    }


    /**
     * List all objects in specific buckets
     * 
     * @param bucketName
     *            bucket name which will be listed
     * @return return contains of selected bucket
     */
    @GetMapping("/{bucketName}")
    public ObjectResponse listObjects(@PathVariable String bucketName) {
        log.info("Receive request for listing content of bucket: {}", bucketName);
        List<S3ObjectSummary> list = s3ObjectService.listObjects(bucketName, null);
        ObjectResponse response = new ObjectResponse();
        utils.setIDAndTimestamp(response).setObjects(list);
        log.info("Send response for listing content of bucket: {}, reponse: {}", bucketName, response);
        return response;
    }


    /**
     * List all objects in specific buckets
     * 
     * @param bucketName
     *            bucket name which will be listed
     * @param prefix
     *            Parameter restricting the response to keys beginning with the specified prefix
     * @return return contains of selected bucket
     */
    @GetMapping("/{bucketName}/{prefix}")
    public ObjectResponse listObjects(@PathVariable String bucketName, @PathVariable String prefix) {
        log.info("Receive request for listing content of bucket: {}, prefix: {}", bucketName, prefix);
        List<S3ObjectSummary> list = s3ObjectService.listObjects(bucketName, prefix);
        ObjectResponse response = new ObjectResponse();
        utils.setIDAndTimestamp(response).setObjects(list);
        log.info("Send response for listing content of bucket: {}, prefix: {}, response: {}", bucketName, prefix,
            response);
        return response;
    }


    /**
     * Upload file to S3 service. This is blocking method. Response is returned after update.
     * 
     * @param uploadFileRequest
     *            request with metadata to upload
     * @return return information about result
     */
    @PostMapping("/upload")
    public UploadResponse uploadFile(@Valid @RequestBody UploadFileRequest uploadFileRequest) {
        boolean result = s3ObjectService.uploadFile(uploadFileRequest.getBucketName(),
            uploadFileRequest.getObjectPath(), uploadFileRequest.getPathToFile());
        UploadResponse uploadResponse = new UploadResponse();
        utils.setIDAndTimestamp(uploadResponse).setUploadSuccessful(result);
        return uploadResponse;
    }
}
