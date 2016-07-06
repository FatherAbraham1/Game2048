package cn.WangHao.game;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JLabel;
import javax.swing.JPanel;

import cn.WangHao.game.MoveHandle.Direct;
import cn.WangHao.view.MainForm;
import cn.WangHao.view.NumberBlock;

public class Desktop {
	
private JLabel labelScore;
	
	private int totalScore;
	
	private JPanel tablePanel;
	
	private NumberBlock [][]blockTable;
	
	private ExecutorService executor;
	
	private boolean gameStat;
	
	private MainForm form;
	
	public static Signal keySignal=new  Signal();
	
	
	public Desktop(JLabel labelScore,JPanel tablePanel,MainForm form){
		
		this.labelScore=labelScore;
		this.tablePanel=tablePanel;
		this.form=form;
		this.executor=Executors.newFixedThreadPool(16);
		
	}
	
	
	public boolean getGameStat(){
		return this.gameStat;
		
	}
	
	
	public void setGameStat(boolean gameStat){
		
		this.form.initAI();
		this.gameStat=gameStat;
	}
	
	public JPanel getTablePanel() {
		return tablePanel;
	}
	public NumberBlock[][] getBlockTable() {
		return blockTable;
	}
	
	public void initScore(){
		this.totalScore=0;
		this.labelScore.setText(String.format("%05d",new Object[]{Integer.valueOf(0)} ));
		
		
	}
	public synchronized void addScore(int score){
		
		this.totalScore+=score;
		this.labelScore.setText(String.format("%05d", new Object[]{Integer.valueOf(this.totalScore)}));
	}
	public synchronized void execute(Runnable runnable){
		this.executor.execute(runnable);
	}
	public void changeResPanel(boolean isVisible,boolean isWin){
		this.form.changeResPanel(isVisible, isWin);
		
	}
	
	//problem :1 cb.create()
	public void Start(){
		initScore();
		this.form.initAI();
		setGameStat(true);
		keySignal=new Signal();
		this.blockTable=new NumberBlock[4][4];
		changeResPanel(false,false);
		this.tablePanel.removeAll();
		this.tablePanel.repaint();
		
		CreateBlock cb=new CreateBlock(this);
		cb.create();
		//cb.create();
	}
	
	public void keyUp() {
	    vertical(Direct.UP);
	  }

	  public void keyDown() {
	    vertical(Direct.DOWN);
	  }

	  public void keyLeft() {
	    horizontal(Direct.LEFT);
	  }

	  public void keyRight() {
	    horizontal(Direct.RIGHT);
	  }
	  
	  
	  public void vertical(Direct direct)
	  {
	    for (int i = 0; i < 4; i++) {
	      boolean hasNode = hasNode(direct, i);
	      NumberBlock[] jLabels = { 
	    		  this.blockTable[0][i], 
	    		  this.blockTable[1][i], 
	    		  this.blockTable[2][i], 
	    		  this.blockTable[3][i] };
	      if ((hasNode) || (hasMerge(jLabels))) {
	        keySignal.pKeyDown();
	        execute(new MoveHandle(jLabels, direct, this));
	      }
	    }
	  }

	  public void horizontal(Direct direct)
	  {
	    for (int i = 0; i < 4; i++) {
	      boolean hasNode = hasNode(direct, i);
	      NumberBlock[] jLabels = { 
	    		  this.blockTable[i][0],
	    		  this.blockTable[i][1],
	    		  this.blockTable[i][2],
	    		  this.blockTable[i][3] };
	      if ((hasNode) || (hasMerge(jLabels))) {
	        keySignal.pKeyDown();
	        execute(new MoveHandle(jLabels, direct, this));
	      }
	    }
	  }
	  
	  
	  
	  private boolean hasNode(Direct direct, int i)
	  {
	    boolean hasSpace = false;
	    boolean hasNode = false;
	    if ((direct == Direct.LEFT) || (direct == Direct.UP)) {
	      for (int j = 0; j < 4; j++) {
	        if (((direct == Direct.LEFT) || (direct == Direct.RIGHT) ? this.blockTable[i][j] : this.blockTable[j][i]) == null)
	          hasSpace = true;
	        else if (hasSpace)
	          hasNode = true;
	      }
	    }
	    else {
	      for (int j = 3; j >= 0; j--) {
	        if (((direct == Direct.LEFT) || (direct ==Direct.RIGHT) ? this.blockTable[i][j] : this.blockTable[j][i]) == null)
	          hasSpace = true;
	        else if (hasSpace)
	          hasNode = true;
	      }
	    }
	    return hasNode;
	  }

	  public boolean hasMerge(NumberBlock[] jLabels)
	  {
	    for (int i = 0; i < jLabels.length - 1; i++) {
	      NumberBlock b1 = jLabels[i];
	      if (b1 != null) {
	        for (int j = i + 1; j < 4; j++) {
	          NumberBlock b2 = jLabels[j];
	          if ((b2 != null) && 
	            (b1.getScore() == b2.getScore())) {
	            return true;
	          }
	        }
	      }
	    }
	    return false;
	  }
	
	
	

}
