
package com.example.myapplication.data

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

const val CURRENT_ID=0
@Entity(tableName="aujourduitData")
data class CovidaujourduitData(
    val CaseFatalityRate: Double,
    val CaseRecoveryRates: Double,
    @Embedded(prefix = "cas_")
    val Cases: Cases,
    @Embedded(prefix = "ville_")
    val City: City,
    @Embedded(prefix = "morts_")
    val Deaths: Deaths,
    @Embedded(prefix = "doses_")
    val Doses: Doses,
    @Embedded(prefix = "tests_")
    val Tests: Tests
){
    @PrimaryKey(autoGenerate = false)
    var id:Int= CURRENT_ID
}