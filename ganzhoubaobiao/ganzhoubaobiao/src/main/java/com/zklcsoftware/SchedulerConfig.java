package com.zklcsoftware;

import java.io.IOException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * Created by david on 2015-01-20.
 */
@Configuration
@EnableScheduling
public class SchedulerConfig {

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() throws IOException {
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        return factory;
    }
}
