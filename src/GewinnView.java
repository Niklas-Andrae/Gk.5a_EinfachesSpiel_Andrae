import javax.swing.*;
import java.awt.*;

/**
 * @author niklas andrae
 * @date 24-09-2026
 * Die GewinnView Klasse erstellt das grafische Fenster (Swing-GUI)
 * und baut alle visuellen Elemente wie Labels und Textfelder auf.
 */
public class GewinnView extends JFrame {


    private GewinnModel model;


    private JLabel lblRundenErgebnisWert;
    private JLabel lblGesamtPunkteWert;
    private JTextField txtSpielerZahl;
    private JTextField txtComputerZahl;
    private JButton btnNochEinmal;

    /**
     * Konstruktor: Initialisiert das Model und baut das Layout auf.
     */
    public GewinnView() {

        model = new GewinnModel();
        // Grundlegende Einstellungen für das Hauptfenster
        setTitle("Zahlen-Gewinnspiel (v1.0)");
        setSize(450, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Zentriert das Fenster auf dem Bildschirm
        setLayout(new BorderLayout(10, 10));


        JPanel panelOben = new JPanel(new GridLayout(2, 2, 5, 5));
        panelOben.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel lblRundenErgebnisTitel = new JLabel("Rundenergebnis:", SwingConstants.CENTER);
        JLabel lblGesamtPunkteTitel = new JLabel("Gesamtpunkte:", SwingConstants.CENTER);

        // Label für das Rundenergebnis mit weißem Hintergrund laut Anforderung
        lblRundenErgebnisWert = new JLabel("Tippe eine Zahl von 1 bis 9", SwingConstants.CENTER);
        lblRundenErgebnisWert.setOpaque(true);
        lblRundenErgebnisWert.setBackground(Color.WHITE);
        lblRundenErgebnisWert.setFont(new Font("Arial", Font.BOLD, 14));

        // Label für die Gesamtpunkte
        lblGesamtPunkteWert = new JLabel("Gesamtpunkte: " + model.getGesamtPunkte(), SwingConstants.CENTER);
        lblGesamtPunkteWert.setOpaque(true);
        lblGesamtPunkteWert.setBackground(Color.WHITE);
        lblGesamtPunkteWert.setFont(new Font("Arial", Font.BOLD, 14));

        panelOben.add(lblRundenErgebnisTitel);
        panelOben.add(lblGesamtPunkteTitel);
        panelOben.add(lblRundenErgebnisWert);
        panelOben.add(lblGesamtPunkteWert);

        add(panelOben, BorderLayout.NORTH);


        JPanel panelMitte = new JPanel(new GridLayout(2, 2, 10, 10));
        panelMitte.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JLabel lblDeineZahl = new JLabel("Deine Zahl:", SwingConstants.CENTER);
        JLabel lblComputer = new JLabel("Computer:", SwingConstants.CENTER);

        // Textfeld für die Eingabe des Spielers
        txtSpielerZahl = new JTextField();
        txtSpielerZahl.setHorizontalAlignment(JTextField.CENTER);
        txtSpielerZahl.setFont(new Font("Arial", Font.BOLD, 22));

        // Textfeld für den Computer (nicht bearbeitbar laut Vorgabe)
        txtComputerZahl = new JTextField();
        txtComputerZahl.setHorizontalAlignment(JTextField.CENTER);
        txtComputerZahl.setFont(new Font("Arial", Font.BOLD, 22));
        txtComputerZahl.setEditable(false);
        txtComputerZahl.setBackground(Color.WHITE);

        panelMitte.add(lblDeineZahl);
        panelMitte.add(lblComputer);
        panelMitte.add(txtSpielerZahl);
        panelMitte.add(txtComputerZahl);

        add(panelMitte, BorderLayout.CENTER);


        JPanel panelUnten = new JPanel();
        panelUnten.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));

        btnNochEinmal = new JButton("Noch einmal!");
        panelUnten.add(btnNochEinmal);

        add(panelUnten, BorderLayout.SOUTH);
        initController();
    }

    /**
     * Registriert die Enter-Taste im Eingabefeld und verknüpft sie mit der Spiellogik.
     */
    public void initController() {
        txtSpielerZahl.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent e) {
                if (e.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
                    spielZugAusfuehren();
                }
            }
        });
    }

    /**
     * Verarbeitet die Spielereingabe, berechnet die Runde über das Model und aktualisiert die Anzeige.
     */
    private void spielZugAusfuehren() {
        try {
            String eingabe = txtSpielerZahl.getText().trim();
            int zahl = Integer.parseInt(eingabe);

            if (zahl < 1 || zahl > 9) {
                JOptionPane.showMessageDialog(this, "Bitte gib eine Zahl zwischen 1 und 9 ein!", "Ungültige Eingabe", JOptionPane.WARNING_MESSAGE);
                return;
            }

            model.berechneRunde(zahl);
            txtComputerZahl.setText(String.valueOf(model.getComputerZahl()));

            int erg = model.getRundenErgebnis();
            if (erg == 20) {
                lblRundenErgebnisWert.setText("Treffer! (+20)");
            } else if (erg == 5) {
                lblRundenErgebnisWert.setText("Knapp daneben! (+5)");
            } else {
                lblRundenErgebnisWert.setText("Verloren (-10)");
            }
            lblGesamtPunkteWert.setText("Gesamtpunkte: " + model.getGesamtPunkte());

            if (model.hatGewonnen()) {
                JOptionPane.showMessageDialog(this, "Herzlichen Glückwunsch! Du hast gewonnen!", "Gewonnen", JOptionPane.INFORMATION_MESSAGE);
                txtSpielerZahl.setEnabled(false);
            } else if (model.hatVerloren()) {
                JOptionPane.showMessageDialog(this, "Leider verloren! Der Punktestand ist bei 0.", "Verloren", JOptionPane.ERROR_MESSAGE);
                txtSpielerZahl.setEnabled(false);
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Bitte gib eine gültige Zahl ein!", "Fehler", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GewinnView().setVisible(true));
    }
}