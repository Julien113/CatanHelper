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
import android.widget.CheckBox;
import android.widget.EditText;

import java.util.ArrayList;

public class FragmentInit extends Fragment implements View.OnClickListener {
    private RecyclerView rV;
    private AdapterJoueur joueurAdapter;
    private LinearLayoutManager mLayoutManager;

    Button ajouterJoueur;
    Button goTerrain;
    EditText nomjoueur;
    CheckBox isExtension;

    ArrayList<Joueur> joueurList;
    public static FragmentInit newInstance() {
        return new FragmentInit();
    }

    public FragmentInit() {
        joueurList = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_init, container, false);
    }

    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

        rV=view.findViewById(R.id.rv);
        ajouterJoueur = view.findViewById(R.id.ajouter);
        nomjoueur = view.findViewById(R.id.nomjoueur);
        goTerrain = view.findViewById(R.id.go);
        isExtension = view.findViewById(R.id.extension);
        ajouterJoueur.setOnClickListener(this);
        goTerrain.setOnClickListener(this);


        joueurAdapter = new AdapterJoueur(getActivity(),joueurList);
        mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, true);
        mLayoutManager.setStackFromEnd(true);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(),
                mLayoutManager.getOrientation());
        rV.addItemDecoration(dividerItemDecoration);
        rV.setLayoutManager(mLayoutManager);
        rV.setAdapter(joueurAdapter);





    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ajouter:
                if (nomjoueur.getText().length()>0){
                    Joueur joueur = new Joueur(nomjoueur.getText().toString());
                    nomjoueur.setText("");
                    joueurList.add(joueur);
                    joueurAdapter.notifyDataSetChanged();
                }
                break;
            case R.id.go:
                getFragmentManager().beginTransaction()
                        .replace(
                                R.id.container, FragmentInitTerrain.newInstance(joueurList,isExtension.isChecked()))
                        .addToBackStack(null)
                        .commit();
                break;
        }
    }
}
