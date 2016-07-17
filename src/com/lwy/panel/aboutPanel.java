package com.lwy.panel;

import java.awt.Font;

import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class aboutPanel extends getPanel {

    private JTextArea aboutText = new JTextArea(10, 10);
    private Font font=new Font("微软雅黑",Font.PLAIN,20);
    
    public aboutPanel() {
        this.setLayout(null);// 自定义布局
        this.setLayout(null);// 自定义布局
        aboutText.setBounds(100, 80, 500, 180);// 文本的位置
        aboutText.setFont(font);
        aboutText.setOpaque(false);
        aboutText.setText(" 2013级计算机科学与技术2班    \n\n" + 
                "                 卢文友\n\n" + 
                "           3113005839 \n\n"   +
                 "");
        this.add(aboutText);
    }
}
