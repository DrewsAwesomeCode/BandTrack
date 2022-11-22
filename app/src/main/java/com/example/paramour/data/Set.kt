package com.example.bandtrack.data

class Set (
    var setId: Int,
    val bandName:String,
    val date: java.sql.Date,
    val rating: Double,
    val venue: String,
    val city: String,
    val ticketCost: Double
)