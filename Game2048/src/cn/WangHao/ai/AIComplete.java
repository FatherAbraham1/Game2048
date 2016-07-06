package cn.WangHao.ai;

import cn.WangHao.game.Desktop;
import cn.WangHao.game.MoveHandle.Direct;
import cn.WangHao.view.NumberBlock;

public class AIComplete implements Runnable {


	int[][]table;
	private Desktop desktop;
	private static boolean isStart=false;//用于判断游戏是否结束
	
	private long startTime;
	
	private static int[] Direct;
	
	//开启时间
	public static boolean isStart(){
		return isStart;
	}
	public static synchronized void setStatus(boolean aIStatus){
		
		isStart=aIStatus;
	}
	
	public AIComplete(Desktop desktop){
		this.desktop=desktop;
		this.table=new int [4][4];
		updateTable();
	}
	
	
	public void run() {
		while (true) {
			checkOver();//检查游戏是否结束
			updateTable();
			
			this.startTime=System.currentTimeMillis();
			Direct direct=new DepthSearch(this.table).depth();
			
			long current=System.currentTimeMillis();
			if(current-this.startTime<350L){
				
				try {
			Thread.sleep(350L - (current - this.startTime));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (!isStart) {
				return;
			}
			switch (direct) {
			case UP:
				desktop.keyUp();
				
				break;

			case DOWN:
		       desktop.keyDown();
		        break;
		      case LEFT:
		       desktop.keyLeft();
		        break;
		      case RIGHT:
		       desktop.keyRight();
		        break;
			}
			System.err.println("没有最优操作！");
			
			
			
			
		}

	}
	private void checkOver() {
		try {
		      while (!Desktop.keySignal.isOver())
		        Thread.sleep(10L);
		    }
		    catch (InterruptedException e1) {
		      e1.printStackTrace();
		    }
		
	}
	
	

	private void updateTable() {
		NumberBlock [][]otable=desktop.getBlockTable();
		for (int i = 0; i < otable.length; i++) {
			for (int j = 0; j < otable.length; j++) {
				this.table[i][j] = (otable[i][j] != null ? otable[i][j].getScore() : 0);
			}
		}
		
	}

}
