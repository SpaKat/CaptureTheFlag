package Server.Message;

import java.io.Serializable;
import java.net.InetAddress;

public class SetupConnection implements Serializable {

	private long id;
	private int teams;
	private InetAddress inetAddress;
	int port;
	public SetupConnection(long id, int numOfteams, InetAddress ia, int i) {
		this.id = id;
		teams = numOfteams;
		inetAddress = ia;
		port = i;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 972622375588748345L;

	
	public long getId() {
		return id;
	}
	public int getTeams() {
		return teams;
	}
	public InetAddress getInetAddress() {
		return inetAddress;
	}
	public int getPort() {
		return port;
	}
}
