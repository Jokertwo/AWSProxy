package com.aveco.awsproxy.shared.io.response.bucket;

import com.aveco.awsproxy.shared.io.response.GeneralResponse;

import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = true)
@Data
public class BucketExistResponse extends GeneralResponse {

    private boolean bucketExist;
}
