package main;

public class PrintQuestion {
	
//	public static void printQuestion(int[][] ans, int[][] user, int numX) {
	public static void printQuestion(int[][] ans, int numX) {
		// x hint
		String[] hintArrX = getHintArrX(ans, numX);
		// y hint
		String[] hintArrY = getHintArrY(ans, numX);

		int hintZone = (ans.length + 1) / 2;
		int entireZone = (ans.length + 1) / 2 + ans.length;
		int len = (ans.length + 1) / 2;

		for (int i = 0; i < entireZone; i++) {
			for (int j = 0; j < entireZone; j++) {
				
				if (i < len) {
					String[] a = hintArrY[j - len].split(",");
					if (a.length > i) {
						System.out.print(a[i] + " ");
					} else {
						System.out.print("  ");
					}
				} else {
					String[] a = hintArrX[i - len].split(",");
					if (a.length > j) {
						System.out.print(a[j] + " ");
					} else {
						System.out.print("  ");
					}
				}
			}
			System.out.println();
		}

	}

	public static String[] getHintArrX(int[][] ans, int numX) {
		int cntNumX = 0;
//		int numX = 5;
		String[] hintArrX = new String[numX];
		for (int i = 0; i < hintArrX.length; i++) {
			hintArrX[i] = "";
		}
//		String[] hintArrX = { "", "", "", "", "" };
		for (int i = 0; i < ans.length; i++) {
			for (int j = 0; j < hintArrX.length; j++) {
				if (ans[i][j] == 1) {
					cntNumX++;
					if (j == numX - 1 && ans[i][numX - 1] == 1) {
						hintArrX[i] += cntNumX + ",";
					}
				} else if (ans[i][j] == 0) {
					if (cntNumX != 0) {
						hintArrX[i] += cntNumX + ",";
					}
					cntNumX = 0;
				}
			}
			cntNumX = 0;
		}
		return hintArrX;
	}

	
	public static String[] getHintArrY(int[][] ans, int numY) {
		int cntNumY = 0;
//		int numY = 5;
		String[] hintArrY = new String[numY];
		for (int i = 0; i < hintArrY.length; i++) {
			hintArrY[i] = "";
		}
//		String[] hintArrY = { "", "", "", "", "" };
		for (int i = 0; i < ans.length; i++) {
			for (int j = 0; j < hintArrY.length; j++) {
				if (ans[j][i] == 1) {
					cntNumY++;
					if (j == numY - 1 && ans[j][i] == 1) {
						hintArrY[i] += cntNumY + ",";
					}
				} else if (ans[j][i] == 0) {
					if (cntNumY != 0) {
						hintArrY[i] += cntNumY + ",";
					}
					cntNumY = 0;
				}
			}
			cntNumY = 0;
		}
		return hintArrY;
	}
	
 	// 답데이터 이중배열로 변경
 	public static int[][] arrMake(String ans, int levelNumber) {
 		int[][] res = new int[levelNumber][levelNumber];
 		String[] arr = ans.split(",");

 		for (int i = 0; i < res.length; i++) {
 			String[] arr3 = arr[i].split("");
 			for (int j = 0; j < arr.length; j++) {
 				res[i][j] = Integer.parseInt(arr3[j]);
 //				System.out.print(res[i][j] + " ");
 			}
 //			System.out.println();
 		}
 		return res;
 	}
	
	
}
