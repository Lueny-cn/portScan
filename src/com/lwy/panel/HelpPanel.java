package com.lwy.panel;

import java.awt.Font;

import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class HelpPanel extends getPanel {

    private JTextArea helpText = new JTextArea(40, 100);
    Font font=new Font("微软雅黑",Font.PLAIN,18);
    public HelpPanel() {
        this.setLayout(null);// 自定义布局
        helpText.setBounds(20, 20, 500, 180);// 文本的位置
        helpText.setOpaque(false);
        helpText.setFont(font);
        helpText.setText("1、选择扫描方式\n" + "2、输入数据\n" + "3、点击“开始扫描”\n"
                + "4、点击“保存扫描结果”进行扫描结果的保存");
        this.add(helpText);
    }
}
