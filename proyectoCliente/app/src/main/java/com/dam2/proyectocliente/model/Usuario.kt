package com.dam2.proyectocliente.model

enum class Rol {
    OFERTANTE, CONSUMIDOR, ADMINISTRADOR
}
/*
class Usuario(
    val id: Int,
    val nombre: String,
    val apellido1: String,
    val apellido2: String,
    val mail: String,
    val rol: Rol,
    val password: String,
    val foto: Int,
    val nif: String,
    val actividadesFav: ArrayList<Actividad> = ArrayList(),
    val actividadesOfertadas: ArrayList<Actividad> = ArrayList(),
    val anuncios: ArrayList<Anuncio> = ArrayList(),
    val contactos: ArrayList<Contacto> = ArrayList()
) {
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
        return anuncios.add(anuncio)
    }

    fun eliminarAnuncio(anuncio: Anuncio): Boolean {
        return anuncios.remove(anuncio)
    }

    fun addContacto(contacto: Contacto): Boolean {
        return contactos.add(contacto)
    }

    fun eliminarContacto(contacto: Contacto): Boolean {
        return contactos.remove(contacto)
    }


}

 */