package com.aveco.awsproxy.shared.io.response.bucket;

import java.util.List;

import com.amazonaws.services.s3.model.Bucket;
import com.aveco.awsproxy.shared.io.response.GeneralResponse;

import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = true)
@Data
public class BucketResponse extends GeneralResponse {

    private List<Bucket> buckets;
}
