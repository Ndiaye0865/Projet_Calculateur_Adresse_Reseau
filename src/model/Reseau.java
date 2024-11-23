package model;

public class Reseau extends AdresseIP {
    private String masque;
    private String adresseDebut;
    private String adresseFin;

    public Reseau(String adresse, String masque) throws InvalidIPException {
        super(adresse);
        this.masque = masque;
        calculerPlageAdresse();
    }

    // Méthode pour calculer la plage d'adresses
    public void calculerPlageAdresse() {
        String[] adresseOctets = getAdresse().split("\\.");
        String[] masqueOctets = masque.split("\\.");

        int[] debut = new int[4];
        int[] fin = new int[4];

        for (int i = 0; i < 4; i++) {
            int adressePart = Integer.parseInt(adresseOctets[i]);
            int masquePart = Integer.parseInt(masqueOctets[i]);

            debut[i] = adressePart & masquePart;
            fin[i] = debut[i] | (~masquePart & 0xFF);
        }

        adresseDebut = String.format("%d.%d.%d.%d", debut[0], debut[1], debut[2], debut[3]);
        adresseFin = String.format("%d.%d.%d.%d", fin[0], fin[1], fin[2], fin[3]);
    }

    public String getAdresseDebut() {
        return adresseDebut;
    }

    public String getAdresseFin() {
        return adresseFin;
    }
}
