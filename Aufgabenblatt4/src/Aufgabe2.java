/*
    Aufgabe 2) Eindimensionale Arrays
*/
public class Aufgabe2 {

    public static void main(String[] args) {

        // AUFGABE 1
        int[] array1 = {1, 4, 7, 0, 3, 6, 2, 8};
        System.out.println("Aufgabe1:");
        printArray(array1);

        // AUFGABE 2
        int[] array2 = new int[20];
        int vierer = 8;
        for (int i = 0; i<array2.length; i++) {
            vierer+=4;
            array2[i] = (vierer%9 == 0) ? 0:vierer;
        }
        System.out.println("\nAufgabe2:");
        printArray(array2);

        // AUFGABE 3
        System.out.println("\nAufgabe3:");
        int[] array3 = {4, 8, 1, 5, 2};
        int[] array3copy = array3.clone();
        array3copy[0] = 100;
        array3copy[array3copy.length-1] = 200;
        printArray(array3);
        printArray(array3copy);

        // AUFGABE 4
        System.out.println("\nAufgabe4:");
        int[] array4 = new int[15];
        for (int i = 15; i >0; i--) {
            array4[(i-15)*-1]=i;
        }

        System.out.println("Erwartete Ausgabe:");
        String wa = "while-Schleife: ";
        int wa_index = 0;
        while (wa_index<array4.length){
            wa+= array4[(wa_index-14)*-1]+" ";
            wa_index++;
        }
        System.out.println(wa);

        String fs = "for-Schleife: ";
        for (int i = array4.length-1; i >= 0 ; i--) {
            fs+= array4[i]+" ";
        }
        System.out.println(fs);

        // AUFGABE 5
        System.out.println("\nAufgabe5:");
        int[] array5 = {61, 13, 19, 10, 2, 33, 41, 73, 0, 56, 94, 6, 45, 84, 23};
        int smallest = array5[0];
        int greatest = array5[0];
        int sum = 0;
        for (int elem:array5){
            if(elem < smallest) smallest=elem;
            if(elem > greatest) greatest=elem;
            sum+=elem;
        }
        int[] array5_2 = {smallest, sum/array4.length, greatest};
        printArray(array5);
        printArray(array5_2);
    }

    private static void printArray(int[] array){
        String text="";
        for (int elem:array) {
            text+=elem+"-";
        }
        System.out.println(text.substring(0,text.length()-1));
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