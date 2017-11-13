package org.domotics.domoticscore.niko.service;

import org.domotics.domoticscore.niko.component.NikoStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class NikoCommandService {

	private final Logger logger = LoggerFactory.getLogger(NikoCommandService.class);

	@Autowired
	private NikoStream stream;

	public NikoCommandService() {
	}

	public void startEvent () {
		String cmd = "{\"cmd\":\"startevents\"}";

		sendData(cmd);
	}

	private void sendData (String cmd) {
		logger.info("Sending data: {}", cmd);
		try {
			stream.getOut().write(cmd.getBytes());
		} catch (IOException e) {
			logger.info("Error: {}", e);
			e.printStackTrace();
		}
	}
}
