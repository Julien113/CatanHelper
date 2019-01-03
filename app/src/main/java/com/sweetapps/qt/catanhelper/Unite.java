package com.sweetapps.qt.catanhelper;

import java.io.Serializable;

public class Unite implements Serializable {
    static final long serialVersionUID = 1234L;

    TypeUnite type;
    Joueur proprio;

    public Unite(TypeUnite type ){
        this.type = type;
    }


    public enum TypeUnite{
        //Objets directement construits
        VIDE("VIDE"),
        COLONIE ("Colonie"),
        VILLE ("Ville");

        private String name = "";

        //Constructeur
        TypeUnite(String name){
            this.name = name;
        }



        public String toString(){
            return name;
        }


    }
}
