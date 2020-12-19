/*
    Aufgabe 1) Zweidimensionale Arrays - Diverse Methoden
*/

import java.util.Arrays;

public class Aufgabe1 {

    private static int[][] genFilledArray(int n) {

        int[][] data = new int[n][n];
        for (int i = 0; i < data.length; i++) { // - Columns
            for (int j = 0; j < data.length; j++) { // - Rows
                int current_number = 1+i+j;
                data[i][j] = current_number > n ? n-(current_number%n) : current_number;
            }
        }
        return data;
    }

    private static void shiftLinesInArray(int[][] workArray) {
        // Create Copy of First Array Line
        int lll = workArray[workArray.length-1].length;
        int[] last_line = new int[lll];
        for (int i = 0; i < lll; i++) {
            last_line[i] = workArray[workArray.length-1][i];
        }

        // Switch Lines
        for (int i = workArray.length-1; i >= 0; i--) {
            workArray[i] = (i > 0) ? workArray[i-1] : last_line; // - If first line, replace with last
        }
    }

    private static int[][] extendArray(int[][] inputArray) {

        // Get Greatest Length
        int gal = 0;
        for (int[] element:inputArray) {
            if(element.length > gal) gal = element.length;
        }

        // Fill Up
        int[][] new_array = new int[inputArray.length][gal]; // - Create Array with Original Amount Of Columns nd Greatest Amount of Rows
        for (int i = 0; i < inputArray.length; i++) { // - Columns
            boolean left;
            int left_index = 0;

            if(i%2 == 0){ // - Left
                left = true;
            }else { // - Right
                left = false;
            }

            int cal = inputArray[i].length; // - Current Array Length
            for (int j = ((left) ? gal - cal : 0); j < ((left) ? gal : cal); j++) { // Default value is 0, index starts and ends only where numbers are
                if(left){
                    new_array[i][j] = inputArray[i][left_index];
                    left_index++;
                }else{
                    new_array[i][j] = inputArray[i][j];
                }
            }
        }
        return new_array;
    }

    private static int[] reformatArray(int[][] inputArray) {
        int[] decimals = new int[inputArray.length];
        for (int i = 0; i < inputArray.length; i++) { // - Columns
            int sum = 0;
            for (int j = inputArray[i].length-1; j >=0; j--) { // - Rows
                sum+= inputArray[i][(inputArray[i].length-1)-j] * Math.pow(2, j);
            }
            decimals[i] = sum;
        }
        return decimals;
    }

    //Vorgegebene Methode - BITTE NICHT VERÄNDERN!
    private static void printArray(int[][] inputArray) {
        if (inputArray != null) {
            for (int i = 0; i < inputArray.length; i++) {
                for (int j = 0; j < inputArray[i].length; j++) {
                    System.out.print(inputArray[i][j] + "\t");
                }
                System.out.println();
            }
        }
    }

    //Vorgegebene Methode - BITTE NICHT VERÄNDERN!
    private static void printArray(int[] inputArray) {
        if (inputArray != null) {
            for (int i = 0; i < inputArray.length; i++) {
                System.out.print(inputArray[i] + "\t");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] array = genFilledArray(2);
        printArray(array);
        assert (Arrays.deepEquals(array, new int[][]{{1, 2}, {2, 1}}));
        System.out.println();

        array = genFilledArray(4);
        printArray(array);
        assert (Arrays.deepEquals(array, new int[][]{{1, 2, 3, 4}, {2, 3, 4, 3}, {3, 4, 3, 2}, {4, 3, 2, 1}}));
        System.out.println();

        array = genFilledArray(7);
        printArray(array);
        System.out.println();


        int[][] array1 = new int[][]{{1, 3, 5}, {6, 2, 1}, {0, 7, 9}};
        shiftLinesInArray(array1);
        assert (Arrays.deepEquals(array1, new int[][]{{0, 7, 9}, {1, 3, 5}, {6, 2, 1}}));
        printArray(array1);
        System.out.println();

        array1 = new int[][]{{1, 5, 6, 7}, {1, 9, 3}, {4}, {6, 3, 0, 6, 2}, {6, 3, 0}};
        shiftLinesInArray(array1);
        assert (Arrays.deepEquals(array1, new int[][]{{6, 3, 0}, {1, 5, 6, 7}, {1, 9, 3}, {4}, {6, 3, 0, 6, 2}}));
        printArray(array1);
        System.out.println();


        int[][] array2 = new int[][]{{4}, {1, 2, 3}, {5, 6}, {7, 8, 9, 1}};
        int[][] array2new1 = extendArray(array2);
        printArray(array2new1);
        assert (Arrays.deepEquals(array2new1, new int[][]{{0, 0, 0, 4}, {1, 2, 3, 0}, {0, 0, 5, 6}, {7, 8, 9, 1}}));
        System.out.println();

        array2 = new int[][]{{1, 0, 1, 1, 0, 0, 0, 0}, {0, 1, 1, 1, 1, 1}, {1, 1}, {1, 0, 0, 0}, {1, 1, 0, 1}, {1}, {1}};
        int[][] array2new2 = extendArray(array2);
        printArray(array2new2);
        assert (Arrays.deepEquals(array2new2, new int[][]{{1, 0, 1, 1, 0, 0, 0, 0}, {0, 1, 1, 1, 1, 1, 0, 0}, {0, 0, 0, 0, 0, 0, 1, 1}, {1, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 1, 1, 0, 1}, {1, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 1}}));
        System.out.println();

        array2 = new int[][]{{1, 3, 2}, {5, 1}, {6, 8, 5, 4}, {9, 4, 1, 9, 2}, {1, 8, 7, 5, 3, 2, 5}, {3}};
        int[][] array2new3 = extendArray(array2);
        printArray(array2new3);
        assert (Arrays.deepEquals(array2new3, new int[][]{{0, 0, 0, 0, 1, 3, 2}, {5, 1, 0, 0, 0, 0, 0}, {0, 0, 0, 6, 8, 5, 4}, {9, 4, 1, 9, 2, 0, 0}, {1, 8, 7, 5, 3, 2, 5}, {3, 0, 0, 0, 0, 0, 0}}));
        System.out.println();


        int[][] array3 = new int[][]{{1, 0, 1, 1}, {0, 1, 1}, {1, 1, 0, 0, 0}, {0, 0, 0, 1, 0}, {1, 0}, {1, 1, 1, 1, 1}};
        int[] array3new = reformatArray(array3);
        printArray(array3new);
        assert (Arrays.equals(array3new, new int[]{11, 3, 24, 2, 2, 31}));
        System.out.println();

        array3 = array2new2.clone();
        array3new = reformatArray(array3);
        printArray(array3new);
        assert (Arrays.equals(array3new, new int[]{176, 124, 3, 128, 13, 128, 1}));
        System.out.println();
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
Sun 20. Dec 2020

 */