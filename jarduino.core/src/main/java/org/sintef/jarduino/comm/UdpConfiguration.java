package org.sintef.jarduino.comm;

import org.sintef.jarduino.ProtocolConfiguration;

public class UdpConfiguration extends ProtocolConfiguration {

	private int port;
	
	public UdpConfiguration(int port) {
		super();
		this.port = port;
	}

	public Integer getPort() {
		return port;
	}

}
