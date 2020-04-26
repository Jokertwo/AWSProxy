package com.aveco.awsproxy.shared.util.impl;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

import com.aveco.awsproxy.shared.util.TimestampProvider;


public class TimestampProviderImpl implements TimestampProvider {

    private final DateTimeFormatter formatter;

    public TimestampProviderImpl() {
        formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)
            .withLocale(Locale.ENGLISH)
            .withZone(ZoneId.of("UTC"));
    }


    @Override
    public String createTimestamp() {
        Instant instant = Instant.now();
        return formatter.format(instant);
    }
}
