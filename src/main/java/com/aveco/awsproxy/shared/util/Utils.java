package com.aveco.awsproxy.shared.util;

import org.springframework.stereotype.Component;

import com.aveco.awsproxy.shared.io.response.GeneralResponse;


@Component
public class Utils {

    private final IDProvider idProvider;
    private final TimestampProvider timestampProvider;

    public Utils(IDProvider idProvider, TimestampProvider timestampProvider) {
        this.idProvider = idProvider;
        this.timestampProvider = timestampProvider;
    }


    public <Response extends GeneralResponse> Response setIDAndTimestamp(Response generalResponse) {
        generalResponse.setResponseId(idProvider.createID());
        generalResponse.setTimestamp(timestampProvider.createTimestamp());
        return generalResponse;
    }
}
