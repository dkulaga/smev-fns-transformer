package com.skat.smev.fns.config;

import com.skat.smev.fns.transmitter.impl.Smev3AdapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class SMEV3AdapterServiceConfig {

    @Value("${adapter.service.url:}")
    private String url;

    @Autowired
    private RestTemplate restTmpl;

    @Bean(name = "smev3AdapterService")
    public Smev3AdapterService getSmev3AdapterService() {
        String adapterUrl = (url != null) ? url.trim() : "";
        return new Smev3AdapterService(restTmpl, adapterUrl);
    }
}
