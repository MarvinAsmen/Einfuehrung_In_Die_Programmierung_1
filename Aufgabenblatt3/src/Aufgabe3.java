/*
    Aufgabe 3) Rekursion
*/
public class Aufgabe3 {

    private static void printNumbersAscending(int start, int end, int divider) {
        if(start > end || divider<0) throw new IllegalArgumentException("End muss größer als Start sein, divider muss größer 0 sein!");
        if(start%divider==0) System.out.println(start);
        start++;
        if(start<end+1) printNumbersAscending(start,end,divider);
    }

    private static void printNumbersDescending(int start, int end, int divider) {
        if(start > end || divider<0) throw new IllegalArgumentException("End muss größer als Start sein, divider muss größer 0 sein!");
        if(end%divider>0) System.out.println(end);
        end--;
        if(start<end+1) printNumbersDescending(start,end,divider);
    }

    private static int calcCrossSum(int number) {
        Exception e = new Exception();
        e.fillInStackTrace();
        if (e.getStackTrace().length == 2) {
            if(number < 1) throw new IllegalArgumentException("Number muss größer als 0 sein!");
        }
        return number == 0 ? 0 : number % 10 + calcCrossSum(number / 10);
    }

    private static String duplicateLetterInString(String text, char letter) {
        if(text == null) throw new IllegalArgumentException("Text darf nicht null sein!");
        if(text.length() ==1) return text.charAt(0) != letter ? text : text+ letter;
        return text.charAt(0) != letter ? text.charAt(0) + duplicateLetterInString(text.substring(1),letter):
                text.charAt(0) + Character.toString(letter) + duplicateLetterInString(text.substring(1),letter);
    }

    public static void main(String[] args) {
        printNumbersAscending(10, 20, 2);
        System.out.println();
        printNumbersDescending(5, 15, 3);
        System.out.println();

        //System.out.println(calcCrossSum(0));
        System.out.println(calcCrossSum(1));
        System.out.println(calcCrossSum(102));
        System.out.println(calcCrossSum(1234));
        System.out.println(calcCrossSum(10000));
        System.out.println(calcCrossSum(93842));
        System.out.println(calcCrossSum(875943789));
        assert (calcCrossSum(1) == 1);
        assert (calcCrossSum(102) == 3);
        assert (calcCrossSum(1234) == 10);
        assert (calcCrossSum(10000) == 1);
        assert (calcCrossSum(93842) == 26);
        assert (calcCrossSum(875943789) == 60);
        System.out.println();

        System.out.println(duplicateLetterInString("hallo", 'a'));
        System.out.println(duplicateLetterInString("Es ist die Erde", 'e'));
        System.out.println(duplicateLetterInString("3HALLO4", 'L'));
        System.out.println(duplicateLetterInString("a1b2c3d4e5", 'g'));
        assert (duplicateLetterInString("hallo", 'a').equals("haallo"));
        assert (duplicateLetterInString("Es ist die Erde", 'e').equals("Es ist diee Erdee"));
        assert (duplicateLetterInString("3HALLO4", 'L').equals("3HALLLLO4"));
        assert (duplicateLetterInString("a1b2c3d4e5", 'g').equals("a1b2c3d4e5"));
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
Tue 24. Nov 2020

 */