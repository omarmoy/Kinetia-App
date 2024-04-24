package com.dam2.proyectocliente.controlador

import androidx.lifecycle.ViewModel
import com.dam2.proyectocliente.model.Actividad
import com.dam2.proyectocliente.model.Anuncio
import com.dam2.proyectocliente.model.Categoria
import com.dam2.proyectocliente.model.Contacto
import com.dam2.proyectocliente.model.Fecha
import com.dam2.proyectocliente.model.Mensaje
import com.dam2.proyectocliente.model.Usuario
import com.example.proyectocliente.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import java.time.LocalDateTime

class AppViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState

    /**
     * usuario
     */
    fun usuario(): Usuario? {
        return _uiState.value.usuario
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
    fun actividadesDestacadas(): ArrayList<Actividad> {
        val listaFiltrada = _uiState.value.actividades.filter { it.destacado }
        return ArrayList(listaFiltrada)
    }

    fun actividadesRecientes(): ArrayList<Actividad> {
        val ordenadas = _uiState.value.actividades.sortedByDescending { it.fecha.localDateTime }
        val recientes = ArrayList<Actividad>()

        for (i in 0 until minOf(ordenadas.size, 5)) {
            recientes.add(ordenadas[i])
        }

        return recientes
    }

    fun selectActividad(a: Actividad) {
        _uiState.update { e -> e.copy(actividadSeleccionada = a) }
    }

    fun selectCategoria(c: Categoria) {
        _uiState.update { e -> e.copy(categoriaSelecciononada = c) }
    }

    fun setIndiceCategoria(c: Categoria? = null) {
        val indice =
            if (c != null) {
                uiState.value.categorias.indexOf(c)
            } else {
                0
            }
        _uiState.update { e -> e.copy(indiceCategoria = indice) }
    }

    fun listaActividades(): ArrayList<Actividad> {

        val listaActividades = if (uiState.value.actividadBuscar != "") {
            resultadoBusquedaActividad()
        } else {
            uiState.value.actividades
        }

        return if (uiState.value.categoriaSelecciononada == Categoria.Todo)
            listaActividades
        else
            ArrayList(listaActividades.filter {
                it.categoria == uiState.value.categoriaSelecciononada
            })
    }

    fun setActividadBuscar(actividad: String) {
        _uiState.update { e -> e.copy(actividadBuscar = actividad) }
    }

    fun resultadoBusquedaActividad(tituloBuscar: String = uiState.value.actividadBuscar): ArrayList<Actividad> {
        return buscarActividad(uiState.value.actividades, tituloBuscar)
    }

    //actividades de usuario
    fun cargarActividadesUsuario(lista: ArrayList<Actividad>): ArrayList<Actividad> {
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

    fun selectModActividad(actividad: Actividad) {
        _uiState.update { e -> e.copy(modActividad = actividad) }
    }

    fun modificarActividad(campos: ArrayList<String>, actividad: Actividad, destacado: Boolean) {
        //TODO: imagen
        actividad.titulo = campos[0]
        actividad.precio = campos[1].toFloat()
        actividad.ubicacion = campos[2]
        actividad.categoria = stringToCategoria(campos[3])
        val fechayHora = LocalDateTime.of(
            campos[6].toInt(),
            campos[5].toInt(),
            campos[4].toInt(),
            campos[7].toInt(),
            campos[8].toInt()
        )
        actividad.fecha = Fecha(fechayHora)
        actividad.contenido = campos[9]
        actividad.contenidoPrueba = R.string.vacio //TODO BORRAR
        actividad.plazas = campos[10].toInt()
        actividad.destacado = destacado
    }

    private fun stringToCategoria(cadena: String): Categoria? {
        return when (cadena) {
            "Todo" -> Categoria.Todo
            "Aire Libre" -> Categoria.AireLibre
            "Arte" -> Categoria.Arte
            "Aventura" -> Categoria.Aventura
            "Bares" -> Categoria.Bares
            "Cursos" -> Categoria.CursosYTalleres
            "Deporte" -> Categoria.Deporte
            "Experiencias" -> Categoria.Experiencias
            "Gastronomía" -> Categoria.Gastronomia
            "Música" -> Categoria.Musica
            "Ocio" -> Categoria.Ocio
            "Ofertas" -> Categoria.Ofertas
            "Salud y Bienestar" -> Categoria.SaludYBienestar
            else -> null
        }
    }

    fun nuevaActividad(campos: ArrayList<String>){

        val fechayHora = LocalDateTime.of(
            campos[6].toInt(),
            campos[5].toInt(),
            campos[4].toInt(),
            campos[7].toInt(),
            campos[8].toInt()
        )

        val actividad = Actividad(
            titulo = campos[0],
            precio = campos[1].toFloat(),
            ubicacion = campos[2],
            categoria = stringToCategoria(campos[3]),
            fecha = Fecha(fechayHora),
            contenido = campos[9],
            plazas = campos[10].toInt(),
            destacado = campos[11].toBoolean(),
            anuncianteID = _uiState.value.usuario!!.id,
            anunciante = _uiState.value.usuario!!.nombreCompleto()
        )
        _uiState.update { e -> e.copy(nuevaActividad = actividad) }
    }

    fun publicarActividad() {
        val actividad = _uiState.value.nuevaActividad
        _uiState.value.usuario!!.addActividadOferta(actividad!!)
    }

    fun borrarActividad(actividad: Actividad){
        _uiState.value.usuario!!.eliminarActividadOferta(actividad)
    }

    /**
    MENSAJES
     */
    fun selectContacto(c: Contacto) {
        _uiState.update { e -> e.copy(contactoSeleccionado = c) }
        if (c.mensajeNuevo) {
            marcarLeido()
        }
    }

    private fun marcarLeido() {
        _uiState.value.usuario!!.marcarMensajeLeido(_uiState.value.contactoSeleccionado)
    }

    fun setMensaje(mensaje: String) {
        _uiState.update { e -> e.copy(mensajeEnviar = mensaje) }
    }

    fun enviarMensaje() {

        val mensajeNuevo = Mensaje(
            _uiState.value.usuario!!.id, Fecha.ahora(), _uiState.value.mensajeEnviar, true
        )
        _uiState.value.usuario!!.addMensaje(_uiState.value.contactoSeleccionado, mensajeNuevo)
        _uiState.update { e -> e.copy(mensajeEnviar = "") }
        //TODO("enviar mensaje")

    }

    fun filtrarMensajesNoleidos() {
        if (_uiState.value.usuario!!.tieneMensajesSinLeer()) {
            _uiState.update { e -> e.copy(filtroMensajesNoleidosActivo = true) }
        }
    }

    fun quitarFiltroMensajesNoLeidos() {
        _uiState.update { e -> e.copy(filtroMensajesNoleidosActivo = false) }
    }


    fun listaContactos(): ArrayList<Contacto> {

        val listaContactos = if (uiState.value.contactosBuscar != "") {
            resultadoBusquedaContacto()
        } else {
            uiState.value.usuario!!.contactos
        }

        return if (uiState.value.filtroMensajesNoleidosActivo)
            ArrayList(listaContactos.filter { it.mensajeNuevo })
        else
            listaContactos
    }

    fun setContactoBuscar(contacto: String) {
        _uiState.update { e -> e.copy(contactosBuscar = contacto) }
    }

    fun resultadoBusquedaContacto(contactoBuscar: String = uiState.value.contactosBuscar): ArrayList<Contacto> {
        return buscarContacto(uiState.value.usuario!!.contactos, contactoBuscar)
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
    fun addFavorito(actividad: Actividad) {
        if (!_uiState.value.usuario!!.actividadesFav.contains(actividad))
            _uiState.value.usuario!!.addActividadFav(actividad)
    }

    fun eliminarFavorito(actividad: Actividad) {
        _uiState.value.usuario!!.actividadesFav.remove(actividad)
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

    fun selectAnuncio(a: Anuncio) {
        _uiState.update { e -> e.copy(anuncioSeleccionado = a) }
    }

    fun nuevoAnuncio(titulo: String, localidad: String, contenido: String) {
        val anuncio =
            Anuncio(
                id = 100, //TODO API ID
                fotoAnunciante = _uiState.value.usuario!!.foto,
                titulo = titulo,
                contenido = contenido,
                anuncianteID = _uiState.value.usuario!!.id,
                anunciante = _uiState.value.usuario!!.nombreCompleto(),
                fecha = Fecha.ahora(),
                localidad = localidad
            )
        _uiState.update { e -> e.copy(nuevoAnuncio = anuncio) }
    }

    fun modAnuncio(titulo: String, localidad: String, contenido: String, anuncio: Anuncio) {
        anuncio.titulo = titulo
        anuncio.localidad = localidad
        anuncio.contenido = contenido
    }

    fun selectModAnuncio(anuncio: Anuncio) {
        _uiState.update { e -> e.copy(modAnuncio = anuncio) }
    }

    fun resetNuevoAnuncio() {
//        _uiState.update { e -> e.copy(nuevoAnuncio = null) }
        //TODO peta cuando llama a esta función
    }

    fun publicarAnuncio() {
        val anuncio = _uiState.value.nuevoAnuncio
        _uiState.value.usuario!!.addAnuncio(anuncio!!)
        resetNuevoAnuncio()
    }

    fun borrarAnuncio(anuncio: Anuncio) {
        _uiState.value.usuario!!.eliminarAnuncio(anuncio)
    }


    fun listaAnuncios(): ArrayList<Anuncio> {

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
        return _uiState.value.anuncios
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
    fun reservar(actividad: Actividad) {
        _uiState.value.usuario!!.reseservar(actividad)

    }

    fun cancelarReserva(actividad: Actividad) {
        _uiState.value.usuario!!.cancelarReserva(actividad)
    }


}