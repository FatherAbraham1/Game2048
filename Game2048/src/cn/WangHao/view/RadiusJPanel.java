package cn.WangHao.view;

import java.awt.Graphics;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JPanel;

public class RadiusJPanel extends JPanel {
	
	 public void paint(Graphics g)
	  {
	    RoundRectangle2D.Double rect = 
	    		new RoundRectangle2D.Double
	    		(0.0D, 0.0D, 
	    		getWidth(), getHeight(), 
	    		10.0D, 10.0D);
	    g.setClip(rect);
	    super.paint(g);
	  }
	
}
