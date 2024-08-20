import java.util.Random;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {
    public static void main(String[] args) {
        Random random = new Random(); // Zufallszahlengenerator
        Scanner scanner = new Scanner(System.in); // Scanner für Benutzereingaben
        System.out.println("---Willkommen bei der Lottostatistik---");
        System.out.println("Wie oft möchten Sie eine Ziehung simulieren: ");

        int anzahl = 0;


        while (true) {
            try {
                anzahl = scanner.nextInt(); // Eingabe der Anzahl der Ziehungen

                if (anzahl <= 0) { // Überprüfen, ob die Eingabe positiv ist
                    System.out.println("Die Eingabe muss eine positive Zahl sein.");
                } else {
                    System.out.println("Die Eingabe war erfolgreich - Bitte warten...");

                    try {
                        Thread.sleep(2000); // Programm für 2 Sekunden pausieren
                    } catch (InterruptedException e) {
                        System.out.println("Der Wartevorgang wurde unterbrochen.");
                    }

                    break; // Gültige Eingabe, Schleife beenden
                }
            } catch (InputMismatchException e) {
                System.out.println("Fehler: Bitte geben Sie eine gültige Zahl ein.");
                scanner.next(); // Falsche Eingabe verwerfen
            }
        }


        // Array zur Zählung der Häufigkeit von Zahlen (1-45)
        int[] frequency = new int[46]; // Index 0 bleibt ungenutzt, weil Zahlen von 1-45

        // Simulation der Ziehungen
        for (int n = 0; n < anzahl; n++) { // Schleife für die Anzahl der Ziehungen
            int[] lottoNumbers = new int[6]; // Array für die 6 gezogenen Lottozahlen

            for (int i = 0; i < lottoNumbers.length; i++) { // Schleife für jede der 6 Zahlen in einer Ziehung
                int newNumber;

                // Schleife zur Generierung einer einzigartigen Zahl
                do {
                    newNumber = random.nextInt(45) + 1; // Generieren einer Zahl zwischen 1 und 45
                } while (istSchonVorhanden(lottoNumbers, newNumber)); // Überprüfen, ob die Zahl bereits in lottoNumbers ist

                lottoNumbers[i] = newNumber; // Einfügen der neuen, einzigartigen Zahl in das Array
                frequency[newNumber]++; // Erhöhen der Häufigkeit dieser Zahl
            }
        }

        // Ergebnisdarstellung
        System.out.println("Häufigkeit des Vorkommens jeder Zahl (1-45) und deren prozentualer Anteil:");

        for (int i = 1; i <= 45; i++) { // Schleife zur Ausgabe der Häufigkeiten und Prozentsätze
            double percentage = (double) frequency[i] / (anzahl * 6) * 100; // Berechnung des Prozentsatzes
            System.out.printf("Zahl %2d: %d Mal, %.2f%%\n", i, frequency[i], percentage); // Ausgabe des Ergebnisses
        }

        scanner.close(); // Schließen des Scanners
    }

    // Methode zur Überprüfung, ob eine Zahl bereits im Array enthalten ist
    private static boolean istSchonVorhanden(int[] zahlen, int zahl) {
        for (int i = 0; i < zahlen.length; i++) { // Schleife durch das Array
            if (zahlen[i] == zahl) { // Wenn die Zahl gefunden wird
                return true; // Zahl ist bereits vorhanden
            }
        }
        return false; // Zahl ist noch nicht vorhanden
    }
}
