package org.domotics.domoticscore.niko.service;

import org.domotics.domoticscore.niko.component.NikoStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;


@Service
public class NikoListenService {

	private final Logger logger = LoggerFactory.getLogger(NikoListenService.class);

	@Autowired
	NikoHandleService handleService;

	@Autowired
	NikoStream stream;

	public NikoListenService() {
		logger.info("Constructor NikoListener");
	}

	@Async
	public void listen() {
		logger.info("Start listening...[{}]", Thread.currentThread().getName());

		String data;

		while (true) {
			try {
				data = stream.getIn().readLine();
				handleService.handleEvent(data);
			} catch (IOException e) {
				logger.info("Error: {}", e);
				e.printStackTrace();
			}
		}

	}

}
