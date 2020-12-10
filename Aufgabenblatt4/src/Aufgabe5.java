/*
    Aufgabe 5) Eindimensionale Arrays und File I/O
*/

import java.awt.*;

public class Aufgabe5 {

    private static String[] readFileData(String fileName, int lineStart, int lineEnd) {
        if(lineStart==0) lineStart=1;
        In fileReader = new In(fileName);
        String[] allLines = fileReader.readAllLines();
        String[] specific = new String[(lineEnd-lineStart)+1];
        for (int i = 0; i < specific.length; i++) {
            specific[i] = allLines[lineStart-1+i];
        }
        return specific;
    }

    private static void extractData(String[] dataArray, int[] resultArray, int numColumn, int entriesPerYear) {
        int current_entry = 1;
        int sum = 0;
        int result_index = 0;
        for (int i = 0; i < dataArray.length; i++) {
            sum+= Integer.parseInt(dataArray[i].split(";")[numColumn]);
            if(current_entry%entriesPerYear == 0){
                resultArray[result_index]=sum;
                System.out.println("Year "+(1955+result_index)+": "+sum);
                sum=0;
                current_entry = 1;
                result_index++;
            }else {
                current_entry++;
            }
        }
    }

    private static void drawChart(int[] sunHours) {
        int width = 1275;
        int height = 600;
        StdDraw.setCanvasSize(width, height);
        StdDraw.setXscale(0, width);
        StdDraw.setYscale(0, height);

        StdDraw.setFont(new Font("Times", Font.PLAIN, 10));

        // -- BARS
        int index = 0;
        int rect_width = 15/2;
        double[] poly_x = new double[sunHours.length];
        double[] poly_y = new double[sunHours.length];
        for (int sunHour:sunHours) {
            int hours = sunHour/4;
            int rect_height = hours/2;
            int x = rect_width+30+15*index+5*index;
            int y = rect_height;

            StdDraw.setPenColor(StdDraw.ORANGE);
            StdDraw.filledRectangle(x,y,rect_width,rect_height);
            StdDraw.setPenColor(StdDraw.BLACK);
            int year = 55+index;
            if(year>99) year -=100;
            String text = (year>9) ? Integer.toString(year) : "0"+year;
            StdDraw.text(x,10,text);

            poly_y[index] = y*2;
            poly_x[index] = x;

            index++;
        }

        // -- VALUE LINE
        StdDraw.setPenRadius(0.005);
        double lowest = poly_y[0];
        double highest = poly_y[0];
        for (int i = 0; i < poly_x.length-1; i++) {
            if(poly_y[i+1]>highest) highest = poly_y[i+1];
            if(poly_y[i+1]<lowest) lowest = poly_y[i+1];
            StdDraw.line(poly_x[i],poly_y[i],poly_x[i+1],poly_y[i+1]);
        }

        // -- LOWEST LINE
        StdDraw.setPenRadius(0.002);
        StdDraw.text(15,lowest,Integer.toString((int)lowest*4));
        StdDraw.line(poly_x[0]-15/2,lowest,poly_x[poly_x.length-1]+15/2,lowest);
        StdDraw.text(poly_x[poly_x.length-1]+15/2+15,lowest,Integer.toString((int)lowest*4));

        // -- HIGHEST LINE
        StdDraw.text(15,highest,Integer.toString((int)highest*4));
        StdDraw.line(poly_x[0]-15/2,highest,poly_x[poly_x.length-1]+15/2,highest);
        StdDraw.text(poly_x[poly_x.length-1]+15/2+15,highest,Integer.toString((int)highest*4));

    }

    public static void main(String[] args) {
        int[] resultArray = new int[61];
        extractData(
                readFileData(
                        "weather_data.csv",
                        2,
                        733
                ),
                resultArray,
                16,
                12
        );
        drawChart(resultArray);
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
Tue 10. Dec 2020

 */