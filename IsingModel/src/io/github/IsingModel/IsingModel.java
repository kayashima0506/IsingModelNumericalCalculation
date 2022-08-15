package io.github.IsingModel;

import java.util.Random;

public class IsingModel {
	public static void main(String[] args) {
		
		// 各パラメータ設定 --------------------------------------------------------------------------------------------
		// n...N*Nのスピン// j...結合定数// m_min...解の下限 // m_max...解の上限
		short n = 100;
		short j = 1;
		int m_min = 1000000;
		int m_max = 1100000;

		// 温度を設定(0.1ずつ上げて計測)
		// 固定する場合は"fixedTemp"をtrueに設定
		double temp = 1.0;
		boolean fixedTemp = false;

		// 外場を加えるときに設定(外場なし：0、cos：1、cosh：2)
		// 加える場合はcos/coshのxを設定(0<=x<360)
		short exField = 0;
		short x = 0;
		
		// -------------------------------------------------------------------------------------------------------------

		// 以下、数値計算処理
		// ランダムにスピンの向きを決める
		int[][] spin = SetSpin.setSpinDirections(n);
		
		if(fixedTemp) {
			if(exField ==0) {
				// 温度変え外場なし
				Temp.changeTemp(temp, n, spin, j, m_min, m_max);
			}else if(exField ==1) {
				// 温度変え外場cos
				Temp.changeTemp(temp, n, spin, j, m_min, m_max);
			}else if(exField ==2) {
				// 温度変え外場cosh
				Temp.changeTemp(temp, n, spin, j, m_min, m_max);
			}
		}else {
			if(exField ==0) {
				// 温度固定外場なし
				Temp.fixedTemp(temp, n, spin, j, m_min, m_max);
			}else if(exField ==1) {
				// 温度固定外場cos
				Temp.fixedTemp(temp, n, spin, j, m_min, m_max);
			}else if(exField ==2) {
				// 温度固定外場cosh
				Temp.fixedTemp(temp, n, spin, j, m_min, m_max);
			}
		}
		// 温度を変えながらエネルギーを計算する
		
		for (int tempIndex = 0; tempIndex < 30; tempIndex++) {
			double sa2 = 0;
			temp += 0.1;

			for (int step = 1; step < m_max; step++) {

				// エネルギーの計算
				// 隣のスピンが存在しない場合(端のスピン)は、0番目のスピンを参照する
				double energy = 0;
				for (int i = 0; i < n; i++) {

					for (int l = 0; l < n; l++) {
						if (i == n - 1 && l == n - 1) {
							energy = energy - j * (spin[i][l] * spin[0][l] + spin[i][l] * spin[i][0]);
						} else if (i == n - 1) {
							energy = energy - j * (spin[i][l] * spin[0][l] + spin[i][l] * spin[i][l + 1]);
						} else if (l == n - 1) {
							energy = energy - j * (spin[i][l] * spin[i + 1][l] + spin[i][l] * spin[i][0]);
						} else {
							energy = energy - j * (spin[i][l] * spin[i + 1][l] + spin[i][l] * spin[i][l + 1]);
						}
					}
				}
				Random rand = new Random();
				// ランダムにスピンを選び、反転させる(判定前の状態は保持しておく)
				int x1 = rand.nextInt(100) % n;
				int y = rand.nextInt(100) % n;
				int saveState = spin[x][y];
				spin[x][y] *= -1;

				// 反転後のエネルギーを計算
				// 隣のスピンが存在しない場合(端のスピン)は、0番目のスピンを参照する
				double flippedEnergy = 0;
				for (int i = 0; i < n; i++) {
					for (int l = 0; l < n; l++) {
						if (i == n - 1 && l == n - 1) {
							flippedEnergy = flippedEnergy - j * (spin[i][l] * spin[0][l] + spin[i][l] * spin[i][0]);
						} else if (i == n - 1) {
							flippedEnergy = flippedEnergy - j * (spin[i][l] * spin[0][l] + spin[i][l] * spin[i][l + 1]);
						} else if (l == n - 1) {
							flippedEnergy = flippedEnergy - j * (spin[i][l] * spin[i + 1][l] + spin[i][l] * spin[i][0]);
						} else {
							flippedEnergy = flippedEnergy
									- j * (spin[i][l] * spin[i + 1][l] + spin[i][l] * spin[i][l + 1]);
						}
					}
				}

				// エネルギーの差を計算し、反転後の新しい状態を採択するかどうかを判定する
				double energyDif = flippedEnergy - energy;
				// δE(energyDif) >= 0のとき、確率P...で採択する
				if (energyDif >= 0) {
					double prob = Math.exp(-1.0 * energyDif / temp);
					double randNum = rand.nextDouble();
					if (randNum > prob) {
						spin[x][y] = saveState;
					}
				}

				// 全体のエネルギー(向き)を算出する
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

			}

			System.out.println(String.format("%.6f", sa2));
		}

	}
}
