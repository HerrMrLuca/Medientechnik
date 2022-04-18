public class SignalAlgorithm {
	static double[] makeSin(int n) {
		double[] A = new double[n];
		for (int i = 0; i < n; i++) {
			A[i] = Math.sin(i);
		}
		return A;
	}

	static double[] makeCos(int n) {
		double[] A = new double[n];

		for (int i = 0; i < A.length; i++) {
			A[i] = Math.cos(i);
		}
		return A;
	}

	static double[] makeSin(int n, int T) {
		double[] A = new double[n];
		for (int i = 0; i < n; i++) {
			A[i] = Math.sin(i * 2 * (Math.PI / T));
		}
		return A;
	}

	static double[] makeCos(int n, int T) {
		double[] A = new double[n];
		for (int i = 0; i < A.length; i++) {
			A[i] = Math.cos(i * 2 * (Math.PI / T));
		}
		return A;
	}

	static double[] makeSin(int n, double T) {
		double[] g = new double[n];
		for (int i = 0; i < n; i++)
			g[i] = Math.sin(2 * Math.PI * i / T);
		return g;
	}

	static double[] makeCos(int n, double T) {
		double[] g = new double[n];
		for (int i = 0; i < n; i++)
			g[i] = Math.cos(2 * Math.PI * i / T);
		return g;
	}

	static double[] add(double[] a, double[] b) {

		double[] A;
		if (a.length < b.length) {
			A = new double[b.length];
			for (int i = 0; i < b.length; i++) {
				if (i < a.length) {
					A[i] = a[i] + b[i];
				} else {
					A[i] = b[i];
				}
			}
		} else {
			A = new double[a.length];
			for (int i = 0; i < a.length; i++) {
				if (i < b.length) {
					A[i] = a[i] + b[i];
				} else {
					A[i] = a[i];
				}
			}
		}
		return A;


	}

	static double[] quantize(double[] g, int K) {
		int Q2 = 1 << K - 1;
		System.out.println(Q2);
		double[] gBar = mult(g, Q2);

		for (int i = 0; i < g.length; i++) {
			gBar[i] = Math.round(gBar[i]);
		}

		gBar = div(gBar, Q2);

		return gBar;
	}

	static double[] mult(double[] g, double m) {
		double[] gBar = new double[g.length];

		for (int i = 0; i < g.length; i++) {
			gBar[i] = g[i] * m;
		}
		return gBar;
	}

	static double[] div(double[] g, int d) {
		double[] gBar = new double[g.length];

		for (int i = 0; i < g.length; i++) {
			gBar[i] = g[i] / d;
		}
		return gBar;
	}

	static double[] sub(double[] g, double[] q) {
		double[] gBar = new double[g.length];

		for (int i = 0; i < g.length; i++) {
			gBar[i] = g[i] - q[i];
		}
		return gBar;
	}

	static double deltaquantize(double[] g) {
		double delta = 0;

		for (int i = 0; i < g.length; i++) {
			delta += Math.pow(g[i], 2);
		}

		return delta;
	}

	static double deltaquantize(double[] g, double[] q) {
		double[] gBar = sub(g, q);
		double delta = 0;

		for (int i = 0; i < gBar.length; i++) {
			delta += Math.pow(gBar[i], 2);
		}

		return delta;
	}

	static double SNR(double[] g, double[] q) {
		return deltaquantize(g) / deltaquantize(g, q);
	}

	static double db(double SNR) {
		return 10 * Math.log10(SNR);
	}
}