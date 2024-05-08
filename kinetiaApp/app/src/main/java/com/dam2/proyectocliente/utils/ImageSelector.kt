package com.dam2.proyectocliente.utils

import com.example.proyectocliente.R

fun selectorProfilePicture(imageString: String): Int {
    return when (imageString) {
        "crunch" -> R.drawable.crunch
        "coco" -> R.drawable.coco
        "cortex" -> R.drawable.cortex
        "crash" -> R.drawable.crash
        "gin" -> R.drawable.gin
        "nina" -> R.drawable.nina
        "tawna" -> R.drawable.tawna
        "tropy" -> R.drawable.tropy
        else ->  R.drawable.nofoto
    }
}

fun selectorActivityPicture(activityString: String): Int {
    return when (activityString) {
        "tech" -> R.drawable.tech
        "photography" -> R.drawable.photography
        "painting" -> R.drawable.painting
        "music" -> R.drawable.music
        "lifestyle" -> R.drawable.lifestyle
        "gaming" -> R.drawable.gaming
        "film" -> R.drawable.film
        "fashion" -> R.drawable.fashion
        "drawing" -> R.drawable.drawing
        "design" -> R.drawable.design
        "culinary" -> R.drawable.culinary
        "crafts" -> R.drawable.crafts
        "business" -> R.drawable.business
        "architecture" -> R.drawable.architecture
        else -> R.drawable.noimagen
    }
}