package cn.WangHao.game;

import cn.WangHao.view.NumberBlock;

public class TableChecker {
	
	
	private MoveHandle handle;
	  private NumberBlock[][] blockTable;

	  public TableChecker(MoveHandle handle)
	  {
	    this.handle = handle;
	    this.blockTable = handle.getBlockTable();
	  }

	  public void checkGameOver() {
	    for (int i = 0; i < this.blockTable.length; i++) {
	      NumberBlock[] blocks = this.blockTable[i];
	      for (int j = 0; j < blocks.length; j++) {
	        NumberBlock block = blocks[j];
	        if (block == null)
	          return;
	        if (block.getScore() == 2048) {
	          this.handle.getDesktop().setGameStat(false);
	          this.handle.getDesktop().changeResPanel(true, true);
	        }
	        if ((j + 1 < blocks.length) && (blocks[(j + 1)] != null) && 
	          (blocks[(j + 1)].getScore() == block.getScore())) {
	          return;
	        }
	        if ((i + 1 < this.blockTable.length) && (this.blockTable[(i + 1)][j] != null) && 
	          (this.blockTable[(i + 1)][j].getScore() == block.getScore())) {
	          return;
	        }
	      }
	    }
	    this.handle.getDesktop().setGameStat(false);
	    this.handle.getDesktop().changeResPanel(true, false);
	  }
	
	

}
