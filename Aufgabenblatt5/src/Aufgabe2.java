/*
    Aufgabe 2) Zweidimensionale Arrays - Sortieren und Filtern
*/
public class  Aufgabe2 {
    
    private static double[][] genMeanFilter(int n) {
        // TODO: Implementieren Sie hier Ihre Lösung für die Methode
        return null; //Zeile kann geändert oder entfernt werden.
    }

    private static double[][] applyFilter(double[][] workArray, double[][] filterArray) {
        // TODO: Implementieren Sie hier Ihre Lösung für die Methode
        return null; //Zeile kann geändert oder entfernt werden.
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

        double[][] myArray2 = {{0,0,0,0,0}, {0,0,0,0,0}, {0,1,1,1,0}, {0,0,0,0,0}, {0,0,0,0,0}};
        print(myArray2);
        //TODO: Erstellen Sie einen Shiftfilter, wenden Sie ihn auf myArray2 an und geben Sie das Ergebnis mittels print() aus

    }
    
    
}
