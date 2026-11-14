package com.example.ipeliculasjaime;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.Locale;

// Implementamos OnMapReadyCallback para que Google nos avise cuando el mapa esté listo
public class mapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    // Coordenadas del Yelmo (latitud y longitud)
    private static final double LAT_YELMO = 37.34136046858522;
    private static final double LNG_YELMO = -5.986354912547859;

    // Número para identificar nuestra petición de permiso (puede ser cualquiera)
    private static final int CODIGO_PERMISO = 1;

    private GoogleMap mMap;   // El mapa de Google
    private String modo;      // "cine" o "ruta", lo que nos manda el menú
    private FusedLocationProviderClient fusedClient; //Para mostra la ruta hasta nuestra ubi

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_maps);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Toolbar con flecha de volver
        Toolbar toolbar = findViewById(R.id.toolbarMapa);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Leemos el modo que nos mandó MainActivity y ponemos el título según el modo
        modo = getIntent().getStringExtra("modo");
        if (modo != null && modo.equals("ruta")) {
            getSupportActionBar().setTitle("Cómo llegar");
        } else {
            getSupportActionBar().setTitle("Ir al cine");
        }

        // Creamos el cliente que nos da nuestra ubicación
        fusedClient = LocationServices.getFusedLocationProviderClient(this);

        // Pedimos el mapa; cuando esté listo Android llamará a onMapReady
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    // Se ejecuta cuando el mapa ya está cargado y se puede usar
    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;

        // Punto del Yelmo, marcador y cámara centrada con zoom 15 (nivel de calle)
        LatLng yelmo = new LatLng(LAT_YELMO, LNG_YELMO);
        mMap.addMarker(new MarkerOptions().position(yelmo).title("Yelmo Cines Premium Lagoh"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(yelmo, 15));

        // Activamos el punto azul de nuestra ubicación
        activarUbicacion();
    }   // <-- AQUÍ se cierra onMapReady, los demás métodos van FUERA

    // Comprueba el permiso de ubicación; si no lo tenemos, lo pide
    @SuppressLint("MissingPermission")
    private void activarUbicacion() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            // Ya tenemos permiso: dibuja el punto azul y el botón de "mi ubicación"
            mMap.setMyLocationEnabled(true);
            // Si venimos de "Cómo llegar", además dibujamos la línea hasta el Yelmo
            if (modo != null && modo.equals("ruta")) {
                dibujarRuta();
            }
        } else {
            // No lo tenemos: muestra el cuadro del sistema para pedirlo
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, CODIGO_PERMISO);
        }
    }

    // Obtiene tu posición, dibuja una línea hasta el Yelmo y muestra la distancia
    @SuppressLint("MissingPermission")
    private void dibujarRuta() {
        fusedClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                // Si el móvil aún no tiene ubicación, avisamos
                if (location == null) {
                    Toast.makeText(mapsActivity.this, "No se pudo obtener tu ubicación. Activa el GPS", Toast.LENGTH_LONG).show();
                    return;
                }

                LatLng origen = new LatLng(location.getLatitude(), location.getLongitude());
                LatLng destino = new LatLng(LAT_YELMO, LNG_YELMO);

                // Línea entre tu posición y el Yelmo
                mMap.addPolyline(new PolylineOptions()
                        .add(origen, destino)
                        .width(10)
                        .color(Color.parseColor("#263B5A")));

                // Encuadra la cámara para que se vean los dos puntos
                LatLngBounds limites = new LatLngBounds.Builder()
                        .include(origen)
                        .include(destino)
                        .build();
                mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(limites, 150));

                // Distancia en línea recta, en kilómetros
                float[] resultado = new float[1];
                Location.distanceBetween(origen.latitude, origen.longitude,
                        destino.latitude, destino.longitude, resultado);
                String km = String.format(Locale.getDefault(), "%.1f", resultado[0] / 1000);
                getSupportActionBar().setSubtitle("Distancia en línea recta: " + km + " km");
            }
        });
    }

    // Android llama a este método cuando el usuario responde al cuadro de permiso
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CODIGO_PERMISO && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            // Aceptó: volvemos a llamar y esta vez sí activa el punto azul
            activarUbicacion();
        }
    }

    // Flecha de volver de la Toolbar
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}