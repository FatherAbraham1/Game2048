package cn.WangHao.ai;

public class Evaluator {

	int[][] table;
	private static final double smoothWeight = 0.1D;
	private static final double monoWeight = 1.0D;
	private static final double emptyWeight = 2.7D;
	private static final double maxWeight = 1.0D;

	public Evaluator(int[][] table) {
		this.table = table;
	}

	public double evaluate() {
		return 0.1D * smooth() + 1.0D * monoCount2() + 2.7D
				* Math.log(emptyCount()) + 1.0D * maxBlock();
	}

	public double smooth() {

		double smooth = 0.0D;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (this.table[i][j] != 0) {
					double selfScore = Math.log(this.table[i][j])
							/ Math.log(2.0D);
					int[] fronts = getFronts(i, j);
					for (int k = 0; k < fronts.length; k++) {
						if (fronts[k] != 0) {
							double frontScore = Math.log(fronts[k])
									/ Math.log(2.0D);
							smooth -= Math.abs(selfScore - frontScore);
						}
					}
				}
			}
		}
		return smooth;
	}

	private int[] getFronts(int i, int j) {
		int[] fronts = new int[2];
		for (int m = i + 1; m < 4; m++)
			if (this.table[m][j] != 0) {
				fronts[0] = this.table[m][j];
				break;
			}
		for (int m = j + 1; m < 4; m++)
			if (this.table[i][m] != 0) {
				fronts[1] = this.table[i][m];
				break;
			}
		return fronts;
	}

	private double monoCount(int[][] blocks) {
		int[] score = new int[2];
		for (int x = 0; x < 4; x++) {
			int current = 0;

			for (int next = current + 1; next < 4; next++) {
				while (next < 4 && blocks[x][next] == 0)
					next++;
				if (next >= 4)
					next--;
				double currentValue = blocks[x][current] == 0 ? 0.0D : Math
						.log(blocks[x][current]) / Math.log(2D);
				double nextValue = blocks[x][next] == 0 ? 0.0D : Math
						.log(blocks[x][next]) / Math.log(2D);
				if (currentValue > nextValue)
					score[0] += nextValue - currentValue;
				else if (nextValue > currentValue)
					score[1] += currentValue - nextValue;
				current = next;
			}

			/*
			 * int next = current + 1; while (next < 4) { while ((next < 4) &&
			 * (blocks[x][next] == 0)) { next++; } if (next >= 4) { next--; }
			 * double currentValue = blocks[x][current] != 0 ?
			 * Math.log(blocks[x][current]) / Math.log(2.0D) : 0.0D; double
			 * nextValue = blocks[x][next] != 0 ? Math.log(blocks[x][next]) /
			 * Math.log(2.0D) : 0.0D; if (currentValue > nextValue) { int
			 * tmp122_121 = 0; int[] tmp122_120 = score; tmp122_120[tmp122_121]
			 * = ((int)(tmp122_120[tmp122_121] + (tmp122_121 - currentValue)));
			 * } else if (tmp122_121 > currentValue) { int tmp146_145 = 1; int[]
			 * tmp146_144 = score; tmp146_144[tmp146_145] =
			 * ((int)(tmp146_144[tmp146_145] + (currentValue - tmp122_121))); }
			 * current = next; next++; }
			 */
		}
		return Math.max(score[0], score[1]);
	}

	private double monoCount2() {
		double score = 0.0D;
		score += monoCount(this.table);
		DepthSearch.reverse(this.table);
		score += monoCount(this.table);
		DepthSearch.reverse(this.table);
		return score;
	}

	private double maxBlock() {
		int max = 0;
		for (int[] is : this.table)
			for (int i : is)
				max = i > max ? i : max;
		return Math.log(max) / Math.log(2.0D);
	}

	private double emptyCount() {
		int empty = 0;
		for (int[] is : this.table)
			for (int i : is)
				if (i == 0)
					empty++;
		return empty;
	}

	public int landCount() {
		boolean[][] markTable = new boolean[4][4];

		int islands = 0;
		for (int x = 0; x < 4; x++) {
			for (int y = 0; y < 4; y++) {
				if ((this.table[x][y] != 0) && !(markTable[x][y])) {
					islands++;
					mark(x, y, this.table[x][y], this.table, markTable);
				}
			}
		}
		return islands;
	}

	private void mark(int x, int y, int value, int[][] table,
			boolean[][] markTable) {

		if (!markTable[x][y]) {
			markTable[x][y] = true;
			if (x - 1 >= 0 && table[x - 1][y] == value && !markTable[x - 1][y])
				markTable[x - 1][y] = true;
			if (x + 1 < 3 && table[x + 1][y] == value && !markTable[x + 1][y])
				markTable[x + 1][y] = true;
			if (y - 1 >= 0 && table[x][y - 1] == value && !markTable[x][y - 1])
				markTable[x][y - 1] = true;
			if (y + 1 < 3 && table[x][y + 1] == value && !markTable[x][y + 1])
				markTable[x][y + 1] = true;
		}

		/*
		 * if (markTable[x][y] == 0) { markTable[x][y] = 1; if ((x - 1 >= 0) &&
		 * (table[(x - 1)][y] == value) && (markTable[(x - 1)][y] == 0))
		 * markTable[(x - 1)][y] = 1; if ((x + 1 < 3) && (table[(x + 1)][y] ==
		 * value) && (markTable[(x + 1)][y] == 0)) markTable[(x + 1)][y] = 1; if
		 * ((y - 1 >= 0) && (table[x][(y - 1)] == value) && (markTable[x][(y -
		 * 1)] == 0)) markTable[x][(y - 1)] = 1; if ((y + 1 < 3) && (table[x][(y
		 * + 1)] == value) && (markTable[x][(y + 1)] == 0)) markTable[x][(y +
		 * 1)] = 1; }
		 */
	}

}
