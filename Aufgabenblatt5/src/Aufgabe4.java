/*
    Aufgabe 4) Rekursion + Zweidimensionale Arrays - primitive Landschaftssimulation
*/

import java.awt.*;

public class Aufgabe4 {

    private static final int canvasSize = 500;

    private static Color[][] genLandscape(int size) {
        // TODO: Implementieren Sie hier Ihre Lösung für die Angabe
        return null; //Zeile kann geändert oder entfernt werden.
    }

    private static void drawLandscape(Color[][] landscape) {
        // TODO: Implementieren Sie hier Ihre Lösung für die Angabe
    }

    private static void simLiquidFlow(Color[][] landscape, int x, int y) {
        // TODO: Implementieren Sie hier Ihre Lösung für die Angabe
    }

    private static void simSpreadingFire(Color[][] landscape, int x, int y) {
        // TODO: Implementieren Sie hier Ihre Lösung für die Angabe
    }

    private static void spreadFireInLiquid(Color[][] landscape, int x, int y) {
        // TODO: Implementieren Sie hier Ihre Lösung für die Angabe
    }

    public static void main(String[] args) {
        StdDraw.setCanvasSize(canvasSize, canvasSize);
        StdDraw.setScale(0, canvasSize);
        StdDraw.enableDoubleBuffering();

        int size = 100;
        Color[][] landscape = genLandscape(size);

        simLiquidFlow(landscape, size / 2, size-1);
        drawLandscape(landscape);
        StdDraw.show();
        StdDraw.pause(1000);

        landscape[75][25] = Color.GREEN;
        simSpreadingFire(landscape, 25, 75);
        drawLandscape(landscape);
        StdDraw.show();
    }
}
