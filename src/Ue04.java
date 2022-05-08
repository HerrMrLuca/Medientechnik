import audio.AudioPlayer;
import fft.FastFourierTransform;
import org.math.plot.PlotPanel;
import plotting.LinePlot;
import plotting.Plotting;
import plotting.StickPlot;

import java.util.Arrays;

public class Ue04 {
    public static void main(String[] args) {

//        { // Aufgabe 4.1
//            double[] g = {1,3,5,7,9,8,6,4,2,0}; // 𝑔re = (1, 3, ..), 𝑔im = (0, 0, ..)
//            for (int u = 0; u < g.length; u++) {
//                System.out.format("g[%d] = %.6f\n", u, g[u]);
//            }
//
//            double[] GRe = SignalAlgorithm.dftRe(g); // 𝐺re
//            double[] GIm = SignalAlgorithm.dftIm(g); // 𝐺im
//            System.out.println("Fourier-Koeffizenten GRe[u], GIm[i]");
//            for (int u = 0; u < g.length; u++) {
//                System.out.format("GRe[%d] = %.6f\t GIm[%d] = %.6f\n", u, GRe[u], u , GIm[u]);
//            }
//
//            double[] gRe2 = SignalAlgorithm.invDftRe(GRe,GIm); // 𝑔re = 𝑔re?
//            double[] gIm2 = SignalAlgorithm.invDftIm(GRe,GIm); // 𝑔im = 𝑔im = (0, . . .)?
//
//            System.out.println("Rücktransformiertes Signal GRe[u], GIm[i]");
//            for (int m = 0; m < g.length; m++) {
//                System.out.format("gRe2[%d] = %.6f\t gIm2[%d] = %.6f\n", m, gRe2[m], m , gIm2[m]);
//            }
//        }
//
//        { // Aufgabe 4.2.ab
//            double[] g = {1,3,5,7,9,8,6,4,2,0}; // 𝑔re = (1, 3, ..), 𝑔im = (0, 0, ..)
//            for (int u = 0; u < g.length; u++) {
//                System.out.format("g[%d] = %.6f\n", u, g[u]);
//            }
//            System.out.println("sum = " + SignalAlgorithm.avg(g));
//
//            double[] GRe = SignalAlgorithm.dftRe(g); // 𝐺re
//            double[] GIm = SignalAlgorithm.dftIm(g); // 𝐺im
//            GRe[0] = 0;
//            System.out.println("Fourier-Koeffizenten GRe[u], GIm[i]");
//            for (int u = 0; u < g.length; u++) {
//                System.out.format("GRe[%d] = %.6f\t GIm[%d] = %.6f\n", u, GRe[u], u , GIm[u]);
//            }
//
//            double[] gRe2 = SignalAlgorithm.invDftRe(GRe,GIm); // 𝑔re = 𝑔re?
//            double[] gIm2 = SignalAlgorithm.invDftIm(GRe,GIm); // 𝑔im = 𝑔im = (0, . . .)?
//
//            System.out.println("Rücktransformiertes Signal GRe[u], GIm[i]");
//            for (int m = 0; m < g.length; m++) {
//                System.out.format("gRe2[%d] = %.6f\t gIm2[%d] = %.6f\n", m, gRe2[m], m , gIm2[m]);
//            }
//            System.out.println("sum = " + Math.round(SignalAlgorithm.avg(gRe2)));
//        }
//
//        { // Aufgabe 4.2.c
//            double[] g = {1,3,5,7,9,8,6,4,2,0}; // 𝑔re = (1, 3, ..), 𝑔im = (0, 0, ..)
//            for (int u = 0; u < g.length; u++) {
//                System.out.format("g[%d] = %.6f\n", u, g[u]);
//            }
//
//            double[] GRe = SignalAlgorithm.dftRe(g); // 𝐺re
//            double[] GIm = SignalAlgorithm.dftIm(g); // 𝐺im
//            GRe[3] = 0;
//            System.out.println("Fourier-Koeffizenten GRe[u], GIm[i]");
//            for (int u = 0; u < g.length; u++) {
//                System.out.format("GRe[%d] = %.6f\t GIm[%d] = %.6f\n", u, GRe[u], u , GIm[u]);
//            }
//
//            double[] gRe2 = SignalAlgorithm.invDftRe(GRe,GIm); // 𝑔re = 𝑔re?
//            double[] gIm2 = SignalAlgorithm.invDftIm(GRe,GIm); // 𝑔im = 𝑔im = (0, . . .)?
//
//            System.out.println("Rücktransformiertes Signal GRe[u], GIm[i]");
//            for (int m = 0; m < g.length; m++) {
//                System.out.format("gRe2[%d] = %.6f\t gIm2[%d] = %.6f\n", m, gRe2[m], m , gIm2[m]);
//            }
//        }
//
//        { // Aufgabe 4.2.d
//            double[] g = {1,3,5,7,9,8,6,4,2,0}; // 𝑔re = (1, 3, ..), 𝑔im = (0, 0, ..)
//            for (int u = 0; u < g.length; u++) {
//                System.out.format("g[%d] = %.6f\n", u, g[u]);
//            }
//
//            double[] GRe = SignalAlgorithm.dftRe(g); // 𝐺re
//            double[] GIm = SignalAlgorithm.dftIm(g); // 𝐺im
//            GIm[3] = 0;
//            System.out.println("Fourier-Koeffizenten GRe[u], GIm[i]");
//            for (int u = 0; u < g.length; u++) {
//                System.out.format("GRe[%d] = %.6f\t GIm[%d] = %.6f\n", u, GRe[u], u , GIm[u]);
//            }
//
//            double[] gRe2 = SignalAlgorithm.invDftRe(GRe,GIm); // 𝑔re = 𝑔re?
//            double[] gIm2 = SignalAlgorithm.invDftIm(GRe,GIm); // 𝑔im = 𝑔im = (0, . . .)?
//
//            System.out.println("Rücktransformiertes Signal GRe[u], GIm[i]");
//            for (int m = 0; m < g.length; m++) {
//                System.out.format("gRe2[%d] = %.6f\t gIm2[%d] = %.6f\n", m, gRe2[m], m , gIm2[m]);
//            }
//        }
//
//        { // Aufgabe 4.3
//            double[] g = SignalAlgorithm.sinSound(1000, SignalAlgorithm.FS, 0.005, 1.0);
//            double[] g2 = SignalAlgorithm.sinSound(1000, SignalAlgorithm.FS, 0.005, 0.6);
//            double[] g3 = SignalAlgorithm.sinSound(1000, SignalAlgorithm.FS, 0.005, 0.3);
//
//            double[] GRe = SignalAlgorithm.dftRe(g); // Realteil
//            double[] GIm = SignalAlgorithm.dftIm(g); // Imaginärteil
//            double[] GRe2 = SignalAlgorithm.dftRe(g2); // Realteil
//            double[] GIm2 = SignalAlgorithm.dftIm(g2); // Imaginärteil
//            double[] GRe3 = SignalAlgorithm.dftRe(g3); // Realteil
//            double[] GIm3 = SignalAlgorithm.dftIm(g3); // Imaginärteil
//
//            PlotPanel panel = Plotting.create("xxx");
//            panel.addPlot(new StickPlot("Re", GRe)); // Realteil
//            panel.addPlot(new StickPlot("Im", GIm)); // Imaginärteil
//            Plotting.show(panel,-1,1);
//            PlotPanel panel2 = Plotting.create("xxx");
//            panel2.addPlot(new StickPlot("Re", GRe2)); // Realteil
//            panel2.addPlot(new StickPlot("Im", GIm2)); // Imaginärteil
//            Plotting.show(panel2,-1,1);
//            PlotPanel panel3 = Plotting.create("xxx");
//            panel3.addPlot(new StickPlot("Re", GRe3)); // Realteil
//            panel3.addPlot(new StickPlot("Im", GIm3)); // Imaginärteil
//            Plotting.show(panel3,-1,1);
//        }
//        { // Aufgabe 4.3.b Cosinus
//            double[] g = SignalAlgorithm.cosSound(1000, SignalAlgorithm.FS, 0.005, 1.0);
//            double[] g2 = SignalAlgorithm.cosSound(1000, SignalAlgorithm.FS, 0.005, 0.6);
//            double[] g3 = SignalAlgorithm.cosSound(1000, SignalAlgorithm.FS, 0.005, 0.3);
//
//            double[] GRe = SignalAlgorithm.dftRe(g); // Realteil
//            double[] GIm = SignalAlgorithm.dftIm(g); // Imaginärteil
//            double[] GRe2 = SignalAlgorithm.dftRe(g2); // Realteil
//            double[] GIm2 = SignalAlgorithm.dftIm(g2); // Imaginärteil
//            double[] GRe3 = SignalAlgorithm.dftRe(g3); // Realteil
//            double[] GIm3 = SignalAlgorithm.dftIm(g3); // Imaginärteil
//
//            PlotPanel panel = Plotting.create("xxx");
//            panel.addPlot(new StickPlot("Re", GRe)); // Realteil
//            panel.addPlot(new StickPlot("Im", GIm)); // Imaginärteil
//            Plotting.show(panel,-1,1);
//            PlotPanel panel2 = Plotting.create("xxx");
//            panel2.addPlot(new StickPlot("Re", GRe2)); // Realteil
//            panel2.addPlot(new StickPlot("Im", GIm2)); // Imaginärteil
//            Plotting.show(panel2,-1,1);
//            PlotPanel panel3 = Plotting.create("xxx");
//            panel3.addPlot(new StickPlot("Re", GRe3)); // Realteil
//            panel3.addPlot(new StickPlot("Im", GIm3)); // Imaginärteil
//            Plotting.show(panel3,-1,1);
//        }
//
//        { // Aufgabe 4.4
//            double D = 0.005;
//            double[] g = SignalAlgorithm.sinSound(1000, SignalAlgorithm.FS, D, 0.5);
//            g = SignalAlgorithm.add(g, SignalAlgorithm.sinSound(2000, SignalAlgorithm.FS, D, 0.8));
//            g = SignalAlgorithm.add(g, SignalAlgorithm.sinSound(4000, SignalAlgorithm.FS, D, 1.0));
//
//            double[] GRe = SignalAlgorithm.dftRe(g); // Realteil
//            double[] GIm = SignalAlgorithm.dftIm(g); // Imaginärteil
//
//            double[] B = SignalAlgorithm.mag(GRe, GIm);
//
//            PlotPanel panel = Plotting.create("xxx");
//            panel.addPlot(new StickPlot("g", g));
//            Plotting.show(panel);
//
//            PlotPanel panel2 = Plotting.create("xxx");
//            panel2.addPlot(new StickPlot("Betragssprektrum", B)); // Realteil
//            Plotting.show(panel2);
//
//        }

        { // Aufgabe 5.1

            double[] A = SignalAlgorithm.compose(new double[0] ,SignalAlgorithm.stimme1, SignalAlgorithm.FS, 0.5);
            double[] B = SignalAlgorithm.compose(new double[0] ,SignalAlgorithm.stimme2, SignalAlgorithm.FS, 0.5);
            double[] song = SignalAlgorithm.add(B, A);

            AudioPlayer player = new AudioPlayer(SignalAlgorithm.FS);
            //player.play(song);

            FastFourierTransform fft = new FastFourierTransform.Forward(song);

            double[] GRe = fft.getRe(); // Realteil
            double[] GIm = fft.getIm(); // Imaginärteil



            double[] t = new double[song.length];
            double m880 = 880 * song.length / SignalAlgorithm.FS;
            for (int m = 0; m < m880; m++) {
                t[m] = (1 - m / m880);
                t[song.length - 1 - m] = t[m];
            }

            double[] spectrum = fft.getMag();

            GRe = SignalAlgorithm.mult(GRe, t);
            GIm = SignalAlgorithm.mult(GIm, t);

//            PlotPanel panel = Plotting.create("xxx");
//            panel.setAxisLabels("i", "q(i)");
//            panel.addPlot(new LinePlot("g", song));
//            Plotting.show(panel);

            double[] TGRe = new double[song.length];
            double[] TGIm = new double[song.length];

            for (int m = 0; m < song.length / 4; m++) {
                TGRe[m*2] = GRe[m];
                TGIm[m*2] = GIm[m];

                TGRe[song.length - 1 - 2 * m] = GRe[song.length - 1 - m];
                TGIm[song.length - 1 - 2 * m] = GIm[song.length - 1 - m];
            }

            PlotPanel panel4 = Plotting.create("xxx");
            panel4.setAxisLabels("i", "q(i)");
            panel4.addPlot(new StickPlot("Betragssprektrum", t)); // Realteil
            Plotting.show(panel4);

            PlotPanel panel2 = Plotting.create("xxx");
            panel2.setAxisLabels("i", "q(i)");
            panel2.addPlot(new StickPlot("Betragssprektrum", spectrum)); // Realteil
            Plotting.show(panel2, 0, 0.05);

            FastFourierTransform ffti = new FastFourierTransform.Inverse(TGRe, TGIm);
            double[] songInvRe = ffti.getRe();
            double[] songInvIm = ffti.getIm();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            player.play(songInvRe);
            player.close();

            PlotPanel panel3 = Plotting.create("xxx");
            panel3.setAxisLabels("i", "q(i)");
            panel3.addPlot(new LinePlot("Re", songInvRe));
            Plotting.show(panel3);


        }
    }

}
