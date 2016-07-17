package com.lwy.panel;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import com.lwy.ip.ip;
import com.lwy.model.Port;
import com.lwy.thread.PortScanThread;

@SuppressWarnings("serial")
public class PortScanPanel extends JPanel{
	
	//开始ip输入
	public static JTextField startIpFieldForPortScan = new JTextField("127.0.0.1",9);
	JLabel label3 = new JLabel(" ~ ");
	//结束ip输入
	public static JTextField endIpFieldForPortScan = new JTextField("127.0.0.10",9);
	//显示扫描结果
	public static JTextArea result1 = new JTextArea("目标主机开放的端口:\n");//显示端口扫描结果，此处显示开放的端口
	public static JTextArea result2 = new JTextArea("目标主机未开放的端口:\n");//显示端口扫描结果，此处显示未开放的端口
	JPanel portPanelNorth = new JPanel();
	JPanel portPanelCenter = new JPanel();
	JPanel portPanelSouth = new JPanel();
	
	//输入主机名文本框
	public static JTextField hostname = new JTextField("127.0.0.1",8);
	
	//ip地址类
	public static ip ip = new ip();
	//端口类
	public static Port port = new Port();
	
	//查询方式：0为ip；1为主机名
	public static int choise;
	
	//输入最小端口的输入框
	public static JTextField startPort = new JTextField("0",4);
	//输入最大端口的输入框
	public static JTextField endPort = new JTextField("500",4);
	
	//错误提示框
	public static JDialog Error = new JDialog();
	public static JLabel ErrorLabel = new JLabel("");
	
	//扫描类型
    public static JRadioButton radioHost = new JRadioButton("IP地址：",true);
	public static JRadioButton radioIp = new JRadioButton("IP地址 范围：");
	//单选框组
	public static ButtonGroup group = new ButtonGroup();

	public static JLabel jLabel1 = new JLabel("端口范围：");
	public static JLabel jLabel2 = new JLabel("~");
	public static JLabel thread = new JLabel("线程数  ");
	
	//输入最大线程数量的输入框
	public static JTextField maxThread = new JTextField("10",3);
	public static int Threadnumber = 0;
	
	//定义按钮
	public static JButton 确定 = new JButton("确定");
	public static JButton scanStart = new JButton(" Go  ");
	public static JButton resultSave = new JButton("Save");
	
	
	private JLabel label4 = new JLabel("程序运行状态：");
	public static JLabel label5 = new JLabel("程序初始状态");
	
