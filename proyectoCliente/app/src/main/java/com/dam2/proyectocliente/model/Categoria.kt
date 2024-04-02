package com.dam2.proyectocliente.model

enum class Categoria {
    Todo,
    AireLibre,
    Arte,
    Aventura,
    Bares,
    CursosYTalleres,
    Deporte,
    Experiencias,
    Gastronomia,
    Musica,
    Ocio,
    Ofertas,
    SaludYBienestar;

    override fun toString(): String {
        return when (this) {
            Todo -> "Todo"
            AireLibre -> "Aire Libre"
            Arte -> "Arte"
            Aventura -> "Aventura"
            Bares -> "Bares"
            CursosYTalleres -> "Cursos"
            Deporte -> "Deporte"
            Experiencias -> "Experiencias"
            Gastronomia -> "Gastronomía"
            Musica -> "Música"
            Ocio -> "Ocio"
            Ofertas -> "Ofertas"
            SaludYBienestar -> "Salud y Bienestar"
        }
    }
}

