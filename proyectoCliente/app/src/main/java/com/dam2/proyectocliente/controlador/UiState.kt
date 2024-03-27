package com.dam2.proyectocliente.controlador

import com.dam2.proyectocliente.model.Actividad
import com.dam2.proyectocliente.model.Anuncio
import com.dam2.proyectocliente.model.Contacto
import com.dam2.proyectocliente.model.Fecha
import com.dam2.proyectocliente.model.Mensaje
import com.dam2.proyectocliente.model.Rol
import com.example.proyectocliente.R

data class UiState(

    //Datos usuario
    val id: Int = 1,
    val nombre: String = "Crash",
    val apellido1: String = "Bandicoot",
    val apellido2: String = "PlayStation",
    val mail: String = "correo@correo.es",
    val rol: Rol = Rol.OFERTANTE,
    val password: String = "crash",
    val foto: Int = R.drawable.crash,
    val nif: String = "44112233M",
    val actividadesFav: ArrayList<Actividad> = DatosPrueba.cargarActividades(),
    val actividadesOfertadas: ArrayList<Actividad> = DatosPrueba.cargarActividadesOfertadas(),
    val anunciosPublicados: ArrayList<Anuncio> = DatosPrueba.cargarAnuncios(),
    val contactos: ArrayList<Contacto> = DatosPrueba.cargarConversaciones(),

    //Datos IU
    val actividades: ArrayList<Actividad> = DatosPrueba.actividades,
    val anuncios: ArrayList<Anuncio> = DatosPrueba.anuncios,

    //Estado IU
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
    //val usuario: Usuario = DatosPrueba.usuario,
    val mostrarPanelNavegacion: Boolean = true,


    //Entradas de texto
    val mensajeEnviar: String = "",
    val actividadBuscar: String = "",
    val contactosBuscar: String = "",

    var modoEmpresa: Boolean? = null
){

    /**
    FUNCIONES DATOS USUARIO
     **/

    fun addActividadFav(actividad: Actividad): Boolean {
        return actividadesFav.add(actividad)
    }

    fun eliminarActividadFav(actividad: Actividad): Boolean {
        return actividadesFav.remove(actividad)
    }

    fun addOfertaActividad(actividad: Actividad): Boolean {
        return actividadesOfertadas.add(actividad)
    }

    fun eliminarOfertaActividad(actividad: Actividad): Boolean {
        return actividadesOfertadas.remove(actividad)
    }

    fun addAnuncio(anuncio: Anuncio): Boolean {
        return anunciosPublicados.add(anuncio)
    }

    fun eliminarAnuncio(anuncio: Anuncio): Boolean {
        return anunciosPublicados.remove(anuncio)
    }

    fun addContacto(contacto: Contacto): Boolean {
        return contactos.add(contacto)
    }

    fun eliminarContacto(contacto: Contacto): Boolean {
        return contactos.remove(contacto)
    }

    fun mensajeLeido(contacto: Contacto){
        val indice = contactos.indexOf(contacto)
        contactos[indice].mensajeNuevo = false
    }

    fun addMensaje(contacto: Contacto, mensaje: Mensaje){
        val indice = contactos.indexOf(contacto)
        contactos[indice].addMensaje(mensaje)
    }
}