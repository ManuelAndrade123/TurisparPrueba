package com.example.turispar.adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.turispar.Entidades.Lugar;
import com.example.turispar.R;

import java.util.ArrayList;



public class AdaptadorLugares extends RecyclerView.Adapter<AdaptadorLugares.ViewHolderLugares> implements View.OnClickListener  {

    ArrayList<Lugar> listalugar;
    private View.OnClickListener listener;

    public AdaptadorLugares(ArrayList<Lugar> listalugar) {
        this.listalugar = listalugar;
    }

    @NonNull
    @Override
    public AdaptadorLugares.ViewHolderLugares onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lugarespersonalizados,null,false);

        view.setOnClickListener(this);
        return new AdaptadorLugares.ViewHolderLugares(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorLugares.ViewHolderLugares holder, int position) {
        holder.titulo.setText(listalugar.get(position).getTitulo());
        holder.lugar.setText(listalugar.get(position).getLugar());
        holder.descripcion.setText(listalugar.get(position).getDescripcion());
        holder.imagen.setImageResource(listalugar.get(position).getFoto());
        boolean isExpanded =listalugar.get(position).isExpanded();
        holder.expandible.setVisibility(isExpanded ? View.VISIBLE : View.GONE);
    }

    @Override
    public int getItemCount() {
        return listalugar.size();
    }
public void setOnclickListener( View.OnClickListener listener){

this.listener=listener;

}
    @Override
    public void onClick(View view) {
        if(listener!= null){


            listener.onClick(view);
        }

    }

    public class ViewHolderLugares extends RecyclerView.ViewHolder {
        TextView titulo,lugar,descripcion;
        ImageView imagen;
        ConstraintLayout expandible;

        public ViewHolderLugares(@NonNull View itemView) {
            super(itemView);

            titulo=itemView.findViewById(R.id.tituloLugares);
            lugar=itemView.findViewById(R.id.ubicacionlugares);
            descripcion=itemView.findViewById(R.id.descripcionlugares);
            imagen= itemView.findViewById(R.id.imageViewlugares);
            expandible = itemView.findViewById(R.id.expandible);



            titulo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Lugar lugar = listalugar.get(getAdapterPosition());

                    lugar.setExpanded(!lugar.isExpanded());
             notifyItemChanged(getAdapterPosition());
                }
            });



        }
    }
}
