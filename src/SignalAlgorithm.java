public class SignalAlgorithm {

	/** take n amount and calculate the sin for every i*/
	static double[] makeSin(int n) {
		double[] A = new double[n];
		for (int i = 0; i < n; i++) {
			A[i] = Math.sin(i);
		}
		return A;
	}

	/** take n amount and calculate the cos for every i*/
	static double[] makeCos(int n) {
		double[] A = new double[n];

		for (int i = 0; i < A.length; i++) {
			A[i] = Math.cos(i);
		}
		return A;
	}

	/** take n amount and calculate the sin for every i for T seconds*/
	static double[] makeSin(int n, int T) {
		double[] A = new double[n];
		for (int i = 0; i < n; i++) {
			A[i] = Math.sin(i * 2 * (Math.PI / T));
		}
		return A;
	}

	/** take n amount and calculate the cos for every i for T seconds*/
	static double[] makeCos(int n, int T) {
		double[] A = new double[n];
		for (int i = 0; i < A.length; i++) {
			A[i] = Math.cos(i * 2 * (Math.PI / T));
		}
		return A;
	}

	/** take n amount and calculate the sin for every i for T seconds*/
	static double[] makeSin(int n, double T) {
		double[] g = new double[n];
		for (int i = 0; i < n; i++)
			g[i] = Math.sin(2 * Math.PI * i / T);
		return g;
	}

	/** take n amount and calculate the cos for every i for T seconds*/
	static double[] makeCos(int n, double T) {
		double[] g = new double[n];
		for (int i = 0; i < n; i++)
			g[i] = Math.cos(2 * Math.PI * i / T);
		return g;
	}

	/** add to Sin-functions together */
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

	/** quantize a Sin-function with K  */
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

	/** multiplication between Sin-functions and a double */
	static double[] mult(double[] g, double m) {
		double[] gBar = new double[g.length];

		for (int i = 0; i < g.length; i++) {
			gBar[i] = g[i] * m;
		}
		return gBar;
	}

	/** multiplication between Sin-functions and a double */
	static double[] mult(double[] g, double[] m) {
		double[] gBar = new double[g.length];

		for (int i = 0; i < g.length; i++) {
			gBar[i] = g[i] * m[i];
		}
		return gBar;
	}

	/** divide between Sin-functions and a double */
	static double[] div(double[] g, int d) {
		double[] gBar = new double[g.length];

		for (int i = 0; i < g.length; i++) {
			gBar[i] = g[i] / d;
		}
		return gBar;
	}

	/** subtraction between two Sin-functions */
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

	static double[] sinSound(double f, double fs, double D, double a) {
		double T = fs/ f;
		int n = (int) (fs * D);

		return SignalAlgorithm.mult(SignalAlgorithm.makeSin(n, T), a);
	}

	static double[] cosSound(double f, double fs, double D, double a) {
		double T = fs/ f;
		int n = (int) (fs * D);

		return SignalAlgorithm.mult(SignalAlgorithm.makeCos(n, T), a);
	}

	static double[] join(double[] A, double[] B) {
		double[] C = new double[A.length + B.length];

		System.arraycopy(A, 0, C, 0, A.length);
		System.arraycopy(B, 0, C, A.length, B.length);
		return  C;
	}

	static double[] compose(double[] A, int[][] V, double fs, double a) {
		for (int i = 0; i < V.length; i++) {

			double[] B = midiSound(V[i][0], fs, TG * V[i][1] / V[i][2], a);
			A = join(A, B);
		}
		return A;
	}

	static double midi(double m) {
		return 440 * Math.pow(2, (m - 69) / 12 );
	}

	static double[] midiSound(int m, double fs, double D, double a) {
		return sinSound(midi(m), fs, D, a);
	}

	static final double FS = 10000;			// [Hz]
	static final double Kammerton = 440;	// [Hz]
	static final double TG = 2.0;			// [s]

	static int[][] stimme1 = {
			{62, 1, 4},         // MidiCode=62, Note=1/4
			{67, 1, 4}, {67, 1, 8}, {69, 1, 8}, {71, 1, 4}, {67, 1, 4},
			{74, 1, 2}, {71, 3, 8}, {71, 1, 8},
			{72, 1, 4}, {74, 1, 8}, {72, 1, 8}, {71, 1, 8}, {72, 1, 8}, {74, 1, 4},
			{69, 1, 8}, {67, 1, 8}, {69, 1, 8}, {71, 1, 8}, {69, 1, 4}, {62, 1, 4},
			{67, 1, 4}, {67, 1, 8}, {69, 1, 8}, {71, 1, 4}, {67, 1, 4},
			{74, 1, 2}, {71, 3, 8}, {71, 1, 8},
			{72, 1, 8}, {74, 1, 8}, {71, 1, 8}, {72, 1, 8}, {69, 3, 8}, {67, 1, 8},
			{67, 1, 2}
	};

	static int[][] stimme2 = {
			{ 67, 1, 4 }, { 67, 1, 2 }, { 67, 1, 4 }, { 67, 1, 4 }, { 66, 1, 4 },
			{ 62, 1, 4 }, { 67, 3, 8 }, { 67, 1, 8 }, { 60, 1, 4 }, { 66, 1, 4 },
			{ 67, 1, 8 }, { 69, 1, 8 }, { 71, 1, 8 }, { 72, 1, 8 }, { 74, 1, 2 },
			{ 57, 1, 8 }, { 60, 1, 8 }, { 66, 1, 8 }, { 62, 1, 8 }, { 67, 1, 2 },
			{ 67, 1, 4 }, { 67, 1, 4 }, { 66, 1, 4 }, { 62, 1, 4 }, { 67, 3, 8 },
			{ 67, 1, 8 }, { 72, 1, 4 }, { 67, 1, 4 }, { 72, 1, 4 }, { 62, 1, 4 },
			{ 67, 3, 4 }
	};

	/** Diskrete forward Fouriertransfromation with real Numbers
	 * Taking in g Array and calculate the real part */
	public static double[] dftRe(double[] g) {
		int M = g.length;
		double[] GRe = new double[M];
		for (int m = 0; m < M; m++) {
			for (int u = 0; u < M; u++) {
				GRe[m] += g[u] * Math.cos(2 * Math.PI * m * u / M);
			}
			GRe[m] = GRe[m] / M;

		}
		return GRe;
	}

	/** Diskrete forward Fouriertransfromation with imaginary Numbers
	 * Taking in g Array and calculate the imaginary part */
	public static double[] dftIm(double[] g) {
		int M = g.length;
		double[] GIm = new double[M];
		for (int m = 0; m < M; m++) {
			for (int u = 0; u < M; u++) {
				GIm[m] -= g[u] * Math.sin(2 * Math.PI * m * u / M);
			}
			GIm[m] = GIm[m] / M;
		}
		return GIm;
	}

	/** Diskrete backwards Fouriertransfromation with real Numbers
	 * Taking in the real and imaginary part and calculate the real part of g */
	static double[] invDftRe(double[] GRe, double[] GIm) {
		int M = GRe.length;
		double[] g = new double[M];
		for (int u = 0; u < M; u++) {
			for (int m = 0; m < M; m++) {
				g[u] += GRe[m] * Math.cos(2 * Math.PI * m * u / M)
						- GIm[m] * Math.sin(2 * Math.PI * m * u / M);
			}
		}
		return g;
	}

	/** Diskrete backwards Fouriertransfromation with imaginary Numbers
	 * Taking in the real and imaginary part and calculate the imaginary part of g */
	static double[] invDftIm(double[] GRe, double[] GIm) {
		int M = GRe.length;
		double[] g = new double[M];
		for (int u = 0; u < M; u++) {
			for (int m = 0; m < M; m++) {
				g[u] -= GIm[m] * Math.cos(2 * Math.PI * m * u / M)
						+ GRe[m] * Math.sin(2 * Math.PI * m * u / M);
			}
		}
		return g;
	}

	/** taking the average from a function */
	static double avg(double[] g) {
		return sum(g) / g.length;
	}

	/** taking the sum from a function */
	static double sum(double[] g) {
		double sum = 0;
		for (double v : g) {
			sum += v;
		}
		return sum;
	}

	static double[] mag(double[] A, double[] B) {
		double[] r = new double[A.length];
		for (int k = 0; k < A.length; k++) {
			r[k] = Math.sqrt(A[k] * A[k] + B[k] * B[k]);
		}
		return r;
	}
}