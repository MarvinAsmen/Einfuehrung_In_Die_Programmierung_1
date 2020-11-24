
/*
    Aufgabe 5) Kreuzmuster mit Rechtecken => Rekursiv vs. Iterativ
*/
public class Aufgabe5 {

    private static void drawPatternRecursive(int x, int y, int l, boolean c) {
        if(l<16) return;

        drawPatternRecursive(x,y,l/2,!c); // Unten Links
        drawPatternRecursive(x,y+l/2,l/2,!c); // Oben Links
        drawPatternRecursive(x+l/2,y,l/2,!c); // Unten Rechts
        drawPatternRecursive(x+l/2,y+l/2,l/2,!c); // Oben Rechts

        StdDraw.setPenColor(c ? StdDraw.ORANGE : StdDraw.BLUE);

        int long_side = l/2;
        int short_side = (int)Math.round((l*0.05)/2);

        //Horizontal
        StdDraw.filledRectangle(x+long_side,y+long_side,long_side,short_side);

        //Vertical
        StdDraw.filledRectangle(x+long_side,y+long_side,short_side,long_side);
    }

    private static void drawPatternIterative(int width) {

        boolean c = false;
        for (int current = 16; current <= width ; current=current*2) {

            StdDraw.setPenColor(c ? StdDraw.ORANGE : StdDraw.BLUE);

            int long_side = current/2;
            int short_side = (int)Math.round((current*0.05)/2);

            for (int columns = 0; columns < ((width*2)/current)-1; columns++) {
                int y = long_side + long_side*columns;

                for (int rows = 0; rows < ((width*2)/current)-1; rows++) {
                    int x = long_side + long_side*rows;

                    //Horizontal
                    StdDraw.filledRectangle(x,y,long_side,short_side);

                    //Vertical
                    StdDraw.filledRectangle(x,y,short_side,long_side);

                }

            }

            c = !c;
        }
    }


    public static void main(String[] args) {
        StdDraw.setCanvasSize(512,512);
        StdDraw.setScale(0,512);
        drawPatternRecursive(0,0,512,true);
        //drawPatternIterative(512);
    }
}


// COOLES ABER LEIDER NICHT GEWOLLTES BSP, SETZT ALLE KREUZE IN DEN PLATZ REIN
//    private static void drawPatternRecursive(int x, int y, int l, boolean c) {
//        //x und y Koordinaten Rechtecksmittelpunkt
//        //l längere Seite eines Rechtecks. Breite = 5%*l
//        // c = True dann orange, ansonsten blau
//        if(l<16) return;
//
//        // Set Color
//        if (c) {
//            StdDraw.setPenColor(Color.orange);
//        } else {
//            StdDraw.setPenColor(Color.blue);
//        }
//
//        int long_side = l/2;
//        int short_side = (int)Math.round((l*0.05)/2);
//
//        //Horizontal
//        StdDraw.filledRectangle(x+long_side,y+long_side,long_side,short_side);
//
//        //Vertical
//        StdDraw.filledRectangle(x+long_side,y+long_side,short_side,long_side);
//
//        drawPatternRecursive(x,y,l/2-short_side,!c); // Unten Links
//        drawPatternRecursive(x,y+l/2+short_side,l/2-short_side,!c); // Oben Links
//        drawPatternRecursive(x+l/2+short_side,y,l/2-short_side,!c); // Unten Rechts
//        drawPatternRecursive(x+l/2+short_side,y+l/2+short_side,l/2-short_side,!c); // Oben Rechts
//
//    }



























































/*

Made by
 __  __                  _                                                              _
|  \/  |                (_)           /\                                  /\           | |
| \  / | __ _ _ ____   ___ _ __      /  \   ___ _ __ ___   ___ _ __      /  \   _ __ __| |_   _  ___
| |\/| |/ _` | '__\ \ / / | '_ \    / /\ \ / __| '_ ` _ \ / _ \ '_ \    / /\ \ | '__/ _` | | | |/ __|
| |  | | (_| | |   \ V /| | | | |  / ____ \\__ \ | | | | |  __/ | | |  / ____ \| | | (_| | |_| | (__
|_|  |_|\__,_|_|    \_/ |_|_| |_| /_/    \_\___/_| |_| |_|\___|_| |_| /_/    \_\_|  \__,_|\__,_|\___|
12024700
Tue 24. Nov 2020

 */