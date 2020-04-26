package com.aveco.awsproxy.shared.io.response.bucket;

import java.util.List;

import com.aveco.awsproxy.shared.io.response.GeneralResponse;

import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = true)
@Data
public class BucketListResponse extends GeneralResponse {

    private List<BucketResponse> buckets;
}
