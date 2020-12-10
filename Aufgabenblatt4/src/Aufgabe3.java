/*
    Aufgabe 3) Eindimensionale Arrays und Methoden
*/

import java.util.Arrays;

public class Aufgabe3 {

    private static int[] genRandomArray(int length, int maxNumber) {
        int[] array = new int[length];
        for (int i = 0; i < length; i++) {
            array[i] = (int)(Math.random() * (maxNumber + 1));
        }
        return array;
    }

    private static void filterMaxMinValue(int[] workArray) {
        int min = workArray[0];
        int max = workArray[0];
        for (int elem:workArray){
            if(elem < min) min=elem;
            if(elem > max) max=elem;
        }
        if(max == min){
            workArray[0] = -1;
        }else {
            boolean setMin = false;
            boolean setMax = false;
            for (int i = 0; i < workArray.length; i++) {
                if(workArray[i] == min && !setMin){
                    workArray[i] = -1;
                    setMin = true;
                }
                if(workArray[i] == max && !setMax){
                    workArray[i] = -1;
                    setMax = true;
                }
            }
        }
    }

    private static int[] combineArrays(int[] workArray1, int[] workArray2) {
        int[] newArray = new int[workArray1.length+workArray2.length];
        for (int i = 0; i < workArray1.length; i++) {
            newArray[i]=workArray1[i];
        }
        for (int i = 0; i < workArray2.length; i++) {
            newArray[i+ workArray1.length]=workArray2[i];
        }
        return newArray;
    }

    public static void main(String[] args) {
        int[] array1 = genRandomArray(20, 50);
        System.out.println(Arrays.toString(array1));

        int[] array2 = new int[]{12, 13, 5, 23, 7, 14, 9, 2, 15, 19};
        filterMaxMinValue(array2);
        System.out.println(Arrays.toString(array2));
        assert (Arrays.equals(array2, new int[]{12, 13, 5, -1, 7, 14, 9, -1, 15, 19}) == true);

        int[] array3 = new int[]{34, 14, 26, 18, 45, 21};
        filterMaxMinValue(array3);
        System.out.println(Arrays.toString(array3));
        assert (Arrays.equals(array3, new int[]{34, -1, 26, 18, -1, 21}) == true);

        int[] array4 = new int[]{5, 5};
        filterMaxMinValue(array4);
        System.out.println(Arrays.toString(array4));
        assert (Arrays.equals(array4, new int[]{-1, 5}) == true);

        int[] array5 = new int[]{3, 2, 1};
        filterMaxMinValue(array5);
        System.out.println(Arrays.toString(array5));
        assert (Arrays.equals(array5, new int[]{-1, 2, -1}) == true);

        int[] array6 = new int[]{5, 5, 7, 7};
        filterMaxMinValue(array6);
        System.out.println(Arrays.toString(array6));
        assert (Arrays.equals(array6, new int[]{-1, 5, -1, 7}) == true);


        int[] array10 = combineArrays(array2, array3);
        System.out.println(Arrays.toString(array10));
        assert (Arrays.equals(array10, new int[]{12, 13, 5, -1, 7, 14, 9, -1, 15, 19, 34, -1, 26, 18, -1, 21}) == true);

        int[] array11 = combineArrays(array4, array5);
        System.out.println(Arrays.toString(array11));
        assert (Arrays.equals(array11, new int[]{-1, 5, -1, 2, -1}) == true);

        int[] array12 = combineArrays(array10, array11);
        System.out.println(Arrays.toString(array12));
        assert (Arrays.equals(array12, new int[]{12, 13, 5, -1, 7, 14, 9, -1, 15, 19, 34, -1, 26, 18, -1, 21, -1, 5, -1, 2, -1}) == true);
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