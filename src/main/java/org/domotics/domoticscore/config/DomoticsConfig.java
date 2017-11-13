package org.domotics.domoticscore.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@ComponentScan("org.domotics")
@EnableAsync
public class DomoticsConfig {

	private final Logger logger = LoggerFactory.getLogger(DomoticsConfig.class);

	@Bean
	public Executor asyncExecutor() {
		logger.info("create executor");
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(10);
		executor.setMaxPoolSize(10);
		executor.setQueueCapacity(500);
		executor.setThreadNamePrefix("Domotics-");
		executor.initialize();
		return executor;
	}
}
