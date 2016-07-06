package cn.WangHao.view;

import java.awt.Color;
import java.util.Random;

import cn.WangHao.game.Point;

public class NumberBlock extends BlockBgLabel {
	
	private int score;
	private Point point;
	
	public int getScore() {
		return score;
	}
	public void doubleScore(){
		this.score*=2;
		
	}
	public Point getPoint(){
		return this.point;
		
	}
	public void setPoint(Point point) {
		this.point = point;
	}
	
	
	 public NumberBlock(Point point)
	  {
	    Random random = new Random(System.currentTimeMillis());
	    int score = random.nextInt(10) == 0 ? 4 : 2;
	    setText(Integer.toString(score));
	    this.point = point;
	    setBounds(point.getX(), point.getY(), 0, 0);
	    init(score);
	  }

	  public NumberBlock(int score, Point point) {
	    super(Integer.toString(score));
	    this.point = point;
	    setBounds(point.getX(), point.getY(), 30, 30);
	    init(score);
	  }
	  
	  
	  public void init(int score) {
		    this.score = score;
		    if (score <= 4) {
		      setForeground(new Color(119, 110, 101));
		    }
		    else {
		      setForeground(new Color(249, 246, 242));
		    }
		    Surface surface = (Surface)Enum.valueOf(Surface.class, "S_" + Integer.toString(score));
		    setBackground(surface.getColor());
		    setFont(surface.getFont());
		  }
	

	  public void update(int score) {
	    init(score);
	    setText(Integer.toString(score));
	  }
	
	
	
	
	
}
