package org.domotics.domoticscore.niko.service;

import org.domotics.domoticscore.niko.component.NikoStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class NikoCommandService {

	private final Logger logger = LoggerFactory.getLogger(NikoCommandService.class);

	@Autowired
	private NikoStream stream;

	public NikoCommandService() {
	}

	public void startEvent () {
		String cmd = "{\"cmd\":\"startevents\"}";

		stream.write(cmd);
	}

}
