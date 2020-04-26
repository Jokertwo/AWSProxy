package com.aveco.awsproxy.configuration;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.TransferManagerBuilder;
import com.aveco.awsproxy.shared.util.IDProvider;
import com.aveco.awsproxy.shared.util.TimestampProvider;
import com.aveco.awsproxy.shared.util.Utils;
import com.aveco.awsproxy.shared.util.impl.IDProviderImpl;
import com.aveco.awsproxy.shared.util.impl.TimestampProviderImpl;

import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
public class AppConfiguration {

    @Bean
    public IDProvider getIDProvider() {
        return new IDProviderImpl();
    }


    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public AmazonS3 amazonS3(AWSConfiguration awsConfiguration) {

        AWSCredentials credentials = new BasicAWSCredentials(
            awsConfiguration.getAccessKey(),
            awsConfiguration.getSecretKey());

        return AmazonS3ClientBuilder
            .standard()
            .withCredentials(new AWSStaticCredentialsProvider(credentials))
            .withRegion(Regions.EU_CENTRAL_1)
            .build();
    }


    @Bean
    public ModelMapper modelMapperBean() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper;
    }


    @Bean
    public TransferManager transferManager(AmazonS3 amazonS3) {
        return TransferManagerBuilder.standard().withS3Client(amazonS3).build();
    }


    @Bean
    public TimestampProviderImpl timestampProvider() {
        return new TimestampProviderImpl();
    }


    @Bean
    public Utils responseUtil(IDProvider idProvider, TimestampProvider timestampProvider) {
        return new Utils(idProvider, timestampProvider);
    }
}
