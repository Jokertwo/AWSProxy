package com.aveco.awsproxy.shared.util;

public interface TimestampProvider {

    /**
     * Create new timestamp from current time
     * 
     * @return return created time stamp in string format
     */
    String createTimestamp();
}
