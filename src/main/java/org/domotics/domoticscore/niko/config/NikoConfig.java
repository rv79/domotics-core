package org.domotics.domoticscore.niko.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.*;
import java.util.Enumeration;

@Component
public class NikoConfig {

	private final Logger logger = LoggerFactory.getLogger(NikoConfig.class);

	private static final int BROADCAST_PORT = 10000;
	private static final String BROADCAST_DEFAULT = "255.255.255.255";
	private static final int BROADCAST_TIMEOUT = 2000;
	private static final String BROADCAST_TEXT = "D";

	private String ip;
	private int port;

	public NikoConfig() {
		this.ip = discover();
		this.port = 8000;
	}

	private String discover() {

		try {

			DatagramSocket socket = new DatagramSocket();
			socket.setBroadcast(true);


			byte[] sendData = BROADCAST_TEXT.getBytes();

			//Try the 255.255.255.255 first
			try {
				DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, InetAddress.getByName(BROADCAST_DEFAULT), BROADCAST_PORT);
				socket.send(sendPacket);
				logger.info("Sending broadcast to: {} (DEFAULT)", BROADCAST_DEFAULT);
			} catch (Exception e) {
				logger.error("Error: {}", e);
			}

			// Broadcast the message over all the network interfaces and wait for an answer
			Enumeration interfaces = NetworkInterface.getNetworkInterfaces();

			while (interfaces.hasMoreElements()) {

				NetworkInterface networkInterface = (NetworkInterface) interfaces.nextElement();

				if (networkInterface.isLoopback() || !networkInterface.isUp()) {
					continue; // Don't want to broadcast to the loopback interface
				}

				for (InterfaceAddress interfaceAddress : networkInterface.getInterfaceAddresses()) {
					InetAddress broadcast = interfaceAddress.getBroadcast();
					if (broadcast == null) {
						// no broadcast available for this Interface, skipping
						continue;
					}

					// Send the broadcast package!
					try {
						logger.info("Sending broadcast to: " + broadcast.getHostAddress() + "; Interface: " + networkInterface.getDisplayName());
						DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, broadcast, BROADCAST_PORT);
						socket.send(sendPacket);
					} catch (Exception e) {
						logger.error("Error: {}", e);
					}
				}
			}

			logger.info("Waiting for an answer...");

			while (true) {
				try {
					byte[] recvBuf = new byte[15000];
					DatagramPacket packet = new DatagramPacket(recvBuf, recvBuf.length);
					socket.setSoTimeout(BROADCAST_TIMEOUT);
					socket.receive(packet);
					logger.debug("Packet received: {}", packet.getData().toString());

					//check if answer received from Niko -- data should start with "D"
					if (packet.getData().toString().substring(0,1).equals("D")) {
						//niko found
						//Retrieving IP
						String ip = packet.getAddress().getHostAddress();
						logger.info("Niko IP found: {}",ip);
						return ip;

					} else {
						//wait for another packet
						continue;
					}
				} catch (SocketTimeoutException e) {
					logger.error("Timeout - Niko not discovered");
					return null;
				}
			}

		} catch (IOException e) {
			//logger.error("Error: {}", e);
			e.printStackTrace();
		}

		return null;

	}


	public String getIp() {
		return ip;
	}

	public int getPort() {
		return port;
	}
}
