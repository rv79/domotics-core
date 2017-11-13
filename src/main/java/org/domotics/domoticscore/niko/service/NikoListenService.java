package org.domotics.domoticscore.niko.service;

import org.domotics.domoticscore.niko.component.NikoStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


@Service
public class NikoListenService {

	private final Logger logger = LoggerFactory.getLogger(NikoListenService.class);

	@Autowired
	NikoHandleService handleService;

	@Autowired
	NikoStream stream;

	public NikoListenService() {
	}

	@Async
	public void listen() {
		logger.info("Start Niko listener...");

		String data;

		while (true) {
			data = stream.read();
			if (data != null)
				handleService.handleEvent(data);
			else
				break;
		}

		logger.info("Stopping Niko listener");

	}

}
