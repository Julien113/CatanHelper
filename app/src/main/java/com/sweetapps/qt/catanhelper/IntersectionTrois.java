package com.sweetapps.qt.catanhelper;

import java.io.Serializable;

public class IntersectionTrois implements Serializable {
    static final long serialVersionUID = 123444567L;

    Case caseUn; //dans l'ordre horaire partant de haut si 1/2  partant de haut gauche si 2/1
    Case caseDeux;
    Case caseTrois;

    Unite construiction ;

    public IntersectionTrois(){
        construiction = new Unite(Unite.TypeUnite.VIDE);
    }


    public int getValue(){
        int s1 = 0;
        if (caseUn!=null){
            s1+=caseUn.getValueProba();
        }
        if (caseDeux!=null){
            s1+=caseDeux.getValueProba();
        }
        if (caseTrois!=null){
            s1+=caseTrois.getValueProba();
        }
        return s1;
    }

}
