package io.github.IsingModel;

import java.util.Random;

public class SetSpin {

	public static int[][] setSpinDirections(int n) {

		int[][] spin = new int[n][n];
		Random rand = new Random();
		for (int i = 0; i < n - 1; i++) {
			for (int l = 0; l < n - 1; l++) {
				double randNum = rand.nextDouble();
				if (randNum > 0.5) {
					spin[i][l] = -1;
				} else {
					spin[i][l] = 1;
				}
			}
		}
		return spin;
	}

	public static int[][] setEdgeSpin(int[][] spin, int n) {

		// 隣のスピンが存在しない場合(端のスピン)は、0番目のスピンを参照させる
		for (int l = 0; l < n - 1; l++) {
			spin[n - 1][l] = spin[0][l];
		}
		for (int i = 0; i < n - 1; i++) {
			spin[i][n - 1] = spin[i][0];
		}
		return spin;
	}
}
