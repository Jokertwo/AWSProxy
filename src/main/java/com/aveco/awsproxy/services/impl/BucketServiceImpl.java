package com.aveco.awsproxy.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.Bucket;
import com.aveco.awsproxy.services.BucketService;


@Service
public class BucketServiceImpl implements BucketService {

    private final AmazonS3 amazonS3;

    public BucketServiceImpl(AmazonS3 amazonS3) {
        this.amazonS3 = amazonS3;
    }


    @Override
    public Bucket createBucket(String bucketName) {
        return amazonS3.createBucket(bucketName);
    }


    @Override
    public List<Bucket> listBuckets() {
        return amazonS3.listBuckets();
    }


    @Override
    public void deleteBucket(String bucketName) {
        amazonS3.deleteBucket(bucketName);
    }


    @Override
    public boolean doesBucketExist(String bucketName) {
        return amazonS3.doesBucketExistV2(bucketName);
    }
}
