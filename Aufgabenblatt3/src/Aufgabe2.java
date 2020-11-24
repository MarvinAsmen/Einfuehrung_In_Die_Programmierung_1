/*
    Aufgabe 2) Überladen von Methoden
*/
public class Aufgabe2 {

    private static void addSeparator(String text, char separator) {
        if(text==null) throw new IllegalArgumentException("Text darf nicht leer sein!");
        String ns="";
        for (int i = 0; i < text.length(); i++) {
            ns+=text.charAt(i);
            if (i!=text.length()-1) ns+=separator;
        }
        System.out.println(ns);
    }

    private static void addSeparator(int number, char separator) {
        if(number<1) throw new IllegalArgumentException("Number muss größer 1 sein!");
        addSeparator(Integer.toString(number),separator);
    }

    private static void addSeparator(String text, String separators) {
        if(text==null || separators==null) throw new IllegalArgumentException("Text oder separators dürfen nicht null sein!");
        for (char ch: separators.toCharArray()){
            addSeparator(text,ch);
        }
    }

    private static void addSeparator(String text) {
        if(text==null) throw new IllegalArgumentException("Text darf nicht null sein!");

        addSeparator(text,"$");
    }

    public static void main(String[] args) {
        String text0 = "A";
        String text1 = "AB";
        String text2 = "Hello!";
        String text3 = "-Java-";
        String text4 = " TEST ";

        System.out.println("Aufgabe 1\n");
        addSeparator(text0, '?');
        addSeparator(text1, ',');
        addSeparator(text2, ':');
        addSeparator(text3, '-');
        addSeparator(text4, '+');

        System.out.println("Aufgabe 2\n");
        addSeparator(1, '$');
        addSeparator(35, '*');
        addSeparator(657, ':');
        addSeparator(2048, '#');
        addSeparator(26348, '+');

        System.out.println("Aufgabe 3\n");
        addSeparator(text1, "+#$");
        addSeparator(text2, ":*&!");

        System.out.println("Aufgabe 4\n");
        addSeparator(text0);
        addSeparator(text1);
        addSeparator(text2);
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