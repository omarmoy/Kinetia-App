package com.dam2.proyectocliente.controlador

import com.dam2.proyectocliente.model.Actividad
import com.dam2.proyectocliente.model.Anuncio
import com.dam2.proyectocliente.model.Categoria
import com.dam2.proyectocliente.model.Contacto
import com.dam2.proyectocliente.model.Fecha
import com.dam2.proyectocliente.model.Usuario
import com.example.proyectocliente.R

data class UiState(

    val usuario: Usuario = DatosPrueba.usuario,

    //Formulario inscripción
    val formularioRegistro: MutableMap<String, String> = mutableMapOf(
        "mail" to "",
        "password" to "",
        "rol" to "",
        "nombre" to "",
        "apellido1" to "",
        "apellido2" to "",
        "diaNac" to "",
        "mesNac" to "",
        "anioNac" to "",
        "nombreEmpresa" to "",
        "cif" to "",
        "direccion" to ""
    ),
    val esEmpresa: Boolean = false,

    //Formulario publicar
    var nuevoAnuncio: Anuncio? = null,
    var nuevaActividad: Actividad? = null,
    var formularioActividad: MutableMap<String, String> = mutableMapOf(
        "titulo" to "",
        "fecha" to "",
        //"duracion" to "", TODO quitar de los demás sitios, poner hora en su lugar
        "precio" to "",
        "ubicacion" to "",
        "categoria" to "",
        "destacado" to "",
        "contenido" to ""
    ),

    //Datos IU
    val actividades: ArrayList<Actividad> = DatosPrueba.actividades,
    val anuncios: ArrayList<Anuncio> = DatosPrueba.anuncios,
    val categorias: ArrayList<Categoria> = DatosPrueba.categorias,
    val indiceCategoria: Int = 0,

    //Variables de Estado IU
    val actividadSeleccionada: Actividad = Actividad(
        id = 0,
        imagen = R.drawable.noimagen,
        titulo = "titulo",
        contenido = R.string.vacio,
        anuncianteID = 0,
        anunciante = "anunciante",
        fecha = Fecha.ahora(),
        ubicacion = "ubicación"
    ),
    val anuncioSeleccionado: Anuncio = Anuncio(
        id = 0,
        fotoAnunciante = R.drawable.nofoto,
        titulo = "titulo anuncio",
        contenidoInt = R.string.vacio,
        anuncianteID = 0,
        anunciante = "anunciante",
        fecha = Fecha.ahora(),
        contenido = ""
    ),
    val contactoSeleccionado: Contacto = Contacto(0, "nombre", R.drawable.nofoto, ArrayList()),
    val categoriaSelecciononada: Categoria = Categoria.Todo,
    //val busquedaActiva: Boolean = false,

    val mostrarPanelNavegacion: Boolean = false,
    val botoneraNav: ArrayList<Boolean> = arrayListOf(true, false, false, false),
    val modoPro: Boolean = false, //TODO

    //Entradas de texto
    val mensajeEnviar: String = "",
    val actividadBuscar: String = "",
    val contactosBuscar: String = "",
    val actividadUsuarioBuscar: String = "",

    //Filtros
    val filtroMensajesNoleidosActivo: Boolean = false


) {
    fun esFavorita(actividad: Actividad): Boolean {
        if (usuario.actividadesFav.size == 0)
            return false
        return usuario.actividadesFav.contains(actividad)
    }

}