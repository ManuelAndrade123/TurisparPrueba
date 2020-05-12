package com.example.turisaplicacion;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link lugares_sitiosinsignias#newInstance} factory method to
 * create an instance of this fragment.
 */
public class lugares_sitiosinsignias extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public lugares_sitiosinsignias() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment lugares_sitiosinsignias.
     */
    // TODO: Rename and change types and number of parameters
    public static lugares_sitiosinsignias newInstance(String param1, String param2) {
        lugares_sitiosinsignias fragment = new lugares_sitiosinsignias();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lugares_sitiosinsignias, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        ImageView imagen1_glorietas=view.findViewById(R.id.img_glorieta1);
        imagen1_glorietas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.glorieta_gallo);
            }
        });
        ImageView imagen2_glorietas=view.findViewById(R.id.img_glorieta2);
        imagen2_glorietas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.glorieta_pilonera);
            }
        });
        ImageView imagen3_glorietas=view.findViewById(R.id.img_glorieta3);
        imagen3_glorietas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.glorieta_musicos);
            }
        });
        ImageView imagen4_glorietas=view.findViewById(R.id.img_glorieta4);
        imagen4_glorietas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.glorieta_acordeon);
            }
        });
        ImageView imagen6_glorietas=view.findViewById(R.id.img_glorieta5);
        imagen6_glorietas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.poporos);
            }
        });

        ImageView imagen5_glorietas=view.findViewById(R.id.img_glorieta6);
        imagen5_glorietas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.glorieta_mulata);
            }
        });
    }
}
