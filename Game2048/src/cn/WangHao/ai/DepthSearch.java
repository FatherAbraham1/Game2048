package cn.WangHao.ai;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

import cn.WangHao.game.MoveHandle.Direct;

public class DepthSearch {

	private int[][] table;

	public static void main(String[] args) {
		int[][] tables = { new int[4], { 0, 0, 0, 4 }, { 0, 2, 4, 8 },
				{ 4, 4, 8, 16 } };
		DepthSearch search = new DepthSearch(tables);
		search.depth();

	}

	public DepthSearch(int[][] table) {
		this.table = table;
	}

	public Direct depth() {
		long start = System.currentTimeMillis();
		int depth = 0;
		Result best = null;

		do {
			Result newBest = search(depth, -10000D, 10000D, 0, 0, true, table);
			if (newBest.move == null) {
				break;
			}
			best = newBest;
			depth++;
		} while (System.currentTimeMillis() - start < 100L);
		System.out.println(depth);
		return best == null ? null : best.move;

	}

	// promblem2:double bestScore
	private Result search(int depth, double alpha, double beta, int positions,
			int cutoffs, boolean isPlayerTrun, int[][] preTable) {
		Direct bestMove = null;

		Direct[] directs = { Direct.UP, Direct.RIGHT, Direct.DOWN, Direct.LEFT };
		double bestScore;
		if (isPlayerTrun) {
			bestScore = alpha;

			Direct adirect[];
			int j = (adirect = directs).length;
			for (int i = 0; i < j; i++) {
				Direct direct = adirect[i];
				int dirTable[][] = cloneArr(preTable);
				move(direct, dirTable);
				if (isMoved(preTable, dirTable)) {
					positions++;
					if (isWin(dirTable)) {
						System.out.println("win");
						return new Result(direct, bestScore + 1000D, positions,
								cutoffs);
					}
					Result result;
					if (depth == 0) {
						result = new Result(direct,
								(new Evaluator(dirTable)).evaluate(), 0, 0);
					} else {
						result = search(depth - 1, bestScore, beta, positions,
								cutoffs, false, dirTable);
						if (result.score > 9900D)
							result.score--;
						positions = result.positions;
						cutoffs = result.cutoffs;
					}
					if (result.score > bestScore) {
						bestScore = result.score;
						bestMove = direct;
					}
					if (bestScore > beta) {
						cutoffs++;
						return new Result(bestMove, beta, positions, cutoffs);
					}
				}
			}

			/*
			 * for (Direct direct : directs) { int[][] dirTable =
			 * cloneArr(preTable); move(direct, dirTable); if (isMoved(preTable,
			 * dirTable)) { positions++; if (isWin(dirTable)) {
			 * System.out.println("win"); return new Result(direct, bestScore +
			 * 1000.0D, positions, cutoffs); } Result result; //Result result;
			 * if (depth == 0) { result = new Result(direct, new
			 * Evaluator(dirTable).evaluate(), 0, 0); } else { result =
			 * search(depth - 1, bestScore, beta, positions, cutoffs, false,
			 * dirTable); if (result.score > 9900.0D) { result.score -= 1.0D; }
			 * 
			 * positions = result.positions; cutoffs = result.cutoffs; }
			 * 
			 * if (result.score > bestScore) { bestScore = result.score;
			 * bestMove = direct; } if (bestScore > beta) { cutoffs++; return
			 * new Result(bestMove, beta, positions, cutoffs); } } }
			 */

		} else {
			bestScore = beta;

			HashMap candidates = new HashMap();

			ArrayList cells = getEmptyCells(preTable);

			ArrayList[] scores = { new ArrayList(), new ArrayList() };
			for (int value = 0; value < 2; value++) {

				for (Iterator iterator = cells.iterator(); iterator.hasNext();) {
					Posit ePosit = (Posit) iterator.next();
					preTable[ePosit.i][ePosit.j] = (value + 1) * 2;
					Evaluator evaluator = new Evaluator(preTable);
					scores[value].add(Double.valueOf(-evaluator.smooth()
							+ (double) evaluator.landCount()));
					preTable[ePosit.i][ePosit.j] = 0;
				}

				/*
				 * for (Posit ePosit : cells) { preTable[ePosit.i][ePosit.j] =
				 * ((value + 1) * 2); Evaluator evaluator = new
				 * Evaluator(preTable);
				 * scores[value].add(Double.valueOf(-evaluator.smooth() +
				 * evaluator.landCount())); preTable[ePosit.i][ePosit.j] = 0; }
				 */

			}

			double maxScore = Math.max(
					((Double) Collections.max(scores[0])).doubleValue(),
					((Double) Collections.max(scores[1])).doubleValue());
			int n;
			for (int m = 0; m < scores.length; m++) {
				ArrayList scorez = scores[m];
				for (n = 0; n < scorez.size(); n++) {
					if (((Double) scorez.get(n)).doubleValue() == maxScore) {
						candidates.put((Posit) cells.get(n),
								Integer.valueOf((m + 1) * 2));
					}
				}

			}

			Set entries = candidates.entrySet();

			for (Iterator iterator1 = entries.iterator(); iterator1.hasNext();) {
				Entry entry = (Entry) iterator1.next();
				Posit position = (Posit) entry.getKey();
				int value = ((Integer) entry.getValue()).intValue();
				int newArr[][] = cloneArr(preTable);
				newArr[position.i][position.j] = value;
				positions++;
				Result result = search(depth, alpha, bestScore, positions,
						cutoffs, true, newArr);
				positions = result.positions;
				cutoffs = result.cutoffs;
				if (result.score < bestScore)
					bestScore = result.score;
				if (bestScore < alpha) {
					cutoffs++;
					return new Result(null, alpha, positions, cutoffs);
				}
			}

			/*
			 * for (Map.Entry entry : entries) { Posit position =
			 * (Posit)entry.getKey(); int value =
			 * ((Integer)entry.getValue()).intValue(); int[][] newArr =
			 * cloneArr(preTable); newArr[position.i][position.j] = value;
			 * positions++; Result result = search(depth, alpha, bestScore,
			 * positions, cutoffs, true, newArr); positions = result.positions;
			 * cutoffs = result.cutoffs;
			 * 
			 * if (result.score < bestScore) { bestScore = result.score; } if
			 * (bestScore < alpha) { cutoffs++; return new Result(null, alpha,
			 * positions, cutoffs); } }
			 */
		}
		return new Result(bestMove, bestScore, positions, cutoffs);
	}

