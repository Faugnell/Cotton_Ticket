package com.example.cotton_ticket.models

import java.sql.Date

data class Ticket(
    val id_utilisateur: Int? = null,
    val id_ticket: Int? = null,
    val date_ouverture: Date? = null,
    val resolution: String? = null,
    val clos: Int? = null,
    val date_clos: Date? = null
)