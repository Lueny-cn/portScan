package com.lwy.panel;

import java.awt.*;
import javax.swing.*;

//java要加背景图比较麻烦,需要重载paintComponent方法
public class getPanel extends JPanel {
	private static final long serialVersionUID = 6702278957072713279L;

	protected void paintComponent(Graphics g) {
		ImageIcon icon = new ImageIcon("bg.jpg");
		Image img = icon.getImage();
		g.drawImage(img, 0, 0, super.getWidth(), super.getHeight(), this);
	}
}