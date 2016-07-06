package cn.WangHao.game;

import java.awt.Color;

import cn.WangHao.view.NumberBlock;

public class UpdateUITask implements Runnable {
	NumberBlock jLabel;
	Color color;
	int[] posits;

	public UpdateUITask(NumberBlock jLabel, int[] posits) {

		this.jLabel = jLabel;
		this.posits = posits;

	}

	public void run() {
		if (this.posits.length == 3) {

			this.jLabel.setBounds(this.posits[0], this.posits[1],
					this.posits[2], this.posits[2]);

		} else {
			this.jLabel.setBounds(this.posits[0], this.posits[1], 100, 100);
		}

	}

}
