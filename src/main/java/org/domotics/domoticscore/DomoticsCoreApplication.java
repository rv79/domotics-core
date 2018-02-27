package org.domotics.domoticscore;

import org.domotics.domoticscore.niko.service.NikoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DomoticsCoreApplication {

	private static final Logger logger = LoggerFactory.getLogger(DomoticsCoreApplication.class);

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(DomoticsCoreApplication.class, args);

		// check if init done
		if (context.getBean(NikoService.class).check()) {
			logger.info("Niko discovered, initializing...");
			context.getBean(NikoService.class).init();

		} else {
			logger.info("Niko not found, closing application...");
			context.close();
		}

		logger.info("end main");
	}

}
