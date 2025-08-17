package com.myproject.booking.infrastructure.config;

import com.myproject.booking.application.ddd.DomainService;
import com.myproject.booking.application.ddd.Stub;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(includeFilters =  { @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {DomainService.class, Stub.class})})
public class DomainConfiguration {
}
