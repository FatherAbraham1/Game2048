package cn.WangHao.game;

import java.lang.reflect.InvocationTargetException;

import javax.swing.SwingUtilities;

import cn.WangHao.view.NumberBlock;

//移动卡片
public class MoveAnimate implements Runnable {

	private Point newPoint;
	private NumberBlock block;
	private boolean isGrow;
	private boolean isRemove;
	private MoveHandle handle;

	public MoveAnimate(Point point, NumberBlock block, boolean isGrow,
			boolean isRemove, MoveHandle handle) {
		this.newPoint = point;
		this.block = block;
		this.isGrow = isGrow;
		this.isRemove = isRemove;
		this.handle = handle;
	}

	private void updateUI(Point oPoint, int i, boolean isVertical) {

		try {
			SwingUtilities.invokeAndWait(new UpdateUITask(this.block,
					new int[] { isVertical ? oPoint.getX() : oPoint.getX() + i,
							isVertical ? oPoint.getY() + i : oPoint.getY() }));
		} catch (InterruptedException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}

	}

	public void run() {

		Point oPoint = this.block.getPoint();
		if (this.newPoint.getY() != oPoint.getY()) {
			if (this.newPoint.getY() > oPoint.getY()) {
				int len = this.newPoint.getY() - oPoint.getY();
				for (int i = 0; (i <= len) || (i - len < 20); i += 20) {
					if ((i > len) && (i - len < 20))
						i = len;
					updateUI(oPoint, i, true);
				}
				this.block.setPoint(this.newPoint);
			} else if (this.newPoint.getY() < oPoint.getY()) {
				int len = this.newPoint.getY() - oPoint.getY();
				for (int i = 0; (i >= len) || (i - len > -20); i -= 20) {
					if ((i < len) && (i - len > -20))
						i = len;
					updateUI(oPoint, i, true);
				}
				this.block.setPoint(this.newPoint);
			}
		}
		if (this.newPoint.getX() != oPoint.getX()) {
			if (this.newPoint.getX() > oPoint.getX()) {
				int len = this.newPoint.getX() - oPoint.getX();
				for (int i = 0; (i <= len) || (i - len < 20); i += 20) {
					if ((i > len) && (i - len < 20))
						i = len;
					updateUI(oPoint, i, false);
				}
				this.block.setPoint(this.newPoint);
			} else if (this.newPoint.getX() < oPoint.getX()) {
				int len = this.newPoint.getX() - oPoint.getX();
				for (int i = 0; (i >= len) || (i - len > -20); i -= 20) {
					if ((i < len) && (i - len > -20))
						i = len;
					updateUI(oPoint, i, false);
				}
				this.block.setPoint(this.newPoint);
			}
		}
		if (this.isRemove) {
			this.handle.getTablePanel().remove(this.block);
			this.handle.getTablePanel().repaint();
		}
		if (this.isGrow) {
			try {
				Thread.sleep(50L);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			this.block.update(this.block.getScore());
			this.handle.execute(new GrowOverAnimate(this.block));
		}
		if (Desktop.keySignal.isLast()) {
			new CreateBlock(this.handle).create();
			new TableChecker(this.handle).checkGameOver();
			this.handle.getTablePanel().repaint();
		}
		Desktop.keySignal.vAniThread();

	}

}
