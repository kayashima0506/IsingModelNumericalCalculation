package io.github.IsingModel;

import java.util.Random;

public class Calc {

	public static double getEnergy(int[][] spin, int n, int j, double radian, int exField) {

		double energy = 0;
		switch (exField) {
		case 0:
			for (int i = 0; i < n - 1; i++) {
				for (int l = 0; l < n - 1; l++) {
					energy = energy - j * (spin[i][l] * spin[i + 1][l] + spin[i][l] * spin[i][l + 1]);
				}
			}
			break;
		case 1:
			for (int i = 0; i < n - 1; i++) {
				for (int l = 0; l < n - 1; l++) {
					energy = energy - j * (spin[i][l] * spin[i + 1][l] + spin[i][l] * spin[i][l + 1]) + Math.cos(radian);
				}
			}
			break;
		case 2:
			for (int i = 0; i < n - 1; i++) {
				for (int l = 0; l < n - 1; l++) {
					energy = energy - j * (spin[i][l] * spin[i + 1][l] + spin[i][l] * spin[i][l + 1]) + Math.cosh(radian);
				}
			}
			break;
		}

		return energy;
	}

	public static double getOverallEnergy(int[][] spin, double sa2, int n, int m_min, int m_max, int step) {

		if (step > m_min) {
			double sa = 0;
			for (int i = 0; i < n; i++) {
				for (int l = 0; l < n; l++) {
					sa += spin[i][l];
				}
			}
			sa = sa / (n * n);
			sa2 = Math.abs(sa) / (m_max - m_min) + sa2;
		}

		return sa2;
	}

	public static int[][] selectedState(int[][] spin, double energyDif, double temp, int x, int y, int saveState) {

		// δE(energyDif) >= 0のとき、確率P...で採択する
		if (energyDif >= 0) {
			double prob = Math.exp(-1.0 * energyDif / temp);
			Random rand = new Random();
			double randNum = rand.nextDouble();
			if (randNum > prob) {
				spin[x][y] = saveState;
			}
		}
		return spin;
	}
}