	private void move(Direct direct, int[][] binArr) {
		if ((direct == Direct.UP) || (direct == Direct.DOWN)) {
			reverse(binArr);
			move(direct == Direct.UP ? Direct.LEFT : Direct.RIGHT, binArr);
			reverse(binArr);
		} else {
			for (int i = 0; i < binArr.length; i++)
				merge(binArr[i], direct);
		}
	}

	private void merge(int[] arr, Direct direct) {
		reverseDir(arr, direct);
		int empty = 0;
		for (int i = 0; i < arr.length; i++)
			if (arr[i] != 0) {
				for (int j = i + 1; j < arr.length; j++) {
					if (arr[j] != 0) {
						if (arr[i] != arr[j]){
							break;
							
						}
						
						arr[j] = 0;
						arr[i] *= 2;

						break;
					}
				}
				if (empty != 0) {
					arr[(i - empty)] = arr[i];
					arr[i] = 0;
				}
			} else {
				empty++;
			}
		reverseDir(arr, direct);
	}

	public static void reverse(int[][] binArr) {
		for (int i = 0; i < 4; i++)
			for (int j = i + 1; j < 4; j++) {
				/*
				 * binArr[i][j] ^= binArr[j][i]; binArr[i][j] ^= binArr[j][i];
				 * binArr[i][j] ^= binArr[j][i];
				 */

				binArr[i][j] = binArr[i][j] ^ binArr[j][i];
				binArr[j][i] = binArr[i][j] ^ binArr[j][i];
				binArr[i][j] = binArr[i][j] ^ binArr[j][i];

			}
	}

	private void reverseDir(int[] arr, Direct direct) {
		if (direct == Direct.RIGHT) {
			swap(arr, 0, 3);
			swap(arr, 1, 2);
		}
	}

	private void swap(int[] arr, int m, int n) {
		/*
		 * arr[m] ^= arr[n]; arr[m] ^= arr[n]; arr[m] ^= arr[n];
		 */

		arr[m] = arr[m] ^ arr[n];
		arr[n] = arr[m] ^ arr[n];
		arr[m] = arr[m] ^ arr[n];

	}

	private int[][] cloneArr(int[][] oArr) {
		int[][] arr = new int[4][4];
		for (int i = 0; i < 4; i++)
			for (int j = 0; j < 4; j++)
				arr[i][j] = oArr[i][j];
		return arr;
	}

	private boolean isMoved(int[][] oldArr, int[][] newArr) {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (oldArr[i][j] != newArr[i][j]) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean isWin(int[][] binArr) {
		for (int[] is : binArr)
			for (int i : is)
				if (i == 2048)
					return true;
		return false;
	}

	class Posit {
		public int i;
		public int j;

		public Posit(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}

	private ArrayList<Posit> getEmptyCells(int[][] binArr) {
		ArrayList list = new ArrayList();
		for (int i = 0; i < binArr.length; i++) {
			for (int j = 0; j < binArr.length; j++) {
				if (binArr[i][j] == 0) {
					list.add(new Posit(i, j));
				}
			}
		}
		return list;
	}

	// problem1:i
	public static void show(int[][] tabs) {
		/*
		 * int[][] arrayOfInt = tabs; int j = tabs.length; for (int i = 0; i <
		 * j; i++) { int[] is = arrayOfInt[i]; for (int i : is) {
		 * System.out.print(i + ","); } System.out.println(); }
		 */

		int ai[][];
		int k = (ai = tabs).length;
		for (int j = 0; j < k; j++) {
			int is[] = ai[j];
			int ai1[];
			int i1 = (ai1 = is).length;
			for (int l = 0; l < i1; l++) {
				int i = ai1[l];
				System.out.print((new StringBuilder(String.valueOf(i))).append(
						",").toString());
			}

			System.out.println();
		}

	}

	class Result {
		public Direct move;
		public double score;
		public int positions;
		public int cutoffs;

		public Result(Direct move, double score, int positions, int cutoffs) {
			this.move = move;
			this.score = score;
			this.positions = positions;
			this.cutoffs = cutoffs;
		}
	}

}
