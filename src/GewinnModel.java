import java.util.Random;
/**
 * @author Niklas Andrae
 * @date 23-09-2026
 * Das GewinnModel verwaltet die gesamte Spiellogik, den Punktestand
 * sowie die Auswertung der Runden zwischen Spieler und Computer.
 */
public class GewinnModel {
    private int gesamtPunkte;
    private int spielerZahl;
    private int computerZahl;
    private int rundenErgebnis;

    public GewinnModel() {
        this.gesamtPunkte = 30; // Startpunkte laut Regeln
    }
    /**
     * Generiert eine zufällige Computerzahl im Bereich von 1 bis 9.
     */
    public void berechneComputerZahl() {
        Random random = new Random();
        this.computerZahl = random.nextInt(9) + 1; // Zahl von 1 bis 9
    }
    /**
     * Berechnet das Ergebnis der aktuellen Spielrunde basierend auf dem Tipp des Spielers
     * und vergleicht ihn mit der Computerzahl.
     *
     * @param spielerZahl Die vom Spieler gewählte Zahl (1 bis 9)
     */
    public void berechneRunde(int spielerZahl) {
        this.spielerZahl = spielerZahl;
        berechneComputerZahl();

        if (this.spielerZahl == this.computerZahl) {
            this.rundenErgebnis = 20;
        } else if (Math.abs(this.spielerZahl - this.computerZahl) == 1) {
            this.rundenErgebnis = 5;
        } else {
            this.rundenErgebnis = -10;
        }

        this.gesamtPunkte += this.rundenErgebnis;
    }
    /**
     * Prüft, ob der Spieler das Spiel gewonnen hat.
     *
     * @return true, wenn der Spieler 100 oder mehr Punkte hat, sonst false
     */
    public boolean hatGewonnen() {
        return this.gesamtPunkte >= 100;
    }
    /**
     * Prüft, ob das Spiel für den Spieler verloren ist.
     *
     * @return true, wenn der Punktestand bei 0 oder darunter liegt, sonst false
     */
    public boolean hatVerloren() {
        return this.gesamtPunkte <= 0;
    }
    /**
     * Gibt den aktuellen Gesamtpunktestand zurück.
     *
     * @return Die aktuellen Punkte
     */
    public int getGesamtPunkte() {
        return gesamtPunkte;
    }
    /**
     * Gibt die zuletzt ermittelte Computerzahl zurück.
     *
     * @return Die Computerzahl (1 bis 9)
     */
    public int getComputerZahl() {
        return computerZahl;
    }
    /**
     * Gibt das Ergebnis der letzten Runde zurück.
     *
     * @return Die in der Runde erzielten Punkte (20, 5 oder -10)
     */
    public int getRundenErgebnis() {
        return rundenErgebnis;
    }
}
