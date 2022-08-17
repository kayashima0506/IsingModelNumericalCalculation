package io.github.IsingModel;

import java.util.Random;

public class NumericalCalculation {

	public static void ising(short n, short j, int m_min, int m_max, double temp, boolean isTemp, short exField, short x) {
		
		double result;
		
		// ランダムにスピンの向きを決める
		int[][] spin = SetSpin.setSpinDirections(n);

		if(isTemp) {
			for (int tempIndex = 0; tempIndex < 30; tempIndex++) {
				result = common(temp, n, spin, j, m_min, m_max, x, 1);
				System.out.println(String.format("%.6f", result));
			}
			
		}else {
			result = common(temp, n, spin, j, m_min, m_max, x, 1);
			System.out.println(String.format("%.6f", result));
		}
		
	}

	public static double common(double temp, short n, int[][] spin, int j, int m_min, int m_max, short x, int exField) {

		double result = 0;
		temp += 0.1;
		double radian = x * Math.PI / 180;

		for (int step = 1; step < m_max; step++) {
			// エネルギーの計算
			double energy = Calc.getEnergy(spin, n, j, radian, exField);

			// ランダムにスピンを選び、反転させる(判定前の状態は保持しておく)
			Random rand = new Random();
			int i = rand.nextInt(100) % n;
			int l = rand.nextInt(100) % n;
			int saveState = spin[i][l];
			spin[i][l] *= -1;

			// 反転後のエネルギーを計算
			double flippedEnergy = Calc.getEnergy(spin, n, j, radian, exField);

			// エネルギーの差を計算し、反転後の新しい状態を採択するかどうかを判定する
			double energyDif = flippedEnergy - energy;
			spin = Calc.selectedState(spin, energyDif, temp, i, l, saveState);

			// 全体のエネルギー(向き)を算出する
			result = Calc.getOverallEnergy(spin, result, n, m_min, m_max, step);
		}

		return result;

	}
}