	public PortScanPanel()
	{
		//布局
		portPanelNorth.setLayout(new GridBagLayout());
		portPanelCenter.setLayout(new GridLayout(0,2));
		this.setLayout(new BorderLayout());
		this.add(portPanelNorth,BorderLayout.NORTH);
		this.add(portPanelCenter,BorderLayout.CENTER);
		this.add(portPanelSouth,BorderLayout.SOUTH);
		
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(5,0,5,0);//组件与其显示区域边缘之间间距的最小量
		//采用GridBagConstraints的布局方式
		
		
		group.add(radioIp);
		group.add(radioHost);
		
		//【IP地址范围】标签布局
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.WEST;
		portPanelNorth.add(radioIp,c); 
		
		c.gridx = 1;
		c.gridy = 2;
		c.gridwidth = 2;
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.WEST;
		portPanelNorth.add(startIpFieldForPortScan,c);
		
		c.gridx = 4;
		c.gridy = 2;
		c.gridwidth = 2;
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.WEST;
		portPanelNorth.add(label3,c);
		
		c.gridx = 6;
		c.gridy = 2;
		c.gridwidth = 2;
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.WEST;
		portPanelNorth.add(endIpFieldForPortScan,c);
		
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.WEST;
		portPanelNorth.add(radioHost,c);
		
		c.gridx = 1;
		c.gridy = 1;
		c.gridwidth = 3;
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.WEST;
		portPanelNorth.add(hostname,c);
		
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.CENTER;
		portPanelNorth.add(jLabel1,c);
		
		
		c.gridx = 1;
		c.gridy = 3;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.WEST;
		portPanelNorth.add(startPort,c);

		c.gridx = 2;
		c.gridy = 3;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.CENTER;
		portPanelNorth.add(jLabel2,c);

		c.gridx = 3;
		c.gridy = 3;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.WEST;
		portPanelNorth.add(endPort,c);
		
		c.gridx = 6;
		c.gridy = 3;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.WEST;
		portPanelNorth.add(thread,c);
		
		c.gridx = 7;
		c.gridy = 3;
		c.gridwidth = 2;
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.WEST;
		portPanelNorth.add(maxThread,c);
		
		c.gridx = 0;
		c.gridy = 4;
		c.gridwidth = 2;
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.EAST;
		scanStart.setSize(2, 1);
		portPanelNorth.add(scanStart,c);
		
		c.gridx = 4;
		c.gridy = 4;
		c.gridwidth = 2;
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.EAST;
		portPanelNorth.add(resultSave,c);
		
		//设置文本区域不可编辑
		result1.setEditable(false);
		result2.setEditable(false);
		JScrollPane jsp1 = new JScrollPane(result1);//使用的port面板
		JScrollPane jsp2 = new JScrollPane(result2);//未使用的port面板
		
		portPanelCenter.add(jsp1);//添加到地下的面板中
		portPanelCenter.add(jsp2);//添加到地下的面板中
		
		portPanelSouth.add(label4);
		portPanelSouth.add(label5);
		
		Error.setSize(250, 100);
		Error.setResizable(false);
		Error.setLocationRelativeTo(null); //设置对话框相对屏幕居中  
		Container dPanel = Error.getContentPane();
		dPanel.setLayout(null);//自定义布局
		ErrorLabel.setBounds(20,-30,250, 100);//按钮的位置大小
		dPanel.add(ErrorLabel);
		确定.setBounds(100, 40, 70, 25);//按钮的位置大小
		dPanel.add(确定);

		//添加监听
		scanStart.addActionListener(new ScanAction());
		确定.addActionListener(new OKAction());
		radioIp.addActionListener(new radioIpAction());
		radioHost.addActionListener(new radioHostAction());
		
		resultSave.addActionListener(new java.awt.event.ActionListener(){ 
		public void actionPerformed(java.awt.event.ActionEvent e){    
			JFileChooser fc=new JFileChooser();
			int returnVal = fc.showSaveDialog(null);
			
			//点击“保存”
			if(returnVal == 0){
				File saveFile=fc.getSelectedFile();
				try {
					FileWriter writeOut = new FileWriter(saveFile);
					writeOut.write(PortScanPanel.result1.getText());
					writeOut.write(PortScanPanel.result2.getText());
					writeOut.close();
				}
				catch (IOException ex) {
						System.out.println("保存失败");
						ex.printStackTrace();
				}
			}
			//点击“取消”
			else
				return;
		}
		});
	}
}

/*
 *实现“scanStart”功能
 *完成扫描
 */
