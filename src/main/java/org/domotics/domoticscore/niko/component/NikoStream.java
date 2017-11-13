package org.domotics.domoticscore.niko.component;

import org.domotics.domoticscore.niko.config.NikoConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

@Component
public class NikoStream {

	private final Logger logger = LoggerFactory.getLogger(NikoStream.class);

	@Autowired
	private NikoConfig config;

	private OutputStream out;
	private BufferedReader in;

	private boolean connected;

	public NikoStream() {
		connected = false;
	}

	@PostConstruct
	public void init () {

		if (config.getIp() != null) {

			Socket socket;

			try {
				socket = new Socket(config.getIp(), config.getPort());
				out = socket.getOutputStream();
				in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

				connected = true;
			} catch (IOException e) {
				logger.info("Error: {}", e);
				e.printStackTrace();
			}
		} else {
			logger.info("Niko not found");
		}
	}

	public void write (String cmd) {
		logger.debug("Sending data: {}", cmd);
		try {
			out.write(cmd.getBytes());
		} catch (IOException e) {
			logger.info("Error: {}", e);
			e.printStackTrace();
		}
	}

	public String read () {
		try {
			String data = in.readLine();
			logger.debug("Receiving data: {}", data);
			return data;
		} catch (IOException e) {
			logger.info("Error: {}", e);
			e.printStackTrace();
			return null;
		}
	}

	public OutputStream getOut() {
		return out;
	}

	public BufferedReader getIn() {
		return in;
	}

	public boolean isConnected() {
		return connected;
	}
}
