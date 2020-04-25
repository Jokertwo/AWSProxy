package com.aveco.awsproxy.shared.util.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.aveco.awsproxy.shared.util.IDProvider;
import com.aveco.awsproxy.shared.util.impl.IDProviderImpl;


@Configuration
public class AppConfiguration {

    @Bean
    public IDProvider getIDProvider() {
        return new IDProviderImpl();
    }
}
