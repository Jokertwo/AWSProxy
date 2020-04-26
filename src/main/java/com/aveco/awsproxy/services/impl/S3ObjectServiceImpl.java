package com.aveco.awsproxy.services.impl;

import java.io.File;

import org.springframework.stereotype.Service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.aveco.awsproxy.services.S3ObjectService;


@Service
public class S3ObjectServiceImpl implements S3ObjectService {

    private final AmazonS3 amazonS3;

    public S3ObjectServiceImpl(AmazonS3 amazonS3) {
        this.amazonS3 = amazonS3;
    }


    @Override
    public PutObjectResult uploadFile(String bucketName, String uploadPath, String pathToFile) {
        File file = new File(pathToFile);
        PutObjectRequest request = new PutObjectRequest(bucketName, uploadPath, file);
        // request.setPro
        return null;
    }


    @Override
    public ObjectListing listObjects(String bucketName) {
        return amazonS3.listObjects(bucketName);
    }

}
