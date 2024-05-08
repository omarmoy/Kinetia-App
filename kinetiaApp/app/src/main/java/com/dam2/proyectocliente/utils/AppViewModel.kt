package com.dam2.proyectocliente.utils

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dam2.proyectocliente.repositories.UserRepository
import com.dam2.proyectocliente.models.Activity
import com.dam2.proyectocliente.models.Advertisement
import com.dam2.proyectocliente.models.Category
import com.dam2.proyectocliente.models.Chat
import com.dam2.proyectocliente.models.User
import com.dam2.proyectocliente.network.request.Login
import com.dam2.proyectocliente.ui.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import java.time.LocalDateTime


sealed interface UserUiState {
    data class Success(val user: User?) : UserUiState
    object Error : UserUiState
    object Loading : UserUiState
}


class AppViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState

    var userUiState: UserUiState by mutableStateOf(UserUiState.Loading)
        private set

    fun login(email: String, password: String) {
        viewModelScope.launch {
            userUiState = UserUiState.Loading
            userUiState = try {
                val userRepository = UserRepository()
                val user = userRepository.login(Login(email, password))
                setUser(user)
                UserUiState.Success(user)
            } catch (e: IOException) {
                UserUiState.Error
            } catch (e: HttpException) {
                UserUiState.Error
            }
        }
    }

    fun setUser(user: User?){
        _uiState.update { e ->
            e.copy(user = user)
        }
    }


    /**
     * usuario
     */
    fun usuario(): User? {
        return _uiState.value.user
    }

    /**
     * FORMULARIO REGISTRO
     */
