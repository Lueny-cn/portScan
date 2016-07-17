package com.lwy.frame;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import com.lwy.panel.HelpPanel;
import com.lwy.panel.PortScanPanel;
import com.lwy.panel.aboutPanel;
import com.lwy.panel.getPanel;

@SuppressWarnings("serial")
public class manager extends JFrame {
	CardLayout card = null;
	Container con = null;
	JPanel pCenter;

	/**
	 * @author 卢文友
	 * 界面类
	 * 2016-06-28 
	 * 分别调用了portScanPanel类、HelpPanel类、aboutPanel类
	 */
	
	public manager() {
		
		// 界面上面的按键菜单
		Font f = new Font("微软雅黑", Font.PLAIN, 12);// 设置字体

		JMenuBar mb = new JMenuBar();// 创建菜单栏

		JMenu 端口扫描 = new JMenu("端口扫描  ");// 创建菜单
		端口扫描.setFont(f);
		JMenuItem portScan = new JMenuItem("端口扫描");
		portScan.setFont(f);
		端口扫描.add(portScan);

		JMenu 帮助 = new JMenu("帮助  ");// 创建菜单
		帮助.setFont(f);
		JMenuItem help = new JMenuItem("帮助");
		help.setFont(f);
		帮助.add(help);
		
		JMenu 关于 = new JMenu("关于  ");// 创建菜单
		关于.setFont(f);
		JMenuItem about = new JMenuItem("关于");
		about.setFont(f);
		关于.add(about);
		
		
		// 添加菜单到菜单栏
		mb.add(端口扫描);
		mb.add(帮助);
		mb.add(关于);
		setJMenuBar(mb);

		// 界面中间有关的面板
		pCenter = new getPanel();
		card = new CardLayout();// 卡片布局
		pCenter.setLayout(card);

		// 添加各个对象到中间容器的卡片上
		PortScanPanel portScanPanel = new PortScanPanel();// 主要面板
		HelpPanel helpPanel = new HelpPanel();// 帮助面板
		aboutPanel aboutPanel = new aboutPanel();// 关于面板
		pCenter.add("端口扫描", portScanPanel);
		pCenter.add("帮助", helpPanel);
		pCenter.add("关于", aboutPanel);
		

		// 中间面板的布局
		con = getContentPane();
		con.add(pCenter, BorderLayout.CENTER);
		con.validate();

		addWindowListener(new WindowAdapter() // 为了关闭窗口
		{
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		setVisible(true);
		setBackground(Color.GREEN);
		setBounds(100, 100, 469, 400);
		setResizable(false);
		setLocationRelativeTo(null);// 设置ClientJFrame相对屏幕居中
		setTitle("端口扫描器");
		validate();

		// 【端口扫面】菜单监听
		portScan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(pCenter, "端口扫描");
			}
		});

		// 【帮助】菜单监听
		help.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(pCenter, "帮助");
			}
		});

		// 【关于】菜单监听
		about.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(pCenter, "关于");
			}
		});
	}
}