package com.dam2.proyectocliente.controlador

import com.dam2.proyectocliente.model.Actividad
import com.dam2.proyectocliente.model.Anuncio
import com.dam2.proyectocliente.model.Categoria
import com.dam2.proyectocliente.model.Contacto
import com.dam2.proyectocliente.model.Fecha
import com.dam2.proyectocliente.model.Mensaje
import com.dam2.proyectocliente.model.Rol
import com.dam2.proyectocliente.model.Usuario
import com.example.proyectocliente.R
import java.time.LocalDateTime

object DatosPrueba {
    val actividades = arrayListOf<Actividad>(
        Actividad(
            id = 1, titulo = "Titulo Actividad 1", imagen = R.drawable.architecture, contenidoPrueba = R.string.t1,
            anunciante = "Crash Bandicoot", fecha = Fecha.ahora(), categoria = Categoria.ARTE,   precio = 10.5f, ubicacion = "C/ Melancolia 7, 41001 Sabina",  anuncianteID = 1 ),
        Actividad(
            id = 2, titulo = "Titulo Actividad 2", imagen = R.drawable.business, contenidoPrueba = R.string.t2,
            anunciante = "Ofertante de Prueba", fecha = Fecha.ahora(), categoria = Categoria.AIRE_LIBRE,   precio = 10.5f, ubicacion = "C/ Melancolia 7, 41001 Sabina",  anuncianteID = 1),
        Actividad(
            id = 3, titulo = "Un Titulo Actividad largo, pero bastante largo, largísimo, tela de largo", imagen = R.drawable.design, contenidoPrueba = R.string.t4,
            anunciante = "Ofertante de Prueba", fecha = Fecha.ahora(), categoria = Categoria.AVENTURA,   precio = 10.5f, ubicacion = "C/ Melancolia 7, 41001 Sabina",  anuncianteID = 1) ,
        Actividad(
            id = 4, titulo = "Titulo Actividad 3", imagen = R.drawable.crafts, contenidoPrueba = R.string.t3,
            anunciante = "Ofertante de Prueba", fecha = Fecha.ahora(), categoria = Categoria.BARES,   precio = 10.5f, ubicacion = "C/ Melancolia 7, 41001 Sabina",  anuncianteID = 1),
        Actividad(
            id = 5, titulo = "Titulo Actividad 5", imagen = R.drawable.culinary, contenidoPrueba = R.string.t5,
            anunciante = "Ofertante de Prueba", fecha = Fecha.ahora(), categoria = Categoria.DEPORTE,   precio = 10.5f, ubicacion = "C/ Melancolia 7, 41001 Sabina" , anuncianteID = 1),
        Actividad(
            id = 6, titulo = "Titulo Actividad 6", imagen = R.drawable.drawing, contenidoPrueba = R.string.t1,
            anunciante = "Ofertante de Prueba", fecha = Fecha.ahora(), categoria = Categoria.CURSOS_Y_TALLERES,   precio = 10.5f, ubicacion = "C/ Melancolia 7, 41001 Sabina" , anuncianteID = 1),
        Actividad(
            id = 7, titulo = "Titulo Actividad 7", imagen = R.drawable.fashion, contenidoPrueba = R.string.t5,
            anunciante = "Ofertante de Prueba", fecha = Fecha.ahora(), categoria = Categoria.EXPERIENCIAS,   precio = 10.5f, ubicacion = "C/ Melancolia 7, 41001 Sabina" ,  anuncianteID = 1),
        Actividad(
            id =8, titulo = "Titulo Actividad 8", imagen = R.drawable.film, contenidoPrueba = R.string.t5,
            anunciante = "Ofertante de Prueba", fecha = Fecha.ahora(), categoria = Categoria.GASTRONOMIA,   precio = 10.5f, ubicacion = "C/ Melancolia 7, 41001 Sabina" ,  anuncianteID = 1, destacado = true),
        Actividad(
            id = 9, titulo = "Titulo Actividad 9", imagen = R.drawable.gaming, contenidoPrueba = R.string.t5,
            anunciante = "Ofertante de Prueba", fecha = Fecha.ahora(), categoria = Categoria.CURSOS_Y_TALLERES,   precio = 10.5f, ubicacion = "C/ Melancolia 7, 41001 Sabina",  anuncianteID = 1 ),
        Actividad(
            id = 10, titulo = "Titulo Actividad 10", imagen = R.drawable.lifestyle, contenidoPrueba = R.string.t5,
            anunciante = "Ofertante de Prueba", fecha = Fecha.ahora(), categoria = Categoria.MUSICA,   precio = 10.5f, ubicacion = "C/ Melancolia 7, 41001 Sabina" ,  anuncianteID = 1, destacado = true),
        Actividad(
            id = 11, titulo = "Titulo Actividad 11", imagen = R.drawable.music, contenidoPrueba = R.string.t5,
            anunciante = "Ofertante de Prueba", fecha = Fecha.ahora(), categoria = Categoria.OCIO,   precio = 10.5f, ubicacion = "C/ Melancolia 7, 41001 Sabina" ,  anuncianteID = 1, destacado = true),
        Actividad(
            id = 12, titulo = "Titulo Actividad 12", imagen = R.drawable.photography, contenidoPrueba = R.string.t5,
            anunciante = "Ofertante de Prueba", fecha = Fecha.ahora(), categoria = Categoria.SALUD_Y_BIENESTAR,   precio = 10.5f, ubicacion = "C/ Melancolia 7, 41001 Sabina" ,  anuncianteID = 1),
        Actividad(
            id = 13, titulo = "Titulo Actividad 13", imagen = R.drawable.painting, contenidoPrueba = R.string.t5,
            anunciante = "Ofertante de Prueba", fecha = Fecha.ahora(), categoria = Categoria.EXPERIENCIAS,   precio = 10.5f, ubicacion = "C/ Melancolia 7, 41001 Sabina" ,  anuncianteID = 1),
        Actividad(
            id = 14, titulo = "Titulo Actividad 14", imagen = R.drawable.tech, contenidoPrueba = R.string.t14,
            anunciante = "Ofertante de Prueba", fecha = Fecha.ahora(), categoria = Categoria.AVENTURA,   precio = 10.5f, ubicacion = "C/ Melancolia 7, 41001 Sabina" ,  anuncianteID = 1, destacado = true)
    )

