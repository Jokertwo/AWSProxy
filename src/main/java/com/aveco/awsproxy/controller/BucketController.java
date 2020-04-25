package com.aveco.awsproxy.controller;

import java.util.Collections;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.services.s3.model.Bucket;
import com.aveco.awsproxy.services.BucketService;
import com.aveco.awsproxy.shared.io.response.BucketResponse;
import com.aveco.awsproxy.shared.util.IDProvider;


@RestController
@RequestMapping("/bucket")
public class BucketController {

    private final IDProvider idProvider;
    private final BucketService bucketService;
    private final ModelMapper modelMapper;

    public BucketController(IDProvider idProvider, BucketService bucketService, ModelMapper modelMapper) {
        this.idProvider = idProvider;
        this.bucketService = bucketService;
        this.modelMapper = modelMapper;
    }


    @GetMapping
    public BucketResponse listBuckets() {
        List<Bucket> bucketList = bucketService.listBuckets();
        BucketResponse bucketResponse = new BucketResponse();
        bucketResponse.setBuckets(bucketList);
        bucketResponse.setResponseID(idProvider.createID());
        bucketResponse.setBucketExist(true);
        return bucketResponse;
    }


    @PostMapping("/{bucketName}")
    public BucketResponse createBucket(@PathVariable String bucketName) {
        Bucket bucket = bucketService.createBucket(bucketName);
        BucketResponse bucketResponse = new BucketResponse();
        bucketResponse.setResponseID(idProvider.createID());
        bucketResponse.setBuckets(Collections.singletonList(bucket));
        bucketResponse.setBucketExist(true);
        return bucketResponse;
    }


    @GetMapping("/{bucketName}")
    public BucketResponse checkExist(@PathVariable String bucketName) {
        BucketResponse bucketResponse = new BucketResponse();
        bucketResponse.setResponseID(idProvider.createID());
        bucketResponse.setBuckets(Collections.emptyList());
        bucketResponse.setBucketExist(false);
        if (bucketService.doesBucketExist(bucketName)) {
            bucketResponse.setBucketExist(true);
        }
        return bucketResponse;
    }


    @DeleteMapping("/{bucketName}")
    public ResponseEntity<String> deleteBucket(@PathVariable String bucketName) {
        bucketService.deleteBucket(bucketName);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }

}
