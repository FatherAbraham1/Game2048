package cn.WangHao.listener;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JMenu;

public class MouseHoverAdapter extends MouseAdapter {
	

	public void mouseEntered(MouseEvent arg0)
	  {
	    JMenu jMenu = (JMenu)arg0.getSource();
	    jMenu.setOpaque(true);
	    jMenu.setBackground(new Color(200, 200, 200));
	    jMenu.repaint();
	  }

	  public void mouseExited(MouseEvent e)
	  {
	    JMenu jMenu = (JMenu)e.getSource();
	    jMenu.setOpaque(false);
	    jMenu.repaint();
	  }
	
	
	

}
