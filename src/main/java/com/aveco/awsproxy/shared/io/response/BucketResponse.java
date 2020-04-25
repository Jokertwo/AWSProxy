package com.aveco.awsproxy.shared.io.response;

import java.util.List;

import com.amazonaws.services.s3.model.Bucket;

import lombok.Data;


@Data
public class BucketResponse {

    private long responseID;
    private boolean bucketExist;
    private List<Bucket> buckets;
}
