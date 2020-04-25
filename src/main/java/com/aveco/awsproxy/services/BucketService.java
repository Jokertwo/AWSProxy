package com.aveco.awsproxy.services;

import java.util.List;

import com.amazonaws.services.s3.model.Bucket;


public interface BucketService {

    /**
     * Create new bucket
     * 
     * @param bucketName
     *            name of created bucket
     */
    Bucket createBucket(String bucketName);


    /**
     * List all buckets
     * 
     * @return return list of buckets
     */
    List<Bucket> listBuckets();


    /**
     * Delete selected bucket
     * 
     * @param bucketName
     *            bucket which will be deleted
     * @return return deleted bucket
     */
    void deleteBucket(String bucketName);


    /**
     * Check if bucket exist
     * 
     * @param bucketName
     *            bucket name which will be controlled
     * @return return true if bucket exists
     */
    boolean doesBucketExist(String bucketName);
}
