package cn.WangHao.view;

import java.awt.Color;
import java.awt.Font;

public enum Surface {
	
	
	S_2(238, 228, 218, 50), S_4(237, 224, 200, 50), S_8(242, 177, 121, 50), S_16(
			245, 149, 99, 50), S_32(246, 124, 95, 50), S_64(246, 94, 59, 50), S_128(
			237, 207, 114, 40), S_256(237, 204, 97, 40), S_512(237, 200, 80, 40), S_1024(
			237, 197, 63, 30), S_2048(237, 194, 46, 30);

	private Color color;
	private Font font;

	public Color getColor() {
		return this.color;
	}

	public Font getFont() {
		return this.font;
	}

	private Surface(int r, int g, int b, int fontSize) {
		this.color = new Color(r, g, b);
		this.font = new Font("SansSerif", 1, fontSize);
	}
}
