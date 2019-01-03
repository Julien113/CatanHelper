package com.sweetapps.qt.catanhelper;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterSpot extends RecyclerView.Adapter<AdapterSpot.ViewHolder> {
    ArrayList<IntersectionTrois> listSpot;
    Context mContext;
    public AdapterSpot(Context context, ArrayList<IntersectionTrois> listSpot){
        this.listSpot = listSpot;
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
        IntersectionTrois spot = listSpot.get(i);

        String text = "";
        if (spot.caseUn!=null)
            text+="case 1:"+spot.caseUn.type+"   nb:"+spot.caseUn.chiffreproba;
        if (spot.caseDeux!=null)
            text+="\ncase 2:"+spot.caseDeux.type+"   nb:"+spot.caseDeux.chiffreproba;
        if (spot.caseTrois!=null)
            text+="\ncase 3:"+spot.caseTrois.type+"   nb:"+spot.caseTrois.chiffreproba;
        text+="\nPuissance:"+spot.getValue();
        holder.tnom.setText(text);

        holder.layout.setVisibility(View.GONE);


    }



    @Override
    public int getItemCount() {
        return listSpot.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tnom;
        TextView tbois;
        TextView tpaille;
        TextView tbrique;
        TextView tchevre;
        TextView tpierre;
        LinearLayout layout;

        View view;
        public ViewHolder(View view){
            super(view);
            this.view = view;
            tnom=view.findViewById(R.id.nom);
            layout = view.findViewById(R.id.linearlayout);
          /*  tbois=view.findViewById(R.id.bois);
            tchevre=view.findViewById(R.id.chevre);
            tpaille=view.findViewById(R.id.paille);
            tbrique=view.findViewById(R.id.brique);
            tpierre=view.findViewById(R.id.pierre);*/


        }


    }
}
