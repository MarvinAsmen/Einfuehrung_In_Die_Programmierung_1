public class Main {

    public static void main(String[] args){

        Uebung u = new Uebung();

        short result;
        String test="Teststring_Einstufungstest";

        System.out.println("--- Starting Test ---");
        System.out.println("-- Task 1 --");

        result = (short) u.myMult(3,7);
        System.out.println(result);
        System.out.println(u.myMult(21,4));
        System.out.println(u.myMult(2,3500));

        System.out.println("\n-- Task 2 --");
        System.out.println(u.findMaxChar(test));
        System.out.println(u.findMaxChar("abcdefg"));
        System.out.println(u.findMaxChar("4321"));

        System.out.println("\n-- Task 3 --");
        System.out.println(u.replaceNthChar(test,1,'0'));
        System.out.println(u.replaceNthChar(test,3,'#'));
        System.out.println(u.replaceNthChar(test,10,'-'));
        System.out.println(u.replaceNthChar(test,30,'X'));
        System.out.println(u.replaceNthChar("A",1,'#'));

        System.out.println("\n-- Task 4 --");
        u.printPattern(3, '*');
        System.out.println();
        u.printPattern(5, '+');

        System.out.println();
        System.out.println("--- END ---");
        System.out.println("by Marvin Asmen Arduç");
        System.out.println("12024700");
        System.out.println("Sat 3. Oct 2020");
    }

}
