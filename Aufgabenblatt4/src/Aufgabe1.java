/*
    Aufgabe 1) Code Analyse - Eindimensionale Arrays
*/
public class Aufgabe1 {
    
    private static void genArray(int[] filledArray){
        int value = 5;
        for (int i = 0; i < filledArray.length; i++) {
            filledArray[i] = value;
            value += 5;
        }
    }
    
    private static void printFilteredArrayContent(int[] workArray){
        int[] copiedArray = workArray;
        for (int i = 0; i < copiedArray.length; i++) {
            if(copiedArray[i] % 4 == 0){
                copiedArray[i] = 0;
            }
        }
        printArray(copiedArray);
    }
    
    private static void genNewArrayContent(int[] workArray){
        int[] helpArray = new int[15];
        int value = 7;
        for (int i = 0; i < helpArray.length; i++) {
            helpArray[i] = value;
            value += 7;
        }
        workArray = helpArray;
        printArray(workArray);
    }
    
    private static void printArray(int[] workArray){
        for (int i = 0; i < workArray.length; i++) {
            System.out.print(workArray[i]+ " ");
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
        int[] filledArray = new int[15];
        genArray(filledArray);
        printArray(filledArray);
        
        printFilteredArrayContent(filledArray);
        printArray(filledArray);
        
        filledArray[0] = 2020;
        printArray(filledArray);
        
        genNewArrayContent(filledArray);
        printArray(filledArray);
    }
    
    //**************************************************************************
    //**** Notizen und Fragebeantwortungen bitte hier unterhalb durchführen! ***
    //**************************************************************************
    //Frage 1:
    // Zuerst muss i-- auf i++ geändert werden, da der index nur positiv sein darf und erhöht werden muss
    // Dann muss <= auf < verbessert werden, da die Schleife bei einem Index Array Length + 1 aufhört, es
    // diesen Index aber kein Element mehr im Array gibt
    //
    //Frage 2:
    // Bei genArray handelt es sich um eine void methode und void methoden returnen nichts (deswegen auch void).
    //
    //Frage 3:
    // Bei printFilteredArrayContent in der Zeile 15 wird keine Kopie von den Werten aus dem Array workArray erstellt,
    // sondern eine Kopie der Speicheradressen der Elemente. Dh wenn ich einen Wert aus dem "kopierten Array" veränder, veränder
    // ich auch den Wert im ursprünglichen Array, weil beide die gleiche Speicheradresse referenziert haben.
    //
    //Frage 4:
    // In Zeile 31 werden dem Workarray neue Array-Element referenzen zugewiesen, dh die Speicheradressen von filledArray und
    // dem workarray in der Methode sind unterschiedlich. Da ein Parameter tatsächlich eine kopie ist und keine reference, werden
    // die Werte vom filledArray nicht verändert

    //Zusatzfragen:
    // 1) int
    // 2) Nein, aber damit man Werte im Array abspeichern kann schon
    // 3) Man schreibt in new int[] wie viele Plätze es haben soll
    // 4) src.clone() oder Arrays.copyOf(src, src.length)
    // 5) Ja
    // 6) Nein, denn die Elementreferencen, also die Speicheradressen der einzelnen Elemente werden verglichen
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