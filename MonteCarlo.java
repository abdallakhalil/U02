package Tut02;
public class MonteCarlo {
    public static void pi(int n) {
        double anzVonZkleiner1 = 0;
        double x, y, z;

        for (int i = 0; i<n; i++) {

            //Berechnung pseudozufälliger Werte für x und y im Intervall [0,1].
            x = Math.random();
            y = Math.random();

            z = (x * x) + (y * y);

            // anzVonZKleiner1 ist eine Variable, die speichert,
            // wie oft z kleiner als 1 war.
            if (z < 1) {
                anzVonZkleiner1 += 1;
            }
        }
        // Formel für pi aus: (anzVonZKleiner1 / n) = (pi / 4)
        double pi = (anzVonZkleiner1 * 4) / n;
        System.out.println(pi);
    }

    public static void dice(int n){
        int wuerfelMax = 6; //maximale Augenzahl eines Würfels
        int wuerfelMin = 1; //minimale Augenzahl eines Würfels
        int wurf1, wurf2;
/*
Die folgenden Variablen kommen in der "A" und "B" Variante vor. Dies ist auf die Aufgaben zurückzuführen,
da man für Aufgabe a) zwei Fünfen und für b) eine Fünf und dann eine Sechs würfeln soll. Hier speichert z.B.
die Variable "anzahlA" wie oft zweimal 5 gewürflet wurde. Dies dient dazu, das ich nur eine Schleife verwende
und beide Aufgaben (a und b) gleichzeitig in dieser Schleife löse.
 */
        int anzahlA = 0; //Anzahl wie oft zweimal 5 gewürfelt wurde
        int anzWuerfeBisErflogA = 0; //Anzahl wie oft zwischen Erfolgen gewürfelt wurde
        double sumWuerfeBisErfolgA = 0; //Summe der Wurfanzahl zwischen Erfolgen
        double mittelwertA;

        int anzahlB = 0;
        int anzWuerfeBisErflogB = 0;
        double sumWuerfeBisErfolgB = 0;
        double mittelwertB;

        for (int i=0; i<n; i++) {
            // Anzahl der Würfe bis Erfolg wird erhöht
            anzWuerfeBisErflogA++;
            anzWuerfeBisErflogB++;

            // Erzeugen von pseudozufälligen ganzzahligen Zahlen im Intervall [1,6]
            wurf1 = (int)(Math.random()*(wuerfelMax-wuerfelMin+1)+wuerfelMin);
            wurf2 = (int)(Math.random()*(wuerfelMax-wuerfelMin+1)+wuerfelMin);


            if (wurf1 == 5 && wurf2 == 5) { //Erfolg, da zwei Fünfen gewürfelt wurden.
                anzahlA++;
                sumWuerfeBisErfolgA += anzWuerfeBisErflogA; //Summe der Würfe wird erhöht
                anzWuerfeBisErflogA = 0;
            }
            else if (wurf1 == 5 && wurf2 ==6) { // Erfolg, da eine Fünf und dann eine Sechs gewürfelt wurde.
                anzahlB++;
                sumWuerfeBisErfolgB += anzWuerfeBisErflogB;
                anzWuerfeBisErflogB = 0;
            }
        }

        mittelwertA = (sumWuerfeBisErfolgA) / anzahlA;
        mittelwertB = (sumWuerfeBisErfolgB) / anzahlB;

        // Bei wenigen Durchläufen kann der Mittelwert noch 0 betragen, da bisher kein Erfolg gewürfelt wurde.
        // Die Werte vom mittelwert werden gerundet, um eine Anzahl der Würfe zu bekommen.
        if (mittelwertA > 0) {
            System.out.println("Fuer zwei Fuenfen hintereinander wuerfelt man im Mittel "
                    + Math.round(mittelwertA) + " mal.");
            System.out.println("Fuer eine Fuenf und darauf eine Sechs wuerfelt man im Mittel "
                    + Math.round(mittelwertB) + " mal.");
        } else{
            // Fehlerausgabe bei unzureichenden Durchläufen
            System.out.println("Zu wenig Durchlaeufe fuer ein sinnvolles Ergenbins");
        }
    }

    public static void zonk(int n) {
        //Array das den Türen entspricht. Der Gewinn ist hinter der Tür, die im Array "true" ist.
        boolean[] tueren = new boolean[3]; //Array das den Türen entspricht. Die
        int tuerNummerGewinn;   //Nummer der Tür, hinter der der Gewinn ist
        double anzBleiben = 0;      //Anzahl, wie oft man beim Bleiben gewonnen hätte.
        double anzWechseln = 0;     //Anzahl, wie oft man beim Wechseln gewonnen hätte.
        double pGewinnBleiben;      //Wahrscheinlichkeit, wie oft man beim Bleiben gewonnen hätte.
        double pGewinnWechseln;     ////Wahrscheinlichkeit, wie oft man beim Wechseln gewonnen hätte.

/*
Die folgende for-Schleife wird eine Position des Arrays tueren[] pro Durchlauf auf "true" setzen. Das soll der
Gewinn sein. Da die Verteilung des Gewinnes stets zufällig ist, vereinfachen wir, dass der Kandidat stets die
erste Tür (tueren[0]) als erstes wählen wird.
 */
        for (int j = 0; j < n; j++) {
            // Alle Werte von tueren[] werden auf false gesetzt, da die Schleife mehrfach durchlaufen wird.
            for (int i = 0; i < tueren.length; i++) {
                tueren[i] = false;
            }

            //Bestimmung der Tür, die den Gewinn enthält, durch pseudozufällige Zahlen von 0 bis 2.
            tuerNummerGewinn = (int) (Math.random() * (3));
            tueren[tuerNummerGewinn] = true;

            //Feststellung ob der Kandidat bei Bleiben oder Wechseln gewinnen würde.
            //Da der Kandidat tueren[0] nimmt, wird die Anzahl von Gewinnen beim Bleiben (anzBleiben)
            //erhöht, wenn der Gewinn hinter tueren[0] liegt (tueren[0] == wahr). Wenn tueren[0] "false"
            //ist, würde der Kandidat beim Wechseln gewinnen.
            if (tueren[0]) {
                anzBleiben++;
            } else{
                anzWechseln++;
            }
        }
        pGewinnBleiben = anzBleiben / n;        //Wahrscheinlichkeitsberechnung
        pGewinnWechseln = anzWechseln / n;

        System.out.println("Die Gewinnwahrscheinlichkeiten betragen fuer: ");
        System.out.println("Bleiben: " + pGewinnBleiben);
        System.out.println("Wechseln: " + pGewinnWechseln);
    }

    public static void main(String[] args){
        int n = 0;

        // try und catch zum Abfangen ungültiger Eingaben.
        try{
            n = Integer.parseInt(args[1]);
        }
        catch(Exception e){
            System.out.println("Bitte geben Sie einen gueltigen Wert ein.");
            System.exit(1);
        }

        //Abfangen zu kleiner n-Werte
        if (n > 1) {
            String method = args[0];
            switch(method){
                case "Pi":
                    pi(n);
                    break;
                case "Zonk":
                    zonk(n);
                    break;
                case "Dice":
                    dice(n);
                    break;
                default:
                    System.out.println("Bitte geben Sie eine gueltige Eingabe ein. (Pi, Zonk oder Dice)");
            }
        }
        else{
            System.out.println("Bitte geben Sie einen Wert groeßer gleich eins ein.");
        }
    }
}
