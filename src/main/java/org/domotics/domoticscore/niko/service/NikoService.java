package org.domotics.domoticscore.niko.service;

import org.domotics.domoticscore.niko.component.NikoStream;
import org.domotics.domoticscore.niko.config.NikoConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NikoService {

	@Autowired
	NikoCommandService commandService;

	@Autowired
	NikoListenService listenService;

	@Autowired
	NikoConfig config;

	@Autowired
	NikoStream stream;

	public NikoService() {
	}

	public void init () {
		// init listen service
		listenService.listen();

		// start event
		commandService.startEvent();
	}

	public boolean check () {
		return stream.isConnected();
	}
}
