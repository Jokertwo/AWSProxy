package com.aveco.awsproxy.services;

import java.io.File;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.PutObjectResult;


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
    PutObjectResult uploadFile(String bucketName, String uploadPath, String pathToFile);


    /**
     * List all objects in selected bucket
     * 
     * @param bucketName
     *            bucket which content will be listed
     * @return return content of bucket
     */
    ObjectListing listObjects(String bucketName);
}
