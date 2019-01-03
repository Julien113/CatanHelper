package com.sweetapps.qt.catanhelper;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class FragmentInitTerrain extends Fragment implements View.OnClickListener,CompoundButton.OnCheckedChangeListener{
    static ArrayList<Joueur> listJoueur;
    static boolean isextension;
    Terrain terrain ;
    ArrayList<ArrayList<Case>> listCases;
    int nb;
    int nbligne;

    Jeu jeu;
    Button goJouer;
    NumberPicker probapicker;
    Button ajouter;
    Button retour;
    CheckBox cbois;
    CheckBox cfoins;
    CheckBox cargile;
    CheckBox cpierre;
    CheckBox cchevre;
    TextView tvterrain;
    TextView typeList;

    static final int[] indiceNoExt={ 3,4,5,4,3};
    static final int[] indiceExt={ 4,5,6,6,5,4};
    int[] indiceList;

    int iremplir = 0;
    int jremplir = 0;

    boolean finiremplir=false;

    public static FragmentInitTerrain newInstance(ArrayList<Joueur> joueurlist,boolean isexten) {
        isextension = isexten;
        listJoueur = joueurlist;
        return new FragmentInitTerrain();
    }

    public FragmentInitTerrain() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_init_terrain, container, false);
    }

    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        goJouer=view.findViewById(R.id.go);
        tvterrain = view.findViewById(R.id.terrain);
        goJouer.setOnClickListener(this);
        ajouter = view.findViewById(R.id.ajouter);
        retour = view.findViewById(R.id.retour);
        retour.setOnClickListener(this);
        ajouter.setOnClickListener(this);
        typeList = view.findViewById(R.id.typelist);


        cbois=view.findViewById(R.id.cbois);
        cfoins=view.findViewById(R.id.cpaille);
        cargile=view.findViewById(R.id.cargile);
        cpierre=view.findViewById(R.id.cpierre);
        cchevre=view.findViewById(R.id.cchevre);
        cbois.setOnCheckedChangeListener(this);
        cfoins.setOnCheckedChangeListener(this);
        cargile.setOnCheckedChangeListener(this);
        cpierre.setOnCheckedChangeListener(this);
        cchevre.setOnCheckedChangeListener(this);

        probapicker= view.findViewById(R.id.proba);
        probapicker.setMaxValue(12);
        probapicker.setMinValue(2);

       listCases = new ArrayList<>();
        terrain = new Terrain(isextension,listCases);
       nb = 5;
       if (isextension)
           nb = 6;
       nbligne= 5;
       if (isextension)
           nbligne = 7;
       if (isextension)
           indiceList=indiceExt;
       else
           indiceList=indiceNoExt;


       String txtTypeList="Type:";
       for (int i = 0 ; i < indiceList.length; i++)
           txtTypeList+=" "+indiceList[i];
       typeList.setText(txtTypeList);




    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ajouter:
                if (!finiremplir) {
                    Case.TypeCase typeCase = Case.TypeCase.VIDE;
                    if (cbois.isChecked()) {
                        typeCase = Case.TypeCase.BOIS;
                    }
                    if (cpierre.isChecked()) {
                        typeCase = Case.TypeCase.PIERRE;
                    }
                    if (cfoins.isChecked()) {
                        typeCase = Case.TypeCase.MAIS;
                    }
                    if (cargile.isChecked()) {
                        typeCase = Case.TypeCase.ARGILE;
                    }
                    if (cchevre.isChecked()) {
                        typeCase = Case.TypeCase.CHEVRE;
                    }
                    Case thecase = new Case(typeCase, probapicker.getValue());

                    if (jremplir == 0) {
                        ArrayList<Case> ligne = new ArrayList<>();
                        listCases.add(ligne);
                    }
                    listCases.get(iremplir).add(thecase);
                    jremplir++;
                    if (jremplir >= indiceList[iremplir]) {
                        iremplir++;
                        jremplir = 0;
                        if (iremplir >= indiceList.length)
                            finiremplir = true;
                    }
                    updateTerrain();
                }else
                    Toast.makeText(getActivity(), "Fini de remplir", Toast.LENGTH_SHORT).show();


                break;
            case R.id.retour:

                if (jremplir==0 && iremplir ==0)
                    return;

                if (jremplir == 1) {
                    listCases.remove(listCases.size()-1);
                    //iremplir--;
                    jremplir = 0; //indiceList[iremplir];
                }else {
                    if (jremplir==0){
                        iremplir--;
                        jremplir = indiceList[iremplir];
                    }
                    jremplir--;
                    listCases.get(iremplir).remove(jremplir);
                }
               if (finiremplir)
                   finiremplir=false;
                updateTerrain();



                break;
            case R.id.go:
                if (finiremplir) {
                    createIntersections();
                    Terrain terrain = new Terrain(isextension,listCases);
                    jeu = new Jeu(terrain,listJoueur);
                    jeu.listJoueur = listJoueur;
                    getFragmentManager().beginTransaction()
                            .replace(
                                    R.id.container, FragmentJeu.newInstance(jeu))
                            .addToBackStack(null)
                            .commit();
                }else
                    Toast.makeText(getActivity(), "Fini de remplir", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        if (!b)
            return;
        switch (compoundButton.getId()){
            case R.id.cbois:
                cargile.setChecked(false);
                cchevre.setChecked(false);
                cfoins.setChecked(false);
                cpierre.setChecked(false);
                break;
            case R.id.cargile:
                cbois.setChecked(false);
                cchevre.setChecked(false);
                cfoins.setChecked(false);
                cpierre.setChecked(false);
                break;
            case R.id.cchevre:
                cargile.setChecked(false);
                cbois.setChecked(false);
                cfoins.setChecked(false);
                cpierre.setChecked(false);
                break;
            case R.id.cpaille:
                cargile.setChecked(false);
                cchevre.setChecked(false);
                cbois.setChecked(false);
                cpierre.setChecked(false);
                break;
            case R.id.cpierre:
                cargile.setChecked(false);
                cchevre.setChecked(false);
                cfoins.setChecked(false);
                cbois.setChecked(false);
                break;
        }

    }

    public void updateTerrain(){
        tvterrain.setText(terrain.toString());
    }

    public void createIntersections(){
        for (int i  = 0; i<listCases.size(); i++){
            for (int j = 0; j <listCases.get(i).size();j++){

                Case laCase = listCases.get(i).get(j);

                if (i==0 && j==0){
                    laCase.iHautGauche = new IntersectionTrois();

                    laCase.iHautDroite=new IntersectionTrois();
                    laCase.iBasDroite=new IntersectionTrois();
                    laCase.iBas=new IntersectionTrois();
                    laCase.iBasGauche=new IntersectionTrois();
                    laCase.iHaut=new IntersectionTrois();




                    laCase.iiHautGauche = new IntersectionDeux(null,laCase);
                    laCase.iiGauche = new IntersectionDeux(null,laCase);
                    laCase.iiBasGauche = new IntersectionDeux(laCase,null);
                    laCase.iiBasDroite = new IntersectionDeux(laCase,null);
                    laCase.iiDroite = new IntersectionDeux(laCase,null);
                    laCase.iiHautDroite = new IntersectionDeux(null,laCase);

                }else{
                    if (i==0){
                        Case laCasedeGauche = listCases.get(i).get(j-1);
                        laCase.iHautGauche = laCasedeGauche.iHautDroite;
                        laCase.iHautDroite=new IntersectionTrois();
                        laCase.iBasDroite=new IntersectionTrois();
                        laCase.iBas=new IntersectionTrois();
                        laCase.iBasGauche= laCasedeGauche.iBasDroite;
                        laCase.iHaut=new IntersectionTrois();


                        laCase.iiHautGauche = new IntersectionDeux(null,laCase);
                        laCase.iiGauche = laCasedeGauche.iiDroite;
                        laCasedeGauche.iiDroite.caseSup = laCase;
                        laCase.iiBasGauche = new IntersectionDeux(laCase,null);
                        laCase.iiBasDroite = new IntersectionDeux(laCase,null);
                        laCase.iiDroite = new IntersectionDeux(laCase,null);
                        laCase.iiHautDroite = new IntersectionDeux(null,laCase);

                    }else{
                        if (i <= listCases.size()/2){
                            if (j==0){
                                Case laCasedeHautDroite = listCases.get(i-1).get(j);
                                laCase.iHautGauche = new IntersectionTrois();
                                laCase.iHautDroite=laCasedeHautDroite.iBas;
                                laCase.iBasDroite=new IntersectionTrois();
                                laCase.iBas=new IntersectionTrois();
                                laCase.iBasGauche= new IntersectionTrois();
                                laCase.iHaut=laCasedeHautDroite.iBasGauche;



                                laCase.iiHautGauche = new IntersectionDeux(null,laCase);
                                laCase.iiGauche = new IntersectionDeux(null,laCase);
                                laCase.iiBasGauche = new IntersectionDeux(laCase,null);
                                laCase.iiBasDroite = new IntersectionDeux(laCase,null);
                                laCase.iiDroite = new IntersectionDeux(laCase,null);
                                laCase.iiHautDroite = laCasedeHautDroite.iiBasGauche;
                                laCasedeHautDroite.iiBasGauche.caseSup = laCase;

                            }else{
                                if (j<listCases.get(i-1).size()) {
                                    Case laCasedeGauche = listCases.get(i).get(j - 1);
                                    Case laCasedeHautDroite = listCases.get(i - 1).get(j);
                                    laCase.iHautGauche = laCasedeGauche.iHautDroite;
                                    laCase.iHautDroite = laCasedeHautDroite.iBas;
                                    laCase.iBasDroite = new IntersectionTrois();
                                    laCase.iBas = new IntersectionTrois();
                                    laCase.iBasGauche = laCasedeGauche.iBasDroite;
                                    laCase.iHaut = laCasedeHautDroite.iBasGauche;

                                    Case laCasedeHautGauche = listCases.get(i - 1).get(j-1);
                                    laCase.iiHautGauche = laCasedeHautGauche.iiBasDroite;
                                    laCasedeHautGauche.iiBasDroite.caseSup = laCase;
                                    laCase.iiGauche = laCasedeGauche.iiDroite;
                                    laCasedeGauche.iiDroite.caseSup = laCase;
                                    laCase.iiBasGauche = new IntersectionDeux(laCase,null);
                                    laCase.iiBasDroite = new IntersectionDeux(laCase,null);
                                    laCase.iiDroite = new IntersectionDeux(laCase,null);
                                    laCase.iiHautDroite = laCasedeHautDroite.iiBasGauche;
                                    laCasedeHautDroite.iiBasGauche.caseSup = laCase;

                                }else{
                                    Case laCasedeHautGauche = listCases.get(i - 1).get(j-1);
                                    Case laCasedeGauche = listCases.get(i).get(j-1);
                                    laCase.iHautGauche = laCasedeGauche.iHautDroite;
                                    laCase.iHautDroite=new IntersectionTrois();
                                    laCase.iBasDroite=new IntersectionTrois();
                                    laCase.iBas=new IntersectionTrois();
                                    laCase.iBasGauche= laCasedeGauche.iBasDroite;
                                    laCase.iHaut= laCasedeHautGauche.iBasDroite;

                                    laCase.iiHautGauche = laCasedeHautGauche.iiBasDroite;
                                    laCasedeHautGauche.iiBasDroite.caseSup = laCase;
                                    laCase.iiGauche = laCasedeGauche.iiDroite;
                                    laCasedeGauche.iiDroite.caseSup = laCase;
                                    laCase.iiBasGauche = new IntersectionDeux(laCase,null);
                                    laCase.iiBasDroite = new IntersectionDeux(laCase,null);
                                    laCase.iiDroite = new IntersectionDeux(laCase,null);
                                    laCase.iiHautDroite = new IntersectionDeux(null,laCase);
                                }
                            }
                        }else{
                            if (j==0){
                                Case laCasedeHautGauche = listCases.get(i-1).get(j);
                                Case laCasedeHautDroite = listCases.get(i - 1).get(j+1);
                                laCase.iHautGauche = laCasedeHautGauche.iBas;
                                laCase.iHautDroite = laCasedeHautDroite.iBas;
                                laCase.iBasDroite = new IntersectionTrois();
                                laCase.iBas = new IntersectionTrois();
                                laCase.iBasGauche = new IntersectionTrois();
                                laCase.iHaut = laCasedeHautDroite.iBasGauche;

                                laCase.iiHautGauche = laCasedeHautGauche.iiBasDroite;
                                laCasedeHautGauche.iiBasDroite.caseSup = laCase;
                                laCase.iiGauche = new IntersectionDeux(null,laCase);
                                laCase.iiBasGauche = new IntersectionDeux(laCase,null);
                                laCase.iiBasDroite = new IntersectionDeux(laCase,null);
                                laCase.iiDroite = new IntersectionDeux(laCase,null);
                                laCase.iiHautDroite = laCasedeHautDroite.iiBasGauche;
                                laCasedeHautDroite.iiBasGauche.caseSup = laCase;
                            }else{
                                Case laCasedeHautGauche = listCases.get(i-1).get(j);
                                Case laCasedeHautDroite = listCases.get(i - 1).get(j+1);
                                Case laCasedeGauche = listCases.get(i).get(j-1);
                                laCase.iHautGauche = laCasedeHautGauche.iBas;
                                laCase.iHautDroite = laCasedeHautDroite.iBas;
                                laCase.iBasDroite = new IntersectionTrois();
                                laCase.iBas = new IntersectionTrois();
                                laCase.iBasGauche = laCasedeGauche.iBasDroite;
                                laCase.iHaut = laCasedeHautDroite.iBasGauche;

                                laCase.iiHautGauche = laCasedeHautGauche.iiBasDroite;
                                laCasedeHautGauche.iiBasDroite.caseSup = laCase;
                                laCase.iiGauche = laCasedeGauche.iiDroite;
                                laCasedeGauche.iiDroite.caseSup = laCase;
                                laCase.iiBasGauche = new IntersectionDeux(laCase,null);
                                laCase.iiBasDroite = new IntersectionDeux(laCase,null);
                                laCase.iiDroite = new IntersectionDeux(laCase,null);
                                laCase.iiHautDroite = laCasedeHautDroite.iiBasGauche;
                                laCasedeHautDroite.iiBasGauche.caseSup = laCase;
                            }
                        }
                    }
                }

                laCase.iHautDroite.caseTrois = laCase;
                laCase.iBasDroite.caseUn = laCase;
                laCase.iBas.caseUn = laCase;
                laCase.iBasGauche.caseDeux = laCase;
                laCase.iHautGauche.caseDeux = laCase;
                laCase.iHaut.caseTrois = laCase;
            }



        }
    }
}