//    fun nuevoUsuario(campo: String, valor: String){
//        var usuario = _uiState.value.usuarioResitro
//        usuario[campo] = valor
//        _uiState.update { e -> e.copy(usuarioResitro = usuario) }
//
//    }

    fun setEsEmpresa(esEmpresa: Boolean) {
        _uiState.update { e ->
            e.copy(esEmpresa = esEmpresa)
        }
    }

    fun addCampoFormularioRegistro(campo: String, valor: String) {
        _uiState.value.formularioRegistro[campo] = valor
    }


    /**
    ACTIVIDADES
     */
    fun actividadesDestacadas(): ArrayList<Activity> {
        val listaFiltrada = _uiState.value.actividades.filter { it.featured }
        return ArrayList(listaFiltrada)
    }

    fun actividadesRecientes(): ArrayList<Activity> {
        val ordenadas = _uiState.value.actividades.sortedByDescending { it.date }
        val recientes = ArrayList<Activity>()

        for (i in 0 until minOf(ordenadas.size, 5)) {
            recientes.add(ordenadas[i])
        }

        return recientes
    }

    fun selectActividad(a: Activity) {
        _uiState.update { e -> e.copy(activitySeleccionada = a) }
    }

    fun selectCategoria(c: Category) {
        _uiState.update { e -> e.copy(categorySelecciononada = c) }
    }

    fun setIndiceCategoria(c: Category? = null) {
        val indice =
            if (c != null) {
                uiState.value.categories.indexOf(c)
            } else {
                0
            }
        _uiState.update { e -> e.copy(indiceCategoria = indice) }
    }

    fun listaActividades(): ArrayList<Activity> {

        val listaActividades = if (uiState.value.actividadBuscar != "") {
            resultadoBusquedaActividad()
        } else {
            uiState.value.actividades
        }

        return if (uiState.value.categorySelecciononada == Category.TODO)
            listaActividades
        else
            ArrayList(listaActividades.filter {
                it.category == uiState.value.categorySelecciononada
            })
    }

    fun setActividadBuscar(actividad: String) {
        _uiState.update { e -> e.copy(actividadBuscar = actividad) }
    }

    fun resultadoBusquedaActividad(tituloBuscar: String = uiState.value.actividadBuscar): ArrayList<Activity> {
        return buscarActividad(uiState.value.actividades, tituloBuscar)
    }

    //actividades de usuario
    fun cargarActividadesUsuario(lista: ArrayList<Activity>): ArrayList<Activity> {
        val actividadBuscada = uiState.value.actividadUsuarioBuscar

        if (actividadBuscada == "") {
            return lista
        } else {
            return buscarActividad(lista, actividadBuscada)
        }
    }

    fun setActividadUsuarioBuscar(actividad: String) {
        _uiState.update { e -> e.copy(actividadUsuarioBuscar = actividad) }
    }

    fun selectModActividad(activity: Activity) {
        _uiState.update { e -> e.copy(modActivity = activity) }
    }

    fun modificarActividad(campos: ArrayList<String>, activity: Activity, destacado: Boolean) {
        //TODO: imagen
        activity.title = campos[0]
        activity.price = campos[1].toDouble()
        activity.location = campos[2]
        activity.category = stringToCategoria(campos[3])!!
        val fechayHora = LocalDateTime.of(
            campos[6].toInt(),
            campos[5].toInt(),
            campos[4].toInt(),
            campos[7].toInt(),
            campos[8].toInt()
        )
        activity.date = fechayHora
        activity.description = campos[9]
        activity.vacancies = campos[10].toInt()
        activity.featured = destacado
    }

    private fun stringToCategoria(cadena: String): Category? {
        return when (cadena) {
            "Todo" -> Category.TODO
            "Aire Libre" -> Category.AIRE_LIBRE
            "Arte" -> Category.ARTE
            "Aventura" -> Category.AVENTURA
            "Bares" -> Category.BARES
            "Cursos" -> Category.CURSOS_Y_TALLERES
            "Deporte" -> Category.DEPORTE
            "Experiencias" -> Category.EXPERIENCIAS
            "Gastronomía" -> Category.GASTRONOMIA
            "Música" -> Category.MUSICA
            "Ocio" -> Category.OCIO
            "Ofertas" -> Category.OFERTAS
            "Salud y Bienestar" -> Category.SALUD_Y_BIENESTAR
            else -> null
        }
    }

    fun nuevaActividad(campos: ArrayList<String>) {

        val fechayHora = LocalDateTime.of(
            campos[6].toInt(),
            campos[5].toInt(),
            campos[4].toInt(),
            campos[7].toInt(),
            campos[8].toInt()
        )

        val activity = Activity(
            title = campos[0],
            price = campos[1].toDouble(),
            location = campos[2],
            category = stringToCategoria(campos[3])!!,
            date = fechayHora,
            description = campos[9],
            vacancies = campos[10].toInt(),
            featured = campos[11].toBoolean(),
            userId = _uiState.value.user!!.id,
            userName = _uiState.value.user!!.fullName(),
            picture = "" //TODO
        )
        _uiState.update { e -> e.copy(nuevaActivity = activity) }
    }

    fun publicarActividad() {
        val actividad = _uiState.value.nuevaActivity
        _uiState.value.user!!.addActividadOferta(actividad!!)
    }

    fun borrarActividad(activity: Activity) {
        _uiState.value.user!!.eliminarActividadOferta(activity)
    }

    /**
    MENSAJES
     */
    fun selectContacto(c: Chat) {
        _uiState.update { e -> e.copy(chatSeleccionado = c) }
        if (c.newMessage) {
            marcarLeido()
        }
    }

    private fun marcarLeido() {
        _uiState.value.user!!.marcarMensajeLeido(_uiState.value.chatSeleccionado)
    }

    fun setMensaje(mensaje: String) {
        _uiState.update { e -> e.copy(mensajeEnviar = mensaje) }
    }

    fun enviarMensaje() {

//        val mensajeNuevo = Message(
//            _uiState.value.user!!.id, Fecha.ahora(), _uiState.value.mensajeEnviar, true
//        )
//        _uiState.value.user!!.addMensaje(_uiState.value.chatSeleccionado, mensajeNuevo)
//        _uiState.update { e -> e.copy(mensajeEnviar = "") }
        //TODO("enviar mensaje")

    }

    fun filtrarMensajesNoleidos() {
        if (_uiState.value.user!!.tieneMensajesSinLeer()) {
            _uiState.update { e -> e.copy(filtroMensajesNoleidosActivo = true) }
        }
    }

    fun quitarFiltroMensajesNoLeidos() {
        _uiState.update { e -> e.copy(filtroMensajesNoleidosActivo = false) }
    }


    fun listaContactos(): ArrayList<Chat> {

        val listaContactos = if (uiState.value.contactosBuscar != "") {
            resultadoBusquedaContacto()
        } else {
            uiState.value.user!!.chats
        }

        return if (uiState.value.filtroMensajesNoleidosActivo)
            ArrayList(listaContactos.filter { it.newMessage })
        else
            listaContactos
    }

    fun setContactoBuscar(contacto: String) {
        _uiState.update { e -> e.copy(contactosBuscar = contacto) }
    }

    fun resultadoBusquedaContacto(contactoBuscar: String = uiState.value.contactosBuscar): ArrayList<Chat> {
        return buscarContacto(uiState.value.user!!.chats, contactoBuscar)
    }


    /**
    PANEL NAVEGACIÓN
     */

    fun mostrarPanelNavegacion() {
        _uiState.update { e -> e.copy(mostrarPanelNavegacion = true) }
    }

    fun ocultarPanelNavegacion() {
        _uiState.update { e -> e.copy(mostrarPanelNavegacion = false) }
    }

    fun cambiarBotonNav(botonPulsado: Int) {
        val nuevoEstadoBotones = arrayListOf<Boolean>()
        for (i in 0 until _uiState.value.botoneraNav.size) {
            if (i == botonPulsado)
                nuevoEstadoBotones.add(true)
            else
                nuevoEstadoBotones.add(false)
        }
        _uiState.update { e -> e.copy(botoneraNav = nuevoEstadoBotones) }
    }

    /**
    FAVORITOS
     */
    fun addFavorito(activity: Activity) {
        if (!_uiState.value.user!!.activitiesFav.contains(activity))
            _uiState.value.user!!.addActividadFav(activity)
    }

    fun eliminarFavorito(activity: Activity) {
        _uiState.value.user!!.activitiesFav.remove(activity)
    }

    /**
    FUNCIONALIDADES
     */
    fun cambiarModo(): Boolean {
        val cambioModo = !uiState.value.modoPro
        _uiState.update { e -> e.copy(modoPro = cambioModo) }
        return cambioModo
    }

    /**
    ANUNCIOS
     */

    fun selectAnuncio(a: Advertisement) {
        _uiState.update { e -> e.copy(advertisementSeleccionado = a) }
    }

    fun nuevoAnuncio(titulo: String, localidad: String, contenido: String) {
        val advertisement =
            Advertisement(
                id = 100, //TODO API ID
                userPhoto = _uiState.value.user!!.profilePicture,
                title = titulo,
                description = contenido,
                userId = _uiState.value.user!!.id,
                userName = _uiState.value.user!!.fullName(),
                location = localidad
            )
        _uiState.update { e -> e.copy(nuevoAdvertisement = advertisement) }
    }

    fun modAnuncio(
        titulo: String,
        localidad: String,
        contenido: String,
        advertisement: Advertisement
    ) {
        advertisement.title = titulo
        advertisement.location = localidad
        advertisement.description = contenido
    }

    fun selectModAnuncio(advertisement: Advertisement) {
        _uiState.update { e -> e.copy(modAdvertisement = advertisement) }
    }

    fun resetNuevoAnuncio() {
//        _uiState.update { e -> e.copy(nuevoAnuncio = null) }
        //TODO peta cuando llama a esta función
    }

    fun publicarAnuncio() {
        val anuncio = _uiState.value.nuevoAdvertisement
        _uiState.value.user!!.addAnuncio(anuncio!!)
        resetNuevoAnuncio()
    }

    fun borrarAnuncio(advertisement: Advertisement) {
        _uiState.value.user!!.eliminarAnuncio(advertisement)
    }


    fun listaAnuncios(): ArrayList<Advertisement> {

//        val listaActividades = if (uiState.value.actividadBuscar != "") {
//            resultadoBusquedaActividad()
//        } else {
//            uiState.value.actividades
//        }
//
//        return if (uiState.value.categoriaSelecciononada == Categoria.Todo)
//            listaActividades
//        else
//            ArrayList(listaActividades.filter {
//                it.categoria == uiState.value.categoriaSelecciononada
//            })
        //TODO
        return _uiState.value.advertisements
    }

    fun setAuncioBuscar(anuncio: String) {
        _uiState.update { e -> e.copy(anuncioBuscar = anuncio) }
    }

    //TODO resultadoBusquedaAnuncio
//    fun resultadoBusquedaActividad(tituloBuscar: String = uiState.value.actividadBuscar): ArrayList<Actividad> {
//        return buscarActividad(uiState.value.actividades, tituloBuscar)
//    }


    /**
    RESERVAS CONSUMIDOR
     */
    fun reservar(activity: Activity) {
        _uiState.value.user!!.reseservar(activity)

    }

    fun cancelarReserva(activity: Activity) {
        _uiState.value.user!!.cancelarReserva(activity)
    }


}