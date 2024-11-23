package model;

import javax.swing.*;

public abstract class UIComposant extends JFrame {
    public abstract void initialiserUI();

    public abstract void mettreAJourResultats(String resultats);
}
