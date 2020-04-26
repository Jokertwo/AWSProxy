package com.aveco.awsproxy.shared.io.response.object;

import com.aveco.awsproxy.shared.io.response.GeneralResponse;

import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = true)
@Data
public class UploadResponse extends GeneralResponse {
    boolean uploadSuccessful;
}
