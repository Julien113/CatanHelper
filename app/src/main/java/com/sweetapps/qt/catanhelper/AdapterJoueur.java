package com.sweetapps.qt.catanhelper;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class AdapterJoueur extends RecyclerView.Adapter<AdapterJoueur.ViewHolder> {
    ArrayList<Joueur> listJoueur;
    Context mContext;
    public AdapterJoueur(Context context, ArrayList<Joueur> listJoueur){
        this.listJoueur = listJoueur;
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.joueur_adapter,viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int i) {
        Joueur joueur = listJoueur.get(i);

        holder.tnom.setText(joueur.nom);
        holder.tbois.setText(joueur.bois+" bois");
        holder.tpaille.setText(joueur.paille+" pailles");
        holder.tbrique.setText(joueur.brique+" brique");
        holder.tchevre.setText(joueur.chevre+" chevres");
        holder.tpierre.setText(joueur.pierre+" pierres");


    }



    @Override
    public int getItemCount() {
        return listJoueur.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tnom;
        TextView tbois;
        TextView tpaille;
        TextView tbrique;
        TextView tchevre;
        TextView tpierre;

        View view;
        public ViewHolder(View view){
            super(view);
            this.view = view;
            tnom=view.findViewById(R.id.nom);
            tbois=view.findViewById(R.id.bois);
            tchevre=view.findViewById(R.id.chevre);
            tpaille=view.findViewById(R.id.paille);
            tbrique=view.findViewById(R.id.brique);
            tpierre=view.findViewById(R.id.pierre);


        }


    }
}
