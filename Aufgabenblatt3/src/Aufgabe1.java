/*
    Aufgabe 1) Codeanalyse, Codingstyle und Methoden
*/
public class Aufgabe1 {

    /*
    Sortiert den String alphabetisch
     */
//    private static String sortAlphabetic(String s) {
//        int index = 0; String ss = s;
//        while (index < getLength(ss)){
//            if (index == 0 || isCharGreaterOrEqual(ss.charAt(index), ss.charAt(index-1))){
//                index = getAddition(index,1);
//            }
//            else {
//                ss = getSubstring(ss,0,index-1) +
//                        ss.charAt(index) +
//                        ss.charAt(index-1) +
//                        getSubstring(ss,index+1, getLength(ss));
//
//                index = getAddition(index,-1);
//            }
//        }
//        return ss;
//    }

    private static String sortAlphabetic(String s) {
        for (int index = 0; index < s.length();) {
            if (index == 0 || s.charAt(index) >= s.charAt(index-1)){
                index++;
            }
            else {
                s = s.substring(0,index-1) +
                        s.charAt(index) +
                        s.charAt(index-1) +
                        s.substring(index+1);

                index--;
            }
        }
        return s;
    }


    /*
    Gibt die Länge des Strings zurück.
     */
//    private static int getLength(String s) {
//        return s.length();
//    }

    /*
    Gibt die Addition 2er Zahlen zurück.
     */
//    private static int getAddition(int n, int s) {
//        return n + s;
//    }

    /*
    Vergleicht 2 chars und gibt TRUE zurück wenn char 1 einen größeren oder gleichen unicode hat wie char 2,
    ansonsten return FALSE.

    (The ‘char' primitive data type also has an associated integer value based on the Unicode table)
     */
//    private static boolean isCharGreaterOrEqual(char c1, char c2) {
//        return c1 >= c2;
//    }

    /*
    Gibt von einem String einen Teilstring in einem bestimmten Bereich zurück.
     */
//    private static String getSubstring(String s, int n1, int n2) {
//        return s.substring(n1, n2);
//    }

    public static void main(String args[]) {

        System.out.println(sortAlphabetic("ab"));
        System.out.println(sortAlphabetic("ba"));
        System.out.println(sortAlphabetic("aa"));
        System.out.println(sortAlphabetic("cba"));
        System.out.println(sortAlphabetic("abababab"));
        System.out.println(sortAlphabetic("abcfghed"));
        System.out.println(sortAlphabetic("abnasnasab"));
        System.out.println(sortAlphabetic("najskaghkkjsfvjhbavbdfsan"));
        System.out.println(sortAlphabetic("jgbgdsjabkjdbvbdjabkjsavbkjbdsvkjbagfgafjdbv"));

        assert (sortAlphabetic("ab").equals("ab"));
        assert (sortAlphabetic("ba").equals("ab"));
        assert (sortAlphabetic("aa").equals("aa"));
        assert (sortAlphabetic("cba").equals("abc"));
        assert (sortAlphabetic("abababab").equals("aaaabbbb"));
        assert (sortAlphabetic("abcfghed").equals("abcdefgh"));
        assert (sortAlphabetic("abnasnasab").equals("aaaabbnnss"));
        assert (sortAlphabetic("najskaghkkjsfvjhbavbdfsan").equals("aaaabbdffghhjjjkkknnsssvv"));
        assert (sortAlphabetic("jgbgdsjabkjdbvbdjabkjsavbkjbdsvkjbagfgafjdbv").equals("aaaaabbbbbbbbbdddddffggggjjjjjjjjkkkksssvvvv"));
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