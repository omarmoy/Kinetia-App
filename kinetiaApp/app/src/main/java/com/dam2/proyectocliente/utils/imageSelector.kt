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

fun getProfilePictureName(drawableId: Int): String {
    return when (drawableId) {
        R.drawable.crunch -> "crunch"
        R.drawable.coco -> "coco"
        R.drawable.cortex -> "cortex"
        R.drawable.crash -> "crash"
        R.drawable.gin -> "gin"
        R.drawable.nina -> "nina"
        R.drawable.tawna -> "tawna"
        R.drawable.tropy -> "tropy"
        else -> "nofoto"
    }
}

fun getActivityPictureName(drawableId: Int): String {
    return when (drawableId) {
        R.drawable.tech -> "tech"
        R.drawable.photography -> "photography"
        R.drawable.painting -> "painting"
        R.drawable.music -> "music"
        R.drawable.lifestyle -> "lifestyle"
        R.drawable.gaming -> "gaming"
        R.drawable.film -> "film"
        R.drawable.fashion -> "fashion"
        R.drawable.drawing -> "drawing"
        R.drawable.design -> "design"
        R.drawable.culinary -> "culinary"
        R.drawable.crafts -> "crafts"
        R.drawable.business -> "business"
        R.drawable.architecture -> "architecture"
        else -> "noimagen"
    }
}