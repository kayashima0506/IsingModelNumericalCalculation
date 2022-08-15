package io.github.IsingModel;

import java.util.Random;

public class Temp {

	public static void fixedTemp(double temp, short n, int[][] spin, int j, int m_min, int m_max) {

		double sa2 = 0;

		for (int step = 1; step < m_max; step++) {
			// エネルギーの計算
			double energy = Calc.getEnergy(spin, n, j);

			// ランダムにスピンを選び、反転させる(判定前の状態は保持しておく)
			Random rand = new Random();
			int i = rand.nextInt(100) % n;
			int l = rand.nextInt(100) % n;
			int saveState = spin[i][l];
			spin[i][l] *= -1;

			// 反転後のエネルギーを計算
			double flippedEnergy = Calc.getEnergy(spin, n, j);

			// エネルギーの差を計算し、反転後の新しい状態を採択するかどうかを判定する
			double energyDif = flippedEnergy - energy;
			spin = Calc.selectedState(spin, energyDif, temp, i, l, saveState);

			// 全体のエネルギー(向き)を算出する
			sa2 = Calc.getOverallEnergy(spin, sa2, n, m_min, m_max, step);
		}

		System.out.println(String.format("%.6f", sa2));

	}
	
	public static void fixedTempAddCosExField(double temp, short n, int[][] spin, int j, int m_min, int m_max, short x) {

		double sa2 = 0;
		double radian = x * Math.PI / 180;

		for (int step = 1; step < m_max; step++) {
			// エネルギーの計算
			double energy = Calc.getEnergyAddCosExField(spin, n, j, radian);

			// ランダムにスピンを選び、反転させる(判定前の状態は保持しておく)
			Random rand = new Random();
			int i = rand.nextInt(100) % n;
			int l = rand.nextInt(100) % n;
			int saveState = spin[i][l];
			spin[i][l] *= -1;

			// 反転後のエネルギーを計算
			double flippedEnergy = Calc.getEnergyAddCosExField(spin, n, j, radian);

			// エネルギーの差を計算し、反転後の新しい状態を採択するかどうかを判定する
			double energyDif = flippedEnergy - energy;
			spin = Calc.selectedState(spin, energyDif, temp, i, l, saveState);

			// 全体のエネルギー(向き)を算出する
			sa2 = Calc.getOverallEnergy(spin, sa2, n, m_min, m_max, step);
		}

		System.out.println(String.format("%.6f", sa2));

	}

	public static void changeTemp(double temp, short n, int[][] spin, int j, int m_min, int m_max) {

		for (int tempIndex = 0; tempIndex < 30; tempIndex++) {
			double sa2 = 0;
			temp += 0.1;

			for (int step = 1; step < m_max; step++) {
				// エネルギーの計算
				double energy = Calc.getEnergy(spin, n, j);

				// ランダムにスピンを選び、反転させる(判定前の状態は保持しておく)
				Random rand = new Random();
				int i = rand.nextInt(100) % n;
				int l = rand.nextInt(100) % n;
				int saveState = spin[i][l];
				spin[i][l] *= -1;
				
				// 反転後のエネルギーを計算
				double flippedEnergy = Calc.getEnergy(spin, n, j);

				// エネルギーの差を計算し、反転後の新しい状態を採択するかどうかを判定する
				double energyDif = flippedEnergy - energy;
				spin = Calc.selectedState(spin, energyDif, temp, i, l, saveState);

				// 全体のエネルギー(向き)を算出する
				sa2 = Calc.getOverallEnergy(spin, sa2, n, m_min, m_max, step);
			}

			System.out.println(String.format("%.6f", sa2));
		}
	}
	
	public static void changeTempAddExField(double temp, short n, int[][] spin, int j, int m_min, int m_max, short x) {

		for (int tempIndex = 0; tempIndex < 30; tempIndex++) {
			double sa2 = 0;
			temp += 0.1;
			double radian = x * Math.PI / 180;

			for (int step = 1; step < m_max; step++) {
				// エネルギーの計算
				double energy = Calc.getEnergyAddCosExField(spin, n, j, radian);

				// ランダムにスピンを選び、反転させる(判定前の状態は保持しておく)
				Random rand = new Random();
				int i = rand.nextInt(100) % n;
				int l = rand.nextInt(100) % n;
				int saveState = spin[i][l];
				spin[i][l] *= -1;

				// 反転後のエネルギーを計算
				double flippedEnergy = Calc.getEnergyAddCosExField(spin, n, j, radian);

				// エネルギーの差を計算し、反転後の新しい状態を採択するかどうかを判定する
				double energyDif = flippedEnergy - energy;
				spin = Calc.selectedState(spin, energyDif, temp, i, l, saveState);

				// 全体のエネルギー(向き)を算出する
				sa2 = Calc.getOverallEnergy(spin, sa2, n, m_min, m_max, step);
			}

			System.out.println(String.format("%.6f", sa2));
		}
	}
}
