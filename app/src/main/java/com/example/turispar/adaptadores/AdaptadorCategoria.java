package com.example.turispar.adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.turispar.Entidades.Categoria;
import com.example.turispar.R;

import java.util.ArrayList;

public class AdaptadorCategoria extends RecyclerView.Adapter<AdaptadorCategoria.ViewHolderCategoria>  implements  View.OnClickListener{
        ArrayList<Categoria> listacategoria;
    private View.OnClickListener listener;
    public AdaptadorCategoria(ArrayList<Categoria> listacategoria) {
        this.listacategoria = listacategoria;
    }

    @NonNull
    @Override
    public ViewHolderCategoria onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.categoriaspersonalizadas,null,false);

        view.setOnClickListener(this);
        return new ViewHolderCategoria(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorCategoria.ViewHolderCategoria holder, int position) {
holder.titulo.setText(listacategoria.get(position).getTitulo());
holder.imagen.setImageResource(listacategoria.get(position).getFoto());
    }

    @Override
    public int getItemCount() {
        return listacategoria.size();
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

    public class ViewHolderCategoria extends RecyclerView.ViewHolder {

        TextView titulo;
        ImageView imagen;
        public ViewHolderCategoria(@NonNull View itemView) {
            super(itemView);

            titulo=itemView.findViewById(R.id.txtcategoriasA);
            imagen= itemView.findViewById(R.id.imageViewCategorias);
        }
    }
}
