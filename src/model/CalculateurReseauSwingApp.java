package model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculateurReseauSwingApp extends UIComposant {
    private JTextField inputAdresseIP;
    private JTextField inputMasque;
    private JTextArea outputResultats;
    private HistoriqueCalculs historique;

    public CalculateurReseauSwingApp() {
        historique = new HistoriqueCalculs();
        initialiserUI();
    }

    @Override
    public void initialiserUI() {
        setTitle("Calculateur Réseau");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panelHaut = new JPanel();
        panelHaut.setLayout(new GridLayout(2, 2));

        panelHaut.add(new JLabel("Adresse IP :"));
        inputAdresseIP = new JTextField();
        panelHaut.add(inputAdresseIP);

        panelHaut.add(new JLabel("Masque :"));
        inputMasque = new JTextField();
        panelHaut.add(inputMasque);

        JButton boutonCalculer = new JButton("Calculer");
        boutonCalculer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculer();
            }
        });

        outputResultats = new JTextArea();
        outputResultats.setEditable(false);

        add(panelHaut, BorderLayout.NORTH);
        add(new JScrollPane(outputResultats), BorderLayout.CENTER);
        add(boutonCalculer, BorderLayout.SOUTH);

        setVisible(true);
    }

    @Override
    public void mettreAJourResultats(String resultats) {
        outputResultats.setText(resultats);
    }

    private void calculer() {
        String adresse = inputAdresseIP.getText();
        String masque = inputMasque.getText();

        try {
            Reseau reseau = new Reseau(adresse, masque);
            String resultat = "Adresse : " + reseau.getAdresse() + "\n";
            resultat += "Classe : " + reseau.calculerClasse() + "\n";
            resultat += "Plage d'adresses : " + reseau.getAdresseDebut() + " - " + reseau.getAdresseFin() + "\n";

            mettreAJourResultats(resultat);
            historique.ajouterCalcul(resultat);
        } catch (Exception ex) {
            mettreAJourResultats("Erreur : " + ex.getMessage());
        }
    }
}
