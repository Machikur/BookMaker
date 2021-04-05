package com.app.client.urlbuilder;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ClientUrl {

    @Value("${client.url}")
    protected String clientUrl;
}
