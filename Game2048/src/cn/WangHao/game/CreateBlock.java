package cn.WangHao.game;

import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

import cn.WangHao.view.NumberBlock;

public class CreateBlock {
	
	private JPanel tablePanel;
	private NumberBlock [][]blockTable;
	
	private MoveHandle handle;
	
	private Desktop desktop;
	
	public CreateBlock(MoveHandle handle){
		
		this.tablePanel=handle.getTablePanel();
		this.blockTable=handle.getBlockTable();
		this.handle=handle;
		
	}
	
	public CreateBlock(Desktop desktop){
		this.tablePanel=desktop.getTablePanel();
		this.blockTable=desktop.getBlockTable();
		this.desktop=desktop;
	}


	public void create(){
		ArrayList list=new ArrayList();
		
		for (int i = 0; i < this.blockTable.length; i++) {
			NumberBlock[]blocks=this.blockTable[i];
			for (int j = 0; j < blocks.length; j++) {
				NumberBlock block= blocks[j];
				if (block==null) {
					list.add((Point)Enum.valueOf(Point.class, "P_"+i+j));
					
				}
			}
		
		}
		
		if (list.size()!=0) {
			addBlock(list);
			
		}
		
	}

	private void addBlock(ArrayList<Point> list) {
		
		Random random=new Random(System.currentTimeMillis());
		
		Point point=(Point)list.get(random.nextInt(list.size()));
		
		NumberBlock block=new NumberBlock(point);
		this.blockTable[point.getI()][point.getJ()]=block;
		this.tablePanel.add(block);
		this.tablePanel.repaint();
		if (this.handle!=null) {
			this.handle.execute(new GrowAnimate(block));
			
			
		}
		else if (this.desktop!=null) {
			
			this.desktop.execute(new GrowAnimate(block));}
	
	}
	
	

}
