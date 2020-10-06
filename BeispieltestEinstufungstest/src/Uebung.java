public class Uebung {

    public static int myMult(int x, int y){
        if(x > 0 && y >0){
            int number=0;

            for (int i = 0; i < y; i++) {
                number+=x;
            }

            return number;
        }else{
            throw new IllegalArgumentException("x and y must be > 0!");
        }
    }

    public static char findMaxChar(String text){
        if(text.length() > 0){
            char biggestAscii = text.charAt(0);
            for (int i = 0; i < text.length(); i++) {
                if(biggestAscii < text.charAt(i)) biggestAscii=text.charAt(i);
            }
            return biggestAscii;
        }else{
            throw new IllegalArgumentException("Enter a Text!");
        }
    }

    public static String replaceNthChar(String text, int n, char replaceChar){
        if(text.length() > 0 && n >0){
            String newString="";
            for (int i = 0; i < text.length(); i++) {
                if(i!=0){
                    if(i%n==0){
                        newString+=replaceChar;
                    }else{
                        newString+=text.charAt(i);
                    }
                }else{
                    newString+=text.charAt(i);
                }
            }
            return newString;
        }else{
            throw new IllegalArgumentException("Enter a Text and n must be > 0");
        }
    }

    public static void printPattern(int n, char character){
        if(n>0){
            for (int i = 0; i < n; i++) {
                String space = "";
                for (int j = 0; j < i; j++) {
                    space+=" ";
                }

                String chars = "";
                for (int j = 0; j < n+i; j++) {
                    chars+=character;
                }
                System.out.println(space+chars);
            }
        }else{
            throw new IllegalArgumentException("n must be > 0");
        }
    }

}



























































/*
 *
 * Made by Marvin Asmen Arduç
 * 12024700
 * Sat 3. Oct 2020
 *
 */
