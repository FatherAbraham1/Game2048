package cn.WangHao.listener;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import cn.WangHao.ai.AIComplete;
import cn.WangHao.game.Desktop;

public class GameKeyListener extends KeyAdapter {
	
	private Desktop desktop;
	  boolean isReleased = true;

	  public GameKeyListener(Desktop desktop)
	  {
	    this.desktop = desktop;
	  }

	  public void keyReleased(KeyEvent e)
	  {
	    super.keyReleased(e);
	    this.isReleased = true;
	  }

	  public void keyPressed(KeyEvent e)
	  {
	    if (this.isReleased)
	      this.isReleased = false;
	    else
	      return;
	    if ((!Desktop.keySignal.isOver()) || (!this.desktop.getGameStat()) || (AIComplete.isStart()))
	      return;
	    int keyCode = e.getKeyCode();
	    switch (keyCode) {
	    case 38:
	    case 87:
	      this.desktop.keyUp();
	      break;
	    case 40:
	    case 83:
	      this.desktop.keyDown();
	      break;
	    case 37:
	    case 65:
	      this.desktop.keyLeft();
	      break;
	    case 39:
	    case 68:
	      this.desktop.keyRight();
	    }
	  }
	
	
	
	
	
}
