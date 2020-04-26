package com.aveco.awsproxy.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.Bucket;
import com.aveco.awsproxy.services.BucketService;

import lombok.extern.log4j.Log4j2;


@Log4j2
@Service
public class BucketServiceImpl implements BucketService {

    private final AmazonS3 amazonS3;

    public BucketServiceImpl(AmazonS3 amazonS3) {
        this.amazonS3 = amazonS3;
    }


    @Override
    public Bucket createBucket(String bucketName) {
        log.info("Creating bucket with name: {}", bucketName);
        return amazonS3.createBucket(bucketName);
    }


    @Override
    public List<Bucket> listBuckets() {
        log.info("Listing all buckets");
        return amazonS3.listBuckets();
    }


    @Override
    public void deleteBucket(String bucketName) {
        log.info("Deleting bucket with name: {}", bucketName);
        amazonS3.deleteBucket(bucketName);
    }


    @Override
    public boolean doesBucketExist(String bucketName) {
        boolean exist = amazonS3.doesBucketExistV2(bucketName);
        log.info("Check if bucket exist bucket name: {}, exists: {}", bucketName, exist);
        return exist;
    }
}
