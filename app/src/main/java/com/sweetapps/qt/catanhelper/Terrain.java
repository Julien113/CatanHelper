package com.sweetapps.qt.catanhelper;

import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Terrain implements Serializable{
    static final long serialVersionUID = 12345L;
    ArrayList<ArrayList<Case>> listeCase;
    boolean extension;
    public Terrain(boolean extension, ArrayList<ArrayList<Case>> listeCase){
        this.listeCase = listeCase;
        this.extension = extension;

    }


    public String toString(){
        String text = "";
        for (int i = 0;i<listeCase.size();i++){
            for (int j=0; j<listeCase.get(i).size();j++){
                text+=" ";
                switch (listeCase.get(i).get(j).type){
                    case BOIS:
                        text+="B";
                        break;
                    case MAIS:
                        text+="M";
                        break;
                    case VIDE:
                        text+="V";
                        break;
                    case ARGILE:
                        text+="A";
                        break;
                    case CHEVRE:
                        text+="C";
                        break;
                    case DESERT:
                        text+="D";
                        break;
                    case PIERRE:
                        text+="P";
                        break;
                }
                text+=String.valueOf(listeCase.get(i).get(j).chiffreproba);


            }
            text+="\n";
        }


        return text;
    }


    public int getStats(Case.TypeCase type){
        int res = 0;
        for (int i = 0;i<listeCase.size();i++) {
            for (int j = 0; j < listeCase.get(i).size(); j++) {
                if (listeCase.get(i).get(j).type == type)
                    res+=listeCase.get(i).get(j).getValueProba();
            }
        }
        return res;
    }

    public ArrayList<IntersectionTrois> getBestSpot(){
        ArrayList<IntersectionTrois> list = new ArrayList<>();
        for (int i = 0; i<listeCase.size();i++){
            for (int j = 0;j<listeCase.get(i).size();j++){
                Case laCase = listeCase.get(i).get(j);
                if (!list.contains(laCase.iBas))
                    list.add(laCase.iBas);
                if (!list.contains(laCase.iBasDroite))
                    list.add(laCase.iBasDroite);
                if (!list.contains(laCase.iBasGauche))
                    list.add(laCase.iBasGauche);
                if (!list.contains(laCase.iHaut))
                    list.add(laCase.iHaut);
                if (!list.contains(laCase.iHautDroite))
                    list.add(laCase.iHautDroite);
                if (!list.contains(laCase.iHautGauche))
                    list.add(laCase.iHautGauche);
            }
        }


        Collections.sort(list, new Comparator<IntersectionTrois>() {
            @Override
            public int compare(IntersectionTrois t1, IntersectionTrois t2) {
                int s1 = t1.getValue();
                int s2 = t2.getValue();
                if (s1 > s2 ){
                    return 1;
                }
                else if (s1 < s2){
                    return -1;
                }
                else
                    return 0;
            }
        });
        return list;
    }




}
