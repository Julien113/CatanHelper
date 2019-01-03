package com.sweetapps.qt.catanhelper;

import java.io.Serializable;

public class IntersectionDeux implements Serializable{

    static final long serialVersionUID = 123455567L;

    Joueur route;
    Case caseSup;  // indice de ligne plus >  puis indice de colonne plus >
    Case caseInf;

    IntersectionDeux(Case caseInf,Case caseSup){
        this.caseInf=caseInf;
        this.caseSup = caseSup;

    }
    IntersectionDeux(){

    }
}
