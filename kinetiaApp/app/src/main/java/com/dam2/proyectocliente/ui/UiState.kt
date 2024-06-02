package com.dam2.proyectocliente.ui

import com.dam2.proyectocliente.moker.Moker
import com.dam2.proyectocliente.models.Activity
import com.dam2.proyectocliente.models.Advertisement
import com.dam2.proyectocliente.models.Category
import com.dam2.proyectocliente.models.Chat
import com.dam2.proyectocliente.models.User
import java.time.Instant

data class UiState(

    val user: User? = Moker.user, //TODO cambiar por null
//    val user: User? = null,

    //Formulario inscripción
    val formSignUp: MutableMap<String, String> = mutableMapOf(
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
    val isCompany: Boolean = false,

    //Formularios
    var newAdvertisement: Advertisement? = null,
    var newActivity: Activity? = null,
    var modAdvertisement: Advertisement? = null,
    var modActivity: Activity? = null,

    //Datos IU
    val activities: ArrayList<Activity> = arrayListOf(),
    val advertisements: ArrayList<Advertisement> = arrayListOf(),
    val categories: ArrayList<Category> = arrayListOf(
        Category.TODO,
        Category.AIRE_LIBRE,
        Category.ARTE,
        Category.AVENTURA,
        Category.BARES,
        Category.CURSOS_Y_TALLERES,
        Category.DEPORTE,
        Category.EXPERIENCIAS,
        Category.GASTRONOMIA,
        Category.MUSICA,
        Category.OCIO,
        Category.OFERTAS,
        Category.SALUD_Y_BIENESTAR
    ),
    val indiceCategoria: Int = 0,

    //Variables de Estado IU
    var selectedActivity: Activity? = null,
    var selectedActivityWithReservation: Activity? = null,

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
    val selectedChat: Chat = Chat(0, "nombre", "R.drawable.nofoto", ArrayList()),
    val selectedCategory: Category = Category.TODO,
    val selectedPicture: Int = 0,

    val showNavigationPanel: Boolean = false,
    val buttonsNav: ArrayList<Boolean> = arrayListOf(true, false, false, false),
    var proMode: Boolean = true,

    //Entradas de texto
    val messageSend: String = "",
    val activitySearched: String = "",
    val advertisementSearched: String = "",
    val contactSearched: String = "",
    val activityUserSearched: String = "",

    //Filtros
    val filterUnreadMessagesActive: Boolean = false


) {
    fun isFavorite(activity: Activity): Boolean {
        if (user!!.activitiesFav.size == 0)
            return false
        return user.activitiesFav.contains(activity)
    }

}