package io.github.IsingModel;

public class Output {

	public static void outmsg(double result, double temp) {

		//		System.out.println(String.format("%.6f", result)); // resultだけ表示したい
		System.out.println(String.format("T=%.1f : %.6f", temp, result));
	}
}
