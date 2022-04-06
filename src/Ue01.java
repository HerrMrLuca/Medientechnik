import org.math.plot.PlotPanel;
import plotting.StickPlot;
import plotting.Plotting;

/**
 * MTD129 Digitale Medientechnik 2
 * Übung 1
 * @author Gianluca Wassermeyer
 */

public class Ue01 {

	public static void main(String[] args) {

		// Aufgabe 1.1.a
		{
			double[] A = SignalAlgorithm.makeSin(30);
			PlotPanel panel = Plotting.create("Aufgabe 1.1.a");
			panel.setAxisLabels("i", "g(i)");
			panel.addPlot(new StickPlot("Sinus n=30", A));
			Plotting.show(panel, -1, 1);
		}

		// Aufgabe 1.1.b
		{
			double[] A =SignalAlgorithm.makeCos(30);
			PlotPanel panel = Plotting.create("Aufgabe 1.1.b");
			panel.setAxisLabels("i", "g(i)");
			panel.addPlot(new StickPlot("Cosinus n=30", A));
			Plotting.show(panel, -1, 1);
		}
		
		// Aufgabe 1.2.a
		{
			double[] A = SignalAlgorithm.makeSin(100, 50);
			double[] B = SignalAlgorithm.makeCos(100, 50);
			
			PlotPanel panel = Plotting.create("Aufgabe 1.2.a");
			panel.setAxisLabels("i", "g(i)");
			panel.addPlot(new StickPlot("Sinus n=100", A));
			panel.addPlot(new StickPlot("Cosinus n=100", B));
			Plotting.show(panel, -1, 1);
		}
		
		// Aufgabe 1.3.a
		{
			double[] sin100 = SignalAlgorithm.makeSin(100, 100);
			double[] cos100 = SignalAlgorithm.makeCos(100, 100);
			double[] sincos100 = SignalAlgorithm.add(sin100, cos100);
			PlotPanel panel = Plotting.create("Aufgabe 1.3.a");
			panel.setAxisLabels("i", "g(i)");
			panel.addPlot(new StickPlot("Sinus n=100", sin100));
			panel.addPlot(new StickPlot("Cosinus n=100", cos100));
			panel.addPlot(new StickPlot("sin*cos n=100", sincos100));
			Plotting.show(panel);
		}
	}
}
