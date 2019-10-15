package com.zklcsoftware;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import com.zklcsoftware.basic.repository.SqlMap;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author audin
 *
 */
@SpringBootApplication
@Slf4j
@ServletComponentScan 
@EnableConfigurationProperties(SqlMap.class)
public class StartApplication extends SpringBootServletInitializer {
	
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		log.debug("configure(SpringApplicationBuilder) - start"); //$NON-NLS-1$

		SpringApplicationBuilder sab = application.sources(StartApplication.class);
		
		log.debug("configure(SpringApplicationBuilder) - end"); //$NON-NLS-1$
        return sab;
    }

	public static void main(String[] args) throws Exception {
		log.debug("main(String[]) - start"); //$NON-NLS-1$

		SpringApplication.run(StartApplication.class, args);
		
		log.debug("main(String[]) - end"); //$NON-NLS-1$
	}

	@Bean
	protected ServletContextListener listener() {
		return new ServletContextListener() {
			@Override
			public void contextInitialized(ServletContextEvent sce) {
				log.info("ServletContext initialized");
			}

			@Override
			public void contextDestroyed(ServletContextEvent sce) {
				log.info("ServletContext destroyed");
			}
		};
	}
}
