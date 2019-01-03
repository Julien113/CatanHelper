package com.sweetapps.qt.catanhelper;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

public class FragmentTourSuivant extends Fragment implements View.OnClickListener {


    Button bInit;
    Button bLoad;
    NumberPicker de;
    TextView aquidejouer;
    Button jouerdes;
    Button construirecolonieville;
    Button construireroute;
    Button echange;
    Button carteDev;
    Button finDuTour;

    Joueur courrant;

    Jeu copyJeu;

    static FragmentJeu fragmentJeu;
    static Jeu jeu ;
    public static FragmentTourSuivant newInstance(FragmentJeu fragjeu,Jeu jeue) {
        jeu = jeue;
        fragmentJeu= fragjeu;
        return new FragmentTourSuivant();
    }

    public FragmentTourSuivant() {
        courrant = jeu.listJoueur.get(jeu.joueurCourrant);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        aquidejouer = view.findViewById(R.id.aquidejouer);
        aquidejouer.setText("A "+courrant.nom+" de jouer!!");


        try {
            copyJeu = (Jeu)jeu.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        de = view.findViewById(R.id.de);
        jouerdes = view.findViewById(R.id.jouerdes);
        construirecolonieville  = view.findViewById(R.id.construire);
        construireroute = view.findViewById(R.id.construireroute);
        echange = view.findViewById(R.id.echanger);
        carteDev = view.findViewById(R.id.cartedev);
        finDuTour = view.findViewById(R.id.findutour);

        jouerdes.setOnClickListener(this);
        construirecolonieville.setOnClickListener(this);
        construireroute.setOnClickListener(this);
        echange.setOnClickListener(this);
        carteDev.setOnClickListener(this);
        finDuTour.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.jouerdes:


                break;
            case R.id.construire:

                break;
            case R.id.construireroute:

                break;
            case R.id.echanger:

                break;
            case R.id.cartedev:

                break;
            case R.id.findutour:
                getFragmentManager().popBackStackImmediate();
                fragmentJeu.init();
                break;
        }
    }
}
