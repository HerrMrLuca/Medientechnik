import audio.AudioPlayer;
import audio.AudioWriter;
import org.math.plot.PlotPanel;
import plotting.Plotting;
import plotting.StickPlot;

public class Ue03 {

    public static void main(String[] args) {

        { // Aufgabe 3.1
            double[] A = sinSound(1000, 10000, 0.005, 0.5);

            PlotPanel panel = Plotting.create("Aufgabe 3.1");
            panel.setAxisLabels("i", "g(i)");
            panel.addPlot(new StickPlot("Sinus", A));
            Plotting.show(panel);

            double[] B = sinSound(440, 10000, 2, 0.75);
            // Audio-Ausgabe:
            AudioPlayer player = new AudioPlayer(10000);
            player.play(B);
            player.close();
        }

        { // Aufgabe 3.2
            double[] A = new double[0];
            double a = 1/32;
            for (int i = 1; a <= 1; i++) {

                double[] B = sinSound(440, 10000, 1, a);
                A = join(A, B);
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

            for (int i = 60; i <= 74; i++) {
                System.out.println("m = " + i + ", fm =  " + midi(i));
            }

            double[] A = new double[0];
            int[] cDur = {60, 62, 64, 65, 67, 69, 71, 72};
            for (int i = 0; i < cDur.length; i++) {
                double[] B = midiSound(cDur[i], 10000, 0.25, 0.5);
                A = join(A, B);
            }
            AudioPlayer player = new AudioPlayer(10000);
            player.play(A);
            player.close();
        }

        { // Aufgabe 3.4

            double[] A = compose(new double[0] ,stimme1, FS, 0.5);
            double[] B = compose(new double[0] ,stimme2, FS, 0.5);
            A = SignalAlgorithm.add(B, A);

            AudioPlayer player = new AudioPlayer(FS);
            player.play(A);
            player.close();
            AudioWriter writer = new AudioWriter(FS);
            writer.writeToWavFile(A, "Composition" + ".wav");
        }

    }

    static double[] sinSound(double f, double fs, double D, double a) {
        double T = fs/ f;
        int n = (int) (fs * D);

        return SignalAlgorithm.mult(SignalAlgorithm.makeSin(n, T), a);
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
}
