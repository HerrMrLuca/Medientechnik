import org.math.plot.PlotPanel;

import audio.AudioPlayer;
import audio.AudioWriter;
import plotting.Plotting;
import plotting.StickPlot;

/**
 * MTD129 Digitale Medientechnik 2 / Audio demo: Wir erzeugen ein diskretes
 * Sinussignal mit folgenden Parametern: Abtastfrequenz fs = 10.000 Hz
 * Signalfrequenz f = 500 Hz Signaldauer D = 1,0 s
 * 
 * @author WB
 */
public class Ue02 {

	public static void main(String[] args) {

		{ // Aufgabe 2.1
			double[] A = SignalAlgorithm.makeSin(100, 100.0);

			int[] K = { 2, 3, 4, 6 };
			for (int i = 0; i < K.length; i++) {
				double[] B = SignalAlgorithm.quantize(A, K[i]);

				PlotPanel panel = Plotting.create("Aufgabe 2.1 " + K[i] + " Bits");
				panel.setAxisLabels("i", "g(i)");
				panel.addPlot(new StickPlot("Sinus n=100 T=100", A));
				panel.addPlot(new StickPlot("QSinus n=100 T=100", B));
				// Plotting.show(panel, -1, 1);
			}
		}

		{ // Aufgabe 2.2
			double[] A = SignalAlgorithm.makeSin(100, 100.0);
			int[] K = { 2, 4, 8, 16 };

			for (int i = 0; i < K.length; i++) {
				double[] B = SignalAlgorithm.quantize(A, K[i]);
				double[] C = SignalAlgorithm.sub(A, B);

				PlotPanel panel = Plotting.create("Aufgabe 2.2 " + K[i] + " Bits");
				panel.setAxisLabels("i", "g(i)");
				panel.addPlot(new StickPlot("Sinus n=100 T=100", A));
				panel.addPlot(new StickPlot("QSinus n=100 T=100", B));
				panel.addPlot(new StickPlot("DeltaSinus n=100 T=100", C));
				Plotting.show(panel, -1, 1);
			}
		}

		// Aufgabe 2.3
		{
			double[] A = SignalAlgorithm.makeSin(100, 100.0);
			int[] K = { 2, 4, 8, 16 };

			for (int i = 0; i < K.length; i++) {
				double[] B = SignalAlgorithm.quantize(A, K[i]);
				double SNR = SignalAlgorithm.SNR(A, B);
				double db = SignalAlgorithm.db(SNR);
				System.out.println(SignalAlgorithm.SNR(A, B) + " " + db);
				
			}
		}

		{ // Aufgabe 2.4
			double Fa = 10000;
			double Fs = 220; // Abtastfrequenz [Hz] - fix
			double Ts = 2;
			double N = Fa * Ts; // 1 Sekunde entspricht 10.000 Abtastwerten
			double T = Fa / Fs; // Signalperiode T = 1/500 s = 20 Abtastperioden

			double[] A = SignalAlgorithm.makeSin((int) N, T); // diskretes Signal

			int[] K = { 16, 12, 8, 4, 2, 1 };

			for (int i = 0; i < K.length; i++) {
				double[] q = SignalAlgorithm.quantize(A, K[i]);

				// Audio-Ausgabe:
				AudioPlayer player = new AudioPlayer(Fa);
				player.play(q);
				player.close();

				// Ausgabe in eine Audio-Datei:
				AudioWriter writer = new AudioWriter(Fa);
				writer.writeToWavFile(q, "500Hz" + K[i] + " Bits" + ".wav");
			}
		}
	}

	/**
	 * Das kennen wir schon aus der 1. Übung:
	 * 
	 * @param n Länge der Signalfolge
	 * @param T Periode des Sinussignals (in Abtastschritten)
	 * @return
	 */
	
}
