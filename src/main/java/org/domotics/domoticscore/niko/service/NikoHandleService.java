package org.domotics.domoticscore.niko.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class NikoHandleService {

	private final Logger logger = LoggerFactory.getLogger(NikoHandleService.class);

	public NikoHandleService() {
	}

	@Async
	public void handleEvent (String data) {
		logger.info("Handling an event...");
		logger.info("Data= {}", data);
	}
}
