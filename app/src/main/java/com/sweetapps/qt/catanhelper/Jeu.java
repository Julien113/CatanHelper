package com.sweetapps.qt.catanhelper;

import android.content.Context;
import android.util.Log;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class Jeu implements Serializable,Cloneable{

    static final long serialVersionUID = 1234567L;
    ArrayList<Joueur> listJoueur;
    Terrain terrain;
    int joueurCourrant = 0;

    public Jeu(Terrain terrain,ArrayList<Joueur> listJoueur){
        this.listJoueur = listJoueur;
        this.terrain = terrain;

    }



    public static void saveJeu(Jeu jeu, Context mActivity){
        try {
            Log.e("tag","save "+jeu.toString());
            // On clean le fichier
            FileOutputStream output = mActivity.openFileOutput("jeu", MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(output);
            oos.writeObject(jeu);
            Log.e("ca a ecris",jeu.toString());
            if(oos != null) {
                oos.flush();
                oos.close();
            }
            if(output != null) {
                output.flush();
                output.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static Jeu getJeu(Context mActivity){
        Jeu jeu = null;
        try {
            FileInputStream input = mActivity.openFileInput("jeu");
            ObjectInputStream ois = new ObjectInputStream(input);

            jeu = (Jeu) ois.readObject();


            Log.e("tag","load "+jeu.toString());
            if (ois!=null)
                ois.close();
            if(input != null)
                input.close();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }

        return jeu;
    }

    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
