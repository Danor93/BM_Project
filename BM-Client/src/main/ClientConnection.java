package main;

import ocsf.server.ConnectionToClient;

/**
 * A function that connects the client to the server and 
 * through it you can get or set the: hostName, ipAddress and status.
 *
 */
public class ClientConnection {
	private String hostName;
	private String ipAddress;
	private String status;

	public ClientConnection(ConnectionToClient client) {
		hostName = client.getInetAddress().getHostName();
		ipAddress = client.getInetAddress().getHostAddress();
		status = client.isAlive() == true ? "Connected" : "Disconnected";
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String statuString) {
		this.status = statuString;
	}

	public String toString() {
		return String.format("%s %s %s", hostName, ipAddress, status);
	}
}

