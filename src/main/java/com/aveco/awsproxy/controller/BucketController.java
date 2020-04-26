package com.aveco.awsproxy.controller;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.services.s3.model.Bucket;
import com.aveco.awsproxy.services.BucketService;
import com.aveco.awsproxy.shared.io.response.bucket.BucketDeleteResponse;
import com.aveco.awsproxy.shared.io.response.bucket.BucketExistResponse;
import com.aveco.awsproxy.shared.io.response.bucket.BucketListResponse;
import com.aveco.awsproxy.shared.io.response.bucket.BucketResponse;
import com.aveco.awsproxy.shared.util.Utils;

import lombok.extern.log4j.Log4j2;


@RestController
@RequestMapping("/bucket")
@Log4j2
public class BucketController {

    private final Utils utils;
    private final BucketService bucketService;

    public BucketController(BucketService bucketService, Utils utils) {
        this.utils = utils;
        this.bucketService = bucketService;
    }


    /**
     * List all bucket assigned to account
     * 
     * @return return all available buckets
     */
    @GetMapping
    public BucketListResponse listBuckets() {
        log.info("Receive request to list all buckets");
        List<Bucket> bucketList = bucketService.listBuckets();
        BucketListResponse bucketResponse = new BucketListResponse();
        bucketResponse = utils.setIDAndTimestamp(bucketResponse);
        utils.setIDAndTimestamp(bucketResponse).setBuckets(
            bucketList.stream().map(bucket -> new BucketResponse(bucket.getName(), bucket.getCreationDate()))
                .collect(Collectors.toList()));
        log.debug("Found buckets: {} ", Arrays.toString(bucketList.toArray()));
        log.info("Send response for bucket list: {}", bucketResponse);
        return bucketResponse;
    }


    /**
     * Create new bucket. Name of created bucket must be unique.
     * 
     * @param bucketName
     *            name of created bucket
     * @return return basic information about created bucket
     */
    @PostMapping("/{bucketName}")
    public BucketListResponse createBucket(@PathVariable String bucketName) {
        log.info("Receive request for creating new bucket with name: {}", bucketName);
        Bucket bucket = bucketService.createBucket(bucketName);
        BucketListResponse bucketResponse = new BucketListResponse();
        utils.setIDAndTimestamp(bucketResponse)
            .setBuckets(Collections.singletonList(new BucketResponse(bucket.getName(), bucket.getCreationDate())));
        log.info("Send response for creating buckets: {}", bucketResponse);
        return bucketResponse;
    }


    /**
     * Check if bucket with specified mane exist.
     * 
     * @param bucketName
     *            name which should be checked
     * @return return true if bucket exists
     */
    @GetMapping("/{bucketName}")
    public BucketExistResponse checkExist(@PathVariable String bucketName) {
        log.info("Recieve request to check if bucket exist bucketName: {}", bucketName);
        BucketExistResponse existResponse = new BucketExistResponse();
        boolean existBucket = false;
        if (bucketService.doesBucketExist(bucketName)) {
            existBucket = true;
        }
        utils.setIDAndTimestamp(existResponse).setBucketExist(existBucket);
        log.info("Send response for checking bucket exist: {}", existResponse);
        return existResponse;
    }


    /**
     * Delete selected bucket
     * 
     * @param bucketName
     *            bucket new which will be deleted
     * @return return true if was deleted successful
     */
    @DeleteMapping("/{bucketName}")
    public BucketDeleteResponse deleteBucket(@PathVariable String bucketName) {
        log.info("Receive request to delete bucket: {}", bucketName);
        bucketService.deleteBucket(bucketName);
        BucketDeleteResponse deleteResponse = new BucketDeleteResponse();
        utils.setIDAndTimestamp(deleteResponse).setDeleted(true);
        log.info("Bucket: {} deleted", bucketName);
        return deleteResponse;
    }

}
