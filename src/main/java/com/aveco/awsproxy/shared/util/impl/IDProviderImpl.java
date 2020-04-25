package com.aveco.awsproxy.shared.util.impl;

import java.util.UUID;

import com.aveco.awsproxy.shared.util.IDProvider;


public class IDProviderImpl implements IDProvider {

    @Override
    public long createID() {
        return UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
    }
}