class ScanAction implements ActionListener{
	public void actionPerformed (ActionEvent a){

		int a1,a2,a3;
		String ipaddress = "";

		//初始化ip地址和Port端口
		PortScanPanel.result1.setText("目标主机开放的端口:\n");
		PortScanPanel.result2.setText("目标主机未开放的端口:\n");
		PortScanPanel.ip.setStartIp(PortScanPanel.startIpFieldForPortScan.getText());
		PortScanPanel.ip.setEndIp(PortScanPanel.endIpFieldForPortScan.getText());
		PortScanPanel.port.setStartPort(Integer.parseInt(PortScanPanel.startPort.getText()));
		PortScanPanel.port.setEndPort(Integer.parseInt(PortScanPanel.endPort.getText()));
		PortScanPanel.label5.setText("运行中......");
	
		//将"scanStart"按钮设置成为不可用
		if(PortScanPanel.scanStart.isEnabled()){
			PortScanPanel.scanStart.setEnabled(false);
		}

		/*
		 *判断搜索的类型
		 *按照ip地址扫描：type = 0
		 *按照主机名称扫描：type = 1
		 */
		if(PortScanPanel.radioIp.isSelected()){
			PortScanPanel.choise = 0;

			//判断ip的前3位是否为int型
			for(int i = 1;i < 4; i++)
			{
				if(this.getIpSub(PortScanPanel.ip.getStartIp(), i) == this.getIpSub(PortScanPanel.ip.getEndIp(), i))
					continue;
				else
				{
					PortScanPanel.ErrorLabel.setText("错误的ip!");
					PortScanPanel.Error.setVisible(true);
					return;
				}
			}
			
			//判断起始ip是否正确
			//判断条件：大于0且小于等于255
			for(int i = 1;i < 5; i++)
			{
				if(this.getIpSub(PortScanPanel.ip.getStartIp(), i) >= 0 && this.getIpSub(PortScanPanel.ip.getStartIp(), i) < 255)
					continue;
				else
				{
					PortScanPanel.ErrorLabel.setText("错误的ip!");
					PortScanPanel.Error.setVisible(true);
					return;
				}
			}
			
			PortScanThread.ip.setStartIp(PortScanPanel.ip.getStartIp());
			
			//判断目标ip是否正确
			//判断条件：大于0且小于等于255
			if(this.getIpSub(PortScanPanel.ip.getEndIp(), 4) < 0 || this.getIpSub(PortScanPanel.ip.getEndIp(), 4) > 255){
				PortScanPanel.ErrorLabel.setText("目标ip地址为0-255的整数!");
				PortScanPanel.Error.setVisible(true);
				return;
			}
			else{
				
				PortScanThread.ip.setEndIp(PortScanPanel.ip.getEndIp());
			}
			
			/*
			 *判断ip地址的有效性
			 */
			try{
				ipaddress = InetAddress.getByName(PortScanPanel.ip.getStartIp()).getHostAddress();
				PortScanPanel.ip.setStartIp(ipaddress);
				
				ipaddress = InetAddress.getByName(PortScanPanel.ip.getEndIp()).getHostAddress();
				PortScanPanel.ip.setEndIp(ipaddress);
			}
			catch(UnknownHostException e){
				PortScanPanel.ErrorLabel.setText(" 错误的IP或地址不可达!");
				PortScanPanel.Error.setVisible(true);
				return;
			}
		}
		
		//根据主机名进行端口扫描
		if(PortScanPanel.radioHost.isSelected()){
			
			PortScanPanel.choise = 1;
			
			/*
			 *判断主机名称的有效性
			 */
			try{
				ipaddress = InetAddress.getByName(PortScanPanel.hostname.getText()).getHostAddress();
				PortScanPanel.ip.setStartIp(ipaddress);
			}
			catch(UnknownHostException e){
				PortScanPanel.ErrorLabel.setText("错误的域名或地址不可达! ");
				PortScanPanel.Error.setVisible(true);
				return;
			}
		}

		/*
		 *判断端口号的有效性
		 */
		try{
			a1 = Integer.parseInt(PortScanPanel.startPort.getText());
			a2 = Integer.parseInt(PortScanPanel.endPort.getText());
			a3 = Integer.parseInt(PortScanPanel.maxThread.getText());
		}
		catch(NumberFormatException e){
			PortScanPanel.ErrorLabel.setText("错误的端口号或线程数!");
			PortScanPanel.Error.setVisible(true);
			return;
		}
		
		/*
		 *判断最小端口号的有效范围
		 *判断条件：大于0且小于65535，最大端口应大于最小端口
		 */
		if(a1 < 0 || a2 > 65535 || a1 > a2){
			PortScanPanel.ErrorLabel.setText("端口必须是0-65535!");
			PortScanPanel.Error.setVisible(true);
			return;			
		}
		else{
			PortScanThread.port.setStartPort(a1);
		}

		/*
		 *判断最大端口号的有效范围
		 *判断条件：大于0且小于65535，最大端口应大于最小端口
		 */
		if(a2 < 0 || a2 > 65535 || a2 < a1){
			PortScanPanel.ErrorLabel.setText("最大端口必须是0-65535并且大于最小端口的整数!");
			PortScanPanel.Error.setVisible(true);
			return;	
		}
		else{
			PortScanThread.port.setEndPort(a2);
		}

		/*
		 *判断线程数量的有效范围
		 *判断条件：大于1且小于200
		 */
		if(a3 < 1 || a3 > 200){
			PortScanPanel.ErrorLabel.setText("                    线程数为1-200的整数!                    ");
			PortScanPanel.Error.setVisible(true);
			return;
		}

		PortScanPanel.result1.append("线程数 " + PortScanPanel.maxThread.getText() + "\n");
		PortScanPanel.result2.append("线程数 " + PortScanPanel.maxThread.getText() + "\n");
		PortScanPanel.Threadnumber = a3;
		//启动线程
		if(PortScanPanel.choise == 0)
		{
			while(getIpSub(PortScanPanel.ip.getStartIp(),4) <= getIpSub(PortScanPanel.ip.getEndIp(),4)){
				PortScanThread.ip.setStartIp(PortScanPanel.ip.getStartIp());
				for(int i = 0;i < a3;i++){
					new PortScanThread("端口扫描" + i,i).start();
					//if (i < 100) {
                    //    break;
                    //}
				}
				int ip4 = getIpSub(PortScanPanel.ip.getStartIp(),4) + 1;
					
				while(true){//等待所有子线程执行完  
					System.out.println(PortScanPanel.ip.getStartIp());	
					if(!PortScanThread.hasThreadRunning()){  
					break;  
					}  
				}
				
				String iptemp = getIpSub(PortScanPanel.ip.getStartIp(),1) + "." + getIpSub(PortScanPanel.ip.getStartIp(),2) + "." + getIpSub(PortScanPanel.ip.getStartIp(),3) + "." + ip4;
				PortScanPanel.ip.setStartIp(iptemp);
			}
			PortScanPanel.label5.setText("扫描完成!");
		}
		else
			if(PortScanPanel.choise == 1)
			{
				for(int i = 0;i< a3;i++){
					new PortScanThread("端口扫描" + i,i).start();
				}
			}
	}
	
	public int getIpSub(String s,int i)
	{
		int temp = 0;
		String[] ipcodes = s.split("[.]");
		try{
			temp = Integer.parseInt(ipcodes[i-1]);
		}
		catch(NumberFormatException e){
			PortScanPanel.ErrorLabel.setText("错误的ip!");
			PortScanPanel.Error.setVisible(true);
			return -1;
		}
		return temp;
	}
}

/*
 *实现错误提示框中的“确定”按钮功能
 */
class OKAction implements ActionListener{

	public void actionPerformed (ActionEvent e){
		PortScanPanel.Error.dispose();
		if(!PortScanPanel.scanStart.isEnabled()){
			PortScanPanel.scanStart.setEnabled(true);
		}
	}
}

class radioIpAction implements ActionListener{

	public void actionPerformed (ActionEvent e){
		if(!PortScanPanel.label5.getText().equals("程序初始状态")){
			PortScanPanel.label5.setText("程序初始状态");
		}
	}
}

class radioHostAction implements ActionListener{

	public void actionPerformed (ActionEvent e){
		if(!PortScanPanel.label5.getText().equals("程序初始状态")){
			PortScanPanel.label5.setText("程序初始状态");
		}
	}
}
