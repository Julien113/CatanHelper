package com.sweetapps.qt.catanhelper;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class FragmentJeu extends Fragment implements View.OnClickListener {
    static Jeu jeu;

    private RecyclerView spotRV;
    private AdapterSpot spotAdapter;
    private LinearLayoutManager mLayoutManager;

    TextView stats;
    Button tourSuivant;
    public static FragmentJeu newInstance(Jeu jeu2) {
        jeu = jeu2;
        return new FragmentJeu();
    }

    public FragmentJeu() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_jeu, container, false);
    }

    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

        spotRV=view.findViewById(R.id.bestspotrv);

        stats = view.findViewById(R.id.stats);
        updateStats();

        tourSuivant = view.findViewById(R.id.nextturn);
        tourSuivant.setOnClickListener(this);


        /*for (ArrayList<Case> ligne:jeu.terrain.listeCase){
            for (Case lacase : ligne){
                listinter.add(lacase.iHautGauche);
                listinter.add(lacase.iHautDroite);
                listinter.add(lacase.iHaut);
                listinter.add(lacase.iBasGauche);
                listinter.add(lacase.iBasDroite);
                listinter.add(lacase.iBas);
            }
        }*/



    }

    public void init(){
        ArrayList<IntersectionTrois> listinter = jeu.terrain.getBestSpot();
        spotAdapter = new AdapterSpot(getActivity(),listinter);
        mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, true);
        mLayoutManager.setStackFromEnd(true);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(),
                mLayoutManager.getOrientation());
        spotRV.addItemDecoration(dividerItemDecoration);
        spotRV.setLayoutManager(mLayoutManager);
        spotRV.setAdapter(spotAdapter);

        Jeu.saveJeu(jeu,getActivity());
    }

    public void updateStats(){
        String textStats = "Stats:";
        for (Case.TypeCase type : Case.TypeCase.values()){
            textStats+="\n"+type+": "+jeu.terrain.getStats(type);

        }
        stats.setText(textStats);


    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.nextturn:
                getFragmentManager().beginTransaction()
                        .replace(
                                R.id.container, FragmentTourSuivant.newInstance(this,jeu))
                        .addToBackStack(null)
                        .commit();
                break;

        }
    }
}
