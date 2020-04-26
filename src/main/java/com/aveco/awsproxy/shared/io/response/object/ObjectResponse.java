package com.aveco.awsproxy.shared.io.response.object;

import java.util.List;

import com.amazonaws.services.s3.model.S3Object;
import com.aveco.awsproxy.shared.io.response.GeneralResponse;

import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = true)
@Data
public class ObjectResponse extends GeneralResponse {

    List<S3Object> objects;
}
