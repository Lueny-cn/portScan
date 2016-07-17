package com.lwy.ip;

import java.net.*;

public class ip {
	private Socket socket;
	private String startIp;
	private String endIp;

	public ip() {
		startIp = "127.0.0.1";
		endIp = "127.0.0.100";
	}

	public ip(String startIp, String endIp) {
		this.startIp = startIp;
		this.endIp = endIp;
	}

	public ip(String startIp) {
		this.startIp = startIp;
	}

	public synchronized void plusIP() {
		String[] ipcodes = startIp.split("[.]");
		int temp = Integer.parseInt(ipcodes[3]);
		temp++;
		startIp = ipcodes[0] + "." + ipcodes[1] + "." + ipcodes[2] + "." + temp;
		System.out.println(startIp);
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public String getStartIp() {
		return startIp;
	}

	public void setStartIp(String startIp) {
		this.startIp = startIp;
	}

	public String getEndIp() {
		return endIp;
	}

	public void setEndIp(String endIp) {
		this.endIp = endIp;
	}
}
