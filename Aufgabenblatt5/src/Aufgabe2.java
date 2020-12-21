/*
    Aufgabe 2) Zweidimensionale Arrays - Sortieren und Filtern
*/
public class  Aufgabe2 {
    
    private static double[][] genMeanFilter(int n) {
        if(n%2==0 || n<1) return null;
        double[][] data = new double[n][n];
        double mult_inverse = 1.0/(n*n); // - Multiplicative inverse / .0 is important
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                data[i][j] = mult_inverse;
            }
        }
        return data;
    }

    private static double[][] applyFilter(double[][] workArray, double[][] filterArray) {
        double[][] newArray = new double[workArray.length][workArray[0].length];

        // Calculate Amount of Elements Around Middle
        int aam = filterArray.length/2; // - Amount Around Middle e.g length = 3, aam = 1, length = 7, aam = 3;

        for (int columns = 0; columns < workArray.length; columns++) { // - Columns
            for (int rows = 0; rows < workArray[columns].length; rows++) { // - Rows

                if(
                        (rows-aam) >= 0 && // - Not Outside Left Side
                        (rows+aam) < workArray[columns].length && // - Not Outside Right Side
                        (columns-aam) >= 0 && // - Not Outside Bottom Side
                        (columns+aam) < workArray.length // - Not Outside Top Side
                ){
                    double sum = 0.0;

                    // Runs Through the Selected Area of Working Array and The Filter Array at the Same Time
                    for (int fc = 0, sc = columns-aam; fc < filterArray.length; fc++, sc++) { // - Selected Column, Filter Column
                        for (int fr = 0, sr = rows-aam; fr < filterArray.length; fr++, sr++) { // - Selected Row, Filter Row
                            sum+= filterArray[fc][fr] * workArray[sc][sr];
                        }
                    }

                    newArray[columns][rows] = sum; // - Inserts Calculated Sum in new Array
                }
                // ! No need to set 0 because 0 is a default value in fixed sized Arrays
            }
        }
        return newArray;
    }
    
    private static void print(double[][] workArray) {
        if(workArray != null) {
            for (int y = 0; y < workArray.length; y++) {
                for (int x = 0; x < workArray[y].length; x++) {
                    System.out.printf("%.2f",workArray[y][x]);
                    System.out.print("\t");
                }
                System.out.println();
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        double[][] myResultArray;

        double[][] myFilter1 = genMeanFilter(3);
        print(myFilter1);
        double[][] myFilter2 = genMeanFilter(5);
        print(myFilter2);

        double[][] myArray1 = {{0,0,0,0,0}, {0,1,1,1,0}, {0,1,1,1,0}, {0,1,1,1,0}, {0,0,0,0,0}};
        print(myArray1);

        myResultArray = applyFilter(myArray1, myFilter1);
        print(myResultArray);
        myResultArray = applyFilter(myArray1, myFilter2);
        print(myResultArray);

        System.out.println("Test MyArray2");
        System.out.println("- Working Array:");
        double[][] myArray2 = {{0,0,0,0,0}, {0,0,0,0,0}, {0,1,1,1,0}, {0,0,0,0,0}, {0,0,0,0,0}};
        print(myArray2);
        double[][] myFilter3 = new double[3][3];
        myFilter3[0][1] = 1;
        System.out.println("- Filter Array:");
        print(myFilter3);
        double[][] myResultArray2 = applyFilter(myArray2,myFilter3);
        System.out.println("- Result:");
        print(myResultArray2);
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