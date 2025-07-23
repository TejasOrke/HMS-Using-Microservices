package com.hms.pharmacy.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Configuration;
///  what is interceptor
/// it is used to intercept requests and responses in a feign client
/// it is used to modify and mutate the requests and responses in a feign client
@Configuration
public class FeignClientInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.header("X-Secret-Key", "SECRET");
    }
}
