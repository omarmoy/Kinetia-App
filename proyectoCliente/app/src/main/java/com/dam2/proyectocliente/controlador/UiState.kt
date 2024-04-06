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

    //Datos IU
    val actividades: ArrayList<Actividad> = DatosPrueba.actividades,
    val anuncios: ArrayList<Anuncio> = DatosPrueba.anuncios,

    //Variables de Estado IU
    val actividadSeleccionada: Actividad = Actividad(
        id = 0,
        imagen = R.drawable.nofoto,
        titulo = "titulo",
        contenido = R.string.vacio,
        anuncianteID = 0,
        anunciante = "anunciante",
        fecha = Fecha.ahora()
    ),
    val anuncioSeleccionado: Anuncio? = null, //TODO
    val contactoSeleccionado: Contacto = Contacto(0, "nombre", R.drawable.nofoto, ArrayList()),
    val categoriaSelecciononada: Categoria = Categoria.Todo,
    //val busquedaActiva: Boolean = false,

    val mostrarPanelNavegacion: Boolean = true,
    val botoneraNav: ArrayList<Boolean> = arrayListOf(true, false, false, false),
    val modoOfertante: Boolean = false, //TODO

    //Entradas de texto
    val mensajeEnviar: String = "",
    val actividadBuscar: String = "",
    val contactosBuscar: String = "",

    //Filtros
    val filtroMensajesNoleidosActivo: Boolean = false


){
    fun esFavorita(actividad: Actividad): Boolean{
        if (usuario.actividadesFav.size==0)
            return false
        return usuario.actividadesFav.contains(actividad)
    }

}