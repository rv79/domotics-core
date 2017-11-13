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

	private String ip;
	private int port;

	public NikoConfig() {
		this.ip = discover();
		this.port = 8000;
	}

	private String discover() {

		//DatagramSocket socket;

		try {

			DatagramSocket socket = new DatagramSocket();
			socket.setBroadcast(true);

			byte[] sendData = "D".getBytes();

			//Try the 255.255.255.255 first


			try {
				DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, InetAddress.getByName("255.255.255.255"), 10000);
				socket.send(sendPacket);
				logger.info(">>> Request packet sent to: 255.255.255.255 (DEFAULT)");
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
						continue;
					}

					// Send the broadcast package!
					try {
						DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, broadcast, 10000);
						socket.send(sendPacket);
					} catch (Exception e) {
						logger.error("Error: {}", e);
					}

					logger.info(">>> Request packet sent to: " + broadcast.getHostAddress() + "; Interface: " + networkInterface.getDisplayName());
				}
			}


			logger.info("Start receiving broadcast...");

			//Receive a packet
			byte[] recvBuf = new byte[15000];
			DatagramPacket packet = new DatagramPacket(recvBuf, recvBuf.length);
			try {
				socket.setSoTimeout(1000);
				socket.receive(packet);
			} catch (SocketTimeoutException e) {
				logger.error("Timeout - Niko not discovered");
				return null;
			}

			//Packet received
			logger.info(">>> Discovery packet received from: " + packet.getAddress().getHostAddress());
			logger.info(">>> Packet received; data: " + new String(packet.getData()));

			//ip found
			return packet.getAddress().getHostAddress();
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
