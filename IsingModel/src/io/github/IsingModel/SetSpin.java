package io.github.IsingModel;

import java.util.Random;

public class SetSpin {
	
	public static int[][] setSpinDirections(int n){
		
		int[][] spin = new int[n][n];
		Random rand = new Random();
		for (int i = 0; i < n; i++) {
			for (int l = 0; l < n; l++) {
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
}