    val categorias = arrayListOf<Categoria>(
        Categoria.TODO,
        Categoria.AIRE_LIBRE,
        Categoria.ARTE,
        Categoria.AVENTURA,
        Categoria.BARES,
        Categoria.CURSOS_Y_TALLERES,
        Categoria.DEPORTE,
        Categoria.EXPERIENCIAS,
        Categoria.GASTRONOMIA,
        Categoria.MUSICA,
        Categoria.OCIO,
        Categoria.OFERTAS,
        Categoria.SALUD_Y_BIENESTAR
    )

    val anuncios = arrayListOf<Anuncio>(
        Anuncio(id = 1, titulo  = "Busco peluquero", fotoAnunciante = R.drawable.cortex, contenidoInt = R.string.t1, anuncianteID = 1, anunciante = "Crash Bandicoot", fecha = Fecha.ahora()),
        Anuncio(id = 2, titulo  = "Busco manzanas", fotoAnunciante = R.drawable.crash, contenidoInt = R.string.t1, anuncianteID = 1, anunciante = "Crash Bandicoot", fecha = Fecha.ahora())
    )

    val usuario = Usuario(
        id = 1, nombre = "Crash", apellido1 = "Bandicoot", apellido2 = "PlayStation", mail = "correo@correo.es",
        rol = Rol.OFERTANTE, password = "crash", foto = R.drawable.crash, cif = "44112233M",
        actividadesFav = cargarActividadesFav(),//arrayListOf<Actividad>(),
        actividadesOfertadas = cargarActividadesOfertadas(),
        anunciosPublicados = cargarAnuncios(),
        contactos = cargarConversaciones(),

        )

