package Server.Message;

import java.io.Serializable;

public class InternetAddress implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -629297716438120486L;
	private String ip;
	private int port;

	public InternetAddress(String ip, int port) {
		this.ip =ip;
		this.port = port;
	}

	public String getIp() {
		return ip;
	}
	public int getPort() {
		return port;
	}

}
