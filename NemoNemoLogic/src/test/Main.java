package test;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		
//		Frame1 a = new Frame1();
		
//        String str = "abcde";
//
//        String[] aaa =str.split("");
//        for (int i = 0; i < aaa.length; i++) {
//			System.out.print(aaa[i] + " ");
//		}
        int num = 10;
		System.out.println();
		String res = "0100000010,1101001011,1001111001,1101111011,1110110111,1111111111,1101111011,1000110001,1100110011,0110110110";
 		int[][] ans = new int[num][num];
 		String[] arr = res.split(",");
 		for (int i = 0; i < ans.length; i++) {
 			String[] arr3 = arr[i].split("");
 			for (int j = 0; j < arr.length; j++) {
 				ans[i][j] = Integer.parseInt(arr3[j]);
// 				System.out.print(ans[i][j] + " ");
 			}
// 			System.out.println();
 		}
		
		// x hint
		String[] hintArrX = getHintArrX(ans, num);
		// y hint
		String[] hintArrY = getHintArrY(ans, num);
//		System.out.println("힌트");
		for (int i = 0; i < hintArrY.length; i++) {
//			System.out.print(hintArrX[i] + ", ");
		}
//		System.out.println();
		
		
		int hintZone = (ans.length + 1) / 2;
		int entireZone = (ans.length + 1) / 2 + ans.length;
		int len = (ans.length + 1) / 2;

		for (int i = 0; i < entireZone; i++) {
			for (int j = 0; j < entireZone; j++) {
				
				if (i < hintZone && j < hintZone) {
					System.out.print("  ");
				} else if (i >= hintZone && j >= hintZone) {
					if (ans[i - len][j - len] == 1) {
						System.out.print("■" + " ");
					} else if (ans[i - len][j - len] == 3) {
						System.out.print("X" + " ");
					} else {
						System.out.print("□" + " ");
					}

				} else {
					if (i < len) {
						String[] a = hintArrY[j - len].split(",");
						if (a.length > i) {
//							System.out.print("y : " + i + ", " + j);
							System.out.print(a[i] + " ");
						} else {
							System.out.print("  ");
						}
					} else {
						String[] a = hintArrX[i - len].split(",");
						if (a.length > j) {
//							System.out.print("x : " + i + ", " + j);
							System.out.print(a[j] + " ");
						} else {
							System.out.print("  ");
						}
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
		
		for (int i = 0; i < hintArrX.length; i++) {
			StringBuffer sb = new StringBuffer(hintArrX[i]);
	        String reversedStr = sb.reverse().toString();
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
	
}
