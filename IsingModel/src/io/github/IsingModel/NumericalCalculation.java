package io.github.IsingModel;

import java.util.Random;

public class NumericalCalculation {

	public static void ising(short n, short j, int m_min, int m_max, double temp, boolean isTemp, short tempLength, short exField,
			short x) {

		double result = 0;

		// ランダムにスピンの向きを設定する
		int[][] spin = SetSpin.setSpinDirections(n);

		if (isTemp) {
			for (int tempIndex = 0; tempIndex < tempLength; tempIndex++) {
				result = 0;
				result = common(temp, n, spin, j, m_min, m_max, exField, x);
				Output.outmsg(result, temp);
				temp += 0.1;
			}

		} else {
			result = common(temp, n, spin, j, m_min, m_max, exField, x);
			Output.outmsg(result, temp);
		}

	}

	public static double common(double temp, short n, int[][] spin, int j, int m_min, int m_max, int exField, short x) {

		double result = 0;
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
