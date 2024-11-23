package model;

public class AdresseIP implements Calculable {
    private String adresse;
    private String classe;

    // Constructeur
    public AdresseIP(String adresse) throws InvalidIPException {
        if (!validerAdresse(adresse)) {
            throw new InvalidIPException("Adresse IP invalide : " + adresse);
        }
        this.adresse = adresse;
        this.classe = calculerClasse();
    }

    // Méthode pour valider une adresse IP
    private boolean validerAdresse(String adresse) {
        String[] octets = adresse.split("\\.");
        if (octets.length != 4) return false;

        try {
            for (String octet : octets) {
                int valeur = Integer.parseInt(octet);
                if (valeur < 0 || valeur > 255) return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }

    // Méthode pour calculer la classe d'une adresse IP
    @Override
    public String calculerClasse() {
        int premierOctet = Integer.parseInt(adresse.split("\\.")[0]);
        if (premierOctet >= 1 && premierOctet <= 126) return "A";
        if (premierOctet >= 128 && premierOctet <= 191) return "B";
        if (premierOctet >= 192 && premierOctet <= 223) return "C";
        return "Inconnu";
    }

    public String getAdresse() {
        return adresse;
    }

    public String getClasse() {
        return classe;
    }
}
