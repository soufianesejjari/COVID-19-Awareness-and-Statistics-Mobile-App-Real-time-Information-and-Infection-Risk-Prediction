package com.example.myapplication.data

import androidx.room.Embedded

data class City(
    @Embedded(prefix = "Casablanca_")

    val Casablanca: Casablanca,
    @Embedded(prefix = "Fès_")

    val Fès: Fès,
    @Embedded(prefix = "Meknès_")

    val Marrakech: Marrakech,
    val Meknès: Meknès,
    @Embedded(prefix = "Tétouan_")

    val Tétouan: Tétouan,

)