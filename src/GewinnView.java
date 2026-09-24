import javax.swing.*;
import java.awt.*;

/**
 * @author niklas andrae
 * @date 24-09-2026
 * Die GewinnView Klasse erstellt das grafische Fenster (Swing-GUI)
 * und baut alle visuellen Elemente wie Labels und Textfelder auf.
 */
public class GewinnView extends JFrame {

    private JLabel lblRundenErgebnisWert;
    private JLabel lblGesamtPunkteWert;
    private JTextField txtSpielerZahl;
    private JTextField txtComputerZahl;
    private JButton btnNochEinmal;

    /**
     * Konstruktor: Baut das Layout auf.
     */
    public GewinnView(GewinnModel model) {
        setTitle("Zahlen-Gewinnspiel (v2.0)");
        setSize(450, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel panelOben = new JPanel(new GridLayout(2, 2, 5, 5));
        panelOben.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel lblRundenErgebnisTitel = new JLabel("Rundenergebnis:", SwingConstants.CENTER);
        JLabel lblGesamtPunkteTitel = new JLabel("Gesamtpunkte:", SwingConstants.CENTER);

        lblRundenErgebnisWert = new JLabel("Tippe eine Zahl von 1 bis 9", SwingConstants.CENTER);
        lblRundenErgebnisWert.setOpaque(true);
        lblRundenErgebnisWert.setBackground(Color.WHITE);
        lblRundenErgebnisWert.setFont(new Font("Arial", Font.BOLD, 14));

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

        txtSpielerZahl = new JTextField();
        txtSpielerZahl.setHorizontalAlignment(JTextField.CENTER);
        txtSpielerZahl.setFont(new Font("Arial", Font.BOLD, 22));

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
        btnNochEinmal.setEnabled(false);
        panelUnten.add(btnNochEinmal);

        add(panelUnten, BorderLayout.SOUTH);
    }

    // --- Getter-Methoden für den Controller ---
    public JTextField getTxtSpielerZahl() { return txtSpielerZahl; }
    public JTextField getTxtComputerZahl() { return txtComputerZahl; }
    public JButton getBtnNochEinmal() { return btnNochEinmal; }
    public JLabel getLblRundenErgebnisWert() { return lblRundenErgebnisWert; }
    public JLabel getLblGesamtPunkteWert() { return lblGesamtPunkteWert; }

    /**
     * Startet die Anwendung, initialisiert Model, View und Controller.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GewinnModel model = new GewinnModel();
            GewinnView view = new GewinnView(model);
            new GewinnController(model, view);
            view.setVisible(true);
        });
    }
}