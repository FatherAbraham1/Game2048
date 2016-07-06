package cn.WangHao.game;

import java.lang.reflect.InvocationTargetException;

import javax.swing.SwingUtilities;

import cn.WangHao.view.NumberBlock;

public class GrowAnimate implements Runnable {
	NumberBlock jLabel;

	public GrowAnimate(NumberBlock jLabel) {

		this.jLabel = jLabel;

	}

	public void run() {

		for (int i = 30; i <= 100; i += 10) {
			addUITask(i);

		}

	}

	protected void addUITask(int i) {

		int x = this.jLabel.getPoint().getX() + (50 - i / 2);
		int y = this.jLabel.getPoint().getY() + (50 - i / 2);
		try {
			SwingUtilities.invokeAndWait(new UpdateUITask(this.jLabel,
					new int[] { x, y, i }));
		} catch (InterruptedException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}

	}

}
