package io.github.IsingModel;

public class IsingModel {
	public static void main(String[] args) {

		// 各パラメータ設定 --------------------------------------------------------------------------------------------
		// n...N*Nのスピン// j...結合定数// m_min...解の下限 // m_max...解の上限
		short n = 50;
		short j = 1;
		int m_min = 1000000;
		int m_max = 1200000;

		// 固定する場合は"isTemp"をfalseに設定
		// 温度を設定(0.1ずつ上げて計測)
		boolean isTemp = true;
		double temp = 1.0;
		short tempLength = 20;

		// 外場を加えるときに設定(外場なし：0、cos：1、cosh：2)
		// 加える場合はcos/coshのxを設定(0<=x<360)
		short exField = 0;
		short x = 0;

		// -------------------------------------------------------------------------------------------------------------

		// 数値計算処理
		NumericalCalculation.ising(n, j, m_min, m_max, temp, isTemp, tempLength, exField, x);

	}
}
