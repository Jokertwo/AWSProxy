package com.aveco.awsproxy.shared.io.request;

import javax.validation.constraints.NotBlank;

import lombok.Data;


@Data
public class UploadFileRequest {

    @NotBlank
    private String bucketName;
    @NotBlank
    private String objectPath;
    @NotBlank
    private String pathToFile;
}
