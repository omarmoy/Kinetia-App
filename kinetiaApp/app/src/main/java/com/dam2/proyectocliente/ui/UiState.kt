package com.dam2.proyectocliente.ui

import com.dam2.proyectocliente.moker.Moker
import com.dam2.proyectocliente.models.Activity
import com.dam2.proyectocliente.models.Advertisement
import com.dam2.proyectocliente.models.Category
import com.dam2.proyectocliente.models.Chat
import com.dam2.proyectocliente.models.User
import java.time.Instant
import java.time.LocalDateTime

data class UiState(

    val user: User? = Moker.user,  //TODO: quitar

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
    var nuevoAdvertisement: Advertisement? = null,
    var nuevaActivity: Activity? = null,
//    var formularioActividad: MutableMap<String, String> = mutableMapOf(
//        "titulo" to "",
//        "fecha" to "",
//        //"duracion" to "", TODO quitar de los demás sitios, poner hora en su lugar
//        "precio" to "",
//        "ubicacion" to "",
//        "categoria" to "",
//        "destacado" to "",
//        "contenido" to ""
//    ),
    var modAdvertisement: Advertisement? = null,
    var modActivity: Activity? = null,

    //Datos IU
    val actividades: ArrayList<Activity> = arrayListOf(),
    val advertisements: ArrayList<Advertisement> = arrayListOf(),
    val categories: ArrayList<Category> = arrayListOf(),
    val indiceCategoria: Int = 0,

    //Variables de Estado IU
    val activitySeleccionada: Activity = Activity(
        id = -1,
        title = "titulo",
        description = "",
        picture = "",
        userId = 0L,
        userName = "anunciante",
        date = LocalDateTime.now(),
        createdAt = Instant.now(),
        price = 0.0,
        location = "ubicación",
        category = Category.TODO,
        vacancies = 0
    ),
    val advertisementSeleccionado: Advertisement = Advertisement(
        id = 0,
        userPhoto = "R.drawable.nofoto",
        title = "titulo anuncio",
        userId = 0,
        userName = "anunciante",
        creationDate = Instant.now(),
        description = "",
        location = ""
    ),
    val chatSeleccionado: Chat = Chat(0, "nombre", "R.drawable.nofoto", ArrayList()),
    val categorySelecciononada: Category = Category.TODO,

    val mostrarPanelNavegacion: Boolean = false, //TODO gestionar
    val botoneraNav: ArrayList<Boolean> = arrayListOf(true, false, false, false),
    val modoPro: Boolean = true, //TODO gestionar

    //Entradas de texto
    val mensajeEnviar: String = "",
    val actividadBuscar: String = "",
    val anuncioBuscar: String = "",
    val contactosBuscar: String = "",
    val actividadUsuarioBuscar: String = "",

    //Filtros
    val filtroMensajesNoleidosActivo: Boolean = false


) {
    fun esFavorita(activity: Activity): Boolean {
        if (user!!.activitiesFav.size == 0)
            return false
        return user!!.activitiesFav.contains(activity)
    }

}