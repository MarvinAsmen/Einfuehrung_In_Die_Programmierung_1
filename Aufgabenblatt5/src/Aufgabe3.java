/*
    Aufgabe 3) Zweidimensionale Arrays und StdDraw - Bildverarbeitung "Finding Waldo"
*/

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Aufgabe3 {

    // converts RGB image into a grayscale array
    private static int[][] convertImg2Array(BufferedImage img) {
        int width = img.getWidth();
        int height = img.getHeight();
        int[][] imgArray = new int[height][width];
        Color tempColor;

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                tempColor = new Color(img.getRGB(col, row));
                imgArray[row][col] = (int)(tempColor.getRed()*0.3 + tempColor.getGreen()*0.59 + tempColor.getBlue()*0.11);
            }

        }
        return imgArray;
    }

    // converts RGB image into a 3D color array
    private static int[][][] convertImg2ColorArray(BufferedImage img) {
        int width = img.getWidth();
        int height = img.getHeight();
        int[][][] imgArray = new int[3][height][width];
        Color tempColor;

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                tempColor = new Color(img.getRGB(col, row));
                imgArray[0][row][col] = tempColor.getRed();
                imgArray[1][row][col] = tempColor.getGreen();
                imgArray[2][row][col] = tempColor.getBlue();
            }
        }
        return imgArray;
    }

    //draws the image array specified by color channels imgArrayR, imgArrayG and imgArrayB into the canvas
    private static void drawImage(int[][] imgArrayR,int[][] imgArrayG,int[][] imgArrayB) {
        // draw color image on the StdDraw window
        StdDraw.enableDoubleBuffering();
        for (int y = 0; y < imgArrayR.length; y++) {
            for (int x = 0; x < imgArrayR[y].length; x++) {
                StdDraw.setPenColor(imgArrayR[y][x],imgArrayG[y][x],imgArrayB[y][x]);
                StdDraw.filledSquare(x,imgArrayR.length-y,0.5);
            }
        }
        StdDraw.show();
        StdDraw.disableDoubleBuffering();
    }

    //detect waldo by template matching and return its bounding box values
    private static int[] detectWaldo(int[][] imgArrayGrayscale,int[][] templateArray) {

        int[] boundingBox = new int[4]; // {x1,y1,x2,y2}, (x1,y1) top left, (x2,y2) bottom right

        int gray_width = imgArrayGrayscale[0].length;
        int gray_height = imgArrayGrayscale.length;

        // Centerpoint of templateArray
        int temp_width = templateArray[0].length;
        int temp_height = templateArray.length;
        int hebm = temp_width/2; // - horizontal elements beside middle
        int vebm = temp_height/2; // - vertical elements beside middle

        int lowest_sad = -1;
        int wcpx = 0; // -Waldos Center Point X-Coordinate
        int wcpy = 0; // -Waldos Center Point Y-Coordinate

        // Run Through every Element and Calculate Sum of Absolute Difference
        for (int columns = 0; columns < gray_height; columns++) {
            for (int rows = 0; rows < gray_width; rows++) {

                // Check if Temp is not outside
                if(
                        (rows-hebm) >= 0 && // - Not Outside Left Side
                        (rows+hebm) < gray_width && // - Not Outside Right Side
                        (columns-vebm) >= 0 && // - Not Outside Bottom Side
                        (columns+vebm) < gray_height // - Not Outside Top Side
                ){
                    int sad = 0;

                    // Runs Through the Selected Area of imgArrayGrayscale and templateArray at the Same Time
                    for (int tc = 0, sc = columns-vebm; tc < temp_height; tc++, sc++) { // - Selected Column, Template Column
                        for (int tr = 0, sr = rows-hebm; tr < temp_width; tr++, sr++) { // - Selected Row, Template Row
                            sad+= Math.abs(imgArrayGrayscale[sc][sr]-templateArray[tc][tr]);
                        }
                    }
                    if(lowest_sad == -1 || sad<lowest_sad){
                        lowest_sad = sad;
                        wcpy = columns;
                        wcpx = rows;
                    }
                }
            }
        }


        // Set boundingBox values
        // {x1,y1,x2,y2}, (x1,y1) top left, (x2,y2) bottom right
        boundingBox[0] = wcpx - hebm; // x top left
        boundingBox[1] = wcpy - vebm; // y top left
        boundingBox[2] = wcpx + hebm; // x bottom right
        boundingBox[3] = wcpy + vebm; // y bottom right

        return boundingBox;
    }

    public static void main(String[] args) {
        //waldo1
        String linkWaldo = "https://owncloud.tuwien.ac.at/index.php/s/lht2cy0GFclxbl2/download"; //waldo1.png
        String linkTemplate = "https://owncloud.tuwien.ac.at/index.php/s/f9onCE9vf89ZYLJ/download"; //template1.png

        //waldo2
        //String linkWaldo = "https://owncloud.tuwien.ac.at/index.php/s/3HYvf4xBkiZUYr1/download"; //waldo2.png
        //String linkTemplate = "https://owncloud.tuwien.ac.at/index.php/s/spG8LoK4x6HqOkf/download"; //template2.png

        //waldo3
        //String linkWaldo = "https://owncloud.tuwien.ac.at/index.php/s/9RmCwGkOjgwwkzh/download"; //waldo3.png
        //String linkTemplate = "https://owncloud.tuwien.ac.at/index.php/s/CDVrqihS7t9lfvm/download"; //template3.png


        BufferedImage img = null;
        // try to open image file
        try {
            URL url_img_waldo = new URL(linkWaldo);
            img = ImageIO.read(url_img_waldo);
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }

        BufferedImage template = null;
        // try to open template image file
        try {
            URL url_img_template = new URL(linkTemplate);
            template = ImageIO.read(url_img_template);
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }

        // set StdDraw window size based on the image size
        int width = img.getWidth();
        int height = img.getHeight();
        StdDraw.setCanvasSize(width, height);
        StdDraw.setXscale(0, width);
        StdDraw.setYscale(0, height);

        //extract color channels R,G,B
        int[][][] imgArray = convertImg2ColorArray(img);
        int[][] imgArrayR = imgArray[0];
        int[][] imgArrayG = imgArray[1];
        int[][] imgArrayB = imgArray[2];
        //convert input image and template image to grayscale, because detection of Waldo works by gray value comparison
        int[][] imgArrayGrayscale = convertImg2Array(img);
        int[][] templateArray = convertImg2Array(template);

        int[] boundingBox = detectWaldo(imgArrayGrayscale,templateArray);

        if (boundingBox !=null) {
            // Run Through Every Element
            for (int columns = 0; columns < imgArrayR.length; columns++) {
                for (int rows = 0; rows < imgArrayR[0].length; rows++) {

                    // If not inside the field then make darker
                    if(
                            !((rows >= boundingBox[0] && rows <= boundingBox[2]) &&
                            (columns >= boundingBox[1] && columns <= boundingBox[3]))
                    ){
                        // If below 0, set to 0, else set to element - 150
                        imgArrayR[columns][rows] = Math.max(0,(imgArrayR[columns][rows]-150));
                        imgArrayB[columns][rows] = Math.max(0,(imgArrayB[columns][rows]-150));
                        imgArrayG[columns][rows] = Math.max(0,(imgArrayG[columns][rows]-150));
                    }
                }
            }
        }

        drawImage(imgArrayR,imgArrayG,imgArrayB);

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
Mon 21. Dec 2020

 */