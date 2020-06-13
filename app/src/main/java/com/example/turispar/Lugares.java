package com.example.turispar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.turispar.Entidades.Lugar;
import com.example.turispar.adaptadores.AdaptadorLugares;

import java.util.ArrayList;

public class Lugares extends AppCompatActivity {
    ArrayList<Lugar> listalugar;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lugares);
        listalugar= new ArrayList<>();
        recyclerView=findViewById(R.id.lugaresRecyclerView);

        String titulo=  getIntent().getStringExtra("titulo");
        switch (titulo){

            case "MONUMENTOS":
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                llenarLugar();
                AdaptadorLugares adaptadorLugares = new AdaptadorLugares(listalugar);
                recyclerView.setAdapter(adaptadorLugares);
                break;


        }




    }

    private void llenarLugar() {
        listalugar.add(new Lugar("YO AMO VALLEDUPAR"," TERMINAL DE TRANSPORTE, SALIDA A FUNDACIÓN Y AV. DEL AMOR AMOR.",
                "Iniciativa de la Alcaldía Municipal que invita a crear sentido de pertenencia por la ciudad. Lugar para iniciar el recorrido por la hermosa capital del departamento del Cesar que abre sus puertas a propios y foráneos para que la conozcan y se lleven el mejor recuerdo de su vida."
                ,R.drawable.yoamovalledupar));
        listalugar.add(new Lugar("LOS POPOROS Y COLISEO CUBIERTO"," CRA. 19 C 1",
                "El Coliseo es uno de los pocos que cuenta con un techo movible. Construido a finales de los años 90s, se encuentra frente a la escultura de Los Poporos. Esta escultura rinde homenaje a las tres etnias indígenas que aún habitan en la Sierra Nevada, como lo son los Arhuacos, los Koguis y los Arzarios.\n" +
                        "\n" +
                        "Poporos: Espacio público\n" +
                        "Coliseo: Solo se puede entrar los días de eventos deportivos."
                ,R.drawable.coliseo));
        listalugar.add(new Lugar("CACIQUE UPAR MONUMENTO","FRENTE A LA GLORIETA DEL TERMINAL.",
                "Al sur este de la ciudad se levanta imponente El Cacique Upar, una hermosa escultura representativa de nuestros aborígenes indígenas quienes defendieron esta región de los invasores españoles. Curiosamente, a él se debe el nombre de nuestra ciudad, pues Valledupar es un acrónimo de: Valle del Cacique Upar. Él era el máximo jefe de los Chimilas, tribu de la región y quienes tuvieron que desplazarse a la sierra Nevada para evitar su exterminio total.\n" +
                        "\n" +
                        "Entrada gratuita a todo público las 24 horas."
                ,R.drawable.cacique));
        listalugar.add(new Lugar("MIRADOR DE SANTO ECCE HOMO"," KM 1 VÍA A SAN JUAN DEL CESAR (LA GUAJIRA)",
                "El Santo Ecce Homo es el patrono de Valledupar. Una réplica de 30 metros, que vigila la ciudad desde el Cerro de las Antenas, es lugar de peregrinaje para devotos y meta para deportistas. Fue diseñada por el maestro colombiano Héctor Lombana, la estructura mide 35 metros de altura.\n" +
                        "\n" +
                        "Espacio público"
                ,R.drawable.santo));
        listalugar.add(new Lugar("AVENIDA DEL FOLCLOR","LA CALLE 44 DE VALLEDUPAR",
                "La Calle 44 de Valledupar es todo un espectáculo para los turistas quienes ven la evolución del vallenato plasmado en diferentes esculturas. Comenzando por el Caracol con el que los aborígenes empezaron a hacer sonidos rítmicos y terminando con el Acordeón traído por los europeos. También se incluyen guitarras, la caja africana y la guacharaca indígena. Son en total 11 esculturas a lo largo de dicha avenida, para ayudar al visitante a entender y conocer los elementos de una canción vallenata."
                ,R.drawable.calle44));
        listalugar.add(new Lugar("MONUMENTO A LOS HÉROES CAÍDOS, ARTILLERÍA COLOMBIANA"," CALLE 16 CON CARRERA 31",
                "Es un bello reconocimiento a nuestros valientes soldados que han salvaguardado a nuestra nación por más de 100 años. Cabe resaltar que este hermoso cañón de artilleria ya no está en funcionamiento, hoy solo es un recuerdo de años pasados en donde la guerra interna era el pan de cada día. Por tal motivo hoy más que nunca se reconoce a los miembros del ejército nacional como los héroes de Colombia.\n" +
                        "\n" +
                        "Espacio público."
                ,R.drawable.batallon));
    }
}
