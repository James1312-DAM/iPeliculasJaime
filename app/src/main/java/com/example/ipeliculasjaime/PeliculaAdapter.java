package com.example.ipeliculasjaime;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PeliculaAdapter extends RecyclerView.Adapter<PeliculaAdapter.ViewHolder>{
    private Context context;
    private List<Pelicula> listaPeliculas;

    public PeliculaAdapter(Context context, List<Pelicula> listaPeliculas) {
        this.context = context;  // Pantalla desde la que se crea (la usamos para inflar)
        this.listaPeliculas = listaPeliculas;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPelicula;
        TextView tvTitulo, tvGenero, tvDescripcion;
        RatingBar ratingPelicula;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPelicula = itemView.findViewById(R.id.imgPelicula);
            tvTitulo = itemView.findViewById(R.id.tvTitulo);
            tvGenero = itemView.findViewById(R.id.tvGenero);
            ratingPelicula = itemView.findViewById(R.id.ratingPelicula);
            tvDescripcion = itemView.findViewById(R.id.tvDescripcion);
        }
    }
    // ViewHolder: guarda las referencias a los componentes de UNA tarjeta.
    // itemView es la tarjeta completa (item_pelicula.xml ya inflado).
    // Así el findViewById se hace una vez por tarjeta y no en cada scroll.
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_item_pelicula, parent, false);
        return new ViewHolder(view);
    }
    // Crea una tarjeta nueva: convierte item_pelicula.xml en un View y se lo da al ViewHolder.
    // El "false" significa "no la añadas todavía al padre, ya lo hará el RecyclerView".
    // Solo se llama para las tarjetas que caben en pantalla, luego se reciclan.
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Pelicula pelicula = listaPeliculas.get(position);

        holder.imgPelicula.setImageResource(pelicula.getImageResId());
        holder.tvTitulo.setText(pelicula.getTitulo());
        holder.tvGenero.setText(pelicula.getGenero());
        holder.ratingPelicula.setRating((float) pelicula.getValoracion());
        holder.tvDescripcion.setText(pelicula.getDescripcionCorta());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("pelicula", pelicula);
                context.startActivity(intent);
            }
        });
    }
    // Rellena una tarjeta con los datos de la película de esa posición.
    // Se llama cada vez que una tarjeta aparece o se reutiliza al hacer scroll
    @Override
    public int getItemCount() {
        return listaPeliculas.size();
    }
    // Le dice al RecyclerView cuántas tarjetas tiene que mostrar.


    //Animacion para pelicula, sino si es para todo se mete en main
    @Override
    public void onViewAttachedToWindow(@NonNull ViewHolder holder) {
        super.onViewAttachedToWindow(holder);

        holder.itemView.clearAnimation();

        Animation animation = AnimationUtils.loadAnimation(context, R.anim.zoom_in);
        holder.itemView.startAnimation(animation);
    }

}
