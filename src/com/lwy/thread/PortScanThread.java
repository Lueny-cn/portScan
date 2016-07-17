package com.lwy.thread;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import com.lwy.ip.ip;
import com.lwy.model.Port;
import com.lwy.panel.PortScanPanel;

public class PortScanThread extends Thread {
	private static ArrayList<Thread> runningThreads = new ArrayList<Thread>();
	public static InetAddress hostAddress;
	// 线程总数
	private int threadnum;
	// ip地址
	public static ip ip = new ip();
	// 端口
	public static Port port = new Port();
	// 的ip地址前3位
	public static String ip123;
	// 端口的类别
	String portType = "0";

	/*
	 * 构造函数
	 */
	public PortScanThread(String name, int threadnum) {
		super(name);
		this.threadnum = threadnum;
	}

	/*
	 * 运行函数
	 */
	public void run() {
		regist(this);// 线程开始时注册
		// 端口号
		int i = 0;
		Socket socket;
		// 多IP扫描
		if (PortScanPanel.choise == 0) {
		   
			for (i = port.getStartPort() + threadnum; i < port.getEndPort(); i += Integer
					.parseInt(PortScanPanel.maxThread.getText())) {
				try {
					socket = new Socket(PortScanPanel.ip.getStartIp(), i);
					 System.out.println(PortScanPanel.ip.getStartIp());
					if (socket.isConnected()) {
						switch (i) {
						case 20:
							portType = "(FTP Data)";
							break;
						case 21:
							portType = "(FTP)";
							break;
						case 23:
							portType = "(TELNET)";
							break;
						case 25:
							portType = "(SMTP)";
							break;
						case 53:
							portType = "(DNS)";
							break;
						case 80:
							portType = "(HTTP)";
							break;
						case 110:
							portType = "(POP)";
							break;
						case 139:
							portType = "(netBIOS)";
							break;
						case 1433:
							portType = "(SQL Server)";
							break;
						case 3389:
							portType = "(Terminal Service)";
							break;
						case 443:
							portType = "(HTTPS)";
							break;
						case 1521:
							portType = "(Oracle)";
							break;
						}

						// 端口没有特定类别
						if (portType.equals("0")) {
							PortScanPanel.result1.append(PortScanPanel.ip
									.getStartIp() + " ：" + i + "\n");
						} else {
							PortScanPanel.result1.append(PortScanPanel.ip
									.getStartIp() + " ：" + i);
							PortScanPanel.result1.append(":默认程序" + portType
									+ "\n");
						}
					}
				} catch (UnknownHostException e) {
					e.printStackTrace();
				} catch (IOException e) {
					PortScanPanel.result2.append(PortScanPanel.ip.getStartIp()
							+ " ：" + i + "\n");
				}
			}
		}
		// 单IP扫描
		if (PortScanPanel.choise == 1) {
			for (i = port.getStartPort() + threadnum; i < port.getEndPort(); i += Integer
					.parseInt(PortScanPanel.maxThread.getText())) {
				try {
					socket = new Socket(PortScanPanel.ip.getStartIp(), i);
					if (socket.isConnected()) {
						switch (i) {
						case 20:
							portType = "(FTP Data)";
							break;
						case 21:
							portType = "(FTP)";
							break;
						case 23:
							portType = "(TELNET)";
							break;
						case 25:
							portType = "(SMTP)";
							break;
						case 53:
							portType = "(DNS)";
							break;
						case 80:
							portType = "(HTTP)";
							break;
						case 110:
							portType = "(POP)";
							break;
						case 139:
							portType = "(netBIOS)";
							break;
						case 1433:
							portType = "(SQL Server)";
							break;
						case 3389:
							portType = "(Terminal Service)";
							break;
						case 443:
							portType = "(HTTPS)";
							break;
						case 1521:
							portType = "(Oracle)";
							break;
						}

						// 端口没有特定类别
						if (portType.equals("0")) {
							PortScanPanel.result1.append("端口：" + i + "\n");
						} else {
							PortScanPanel.result1.append("端口：" + i);
							PortScanPanel.result1.append(":默认程序" + portType
									+ "\n");
						}
					}
				} catch (UnknownHostException e) {
					e.printStackTrace();
				} catch (IOException e) {
					PortScanPanel.result2.append("端口：" + i + "\n");
				}

			}
		}
		PortScanPanel.Threadnumber = PortScanPanel.Threadnumber - 1;
		// 扫描完成后，显示扫描完成，并将【scanStart】按钮设置为可用
		// System.out.println(runningThreads.size());
		if (runningThreads.size() <= 1) {
			PortScanPanel.label5.setText("扫描完成!");
			// 将【scanStart】按钮设置成为可用
			if (!PortScanPanel.scanStart.isEnabled()) {
				PortScanPanel.scanStart.setEnabled(true);
			}
		}
		unRegist(this);// 线程结束时取消注册
	}

	// 获得每个IP中十进制的第几位
	public int getIpSub(String s, int i) {
		int temp = 0;
		String[] ipcodes = s.split("[.]");
		try {
			temp = Integer.parseInt(ipcodes[i - 1]);
		} catch (NumberFormatException e) {
			PortScanPanel.ErrorLabel.setText("错误的ip!");
			PortScanPanel.Error.setVisible(true);
			return -1;
		}
		return temp;
	}

	// 注册线程
	public void regist(Thread t) {
		synchronized (runningThreads) {
			runningThreads.add(t);
		}
	}

	// 注销线程
	public void unRegist(Thread t) {
		synchronized (runningThreads) {
			runningThreads.remove(t);
		}
	}

	// 运行线程的数量判定
	public static boolean hasThreadRunning() {
		return (runningThreads.size() > 0);// 通过判断runningThreads是否为空就能知道是否还有线程未执行完
	}

}