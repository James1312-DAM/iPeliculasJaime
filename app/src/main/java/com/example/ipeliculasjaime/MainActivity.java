package com.example.ipeliculasjaime;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {
    //Importamos para poder mostrar las tarjetas de peliculas
    private RecyclerView recyclerView;
    private PeliculaAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        //TOOLBAR
        Toolbar toolbar =findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //CARGAR CLASE PELICULADATOS Y PELICULAADAPTER
        PeliculaDatos.cargarPeliculas();
        recyclerView = findViewById(R.id.recyclerPeliculas);
        //O se hace aqui o en el xml tamb se puede escribir la linea
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //
        adapter = new PeliculaAdapter(this, PeliculaDatos.listaPeliculas);
        recyclerView.setAdapter(adapter);

        //Animacion si queremos que se ejecute para todo al principio
        //recyclerView.setLayoutAnimation(AnimationUtils.loadLayoutAnimation(this, R.anim.layout_animation));
        //recyclerView.scheduleLayoutAnimation();
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu,menu);

        return true;
    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id==R.id.acercaDe){

            Intent intent = new Intent(MainActivity.this, aboutActivity.class);
            startActivity(intent);
        }
        if(id==R.id.iralCine){
            Intent intent = new Intent(MainActivity.this, mapsActivity.class);
            intent.putExtra("modo", "cine");
            startActivity(intent);


            //esto seria sin usar la api usando itent que te lanza fuera a la app de maps
            /*String lugar = "Yelmo Cines Premium Lagoh, Centro Comercial Palmas Altas, Sevilla";
            Uri uri = Uri.parse("geo:0,0?q=" + Uri.encode(lugar));
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.setPackage("com.google.android.apps.maps");
            try {
                startActivity(intent);
            } catch (ActivityNotFoundException e) {
                Toast.makeText(MainActivity.this, "Google Maps no está instalado", Toast.LENGTH_SHORT).show();
            }*/
        }
        if (id == R.id.comoLlegar) {
            Intent intent = new Intent(MainActivity.this, mapsActivity.class);
            intent.putExtra("modo", "ruta");
            startActivity(intent);


            /*SIN USAR API DE GOOGLE LANZADO A FUERA A LA APP MAPS
            String lugar = "Yelmo Cines Premium Lagoh, Centro Comercial Palmas Altas, Sevilla";
            Uri uri = Uri.parse("https://www.google.com/maps/dir/?api=1&destination=" + Uri.encode(lugar) + "&travelmode=driving");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.setPackage("com.google.android.apps.maps");
            try {
                startActivity(intent);
            } catch (ActivityNotFoundException e) {
                Toast.makeText(MainActivity.this, "Google Maps no está instalado", Toast.LENGTH_SHORT).show();
            }*/
        }

        return true;
    }
}