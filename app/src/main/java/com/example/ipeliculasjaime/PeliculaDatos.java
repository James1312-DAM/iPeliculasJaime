package com.example.ipeliculasjaime;

import java.util.ArrayList;
import java.util.List;

public class PeliculaDatos {
    public static List<Pelicula> listaPeliculas = new ArrayList<>();

    public static  void cargarPeliculas(){
        if (listaPeliculas.isEmpty()) {
            listaPeliculas.add(new Pelicula(1, "Caballo salvaje nueve", "Thriller", 4,
                    "Nuevo thriller de suspense de Martin McDonagh.",
                    "Martin McDonagh reúne de nuevo a Sam Rockwell y John Malkovich en un intrigante thriller de suspense con su sello inconfundible.",
                    "Martin McDonagh", "13/11/2026", R.drawable.caballosalvaje));
            listaPeliculas.add(new Pelicula(2, "Cómo robar un banco", "Acción", 2.5,
                    "Ladrones que retransmiten sus atracos en redes sociales.",
                    "Un grupo de astutos ladrones de bancos transmite sus atracos en redes sociales, ganando fama mientras evade a la policía en plena era digital.",
                    "David Leitch", "13/11/2026", R.drawable.robarunbanco));
            listaPeliculas.add(new Pelicula(3, "Scrooge y los fantasmas de la Navidad", "Fantasía", 2.5,
                    "Johnny Depp es Ebenezer Scrooge en un clásico navideño.",
                    "Adaptación de Cuento de Navidad, de Charles Dickens, con Johnny Depp como Ebenezer Scrooge y un reparto encabezado también por Daisy Ridley.",
                    "Ti West", "13/11/2026", R.drawable.scrooge));
            listaPeliculas.add(new Pelicula(4, "Victorian Psycho", "Terror", 3,
                    "Una institutriz victoriana con intenciones muy oscuras.",
                    "Thriller de terror histórico ambientado en la época victoriana, protagonizado por Maika Monroe, Thomasin McKenzie y Jason Isaacs.",
                    "Zachary Wigon", "13/11/2026", R.drawable.victorian));
            listaPeliculas.add(new Pelicula(5, "Ruega por nosotras", "Drama", 3,
                    "Una joven internada en el Patronato de Protección a la Mujer.",
                    "Ambientada en 1974, cuenta la historia de una joven de 19 años a la que su familia mete en el Patronato de Protección a la Mujer.",
                    "Daniel Monzón", "06/11/2026", R.drawable.ruegapornosotras));
            listaPeliculas.add(new Pelicula(6, "El gato con sombrero", "Animación", 3f,
                    "Un astuto gato lleva el caos a la vida de dos hermanos.",
                    "Un astuto gato lleva el caos con sus travesuras a la vida de un par de hermanos que lidian con su mudanza a una nueva ciudad. Nueva película animada basada en el clásico libro infantil del Dr. Seuss.",
                    "Alessandro Carloni y Erica Rivinoja", "06/11/2026", R.drawable.elgatoconsombrero));
            listaPeliculas.add(new Pelicula(1, "Caballo salvaje nueve", "Thriller", 4,
                    "Nuevo thriller de suspense de Martin McDonagh.",
                    "Martin McDonagh reúne de nuevo a Sam Rockwell y John Malkovich en un intrigante thriller de suspense con su sello inconfundible.",
                    "Martin McDonagh", "13/11/2026", R.drawable.caballosalvaje));
            listaPeliculas.add(new Pelicula(2, "Cómo robar un banco", "Acción", 2.5,
                    "Ladrones que retransmiten sus atracos en redes sociales.",
                    "Un grupo de astutos ladrones de bancos transmite sus atracos en redes sociales, ganando fama mientras evade a la policía en plena era digital.",
                    "David Leitch", "13/11/2026", R.drawable.robarunbanco));
            listaPeliculas.add(new Pelicula(3, "Scrooge y los fantasmas de la Navidad", "Fantasía", 2.5,
                    "Johnny Depp es Ebenezer Scrooge en un clásico navideño.",
                    "Adaptación de Cuento de Navidad, de Charles Dickens, con Johnny Depp como Ebenezer Scrooge y un reparto encabezado también por Daisy Ridley.",
                    "Ti West", "13/11/2026", R.drawable.scrooge));
            listaPeliculas.add(new Pelicula(4, "Victorian Psycho", "Terror", 3,
                    "Una institutriz victoriana con intenciones muy oscuras.",
                    "Thriller de terror histórico ambientado en la época victoriana, protagonizado por Maika Monroe, Thomasin McKenzie y Jason Isaacs.",
                    "Zachary Wigon", "13/11/2026", R.drawable.victorian));
            listaPeliculas.add(new Pelicula(5, "Ruega por nosotras", "Drama", 3,
                    "Una joven internada en el Patronato de Protección a la Mujer.",
                    "Ambientada en 1974, cuenta la historia de una joven de 19 años a la que su familia mete en el Patronato de Protección a la Mujer.",
                    "Daniel Monzón", "06/11/2026", R.drawable.ruegapornosotras));
            listaPeliculas.add(new Pelicula(6, "El gato con sombrero", "Animación", 3f,
                    "Un astuto gato lleva el caos a la vida de dos hermanos.",
                    "Un astuto gato lleva el caos con sus travesuras a la vida de un par de hermanos que lidian con su mudanza a una nueva ciudad. Nueva película animada basada en el clásico libro infantil del Dr. Seuss.",
                    "Alessandro Carloni y Erica Rivinoja", "06/11/2026", R.drawable.elgatoconsombrero));
        }
    }
}
