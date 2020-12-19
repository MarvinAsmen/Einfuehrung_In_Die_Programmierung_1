# 📝  Beispieltest Einstufungstest
This branch contains my solution for the task [Beispieltest](https://tuwel.tuwien.ac.at/mod/resource/view.php?id=878839).

> :warning: **Important**: Please do not copy and paste my solutions, this is just a guide! Try to understand the code on your own, otherwise you will not learn anything. I am not responsible for any bad grades, be advised!

## What's the task?
* int myMult(int x, int y) multipliziert x mit y ohne den Operator '*' zu benutzen und gibt das Ergebnis zurück.
  Annahme:x > 0,y > 0
* char findMaxChar(String text) sucht das Zeichen mit dem größten ASCII-Wert innerhalb des Strings text und
  gibt dieses zurück.
  Annahme: text.length() > 0.
* String replaceNthChar(String text, int n, char replaceChar) ersetzt jedes Zeichen an einer Position, die ein
  echtes Vielfaches von n ist (also n, n*2, n*3, usw.) im String text durch das Zeichen replaceChar und gibt       diesen String zurück.
  Annahme: text.length() > 0, n > 0
* void printPattern(int n, char character) gibt auf der Konsole n Zeilen aus. In der ersten Zeile wird das         Zeichen character n-mal ausgegeben. Ab der zweiten Zeile wird die Ausgabe immer um jeweils eine Position nach     rechts verschoben und es wird immer ein Zeichen character mehr als in der Zeile davor ausgegeben (also n + 1     Zeichen in Zeile 2, n + 2 Zeichen in Zeile 3 usw.).
  Annahme: n > 0

## Constraints
* Sie dürfen keine globalen Variablen verwenden.
* Die vorgegebenen Methodenköpfe dürfen nicht erweitert oder geändert werden.
* Sie dürfen nicht die Klassen StringBuffer bzw. StringBuilder verwenden.
* Sie dürfen keine Lambdas, Streams oder Methodenreferenzen verwenden.
* Sie dürfen nur folgende Methoden aus der Klasse String benutzen: length, charAt

## How to use this?
* [Uebung.java](https://github.com/MarvinAsmen/Einfuehrung_In_Die_Programmierung_1/blob/BeispieltestEinstufungstest/BeispieltestEinstufungstest/src/Uebung.java) contains the Uebung class, where all awaited functions are implemented.
* [Main.java](https://github.com/MarvinAsmen/Einfuehrung_In_Die_Programmierung_1/blob/BeispieltestEinstufungstest/BeispieltestEinstufungstest/src/Main.java) contains the main() function where the code is executed and the expected results are printed to the command line.
