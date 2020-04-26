package com.aveco.awsproxy;

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
import com.aveco.awsproxy.shared.util.IDProvider;
import com.aveco.awsproxy.shared.util.impl.IDProviderImpl;
import com.aveco.awsproxy.shared.util.impl.TimestampProviderImpl;


@Configuration
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
    public TimestampProviderImpl timestampProvider() {
        return new TimestampProviderImpl();
    }
}
