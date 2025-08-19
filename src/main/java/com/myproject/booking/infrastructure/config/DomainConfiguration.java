package com.myproject.booking.infrastructure.config;

import com.myproject.booking.application.ddd.DomainService;
import com.myproject.booking.application.ddd.Stub;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        basePackages = "com.myproject.booking.application",
        includeFilters =  { @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {DomainService.class})},
        excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {Stub.class})}
)
public class DomainConfiguration {
}
