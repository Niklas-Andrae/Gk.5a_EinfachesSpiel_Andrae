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
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GewinnView().setVisible(true));
    }
}