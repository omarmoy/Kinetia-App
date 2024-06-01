package com.dam2.proyectocliente

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dam2.proyectocliente.repositories.LoginRepository
import com.dam2.proyectocliente.models.Activity
import com.dam2.proyectocliente.models.Advertisement
import com.dam2.proyectocliente.models.Category
import com.dam2.proyectocliente.models.Chat
import com.dam2.proyectocliente.models.Message
import com.dam2.proyectocliente.models.Reservation
import com.dam2.proyectocliente.models.Role
import com.dam2.proyectocliente.models.User
import com.dam2.proyectocliente.moker.Moker
import com.dam2.proyectocliente.network.request.Login
import com.dam2.proyectocliente.repositories.ActivityRepository
import com.dam2.proyectocliente.repositories.AdvertisementRepository
import com.dam2.proyectocliente.repositories.MessageRepository
import com.dam2.proyectocliente.repositories.SignUpRepository
import com.dam2.proyectocliente.ui.UiState
import com.dam2.proyectocliente.utils.Painter
import com.dam2.proyectocliente.utils.buscarActividad
import com.dam2.proyectocliente.utils.buscarContacto
import com.dam2.proyectocliente.utils.searchAdvertisements
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
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

    private var userPass: String by mutableStateOf("")

    var login: Boolean by mutableStateOf(false)

    private val activityRepository = ActivityRepository()
    private val messageRepository = MessageRepository()
    private val adRepository = AdvertisementRepository()
    private val loginRepository = LoginRepository()

    fun login(email: String, password: String) {
        viewModelScope.launch {
            userUiState = UserUiState.Loading
            userUiState = try {
//                val loginRepository = LoginRepository()
                val user = loginRepository.login(Login(email, password))
                if (user != null && user.role == Role.PROVIDER) {
                    setAdvertisements(loginRepository.getAdvertisements(user.id))
                    if (user.company != null || user.company != "")
                        setIsCompany(true)
                } else
                    consumerMode()
                setActivities(loginRepository.getActivities(user!!.id))
                setUser(user)
                userPass=password
                login = true
                mostrarPanelNavegacion()
                UserUiState.Success(user)
            } catch (e: Exception) {
                UserUiState.Error
            }

        }
    }

    suspend fun refresh(): Boolean{
        return try {
//            val loginRepository = LoginRepository()
            val user = loginRepository.login(Login(_uiState.value.user!!.email, userPass))
            if (user != null && user.role == Role.PROVIDER) {
                setAdvertisements(loginRepository.getAdvertisements(user.id))
            }
            setActivities(loginRepository.getActivities(user!!.id))
            setUser(Moker.user)
            setUser(user)
            false
        } catch (e: Exception) {
            false
        }
    }

    fun deleteUser(){
        viewModelScope.launch {
            loginRepository.deleteUser(_uiState.value.user!!.id)
            setUser(Moker.user)
        }
    }



    private fun setUser(user: User?) {
        _uiState.value = _uiState.value.copy(user = user)
//        _uiState.value.user = user
    }

    private fun setActivities(activities: ArrayList<Activity>) {
        _uiState.value = _uiState.value.copy(activities = activities)
    }

    private fun setAdvertisements(advertisements: ArrayList<Advertisement>) {
        _uiState.value = _uiState.value.copy(advertisements = advertisements)
    }


    /**
     * usuario
     */
    fun userCurrent(): User? {
        return _uiState.value.user
    }

    /**
     * FORMULARIO REGISTRO
     */


    fun setIsCompany(isCompany: Boolean) {
        _uiState.update { e ->
            e.copy(isCompany = isCompany)
        }
    }

    fun addFieldFormSignUp(field: String, value: String) {
        _uiState.value.formSignUp[field] = value
    }

    suspend fun verifyField(value: String): Boolean {
        val signUpRepository = SignUpRepository()
        return signUpRepository.existField(value)
    }

    suspend fun signUp(): Boolean {
        val signUpRepository = SignUpRepository()
        val result = signUpRepository.signUp(
            _uiState.value.formSignUp, _uiState.value.selectedPicture
        )

        if(result)
            setPicture(0)

        return result
    }


    /**
    ACTIVIDADES
     */
    fun actividadesDestacadas(): ArrayList<Activity> {
        val listaFiltrada = _uiState.value.activities.filter { it.featured }
        return ArrayList(listaFiltrada)
    }

    fun actividadesRecientes(): ArrayList<Activity> {
        val ordenadas = _uiState.value.activities.sortedByDescending { it.date }
        val recientes = ArrayList<Activity>()

        for (i in 0 until minOf(ordenadas.size, 5)) {
            recientes.add(ordenadas[i])
        }

        return recientes
    }

    fun selectActivity(a: Activity) {
        _uiState.update { e -> e.copy(selectedActivity = a) }
//            _uiState.value = _uiState.value.copy(selectedActivity = a)
//        _uiState.value.selectedActivity = a

    }

    fun selectCategory(c: Category) {
        _uiState.update { e -> e.copy(selectedCategory = c) }
    }

    fun setCategoryIndex(c: Category? = null) {
        val index =
            if (c != null) {
                uiState.value.categories.indexOf(c)
            } else {
                0
            }
        _uiState.update { e -> e.copy(indiceCategoria = index) }
    }

    fun activitiesList(): ArrayList<Activity> {

        val listaActividades = if (uiState.value.activitySearched != "") {
            resultadoBusquedaActividad()
        } else {
            uiState.value.activities
        }

        return if (uiState.value.selectedCategory == Category.TODO)
            listaActividades
        else
            ArrayList(listaActividades.filter {
                it.category == uiState.value.selectedCategory
            })
    }

    fun setActividadBuscar(actividad: String) {
        _uiState.update { e -> e.copy(activitySearched = actividad) }
    }

    private fun resultadoBusquedaActividad(tituloBuscar: String = uiState.value.activitySearched): ArrayList<Activity> {
        return buscarActividad(uiState.value.activities, tituloBuscar)
    }

    //actividades de usuario
    fun cargarActividadesUsuario(lista: ArrayList<Activity>): ArrayList<Activity> {
        val actividadBuscada = uiState.value.activityUserSearched

        return if (actividadBuscada == "") {
            lista
        } else {
            buscarActividad(lista, actividadBuscada)
        }
    }

    fun setActividadUsuarioBuscar(actividad: String) {
        _uiState.update { e -> e.copy(activityUserSearched = actividad) }
    }

    fun selectModActividad(activity: Activity) {
        _uiState.update { e -> e.copy(modActivity = activity) }
    }

    fun editActivity(
        fields: ArrayList<String>,
        activity: Activity,
        featured: Boolean,
        picture: Int
    ) {
        val editedActivity = Activity(
            id = activity.id,
            title = fields[0],
            description = fields[9],
            picture = if (picture != 0) Painter.getActivityPictureName(picture) else activity.picture,
            userId = activity.userId,
            userName = activity.userName,
            date = LocalDateTime.of(
                fields[6].toInt(),
                fields[5].toInt(),
                fields[4].toInt(),
                fields[7].toInt(),
                fields[8].toInt()
            ),
            createdAt = activity.createdAt,
            price = fields[1].toDouble(),
            location = fields[2],
            category = stringToCategory(fields[3]) ?: activity.category,
            featured = featured,
            vacancies = fields[10].toInt(),
            availableVacancies = activity.availableVacancies,
            reservations = activity.reservations.clone() as ArrayList<Reservation>
        )

        viewModelScope.launch {
            val result = activityRepository.edit(editedActivity)
            if (result)
                updateActivity(fields, activity, featured, picture)
        }
    }

    private fun updateActivity(
        campos: ArrayList<String>,
        activity: Activity,
        destacado: Boolean,
        picture: Int
    ) {
        activity.title = campos[0]
        activity.price = campos[1].toDouble()
        activity.location = campos[2]
        activity.category = stringToCategory(campos[3])!!
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
        if (picture != 0) {
            activity.picture = Painter.getActivityPictureName(picture)
            setPicture(0)
        }
    }

    private fun stringToCategory(cadena: String): Category? {
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

    fun newActivity(fields: ArrayList<String>) {

        val dateTime = LocalDateTime.of(
            fields[6].toInt(),
            fields[5].toInt(),
            fields[4].toInt(),
            fields[7].toInt(),
            fields[8].toInt()
        )

        val activity = Activity(
            title = fields[0],
            price = fields[1].toDouble(),
            location = fields[2],
            category = stringToCategory(fields[3])!!,
            date = dateTime,
            description = fields[9],
            vacancies = fields[10].toInt(),
            featured = fields[11].toBoolean(),
            userId = _uiState.value.user!!.id,
            userName = _uiState.value.user!!.fullName(),
            picture = Painter.getActivityPictureName(_uiState.value.selectedPicture)
        )
//        _uiState.update { e -> e.copy(newActivity = activity) }
        _uiState.value.newActivity = activity
    }

    fun postActivity() {
        viewModelScope.launch {
            val activity = _uiState.value.newActivity
            val result = activityRepository.add(activity!!)
            activity.id = result
            _uiState.value.user!!.addActividadOferta(activity!!)
            setPicture(0)
//            _uiState.update { e -> e.copy(newActivity = null) }
        }
    }

    fun deleteActivity(activity: Activity) {
        viewModelScope.launch {
            val result = activityRepository.delete(activity.id!!)
            if (result)
                _uiState.value.user!!.deleteActivityOffered(activity)
        }
    }

    /**
    MENSAJES
     */
    fun selectContact(c: Chat) {
        _uiState.update { e -> e.copy(selectedChat = c) }
        if (c.newMessage) {
            messageRead()
        }
    }

    private fun messageRead() {
        _uiState.value.user!!.messageRead(_uiState.value.selectedChat)
        viewModelScope.launch {
            val chat = _uiState.value.selectedChat
            for (message in chat.messages) {
                if (message.sender != _uiState.value.user!!.id && !message.isRead)
                    messageRepository.messageRead(message.id!!)
            }
        }
    }

    fun setMessage(mensaje: String) {
        _uiState.update { e -> e.copy(messageSend = mensaje) }
    }

    fun sendMessage(recipientId: Long) {

        val newMessage = Message(
            sender = _uiState.value.user!!.id,
            recipient = recipientId,
            content = _uiState.value.messageSend
        )
        _uiState.value.user!!.addMensaje(_uiState.value.selectedChat, newMessage)
        _uiState.update { e -> e.copy(messageSend = "") }

        viewModelScope.launch {
            messageRepository.sendMessage(newMessage)
        }

    }

    fun deleteContact(chat: Chat) {
        viewModelScope.launch {
            messageRepository.deleteContact(_uiState.value.user!!.id, chat.contactId)
        }

        val user = _uiState.value.user!!
        user.chats.remove(chat)
        _uiState.update { e -> e.copy(user = user) }

    }

    fun filtrarMensajesNoleidos() {
        if (_uiState.value.user!!.tieneMensajesSinLeer()) {
            _uiState.update { e -> e.copy(filterUnreadMessagesActive = true) }
        }
    }

    fun quitarFiltroMensajesNoLeidos() {
        _uiState.update { e -> e.copy(filterUnreadMessagesActive = false) }
    }


    fun listaContactos(): ArrayList<Chat> {

        val listaContactos = if (uiState.value.contactSearched != "") {
            resultadoBusquedaContacto()
        } else {
            uiState.value.user!!.chats
        }

        return if (uiState.value.filterUnreadMessagesActive)
            ArrayList(listaContactos.filter { it.newMessage })
        else
            listaContactos
    }

    fun setContactoBuscar(contacto: String) {
        _uiState.update { e -> e.copy(contactSearched = contacto) }
    }

    private fun resultadoBusquedaContacto(contactoBuscar: String = uiState.value.contactSearched): ArrayList<Chat> {
        return buscarContacto(uiState.value.user!!.chats, contactoBuscar)
    }


    /**
    PANEL NAVEGACIÓN
     */

    fun mostrarPanelNavegacion() {
        _uiState.update { e -> e.copy(showNavigationPanel = true) }
    }

    fun ocultarPanelNavegacion() {
        _uiState.update { e -> e.copy(showNavigationPanel = false) }
    }

    fun cambiarBotonNav(botonPulsado: Int) {
        val nuevoEstadoBotones = arrayListOf<Boolean>()
        for (i in 0 until _uiState.value.buttonsNav.size) {
            if (i == botonPulsado)
                nuevoEstadoBotones.add(true)
            else
                nuevoEstadoBotones.add(false)
        }
        _uiState.update { e -> e.copy(buttonsNav = nuevoEstadoBotones) }
    }

    /**
    FAVORITOS
     */
    fun addFav(activity: Activity) {
        if (!_uiState.value.user!!.activitiesFav.contains(activity)) {
            _uiState.value.user!!.addActividadFav(activity)

            viewModelScope.launch {
                activityRepository.addFav(_uiState.value.user!!.id, activity.id!!)
            }

        }
    }

    fun deleteFav(activity: Activity) {
        _uiState.value.user!!.activitiesFav.remove(activity)
        viewModelScope.launch {
            activityRepository.deleteFav(_uiState.value.user!!.id, activity.id!!)
        }
    }

    /**
    FUNCIONALIDADES
     */
    fun changeMode(): Boolean {
        val mode = !uiState.value.proMode
        _uiState.update { e -> e.copy(proMode = mode) }
        return mode
    }

    private fun consumerMode() {
        _uiState.update { e -> e.copy(proMode = false) }
    }

    fun resetMode() {
        _uiState.update { e -> e.copy(proMode = true) }
    }

    /**
    ANUNCIOS
     */

    fun selectAnuncio(a: Advertisement) {
        _uiState.update { e -> e.copy(advertisementSeleccionado = a) }
    }

    fun newAdvertisement(title: String, location: String, description: String) {
        val advertisement =
            Advertisement(
                id = 100,
                userPhoto = _uiState.value.user!!.profilePicture,
                title = title,
                description = description,
                userId = _uiState.value.user!!.id,
                userName = _uiState.value.user!!.fullName(),
                location = location
            )
        _uiState.update { e -> e.copy(newAdvertisement = advertisement) }
    }


    fun selectModAdvertisement(advertisement: Advertisement) {
        _uiState.update { e -> e.copy(modAdvertisement = advertisement) }
    }

//    fun resetNuevoAnuncio() {
//        _uiState.update { e -> e.copy(nuevoAnuncio = null) }
//    }

    fun postAdvertisement() {
        val advertisement = _uiState.value.newAdvertisement
        _uiState.value.user!!.addAnuncio(advertisement!!)
        viewModelScope.launch {
            advertisement.id = adRepository.post(advertisement)

        }
//        resetNuevoAnuncio()
    }

    fun editAdvertisement(
        titulo: String,
        localidad: String,
        contenido: String,
        advertisement: Advertisement
    ) {
        advertisement.title = titulo
        advertisement.location = localidad
        advertisement.description = contenido
        viewModelScope.launch {
            adRepository.edit(advertisement)
        }
    }

    fun deleteAdvertisement(advertisement: Advertisement) {
        _uiState.value.user!!.eliminarAnuncio(advertisement)
        viewModelScope.launch {
            adRepository.delete(advertisement.id!!)
        }
    }


    fun advertisementsList(): ArrayList<Advertisement> {
        return if (uiState.value.advertisementSearched != "") {
            resultSearchAdvertisements()
        } else {
            uiState.value.advertisements
        }
    }

    fun setAdvertisementSearched(ad: String) {
        _uiState.update { e -> e.copy(advertisementSearched = ad) }
    }

    private fun resultSearchAdvertisements(titleSearched: String = uiState.value.advertisementSearched): ArrayList<Advertisement> {
        return searchAdvertisements(uiState.value.advertisements, titleSearched)
    }


    /**
    RESERVAS CONSUMIDOR
     */
    fun reserveActivity(activity: Activity) {
        _uiState.value.user!!.reserveActivity(activity)
        activity.consumerAddReservation()
        viewModelScope.launch {
            activityRepository.reserve(_uiState.value.user!!.id, activity.id!!)
        }
    }

    fun cancelReservation(activity: Activity) {
        _uiState.value.user!!.cancelReservation(activity)
        activity.consumerCancelReservation()
        viewModelScope.launch {
            activityRepository.cancelReserve(_uiState.value.user!!.id, activity.id!!)
        }
    }

    /**
    RESERVAS PRO
     */
    fun cancelReservation(activity: Activity, reservation: Reservation) {
        viewModelScope.launch {
            val result = activityRepository.cancelReserve(reservation.contactId, activity.id!!)
            if (result) {
                val user = _uiState.value.user!!
                user.cancelReservation(activity, reservation)
                _uiState.update { e -> e.copy(user = user) }
            }
        }
    }


    /**
     * CHATS NUEVO
     */
    fun createChatIfNoExist(reservation: Reservation) {
        val chat = Chat(
            contactId = reservation.contactId,
            contactName = reservation.contactName,
            contactPicture = reservation.contactPicture
        )
        createChatIfNoExist(chat)
    }

    fun createChatIfNoExist(advertisement: Advertisement) {
        val chat = Chat(
            contactId = advertisement.userId,
            contactName = advertisement.userName,
            contactPicture = advertisement.userPhoto
        )
        createChatIfNoExist(chat)
    }

    fun createChatIfNoExist(activity: Activity) {
        val chat = Chat(
            contactId = activity.userId,
            contactName = activity.userName,
            contactPicture = ""
        )
        createChatIfNoExist(chat)
    }

    private fun createChatIfNoExist(chat: Chat) {
        if (!_uiState.value.user!!.chats.contains(chat)) {
            _uiState.value.user!!.addChat(chat)
            selectContact(chat)
        } else {
            val idx = _uiState.value.user!!.chats.indexOf(chat)
            selectContact(_uiState.value.user!!.chats[idx])
        }
    }


    /**
    PICTURSE
     */
    fun setPicture(picture: Int) {
        _uiState.update { e -> e.copy(selectedPicture = picture) }
    }

}