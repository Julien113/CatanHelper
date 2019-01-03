package com.sweetapps.qt.catanhelper;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class FragmentMain extends Fragment implements View.OnClickListener {


    Button bInit;
    Button bLoad;

    Jeu jeuload ;
    public static FragmentMain newInstance() {
        return new FragmentMain();
    }

    public FragmentMain() {
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
        bInit = view.findViewById(R.id.init);
        bInit.setOnClickListener(this);
        bLoad = view.findViewById(R.id.load);
        bLoad.setOnClickListener(this);

        jeuload = Jeu.getJeu(getActivity());
        if (jeuload!=null){
            bLoad.setVisibility(View.VISIBLE);
        }else{
            bLoad.setVisibility(View.GONE);
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.init:
                getFragmentManager().beginTransaction()
                        .replace(
                                R.id.container, FragmentInit.newInstance())
                        .addToBackStack(null)
                        .commit();
                break;
            case R.id.load:
                getFragmentManager().beginTransaction()
                        .replace(
                                R.id.container, FragmentJeu.newInstance(jeuload))
                        .addToBackStack(null)
                        .commit();
                break;
        }
    }
}
