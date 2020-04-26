package com.aveco.awsproxy.shared.io.response.object;

import com.amazonaws.services.s3.model.ObjectListing;
import com.aveco.awsproxy.shared.io.response.GeneralResponse;

import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = true)
@Data
public class ObjectResponse extends GeneralResponse {

    ObjectListing objects;
}
