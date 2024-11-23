package model;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HistoriqueCalculs {
    private List<String> historique;

    public HistoriqueCalculs() {
        historique = new ArrayList<>();
    }

    public void ajouterCalcul(String calcul) {
        historique.add(calcul);
    }

    public void sauvegarderHistorique(String fichier) throws IOException {
        try (FileWriter writer = new FileWriter(fichier)) {
            for (String ligne : historique) {
                writer.write(ligne + "\n");
            }
        }
    }
}
