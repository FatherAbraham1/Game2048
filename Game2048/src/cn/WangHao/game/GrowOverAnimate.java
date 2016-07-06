package cn.WangHao.game;

import cn.WangHao.view.NumberBlock;

public class GrowOverAnimate extends GrowAnimate {
	

	 public GrowOverAnimate(NumberBlock jLabel)
	  {
	    super(jLabel);
	  }

	  public void run()
	  {
	    for (int i = 100; i <= 114; i++) {
	      addUITask(i);
	    }
	    for (int i = this.jLabel.getWidth(); i >= 100; i--)
	      addUITask(i);
	  }
	
	
	
	
	
}
