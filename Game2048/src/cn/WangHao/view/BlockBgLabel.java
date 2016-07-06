package cn.WangHao.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JLabel;

public class BlockBgLabel extends JLabel {
	
	 public BlockBgLabel()
	  {
	    setOpaque(true);
	    setBackground(new Color(204, 192, 179));
	    setHorizontalAlignment(0);
	  }

	  public BlockBgLabel(String text)
	  {
	    super(text);
	    setOpaque(true);
	    setHorizontalAlignment(0);
	  }

	  public void paint(Graphics g)
	  {
	    RoundRectangle2D.Double rect = new RoundRectangle2D.Double(0.0D, 0.0D, getWidth(), getHeight(), 8.0D, 8.0D);
	    g.setClip(rect);
	    super.paint(g);
	  }
	
}
