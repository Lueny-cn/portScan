package com.lwy.model;

import java.net.*;

/*端口类
 * 其结构
 * ip地址
 * 开始端口
 * 结束端口
 */
public class Port {
    @SuppressWarnings("unused")
    private Socket socket;
    private int startPort = 0, endPort = 65525;

    public Port() {
    }

    public void setStartPort(int Startport) {
        this.startPort = Startport;
    }

    public int getStartPort() {
        return startPort;
    }

    public void setEndPort(int endPort) {
        this.endPort = endPort;
    }

    public int getEndPort() {
        return endPort;
    }
}
