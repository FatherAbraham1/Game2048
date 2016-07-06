package cn.WangHao.game;

import javax.swing.JPanel;

import cn.WangHao.view.NumberBlock;

public class MoveHandle implements Runnable {

	private NumberBlock[] jLabels;
	private Direct direct;
	private JPanel tablePanel;
	private NumberBlock[][] blockTable;

	private Desktop desktop;
	//方向的定义
	public static enum Direct {
		UP, DOWN, LEFT, RIGHT;
	}

	public MoveHandle(NumberBlock[] jLabels, Direct direct, Desktop desktop) {
		this.direct = direct;
		this.tablePanel = desktop.getTablePanel();
		this.blockTable = desktop.getBlockTable();
		this.desktop = desktop;
		this.jLabels = jLabels;
	}
	

	public Desktop getDesktop() {
		return desktop;
	}
	public JPanel getTablePanel() {
		return tablePanel;
	}
	public NumberBlock[][] getBlockTable() {
		return blockTable;
	}
	public Direct getDirect() {
		return direct;
	}
	
	

	public synchronized void execute(Runnable runnable){
		
		this.desktop.execute(runnable);
	}
	
	
	
	
	//格式化列表
	private void formatList() {

		if ((this.direct == Direct.DOWN) || (this.direct == Direct.RIGHT)) {
		      NumberBlock tmpBlock = this.jLabels[0];
		      this.jLabels[0] = this.jLabels[3];
		      this.jLabels[3] = tmpBlock;
		      tmpBlock = this.jLabels[1];
		      this.jLabels[1] = this.jLabels[2];
		      this.jLabels[2] = tmpBlock;
		    }
	}
	//添加一个卡片
	private void addAnimate(int empty, String grow, String remove, int k,
			NumberBlock block) {
		int i = block.getPoint().getI();
	    int j = block.getPoint().getJ();
	    this.blockTable[i][j] = null;

	    if ((this.direct == Direct.UP) || (this.direct == Direct.DOWN))
	      i = this.direct == Direct.UP ? i - empty : i + empty;
	    else
	      j = this.direct == Direct.LEFT ? j - empty : j + empty;
	    Point newPoint = (Point)Enum.valueOf(Point.class, "P_" + i + j);
	    boolean isRemove = remove.contains(Integer.toString(k));
	    if (!isRemove)
	      this.blockTable[i][j] = block;
	    Desktop.keySignal.pAniThread();
	    this.desktop.execute(new MoveAnimate(newPoint, block, grow.contains(Integer.toString(k)), isRemove, this));
		
	}
	
	
	
	
	
	

	public void run() {
		formatList();
		int empty=0;
		String remove="";
		String grow="";
		
		for (int k = 0; k < jLabels.length; k++) {
			NumberBlock block=jLabels[k];
			if (!remove.contains(Integer.toString(k))) {
				if (block!=null) {
					boolean merge=false;
					for (int l = k+1; l <jLabels.length; l++) {
						NumberBlock block2=this.jLabels[l];
						
						if (block2!=null) {
							if (block.getScore()!=block2.getScore()) {
								break;
							}
							merge=true;
							block.doubleScore();
							desktop.addScore(block.getScore());
							remove=remove+l;
							grow=grow+k;
							addAnimate(empty,grow,remove,k,block);
							break;
							
						}
						
					
					}
					if ((!merge)&&(empty!=0)) {
						addAnimate(empty, grow, remove, k, block);}
					
				}
				else {
					empty++;
				}
			}else {
				empty++;
				addAnimate(empty, grow, remove, k, block);
			}
			
		}
		
		desktop.keySignal.vKeyDown();
		
		
		/*for (int k = 0; k < blockTable.length; k++) {
			NumberBlock block=jLabels[k];
			if (!remove.contains(Integer.toString(k))) {
				
			}else {
				empty++;
				
			}
			
		}*/

	}

}
