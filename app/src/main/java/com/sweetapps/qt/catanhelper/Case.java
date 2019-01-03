package com.sweetapps.qt.catanhelper;

import android.util.Log;

import java.io.Serializable;
import java.util.HashMap;

public class Case implements Serializable {

    static final long serialVersionUID = 123456L;

    IntersectionTrois iHaut;
    IntersectionTrois iHautDroite;
    IntersectionTrois iBasDroite;
    IntersectionTrois iBas;
    IntersectionTrois iBasGauche;
    IntersectionTrois iHautGauche;

    IntersectionDeux iiHautDroite;
    IntersectionDeux iiDroite;
    IntersectionDeux iiBasDroite;
    IntersectionDeux iiBasGauche;
    IntersectionDeux iiGauche;
    IntersectionDeux iiHautGauche;

    TypeCase type;
    int chiffreproba;

    public Case(TypeCase typeres, int proba){

        type=typeres;
        chiffreproba = proba;
        if (typeres==TypeCase.DESERT ||typeres==TypeCase.VIDE)
            chiffreproba = 0;
    }




    public enum TypeCase{
        //Objets directement construits
        VIDE("VIDE"),
        DESERT ("Desert"),
        BOIS ("Bois"),
        ARGILE ("Argile"),
        PIERRE ("Pierre"),
        CHEVRE ("Chevre"),
        MAIS ("Mais");

        private String name = "";

        //Constructeur
        TypeCase(String name){
            this.name = name;
        }



        public String toString(){
            return name;
        }


    }


    public int getValueProba(){
        int val = 0;

        switch (chiffreproba){
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                val = chiffreproba - 1;
                break;
            case 8:
                val =5;
                break;
            case 9:
                val =4;
                break;
            case 10:
                val =3;
                break;
            case 11:
                val =2;
                break;
            case 12:
                val =1;
                break;
            case 0:
                val = 0;
                break;
        }
        Log.e("valu","chiffre"+chiffreproba+"   "+val);
        return val;

    }
}
