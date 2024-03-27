package com.dam2.proyectocliente.controlador

import androidx.lifecycle.ViewModel
import com.dam2.proyectocliente.model.Actividad
import com.dam2.proyectocliente.model.Anuncio
import com.dam2.proyectocliente.model.Contacto
import com.dam2.proyectocliente.model.Fecha
import com.dam2.proyectocliente.model.Mensaje
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class AppViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState

    fun selectActividad(a: Actividad) {
        _uiState.update { e -> e.copy(actividadSeleccionada = a) }
    }

    fun selectAnuncio(a: Anuncio) {
        _uiState.update { e -> e.copy(anuncioSeleccionado = a) }
    }

    fun selectContacto(c: Contacto) {
        _uiState.update { e -> e.copy(contactoSeleccionado = c) }
        if (c.mensajeNuevo) {
            marcarLeido()
        }
    }

    /**
     * MENSAJES
     */

    private fun marcarLeido() {
        /*
        val usuario = _uiState.value.usuario
        val indice = usuario.contactos.indexOf(contacto)
        usuario.contactos[indice].mensajeNuevo = false
        _uiState.update { e -> e.copy(usuario = usuario) }

         */
/*
        val contactos = _uiState.value.contactos
        val indice = contactos.indexOf(_uiState.value.contactoSeleccionado)
        contactos[indice].mensajeNuevo = false
        _uiState.update { e-> e.copy(contactos = contactos) }

 */
        _uiState.value.mensajeLeido(_uiState.value.contactoSeleccionado)
    }

    fun setMensaje(mensaje: String) {
        _uiState.update { e -> e.copy(mensajeEnviar = mensaje) }
    }

    fun enviarMensaje() {

        val mensajeNuevo = Mensaje(
            _uiState.value.id, Fecha.ahora(), _uiState.value.mensajeEnviar, true
        )
        _uiState.value.addMensaje(_uiState.value.contactoSeleccionado, mensajeNuevo)
        _uiState.update { e -> e.copy(mensajeEnviar = "") }
        //TODO("enviar mensaje")

    }


    /**
     *  PANEL NAVEGACIÓN
     */

    fun mostrarPanelNavegacion() {
        _uiState.update { e -> e.copy(mostrarPanelNavegacion = true) }
    }

    fun ocultarPanelNavegacion() {
        _uiState.update { e -> e.copy(mostrarPanelNavegacion = false) }
    }


}