package com.example.turispar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.turispar.BaseDeDatos.Conexion;
import com.example.turispar.BaseDeDatos.Utilidades;
import com.example.turispar.Entidades.Lugar;
import com.example.turispar.adaptadores.AdaptadorLugares;

import java.util.ArrayList;

public class Lugares extends AppCompatActivity {
    ArrayList<Lugar> listalugar;
    ArrayList<Lugar> balneario;
    ArrayList<Lugar> glorietas;
    ArrayList<Lugar> sites;
    ArrayList<Lugar> pueblos;
    ArrayList<Lugar> parques;
    String temp_titulo;
    String temp_descripcion;
    String temp_ubicacion;
    int temp_foto;
    RecyclerView recyclerView;
    TextView titulos;
    Conexion conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lugares);
        conn = new Conexion(this, "datos", null, 1);


        listalugar= new ArrayList<>();
        balneario= new ArrayList<>();
        glorietas= new ArrayList<>();
        pueblos= new ArrayList<>();
        parques= new ArrayList<>();
        sites= new ArrayList<>();
        recyclerView=findViewById(R.id.lugaresRecyclerView);
        titulos=findViewById(R.id.txtLugar);

        String titulo=  getIntent().getStringExtra("titulo");
        titulos.setText(titulo);

        switch (titulo){
            case "MONUMENTOS":
                llenarMonumentos();
              favorito(listalugar);
                break;

            case "BALNEARIOS":
                llenarbalneario();
              favorito(balneario);

                break;
            case "GLORIETAS":
                llenarGlorieta();
              favorito(glorietas);
                break;
            case "SITIOS INSIGNES":
                llenarSites();
              favorito(sites);
                break;

            case "PUEBLOS":
                llenarPueblo();
             favorito(pueblos);
                break;
            case "PARQUES":
                llenarParque();
             favorito(parques);
                break;

        }




    }

    public void favorito(final ArrayList<Lugar> lista){

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        AdaptadorLugares adaptador = new AdaptadorLugares(lista);
        recyclerView.setAdapter(adaptador);
        adaptador.setOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {


                AlertDialog.Builder  builder = new AlertDialog.Builder(Lugares.this);
                String titulodialog=  getIntent().getStringExtra("titulo");
                builder.setTitle(titulodialog);
                builder.setMessage("¿Quieres agregar este lugar a favoritos?").
                        setPositiveButton("SI", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {


                                String titulo = lista.get(recyclerView.getChildAdapterPosition(view)).getTitulo();
                                String dato = getIntent().getStringExtra("usuario");


                                if (dato.equals("invitado")) {


                                    Toast.makeText(getApplicationContext(), "debes registrarte", Toast.LENGTH_SHORT).show();

                                } else {

                                    Conexion admin = new Conexion(getApplicationContext(), "datos", null, 1);
                                    final SQLiteDatabase acceso = admin.getWritableDatabase();


                                    Cursor fila = acceso.rawQuery
                                            ("select * from lugar where usuario= '" + dato + "' and titulo= '" + titulo + "'", null);

                                    if (fila.moveToFirst()) {

                                        Toast.makeText(getApplicationContext(), "Este sitio ya esta en favortios", Toast.LENGTH_SHORT).show();
                                    } else {


                                        String descripcion = lista.get(recyclerView.getChildAdapterPosition(view)).getDescripcion();
                                        String ubicacion = lista.get(recyclerView.getChildAdapterPosition(view)).getLugar();
                                        int imagen = lista.get(recyclerView.getChildAdapterPosition(view)).getFoto();

                                        temp_titulo = titulo;
                                        temp_descripcion = descripcion;
                                        temp_ubicacion = ubicacion;
                                        temp_foto = imagen;
                                        registrarFavoritos();
                                        Toast.makeText(getApplicationContext(), "Agregado correctamente", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        }).setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).show();


            }
        });

    }

    public void registrarFavoritos(){

        String dato=  getIntent().getStringExtra("usuario");
        SQLiteDatabase favorito = conn.getReadableDatabase();
        ContentValues registrar = new ContentValues();
        registrar.put(Utilidades.CAMPO_TITULO,temp_titulo);
        registrar.put(Utilidades.CAMPO_DESCRIPCION_LUGAR,temp_descripcion);
        registrar.put(Utilidades.CAMPO_UBICACION,temp_ubicacion);
        registrar.put(Utilidades.CAMPO_USUARIO,dato);
        registrar.put(Utilidades.CAMPO_IMAGEN_LUGAR,temp_foto);

        favorito.insert(Utilidades.TABLA_LUGAR,null,registrar);
        favorito.close();




    }

    private void llenarParque() {
        parques.add(new Lugar("PARQUE DE LA LEYENDA VALLENATA CONSUELO ARAUJO NOGUERA"," SALIDA A PATILLAL",
                "Es un conjunto arquitectónico con una extensión de 23 hectáreas, ubicado al norte de la ciudad de Valledupar, en la margen derecha del río Guatapurí. Desde 2004 se realiza aquí el Festival Vallenato. Es un sitio ecológico, cultural, turístico y recreativo rodeado de arbustos donde se encuentra la tarima ‘Compae Chipuco’ del Coliseo Cacique Upar, con una capacidad para 40.000 espectadores. Es quizás el mejor sitio para conciertos del país.\n" +
                        "\n" +
                        "Entrada gratuita a todo público de 8:00 hasta las 18:00 horas"
                ,R.drawable.pa1));
        parques.add(new Lugar("PARQUE NOVALITO"," CARRERA 6 CON CALLE 12",
                "Lugar de esparcimiento al norte de la ciudad, cuenta con zona WIFI y ciclo ruta. Es sede de muchos campeonatos empresariales de Micro-Fútbol. Es un buen lugar para descansar y adentrarse en el Centro Histórico de la ciudad. Se encuentra en pleno corazón de uno de los barrios más tradicionales del imaginario cultural vallenato, del cual toma su nombre.\n" +
                        "\n" +
                        "Espacio público."
                ,R.drawable.pa2));
        parques.add(new Lugar("PARQUE EL VIAJERO"," CARRERA. 9 ENTRE CALLES 11 Y 12",
                "El parque ‘El Viajero’, es un lugar con juegos para los niños. Además, en él se encuentra el monumento en homenaje al Viajero. El propósito de esta escultura fue plasmar la figura bonachona del viajero que antaño, en los llamados buses municipales o chivas, venía de las poblaciones vecinas -La Mina, Guacoche, Badillo, Patillal, Atánquez- a mercar y hacer diligencias en Valledupar. Logrado su cometido regresaba, con la maleta llena, a sentarse y a esperar el transporte que lo devolvería a su lugar de origen.\n" +
                        "\n" +
                        "Espacio público."
                ,R.drawable.pa3));
        parques.add(new Lugar("PARQUE DE LAS MADRES"," CARRERA 9 ENTRE CALLES 15 Y 15A",
                "Frente al Cementerio Central, lugar donde descansan los restos mortales de juglares famosos como Rafael Escalona, se encuentra el Parque de las Madres. Es un punto predilecto para encontrar flores tropicales, nacionales e importadas para todo tipo de ocasión. También, sirve de exposición y ventas de artesanías y mochilas de la cultura Wayúu. Es un buen lugar para detenerse a degustar un delicioso café.\n" +
                        "\n" +
                        "Espacio público."
                ,R.drawable.pa4));
        parques.add(new Lugar("PARQUE DE LA PROVINCIA","CARRERA 4",
                "Lugar en donde se le rinde homenaje a Carlos Vives como embajador de nuestra cultura y folclor,quien también es el padrino de este bello lugar. Estará ubicado en donde es hoy el parque del Helado. Estará conectado a la glorieta de los Juglares y tendrá fuentes de agua. El avión DC6, parqueado allí para disfrute de los niños, se convertirá en un museo de la vida y obra de este gran artista.\n" +
                        "\n" +
                        "Espacio público."
                ,R.drawable.pa5));
        parques.add(new Lugar("PARQUE DE LOS ALGARROBILLOS","CARRERA 5B # 29-2 A 29-74",
                "Pulmón de la ciudad y una de las zonas verdes más grandes del departamento. La escuela musical ahí ubicada es usada para la formación artística y musical de la población infantil y juvenil de Valledupar. Funciona en un área de 1.542 metros cuadrados. Cuenta con siete aulas, un centro de producción, bodega de instrumentos y cafetería.\n" +
                        "\n" +
                        "Espacio público."
                ,R.drawable.pa6));
        parques.add(new Lugar("PARQUE DE PANAMÁ"," CALLE 41 CON CARRERA 6 BIS",
                "Es uno de los parques más grandes del departamento ya que dentro tiene la cancha de fútbol sintética más grande de la región. Ésta cumple con las especificaciones FIFA. También tiene zona de juegos infantiles, canchas multifuncionales, parque biosaludable, ciclo-ruta y espacios verdes para disfrutar en familia.\n" +
                        "\n" +
                        "Espacio público."
                ,R.drawable.pa7));
        parques.add(new Lugar("PARQUE CRISTO REY"," CARRERA. 16 #26-87",
                "Como atractivo, esta obra cuenta con una fuente seca con un piso en arte con trencadi. Es un homenaje a la paz de Valledupar y Colombia. Tiene 72 chorros de agua e igual número de luces interactivas. Es una fuente que puede estar sincronizada con música, cuenta con parque infantil, estación biosaludable, quiosco cafetería con bodega y baños públicos. Es un parque para integrar la familia.\n" +
                        "\n" +
                        "Espacio público."
                ,R.drawable.pa8));
    }

    private void llenarPueblo() {
        pueblos.add(new Lugar("ATÁNQUEZ RESGUARDO INDÍGENA","A 48 KILÓMETROS DE VALLEDUPAR",
                "Enclavado en la sierra Nevada de Santa Marta se encuentra este resguardo indígena kankuamo. Es un lugar en donde parece que el tiempo no hubiese pasado. Único y con casas antiguas muy bien conservadas, este territorio debe su nombre a los múltiples Ataques (españoles) sobre el pueblo indígena para conquistarlos. Al final no pudieron hacerlo, pues ellos se adentraron en la montaña, desconocida para los foráneos, pero amigable con los locales.\n" +
                        "\n" +
                        "Espacio público."
                ,R.drawable.p1));
        pueblos.add(new Lugar("PATILLAL “TIERRA DE COMPOSITORES”"," A 31 KILÓMETROS DE VALLEDUPAR",
                "“¡Ay! Patillal, Vuelvo aquí un ratico nada más” dice la canción Recuerdos de mi Tierra del compositor patillalero José Alfonso “Chiche” Maestre, un clásico de la música vallenata y en la que se expresa todo el sentir por esta hermosa tierra de compositores. Ella tiene su propio festival que se celebra en el mes de diciembre. Ubicado en las estribaciones de la sierra Nevada de Santa Marta, tiene un clima fabuloso.\n" +
                        "\n" +
                        "Espacio público."
                ,R.drawable.p2));
        pueblos.add(new Lugar("LA JUNTA"," A 68 KILÓMETROS DE VALLEDUPAR",
                "Pueblo de nacimiento del artista Diomedes Díaz. Allí está la famosa ventana marroncita, la misma que el cantautor inmortalizó en una de sus canciones. También se encuentra una casa museo sobre la vida y obra de dicho compositor. Su familia mantiene viva la memoria del artista. Allí puede verse la casa original de la infancia del artista. Hay visitas guiadas.\n" +
                        "\n" +
                        "Entrada gratuita a todo público las 24 horas."
                ,R.drawable.p3));
        pueblos.add(new Lugar("SAN JUÁN DEL CESAR"," A 50 KILÓMETROS DE VALLEDUPAR\n",
                "Muy cerca de Valledupar está este municipio de la Guajira, famoso por sus cantautores y acordeoneros de los cuales Nicolás Elías “Colacho” Mendoza es el más Reconocido, Esta tierra es un paso obligado para los amantes del vallenato y de la buena música. En su plaza colonial se puede disfrutar de su centro histórico y sus casas coloniales.\n" +
                        "\n" +
                        "Espacio público."
                ,R.drawable.p4));
        pueblos.add(new Lugar("GUACOCHE “TIERRA DE ALFAREROS”"," A 9 KILÓMETROS DE VALLEDUPAR",
                "A solo 9 kilómetros de Valledupar se encuentra este hermoso corregimiento, pegado al rio Cesar y famoso por sus artesanías y sus arepas asadas, Guacoche es un lugar mágico con olor a naturaleza. El silencio y la paz en sus calles es de las mejores experiencias que se pueda tener. Inicialmente fue un Palenque, en donde se refugiaban los esclavos en busca de su libertad.\n" +
                        "\n" +
                        "Espacio público."
                ,R.drawable.p5));
        pueblos.add(new Lugar("PUEBLO BELLO"," A 49 KILÓMETROS DE VALLEDUPAR",
                "Su nombre es un claro ejemplo de lo que se encuentra en su interior, lugares únicos y coloridos. Este hermoso pueblo está a más de 1200 msnm en las estribaciones de la Sierra Nevada de Santa Marta en cuya zona rural queda la capital del pueblo Arhuaco: NABUSIMAKE, un lugar sagrado y milenario al que no todos podemos llegar."
                ,R.drawable.p6));
        pueblos.add(new Lugar("LA PAZ"," A 13 KILÓMETROS DE VALLEDUPAR",
                "\n" +
                        "La capital de la almojábana se encuentra a solo 13 kilómetros de Valledupar y es un paso obligado para los viajeros provenientes del interior del país. Sus calles pintorescas con mariposas y cometas de colores invitan a soñar. Es un lugar perfecto para almorzar en familia, disfrutar de las almojábanas o de los otros pasabocas populares allí; o simplemente tomar un baño en la piscina de agua natural más grande de Sudamérica.\n" +
                        "Espacio público."
                ,R.drawable.p7));
        pueblos.add(new Lugar("MANAURE BALCÓN DEL CESAR"," A 36 KILÓMETROS DE VALLEDUPAR",
                "A casi 1000 msnm se encuentra el balcón turístico del cesar, con un clima agradable y sus calles empinadas Manaure (Cesar) es un territorio enclavado en la serranía del Perijá. Desde sus cerros se puede ver Valledupar. Su cerro de las tres cruces es uno de los lugares obligados para los visitantes, como también lo son su plaza y su helado río. Es una despensa de aguacates, frutas y flores. El balcón turístico del Cesar abre sus puertas como una magnífica oportunidad para encontrarse con la naturaleza. Aquí se practican parapente y otros deportes extremos.\n" +
                        "Espacio público."
                ,R.drawable.p8));

    }

    private void llenarbalneario() {
        balneario.add(new Lugar("BALNEARIO HURTADO","CARRERA 4.",
                "Es uno de los balnearios más importantes de la región. Se forma en uno de los pozos del rio GuatapurÍ. Nace en la laguna de Curigua, en la Sierra Nevada de Santa Marta y se encuentra acorazonado por predominantes rocas. El Río Guatapurí es el balneario por excelencia de los Vallenatos. Ofrece muchas comidas típicas de la región: butifarra, bollo y sopas.\n" +
                        "\n" +
                        "Espacio público."
                ,R.drawable.b1));
        balneario.add(new Lugar("PISCINAS DE IRAKUA","  A 8 KILÓMETROS, VÍA DESTAPADA Y SÓLO SE PUEDE IR EN BICICLETA O MOTOCICLETAS DE MONTAÑA.",
                "En las estribaciones de la Sierra Nevada de Santa Marta y muy cerca de Valledupar se encuentran estas hermosas piscinas naturales creadas por un arroyo natural, en donde se puede disfrutar de un buen paseo de olla. Preferiblemente ir de mañana para volver por la tarde.\n" +
                        "\n" +
                        "Espacio público."
                ,R.drawable.b2));
        balneario.add(new Lugar("BALNEARIOS EL PONTÓN Y EL MOJAO"," A 49 Y 45 KILÓMETROS DE VALLEDUPAR RESPECTIVAMENTE",
                "Al norte de Valledupar y en una vía en perfectas condiciones hay dos balnearios hermosos y con aguas refrescantes. Nuestra ciudad, por estar cerca de la Sierra Nevada de Santa Marta es privilegiada con esta clase de lugares. Es un plan perfecto para disfrutar un momento con la naturaleza, la familia o los amigos.\n" +
                        "\n" +
                        "Espacio público."
                ,R.drawable.b3));
        balneario.add(new Lugar("BALNEARIO DE LA MINA","A 40 KILÓMETROS DE VALLEDUPAR",
                "Sus imponentes y a la vez hermosas piedras que se levantan sobre el rio Badillo no son más que una invitación a refrescarte en sus aguas. Este hermoso corregimiento es un paso obligado para todos los aventureros que quieran experimentar el verde de la sierra combinado con el azul del río Badillo. Los restaurantes locales ofrecen deliciosos platos típicos. Es un destino obligado para propios y foráneos.\n" +
                        "\n" +
                        "Espacio público"
                ,R.drawable.b4));
        balneario.add(new Lugar("MANANTIAL DE CAÑAVERALES"," A 78 KILÓMETROS DE VALLEDUPAR",
                "Escondido en el corregimiento de cañaverales, en San Juan del Cesar está este hermoso manantial cuyas aguas son mágicamente cristalinas. Este paraíso se hizo famoso por las redes sociales, ya que muchas personas ni siquiera sabían que existía tan bello sitio cerca de Valledupar. Es una propiedad privada.\n" +
                        "\n" +
                        "Se necesita un permiso especial."
                ,R.drawable.b5));

    }
    private void llenarMonumentos() {
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
    private void llenarSites() {
        sites.add(new Lugar("PLAZA ALFONSO LÓPEZ","CARRERA 6 ENTRE CALLES 15 Y 16",
                "\n" +
                        "Es la Plaza fundacional de la ciudad. Sede de la famosa Tarima Francisco el hombre y cuna del Festival de la leyenda vallenata, que se celebró allí por varias décadas hasta que fue insuficiente para alojar a los turistas que nos visitaban. Al recorrer las casas que la rodean podrás conocer la historia de la ciudad, leyendo las placas en cada una de ellas. Sus lozas, desde lo alto, representan los caminos de la vida de la cosmogonía arhuaca. Actualmente está siendo remodelada por la alcaldía municipal.\n" +
                        "\n" +
                        "Espacio público."
                ,R.drawable.s1));
        sites.add(new Lugar("CASA DE LA CULTURA"," CALLE 5 NO. 15-69",
                "En sus inicios en esa esquina funcionaba el convento de San Cayetano. Posteriormente en el año 1947 dio paso a la cárcel del mamón del circuito judicial, hasta que en el año 1970 fue trasladada al barrio Dangond. En ese año, albores del departamento del Cesar, se construyó el edificio donde funciona la Casa de la Cultura. Uno de los más nobles valores fue que allí prestaba sus servicios la primera biblioteca pública, que suspendió sus servicios en el año 2012 a raíz de unos arreglos arquitectónicos. Allí mismo también funcionó por muchos años el museo de arqueología, que llegó a contar en su colección con 350 piezas representativas de las culturas precolombinas del bajo magdalena y el alto Cesar. Sus prioridades eran conservar y exponer el patrimonio arqueológico. Estos vestigios permiten conocer los orígenes y las trayectorias socioculturales pasadas en Valledupar. En el año 2016 se cedió esta colección y hoy la conservan en la facultad de Bellas Artes de la Universidad Popular del Cesar. Finalmente, en el año 2017 se hizo la última restauración locativa, y tras varios meses retomó sus servicios tradicionales donde se hacen exposiciones y presentaciones culturales, con el teatro al servicio del público.\n" +
                        "\n" +
                        "Entrada gratuita a todo público desde las 8:00 hasta las 18:00 horas."
                ,R.drawable.s2));
        sites.add(new Lugar("CASA DE MAMA VILA","CARRERA 17 CON CALLE 9C",
                "Lejos de ser una señora modesta, Elvira Maestre o “mama Vila” como todos le dicen por cariño, es una señora emprendedora y resiliente, con el talante que caracteriza a las mujeres costeñas. Ha sabido reponerse de las adversidades y hoy más que nunca disfruta de la vida, @mama.vila es su perfil en Instagram que cuenta con más de 173.000 seguidores en donde expresa las historias, anécdotas, conciertos y demás momentos de la dinastía Díaz.\n" +
                        "\n" +
                        "Entrada gratuita a todo público de 8:00 hasta las 18:00 horas."
                ,R.drawable.s3));
        sites.add(new Lugar("MUSEO DEL ACORDEÓN"," CARRERA 17 NO. 9A-18, SAN JOAQUÍN",
                "La casa Museo del Acordeón es un espacio para compartir los orígenes, importancia y evolución de la música de nuestra región. Ofrece un recorrido guiado en el que se cuenta la historia del Museo y se muestran las piezas que han sido donadas.\n" +
                        "\n" +
                        "Visitas Guiadas por el Museo. Iniciativa privada.\n" +
                        "Donación mínima por persona: 20.000 pesos.\n" +
                        "Entrada gratuita para personas con discapacidad."
                ,R.drawable.s4));
        sites.add(new Lugar("CENTRO ARTESANAL CALLE GRANDE"," CALLE 16 # 7 – 140",
                "En todo el corazón de Valledupar está el Centro Artesanal Calle Grande, lugar predilecto para llevarse un bello recuerdo de nuestra ciudad. Allí se encuentran toda clase de artesanías hechas por las diversas culturas que rodean la ciudad. Es el lugar ideal para comprar gratos recuerdos de Valledupar. Los vendedores y artistas locales se esmeran por brindar una apropiada atención a sus visitantes.\n" +
                        "\n" +
                        "Entrada gratuita a todo público de 8:00 hasta las 18:00 horas."
                ,R.drawable.s5));
        sites.add(new Lugar("GOBERNACIÓN DEL CESAR Y PLAZA DE LAS BANDERAS"," CARRERA. 14 #14-100",
                "La plaza cuenta con un parque de banderas que tiene 27 astas de 27 metros de altura en las que ondean las banderas de cada uno de los municipios del departamento del Cesar y la de Colombia. Esta recién reformada plaza es un espacio verde de esparcimiento que cuenta con varios monumentos que representan la idiosincrasia de la región.\n" +
                        "\n" +
                        "Espacio público."
                ,R.drawable.s6));
        sites.add(new Lugar("COLEGIO LOPERENA"," CALLE 16 CON CALLE 11A",
                "Fue creado como homenaje a la memoria de la heroína vallenata Doña María Concepción Loperena de Fernández de Castro, quien prestó invaluables servicios a la independencia de la República. Reconocida como el “Alma mater del Cesar” por haber formado en sus aulas a la mayoría de los dirigentes del Departamento en el siglo XX. En 1993, las instalaciones del Colegio Nacional Loperena fueron declaradas “Monumento Nacional de Colombia”.\n" +
                        "\n" +
                        "Se necesita un permiso especial para entrar desde las 8:00 hasta las 18:00 horas."
                ,R.drawable.s7));
        sites.add(new Lugar("MADEROS TEATRO","CARRERA 7 N 14-25 BARRIO CAÑAHUATE",
                "Espacio cultural independiente para el arte teatral, dentro del centro histórico de la ciudad. Maderos es un lugar único y lleno de sorpresas para el deleite de los asistentes. Tiene funciones semanales, En su interior, además, hay restos de nuestro pasado colonial que se envuelve en sus instalaciones. Está cerca de donde Funciono por muchos años el teatro Cesar, sitio de grata recordación para los vallenatos, que cerró sus puertas a principios de los 90s.\n" +
                        "\n" +
                        "Entrada 10.000 pesos por persona."
                ,R.drawable.s8));


    }
    private void llenarGlorieta() {
        glorietas.add(new Lugar("GLORIETA MI PEDAZO DE ACORDEÓN"," AVENIDA HURTADO. INTERSECCIÓN CARRERA NOVENA Y CARRERA 19",
                "Lugar para observar “Mi Pedazo de Acordeón” obra realizada por el Escultor Gabriel Beltrán, en honor al juglar y primer Rey Vallenato, Alejandro Durán, autor de la famosa canción “Mi pedazo de Acordeón”. Hoy, es un punto icónico de los acordeoneros de la región que muestra nuestra cultura al mundo.\n" +
                        "\n" +
                        "Espacio público."
                ,R.drawable.g1));
        glorietas.add(new Lugar("GLORIETA DE LA PILONERA MAYOR","INTERSECCIÓN DE LA AVENIDA FRANCISCO EL HOMBRE Y CARRERA 19\n",
                "Sede del monumento La Pilonera Mayor del escultor Amilkar Ariza en homenaje a la fundadora del Festival Vallenato, Consuelo Araujo Noguera, “La Cacica.” Si se colocan en las faldas del imponente monumento dicen que se puede escuchar su voz cantando su hermosa frase y estribillo del baile que abre el Festival Vallenato: …” a los que vienen de afuera o a los dueños de la casa”.\n" +
                        "\n" +
                        "Espacio público."
                ,R.drawable.g2));
        glorietas.add(new Lugar("GLORIETA DE LOS JUGLARES","CARRERA 4, CALLE PRIMERA.",
                "Es un nuevo sitio turístico de Valledupar, un lugar contiguo al parque El Helado, que cuenta con obras de jardinería, cuatro monedas con los Juglares: Lorenzo Morales, Emiliano Zuleta, Leandro Díaz y Rafael Escalona; un acordeón con los grandes exponentes de la música vallenata: Jorge Oñate, Diomedes Díaz y Tomás Alfonso ‘Poncho’ Zuleta y dos estatuas en tamaño real de Diomedes y Martin Elías Díaz, es el monumento más visitado de Colombia.\n" +
                        "\n" +
                        "Espacio público."
                ,R.drawable.g3));
        glorietas.add(new Lugar("GLORIETA DE HERNADO DE SANTANA"," CALLE 21 # 7-92\n",
                "Es un homenaje al fundador de la ciudad en 1550. El conquistador español se representa en una obra construida en material de chatarra. Es uno de nuestros monumentos más antiguos."
                ,R.drawable.g4));
        glorietas.add(new Lugar("GLORIETA DE LOS GALLOS","CARRERA 19 CON TRANSVERSAL 18",
                "Es un homenaje a las insignes peleas de gallos que se hicieron muy famosas en la provincia vallenata. Es uno de los monumentos más antiguos de la ciudad y de los mejor conservados en su zona verde.\n" +
                        "\n" +
                        "Espacio público."
                ,R.drawable.g5));
        glorietas.add(new Lugar("GLORIETA DE LA MARIA MULATA"," CARRERA 19 CON CALLE 16",
                "“La maría mulata es la que nos acompaña desde que nacemos, es la que está en los patios, donde está la muchacha barriendo, en el corredor, en la entrada o asomada en las ventanas mirando lo que uno está haciendo”, manifestó el artista Enrique Grau, creador de esta obra. Así las hizo protagonistas de pinturas al óleo, dibujos al carboncillo y al pastel, de serigrafías y esculturas, como la nuestra."
                ,R.drawable.g6));
        glorietas.add(new Lugar("GLORIETA DEL OBELISCO"," CARRERA 19 C 1 CALLE 44",
                "Es la escultura más alta del municipio, ya que tiene 30 metros de alto. Es un homenaje a la vida y se encuentra ubicada en la entrada sur oeste de Valledupar. Desde 1994, año en que fue inaugurada, se convirtió en un hermoso referente para propios y visitantes.\n" +
                        "\n" +
                        "Espacio público."
                ,R.drawable.g7));
        glorietas.add(new Lugar("GLORIETA DE LOS MUSICOS"," AVENIDA FUNDACIÓN CON AVENIDA SIMÓN BOLÍVAR.",
                "\n" +
                        "Los tres integrantes de un conjunto típico de música vallenata: acordeonero, cajero y guacharaquero están en la obra que el escultor vallenato Jorge Maestre realizó en honor de la cultura vallenata. Es una de las glorietas más famosas de la ciudad.\n" +
                        "\n" +
                        "Espacio público."
                ,R.drawable.g8));

    }
}
