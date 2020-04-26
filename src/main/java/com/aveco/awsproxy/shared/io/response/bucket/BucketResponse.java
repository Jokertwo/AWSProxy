package com.aveco.awsproxy.shared.io.response.bucket;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BucketResponse {

    /** The name of this S3 bucket */
    private String name = null;

    /** The date this bucket was created */
    private Date creationDate = null;
}
