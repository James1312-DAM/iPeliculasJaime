package com.example.ipeliculasjaime;

import static com.example.ipeliculasjaime.R.*;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
//ESTE IMPORT SIEMPRE SE PASA MAL OJO
import androidx.appcompat.widget.Toolbar;
//
import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
//MIRAR IMPORT DE ARRIBA SIEMPRE DA FALLLO PARA PONERLO CORRECTO ESTA MARCADO
public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Toolbar toolbar = findViewById(id.toolbarDetalle);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Detalle");

        Pelicula pelicula = (Pelicula) getIntent().getSerializableExtra("pelicula");

        ImageView imgDetalle = findViewById(id.imgDetalle);
        TextView tvTituloDetalle = findViewById(R.id.tvTituloDetalle);
        TextView tvGeneroDetalle = findViewById(R.id.tvGeneroDetalle);
        RatingBar ratingDetalle = findViewById(R.id.ratingDetalle);
        TextView tvDirector = findViewById(R.id.tvDirector);
        TextView tvFecha = findViewById(R.id.tvFecha);
        TextView tvSinopsis = findViewById(R.id.tvSinopsis);

        if (pelicula != null) {
            imgDetalle.setImageResource(pelicula.getImageResId());
            tvTituloDetalle.setText(pelicula.getTitulo());
            tvGeneroDetalle.setText(pelicula.getGenero());
            ratingDetalle.setRating((float) pelicula.getValoracion());
            tvDirector.setText("Director: " + pelicula.getDirector());
            tvFecha.setText("Estreno: " + pelicula.getFechaEstreno());
            tvSinopsis.setText(pelicula.getSinopsis());
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}