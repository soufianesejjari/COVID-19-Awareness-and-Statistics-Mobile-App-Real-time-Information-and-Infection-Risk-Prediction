package com.example.myapplication.types.ApisTypes

import java.io.Serializable

class TypecovidData(
    var ID: Int,
    var Nom: String,
    var dateDebut: String,
    var origine: String,

    var symptoms: String,
    var infectation: String,
    var q1: String,
    var q2: String,
    var q3: String,
    var q4: String,
    var q5: String,
    var q6: String,


    ) : Serializable



