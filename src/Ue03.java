import audio.AudioPlayer;
import audio.AudioWriter;
import org.math.plot.PlotPanel;
import plotting.Plotting;
import plotting.StickPlot;

public class Ue03 {

    public static void main(String[] args) {

        { // Aufgabe 3.1
            double[] A = SignalAlgorithm.sinSound(1000, 10000, 0.005, 0.5);

            PlotPanel panel = Plotting.create("Aufgabe 3.1");
            panel.setAxisLabels("i", "g(i)");
            panel.addPlot(new StickPlot("Sinus", A));
            Plotting.show(panel);

            double[] B = SignalAlgorithm.sinSound(440, 10000, 2, 0.75);
            // Audio-Ausgabe:
            AudioPlayer player = new AudioPlayer(10000);
            player.play(B);
            player.close();
        }

        { // Aufgabe 3.2
            double[] A = new double[0];
            double a = 1/32;
            for (int i = 1; a <= 1; i++) {

                double[] B = SignalAlgorithm.sinSound(440, 10000, 1, a);
                A = SignalAlgorithm.join(A, B);
                a = Math.pow(2,i)/32;
            }
            AudioPlayer player = new AudioPlayer(10000);
            player.play(A);
            player.close();
        }

//        for (int i = 2; i + 60 <= 74; i += 2) {
//            double[] B = midiSound(60 + i, 10000, 0.25, 0.5);
//            A = join(A, B);
//        }

        { // Aufgabe 3.3

            System.out.println("m = " + 0 + ", fm =  " + SignalAlgorithm.midi(0));
            for (int i = 60; i <= 74; i++) {
                System.out.println("m = " + i + ", fm =  " + SignalAlgorithm.midi(i));
            }

            double[] A = new double[0];
            int[] cDur = {60, 62, 64, 65, 67, 69, 71, 72};
            for (int i = 0; i < cDur.length; i++) {
                double[] B = SignalAlgorithm.midiSound(cDur[i], 10000, 0.25, 0.5);
                A = SignalAlgorithm.join(A, B);
            }
            AudioPlayer player = new AudioPlayer(10000);
            player.play(A);
            player.close();
        }

        { // Aufgabe 3.4

            double[] A = SignalAlgorithm.compose(new double[0] ,SignalAlgorithm.stimme1, SignalAlgorithm.FS, 0.5);
            double[] B = SignalAlgorithm.compose(new double[0] ,SignalAlgorithm.stimme2, SignalAlgorithm.FS, 0.5);
            A = SignalAlgorithm.add(B, A);

            AudioPlayer player = new AudioPlayer(SignalAlgorithm.FS);
            player.play(A);
            player.close();
            AudioWriter writer = new AudioWriter(SignalAlgorithm.FS);
            writer.writeToWavFile(A, "Composition" + ".wav");
        }

    }
}
