/*
    Aufgabe 4) Rekursion + Zweidimensionale Arrays - primitive Landschaftssimulation
*/

import java.awt.*;

public class Aufgabe4 {

    private static final int canvasSize = 500;

    private static Color[][] genLandscape(int size) {
        Color[][] map = new Color[size][size];

        for (int columns = 0; columns < map.length; columns++) { // - Columns
            for (int rows = 0; rows < map[0].length; rows++) { // - Rows
                int random = (int)(Math.random() * 10 + 1); // - Random Number between 1-10
                map[columns][rows] = (random == 1) ? Color.GRAY : Color.GREEN; // - 1 = 10% Chance to get rock, else grass
            }
        }
        return map;
    }

    private static void drawLandscape(Color[][] landscape) {
        double pixelScale = canvasSize / landscape.length;

        // Draws From Top Right top Bottom Left
        for (int columns = landscape.length-1; columns >= 0; columns--) { // - Columns
            for (int rows = landscape[0].length-1; rows >= 0; rows--) { // - Rows

                // Set Pixel Color
                StdDraw.setPenColor(landscape[columns][rows]);

                // Draw Pixel
                StdDraw.filledRectangle(
                        pixelScale*rows+pixelScale/2,
                        pixelScale*columns+pixelScale/2,
                        pixelScale/2,
                        pixelScale/2
                );
            }
        }
    }

    private static void simLiquidFlow(Color[][] landscape, int x, int y) {
        if(x < 0 || x > canvasSize || y < 0 || !(y < landscape.length) || !(x <landscape[0].length)) return; // - end if outside of frame
        if(landscape[y][x] == Color.BLACK) return; // - end if hits black

        double pixelScale = canvasSize / landscape.length;
        StdDraw.setPenColor(Color.ORANGE);

        // Current Hits Stone
        if(landscape[y][x] == Color.GRAY){
            //Draw Pixel Above
            if(y+1<landscape.length){ // - if not Outside Border
                StdDraw.filledRectangle(
                        pixelScale*x+pixelScale/2,
                        pixelScale*(y+1)+pixelScale/2,
                        pixelScale/2,
                        pixelScale/2
                );
                landscape[y+1][x] = Color.ORANGE;
            }
            StdDraw.setPenColor(Color.BLACK);
        }

        // Draw Current Pixel (Can be Orange or Black if hits Stone)
        StdDraw.filledRectangle(
                pixelScale*x+pixelScale/2,
                pixelScale*y+pixelScale/2,
                pixelScale/2,
                pixelScale/2
        );
        landscape[y][x] = StdDraw.getPenColor();


        // Call Draw Next
        if(landscape[y][x] == Color.BLACK){ // - If Current Hits Rock, split the liquid
            // Flow Left Side
            simLiquidFlow(landscape,x-1,y);

            // Flow Right Side
            simLiquidFlow(landscape,x+1,y);
        }else {
            // 50% Chance to Flow Left or Right
            boolean left = ((int)(Math.random() * 2 + 1) == 1); // - Random Number between 1-2, if 1, liquid flows left else right
            simLiquidFlow(landscape, (left) ? x-1 : x+1, y-1);
        }
    }

    private static void simSpreadingFire(Color[][] landscape, int x, int y) {
        if(x < 0 || x > canvasSize-1 || y < 0 || y > canvasSize-1 || !(y < landscape.length) || !(x <landscape[0].length)) return; // - end if outside of frame

        // Draw Current
        if(landscape[y][x] == Color.GREEN){ // - if Current on Gras, burn
            double pixelScale = canvasSize / landscape.length;
            StdDraw.setPenColor(Color.RED);
            StdDraw.filledRectangle(
                    pixelScale*x+pixelScale/2,
                    pixelScale*y+pixelScale/2,
                    pixelScale/2,
                    pixelScale/2
            );
            landscape[y][x] = StdDraw.getPenColor();
        }else if (landscape[y][x] == Color.ORANGE){ // - if Current on Liquid, inflame whole liquid area
            spreadFireInLiquid(landscape,x,y);
        }else return; // - hits stone or black stone, end


        // Call Draw Next
        // Random Number between 1-10, if <7 spread in this direction (60% chance to spread)
        boolean north = ((int)(Math.random() * 10 + 1) < 7);
        boolean south = ((int)(Math.random() * 10 + 1) < 7);
        boolean west = ((int)(Math.random() * 10 + 1) < 7);
        boolean east = ((int)(Math.random() * 10 + 1) < 7);

        if(north) {
            simSpreadingFire(landscape, x,y+1);
        }
        if(south) {
            simSpreadingFire(landscape, x,y-1);
        }
        if(west) {
            simSpreadingFire(landscape, x-1,y);
        };
        if(east) {
            simSpreadingFire(landscape, x+1,y);
        }
    }

    private static void spreadFireInLiquid(Color[][] landscape, int x, int y) {
        if(x < 0 || x > canvasSize-1 || y < 0 || y > canvasSize-1 || !(y < landscape.length) || !(x <landscape[0].length)) return; // - end if outside of frame
        if(landscape[y][x] != Color.ORANGE) return; // - end if not liquid

        // Draw Current
        int pixelScale = canvasSize / landscape.length;
        StdDraw.setPenColor(Color.RED);
        StdDraw.filledRectangle(
                pixelScale*x+pixelScale/2,
                pixelScale*y+pixelScale/2,
                pixelScale/2,
                pixelScale/2
        );
        landscape[y][x] = Color.RED;


        // Call Draw Next
        spreadFireInLiquid(landscape, x,y+1); // Top
        spreadFireInLiquid(landscape, x,y-1); // Bottom
        spreadFireInLiquid(landscape, x-1,y); // Left
        spreadFireInLiquid(landscape, x+1,y); // Right
        spreadFireInLiquid(landscape, x-1,y+1); // Top Left
        spreadFireInLiquid(landscape, x+1,y+1); // Top Right
        spreadFireInLiquid(landscape, x-1,y-1); // Bottom Left
        spreadFireInLiquid(landscape, x+1,y-1); // Bottom Right
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
































































/*

Made by
 __  __                  _                                                              _
|  \/  |                (_)           /\                                  /\           | |
| \  / | __ _ _ ____   ___ _ __      /  \   ___ _ __ ___   ___ _ __      /  \   _ __ __| |_   _  ___
| |\/| |/ _` | '__\ \ / / | '_ \    / /\ \ / __| '_ ` _ \ / _ \ '_ \    / /\ \ | '__/ _` | | | |/ __|
| |  | | (_| | |   \ V /| | | | |  / ____ \\__ \ | | | | |  __/ | | |  / ____ \| | | (_| | |_| | (__
|_|  |_|\__,_|_|    \_/ |_|_| |_| /_/    \_\___/_| |_| |_|\___|_| |_| /_/    \_\_|  \__,_|\__,_|\___|
12024700
Mo 22. Dec 2020

 */