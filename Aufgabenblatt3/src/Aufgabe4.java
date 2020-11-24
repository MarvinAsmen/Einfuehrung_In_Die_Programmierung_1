/*
    Aufgabe 4) Rekursion
*/
public class Aufgabe4 {

    private static int countCharsSmaller(String text, char value) {
        Exception e = new Exception();
        e.fillInStackTrace();
        if (e.getStackTrace().length == 2) {
            if (text == null || text.length() <=0) throw new IllegalArgumentException("Text darf nicht null sein oder länge muss größer 0 sein");
        }
        if(text.length() == 1) return text.charAt(0) < value ? 1 : 0;
        return (text.charAt(0) < value ? 1 : 0) + countCharsSmaller(text.substring(1),value) ;
    }

    private static String removeCharsInString(String text, char start, char end) {
        Exception e = new Exception();
        e.fillInStackTrace();
        if (e.getStackTrace().length == 2) {
            if(text == null || text.length() <=0 || start>end) throw new IllegalArgumentException(
                    "Text dar nicht null sein. Length muss >0 und start muss kleiner gleich end sein!"
            );
        }
        char c = text.charAt(0);
        if(text.length() == 1) return (c > start && c< end) ? "" : ""+c;
        return (c > start && c< end) ? removeCharsInString(text.substring(1), start,end) : c + removeCharsInString(text.substring(1), start,end);
    }

    private static String shiftDigitRight(String text) {
        Exception e = new Exception();
        e.fillInStackTrace();
        if (e.getStackTrace().length == 2) {
            String numbers = text.replaceAll("[^0-9]", "");
            if(numbers.length() == 0 || text.length() <=1) return text;
            if (numbers.length()>1) throw new IllegalArgumentException("Dar nur 1 Ziffer vorhanden sein!");
            if (text==null) throw new IllegalArgumentException("Text darf nicht null sein");
        }
        if(text.length() == 2){
            if(!Character.isDigit(text.charAt(0))) return text;
            return Character.toString(text.charAt(1))+Character.toString(text.charAt(0));
        }
        if(!Character.isDigit(text.charAt(0))){
            return Character.toString(text.charAt(0)) + shiftDigitRight(text.substring(1));
        }else {
            String shifted = Character.toString(text.charAt(1))+Character.toString(text.charAt(0))+text.substring(2);
            return shiftDigitRight(shifted);
        }
    }

    public static void main(String[] args) {
        System.out.println(countCharsSmaller("DAS (ist) ]ein] Test!", (char) 100));
        System.out.println(countCharsSmaller("a!", (char) 200));
        System.out.println(countCharsSmaller("Ein Test", (char) 100));
        System.out.println();

        System.out.println(removeCharsInString("testtrompete", 'd', 'n'));
        System.out.println(removeCharsInString("test", 's', 'u'));
        System.out.println(removeCharsInString("t", 't', 't'));
        System.out.println(removeCharsInString("angabe", 'a', 'z'));
        System.out.println();

        System.out.println(shiftDigitRight("az3kj"));
        System.out.println(shiftDigitRight("kjdn{nd8xngs+d#k"));
        System.out.println(shiftDigitRight(""));
        System.out.println(shiftDigitRight("4"));
        System.out.println(shiftDigitRight("ji)oiepk(2"));
        System.out.println(shiftDigitRight("ohneziffer"));

        assert (countCharsSmaller("DAS (ist) ]eine] Test!", (char) 100) == 12);
        assert (countCharsSmaller("a!", (char) 200) == 2);
        assert (countCharsSmaller("Ein Test", (char) 100) == 3);

        assert (removeCharsInString("testtrompete", 'd', 'n').equals("tsttropt"));
        assert (removeCharsInString("test", 's', 'u').equals("es"));
        assert (removeCharsInString("t", 't', 't').equals("t"));
        assert (removeCharsInString("angabe", 'a', 'z').equals("aa"));

        assert (shiftDigitRight("az3kj").equals("azkj3"));
        assert (shiftDigitRight("kjdn{nd8xngs+d#k").equals("kjdn{ndxngs+d#k8"));
        assert (shiftDigitRight("").equals(""));
        assert (shiftDigitRight("4").equals("4"));
        assert (shiftDigitRight("ji)oiepk(2").equals("ji)oiepk(2"));
        assert (shiftDigitRight("ohneziffer").equals("ohneziffer"));
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