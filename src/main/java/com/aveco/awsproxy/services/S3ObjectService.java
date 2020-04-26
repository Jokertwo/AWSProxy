package com.aveco.awsproxy.services;

import java.io.File;
import java.util.List;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.S3ObjectSummary;


public interface S3ObjectService {

    /**
     * Upload file to S3 aws. {@link AmazonS3#putObject(String, String, File)}
     * 
     * @param bucketName
     *            bucket name where will be object stored
     * @param uploadPath
     *            path inside of bucket
     * @param pathToFile
     *            path to file which should be uploaded
     * @return return info about upload file
     */
    boolean uploadFile(String bucketName, String uploadPath, String pathToFile);


    /**
     * List all objects in selected bucket
     * 
     * @param bucketName
     *            bucket which content will be listed
     * @param prefix
     *            prefix for searching
     * @return return content of bucket
     */
    List<S3ObjectSummary> listObjects(String bucketName, String prefix);
}
