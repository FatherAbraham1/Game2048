package cn.WangHao.game;

public enum  Point {
	 P_00(10, 10, 0, 0), 
	 P_01(120, 10, 0, 1),
	 P_02(230, 10, 0, 2),
	 P_03(340, 10, 0, 3),
	 P_10(10, 120, 1, 0),
	 P_11(120, 120, 1, 1), 
	 P_12(230, 120, 1, 2),
	 P_13(340, 120, 1, 3),
	 P_20(10, 230, 2, 0), 
	 P_21(120, 230, 2, 1),
	 P_22(230, 230, 2, 2),
	 P_23(340, 230, 2, 3),
	 P_30(10, 340, 3, 0),
	 P_31(120, 340, 3, 1),
	 P_32(230, 340, 3, 2), 
	 P_33(340, 340, 3, 3);

	private int x;
	private int y;
	private int i;
	private int j;

	public int getI() {
		return this.i;
	}

	public int getJ() {
		return this.j;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	private Point(int x, int y, int i, int j) {
		this.x = x;
		this.y = y;
		this.i = i;
		this.j = j;
	}

	public boolean equals(Point point) {
		return (this.x == point.x) && (this.y == point.y);
	}
	
	
	
}