    fun cargarActividadesOfertadas(): ArrayList<Actividad>{
        return arrayListOf<Actividad>(
            Actividad(
                id = 1, titulo = "Titulo Actividad 1", imagen = R.drawable.architecture, contenidoPrueba = R.string.t1,
                anunciante = "Crash Bandicoot", fecha = Fecha.ahora(), categoria = Categoria.ARTE,   precio = 10.5f, ubicacion = "C/ Melancolia 7, 41001 Sabina",  anuncianteID = 1,
                plazas = 15, reservas = arrayListOf(
                    Contacto(3, "Cortex N. Bandicoot", R.drawable.cortex),
                    Contacto(5, "Coco Bandicoot", R.drawable.coco),
                    Contacto(4, "Crunch. Bandicoot", R.drawable.crunch),
                    Contacto(32, "Gin. Bandicoot", R.drawable.gin),
                    Contacto(31, "Nina. Bandicoot", R.drawable.nina)
                )
            )
        )
    }

    fun cargarAnuncios(): ArrayList<Anuncio>{
        return arrayListOf(
            Anuncio(id = 2, titulo  = "Busco manzanas", fotoAnunciante = R.drawable.crash, contenidoInt = R.string.t1, anuncianteID = 1, anunciante = "Crash Bandicoot", fecha = Fecha.ahora()),
            Anuncio(id = 3, titulo  = "Busco muchas manzanas", fotoAnunciante = R.drawable.crash, contenidoInt = R.string.t1, anuncianteID = 1, anunciante = "Crash Bandicoot", fecha = Fecha.ahora()))
    }
    fun cargarActividadesFav(): ArrayList<Actividad>{
        return arrayListOf(
            Actividad(
                id = 10, titulo = "Titulo Actividad 10", imagen = R.drawable.lifestyle, contenidoPrueba = R.string.t5,
                anunciante = "Ofertante de Prueba", fecha = Fecha.ahora(), categoria = Categoria.MUSICA,   precio = 10.5f, ubicacion = "C/ Melancolia 7, 41001 Sabina" ,  anuncianteID = 1),
            Actividad(
                id = 11, titulo = "Titulo Actividad 11", imagen = R.drawable.music, contenidoPrueba = R.string.t5,
                anunciante = "Ofertante de Prueba", fecha = Fecha.ahora(), categoria = Categoria.OCIO,   precio = 10.5f, ubicacion = "C/ Melancolia 7, 41001 Sabina" ,  anuncianteID = 1),
            Actividad(
                id = 13, titulo = "Titulo Actividad 13", imagen = R.drawable.painting, contenidoPrueba = R.string.t5,
                anunciante = "Ofertante de Prueba", fecha = Fecha.ahora(), categoria = Categoria.EXPERIENCIAS,   precio = 10.5f, ubicacion = "C/ Melancolia 7, 41001 Sabina" ,  anuncianteID = 1),
        )
    }

    fun cargarConversaciones(): ArrayList<Contacto>{
        return arrayListOf(
            Contacto(2, "Coco Bandicoot", R.drawable.coco, arrayListOf(
                Mensaje(2, Fecha(LocalDateTime.of(2023, 5, 15, 14, 30, 0)), "Hola", true),
                Mensaje(1, Fecha(LocalDateTime.of(2023, 5, 15, 14, 35, 0)), "Hola", true),
                Mensaje(2, Fecha(LocalDateTime.of(2023, 5, 15, 14, 36, 0)), "Que tal", true),
                Mensaje(1, Fecha(LocalDateTime.of(2023, 5, 15, 14, 37, 0)), "Muy Bien", true)
            )),
            Contacto(3, "Cortex N. Bandicoot", R.drawable.cortex, arrayListOf(
                Mensaje(1, Fecha(LocalDateTime.of(2023, 5, 15, 14, 30, 0)), "Hola", true),
                Mensaje(3, Fecha(LocalDateTime.of(2023, 5, 15, 14, 35, 0)), "Adios", true),
                Mensaje(1, Fecha(LocalDateTime.of(2023, 5, 15, 14, 36, 0)), "Que tal", true),
                Mensaje(3, Fecha(LocalDateTime.of(2023, 5, 15, 14, 37, 0)), "Muy Mal", false)),
                true)

        )
    }
}