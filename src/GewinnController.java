import javax.swing.*;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * @author niklas andrae
 * @date 24-09-2026
 * Der GewinnController verbindet Model und View und steuert die Programmlogik.
 */
public class GewinnController {

    private GewinnModel model;
    private GewinnView view;

    /**
     * Konstruktor des Controllers.
     * Initialisiert die Referenzen auf Model und View und registriert die Listener.
     *
     * @param model Das Datenmodell des Spiels
     * @param view Die Benutzeroberfläche des Spiels
     */
    public GewinnController(GewinnModel model, GewinnView view) {
        this.model = model;
        this.view = view;

        // Controller-Listener registrieren
        initController();
    }

    /**
     * Registriert alle Event-Listener an den UI-Komponenten der View.
     * Lauscht auf Tastatureingaben (Enter im Textfeld) und Button-Klicks.
     */
    private void initController() {
        // Enter-Taste im Textfeld abfangen
        view.getTxtSpielerZahl().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    spielZugAusfuehren();
                }
            }
        });

        // Klick auf den "Noch einmal!"-Button abfangen
        view.getBtnNochEinmal().addActionListener(e -> zuruecksetzen());
    }

    /**
     * Führt einen kompletten Spielzug aus, wenn der Spieler seine Zahl bestätigt.
     * Liest die Eingabe aus, validiert sie, stößt die Logik im Model an,
     * aktualisiert die Ansicht (Farben, Punkte, Computerzahl) und prüft auf Sieg/Niederlage.
     */
    private void spielZugAusfuehren() {
        try {
            String eingabe = view.getTxtSpielerZahl().getText().trim();
            int zahl = Integer.parseInt(eingabe);

            // Validierung, ob die Zahl im erlaubten Bereich liegt
            if (zahl < 1 || zahl > 9) {
                JOptionPane.showMessageDialog(view, "Bitte gib eine Zahl zwischen 1 und 9 ein!", "Ungültige Eingabe", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Model berechnet die Runde und liefert die Computerzahl
            model.berechneRunde(zahl);
            view.getTxtComputerZahl().setText(String.valueOf(model.getComputerZahl()));

            // Farb- und Text-Logik basierend auf dem Rundenergebnis (Version 2.0)
            int erg = model.getRundenErgebnis();
            if (erg == 20) {
                view.getLblRundenErgebnisWert().setText("Treffer! (+20)");
                view.getLblRundenErgebnisWert().setBackground(new Color(144, 238, 144)); // Sanftes Grün
            } else if (erg == 5) {
                view.getLblRundenErgebnisWert().setText("Knapp daneben! (+5)");
                view.getLblRundenErgebnisWert().setBackground(new Color(144, 238, 144)); // Sanftes Grün
            } else {
                view.getLblRundenErgebnisWert().setText("Verloren (-10)");
                view.getLblRundenErgebnisWert().setBackground(new Color(255, 182, 193)); // Sanftes Rot
            }
            view.getLblGesamtPunkteWert().setText("Gesamtpunkte: " + model.getGesamtPunkte());

            // Prüfung, ob das Spiel gewonnen oder verloren ist
            if (model.hatGewonnen()) {
                JOptionPane.showMessageDialog(view, "Herzlichen Glückwunsch! Du hast gewonnen!", "Gewonnen", JOptionPane.INFORMATION_MESSAGE);
                view.getLblRundenErgebnisWert().setBackground(Color.GREEN);
            } else if (model.hatVerloren()) {
                JOptionPane.showMessageDialog(view, "Leider verloren! Der Punktestand ist bei 0.", "Verloren", JOptionPane.ERROR_MESSAGE);
                view.getLblRundenErgebnisWert().setBackground(Color.RED);
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(view, "Bitte gib eine gültige Zahl ein!", "Fehler", JOptionPane.ERROR_MESSAGE);
        }

        // Eingabefeld sperren und den "Noch einmal!"-Button aktivieren (Input-Locking aus v1.1)
        view.getTxtSpielerZahl().setEnabled(false);
        view.getBtnNochEinmal().setEnabled(true);
    }

    /**
     * Setzt das Spielfeld für eine neue Runde zurück.
     * Leert Textfelder, setzt Farben und Status zurück und gibt das Eingabefeld wieder frei.
     */
    private void zuruecksetzen() {
        view.getTxtSpielerZahl().setText("");
        view.getTxtComputerZahl().setText("");
        view.getLblRundenErgebnisWert().setText("Tippe eine Zahl von 1 bis 9");
        view.getLblRundenErgebnisWert().setBackground(Color.WHITE);
        view.getTxtSpielerZahl().setEnabled(true);
        view.getTxtSpielerZahl().requestFocus();
        view.getBtnNochEinmal().setEnabled(false);
    }
}