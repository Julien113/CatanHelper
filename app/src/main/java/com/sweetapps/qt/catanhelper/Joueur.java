package com.sweetapps.qt.catanhelper;

import java.io.Serializable;


// Initialisation ressource
// JUJ JJ/MM/AA
public class Joueur implements Serializable {
    static final long serialVersionUID = 12345678L;
    String nom;

    int bois = 0;
    int chevre = 0;
    int paille = 0;
    int brique = 0;
    int pierre = 0;
    int pointVictoire = 0;

    public Joueur(String nom){
        this.nom = nom;
    }
}
