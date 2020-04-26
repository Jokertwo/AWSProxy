package com.aveco.awsproxy.services.impl;

import java.io.File;
import java.util.List;

import org.springframework.stereotype.Service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.Upload;
import com.aveco.awsproxy.services.S3ObjectService;


@Service
public class S3ObjectServiceImpl implements S3ObjectService {

    private final AmazonS3 amazonS3;
    private final TransferManager transferManager;

    public S3ObjectServiceImpl(AmazonS3 amazonS3, TransferManager transferManager) {
        this.amazonS3 = amazonS3;
        this.transferManager = transferManager;
    }


    @Override
    public PutObjectResult uploadFile(String bucketName, String uploadPath, String pathToFile) {
        File file = new File(pathToFile);
        PutObjectRequest request = new PutObjectRequest(bucketName, uploadPath, file);
        request.withBucketName(bucketName).withKey(uploadPath).withFile(file)
            .setGeneralProgressListener(progressEvent -> {
                System.out.println("----------------");
                System.out.println("Transfered bytes: " + progressEvent.getBytesTransferred());
                System.out.println("Event type: " + progressEvent.getEventType());
                System.out.println(progressEvent.toString());
            });
        Upload upload = transferManager.upload(request);
        try {
            upload.waitForUploadResult();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public List<S3ObjectSummary> listObjects(String bucketName, String prefix) {
        ObjectListing current = amazonS3.listObjects(bucketName, prefix);
        List<S3ObjectSummary> objects = current.getObjectSummaries();
        while (current.isTruncated()) {
            current = amazonS3.listNextBatchOfObjects(current);
            objects.addAll(current.getObjectSummaries());
        }
        return objects;
    }

}
