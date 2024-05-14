package com.dam2.proyectocliente.moker

import com.dam2.proyectocliente.models.Activity
import com.dam2.proyectocliente.models.Advertisement
import com.dam2.proyectocliente.models.Category
import com.dam2.proyectocliente.models.Chat
import com.dam2.proyectocliente.models.Message
import com.dam2.proyectocliente.models.Reservation
import com.dam2.proyectocliente.models.Role
import com.dam2.proyectocliente.models.User
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime

object Moker {
    val user = User(
        id = 1,
        email = "example@example.com",
        password = "password123",
        role = Role.PROVIDER,
        name = "John",
        surname = "Doe",
        birthDate = LocalDate.now(),
        profilePicture = "crash",
        company = "Company ABC",
        cif = "12345678A",
        address = "123 Main Street",
        activitiesOffered = arrayListOf(
            Activity(
                title = "Actividad 1",
                description = "Descripción de la actividad 1",
                picture = "photography",
                userId = 1,
                userName = "John Doe",
                date = LocalDateTime.now(),
                price = 50.0,
                location = "Ubicación 1",
                category = Category.AIRE_LIBRE,
                vacancies = 20
            ),
            Activity(
                title = "Actividad 2",
                description = "Descripción de la actividad 2",
                picture = "music",
                userId = 1,
                userName = "John Doe",
                date = LocalDateTime.now(),
                price = 50.0,
                location = "Ubicación 1",
                category = Category.AIRE_LIBRE,
                vacancies = 20
            )
        ),
        advertisements = arrayListOf(Advertisement(
            userId = 123,
            userName = "John Doe",
            userPhoto = "crash",
            title = "Venta de coche",
            description = "Se vende coche en excelente estado",
            creationDate = Instant.parse("2024-05-11T16:40:00Z"),
            location = "Ciudad"
        ))
    )

    val activity = Activity(
        title = "Actividad 1",
        description = "Descripción de la actividad 1",
        picture = "photography",
        userId = 1,
        userName = "John Doe",
        date = LocalDateTime.now(),
        price = 50.0,
        location = "Ubicación 1",
        category = Category.AIRE_LIBRE,
        vacancies = 20,
        reservations = arrayListOf(
            Reservation(
                contactId=1,
                contactName = "Pedro Pedro Pedro",
                contactPicture = "crash"
            ),
            Reservation(
                contactId=2,
                contactName = "Paco Paquito Kiko",
                contactPicture = "nina"
            ),
            Reservation(
                contactId=3,
                contactName = "Pedro No Porfavor",
                contactPicture = "coco"
            )

        )
    )

    val activities = arrayListOf(
        Activity(
            title = "Actividad 1",
            description = "Descripción de la actividad 1",
            picture = "photography",
            userId = 1,
            userName = "John Doe",
            date = LocalDateTime.now(),
            price = 50.0,
            location = "Ubicación 1",
            category = Category.AIRE_LIBRE,
            vacancies = 20
        ),
        Activity(
            title = "Actividad 2",
            description = "Descripción de la actividad 2",
            picture = "music",
            userId = 1,
            userName = "John Doe",
            date = LocalDateTime.now(),
            price = 50.0,
            location = "Ubicación 1",
            category = Category.SALUD_Y_BIENESTAR,
            vacancies = 20,
            reservations = arrayListOf(
                Reservation(
                    contactId=1,
                    contactName = "Pedro Pedro Pedro",
                    contactPicture = "crash"
                ),
                Reservation(
                    contactId=2,
                    contactName = "Paco Paquito Kiko",
                    contactPicture = "nina"
                ),
                Reservation(
                    contactId=3,
                    contactName = "Pedro No Porfavor",
                    contactPicture = "coco"
                )

            )
        )
    )


    val advertisement = Advertisement(
        userId = 123,
        userName = "John Doe",
        userPhoto = "crash",
        title = "Venta de coche",
        description = "Se vende coche en excelente estado",
        creationDate = Instant.parse("2024-05-11T16:40:00Z"),
        location = "Ciudad, o por los alrededores"
    )

    val chat = Chat(
        contactId = 1,
        contactName = "Juan el Hablador",
        contactPicture = "crash",
        messages = arrayListOf(
            Message(1, 1, 2, "¡Hola, cómo estás?", Instant.now(), true),
            Message(2, 2, 1, "¡Hola! Estoy bien, ¿y tú?", Instant.now(), true),
            Message(3,1, 2, "¡Me alegro! Yo también estoy bien", Instant.now(), true)
        ),
        newMessage = false
    )
}
