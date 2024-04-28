package com.dam2.proyectocliente.model

enum class Categoria {
    TODO,
    AIRE_LIBRE,
    ARTE,
    AVENTURA,
    BARES,
    CURSOS_Y_TALLERES,
    DEPORTE,
    EXPERIENCIAS,
    GASTRONOMIA,
    MUSICA,
    OCIO,
    OFERTAS,
    SALUD_Y_BIENESTAR;

    override fun toString(): String {
        return when (this) {
            TODO -> "Todo"
            AIRE_LIBRE -> "Aire Libre"
            ARTE -> "Arte"
            AVENTURA -> "Aventura"
            BARES -> "Bares"
            CURSOS_Y_TALLERES -> "Cursos"
            DEPORTE -> "Deporte"
            EXPERIENCIAS -> "Experiencias"
            GASTRONOMIA -> "Gastronomía"
            MUSICA -> "Música"
            OCIO -> "Ocio"
            OFERTAS -> "Ofertas"
            SALUD_Y_BIENESTAR -> "Salud y Bienestar"
        }
    }



}

