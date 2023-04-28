package com.example.cotton_ticket.models

import java.sql.Date

data class Ticket(
    val ticket_id: Int? = null,
    val ticket_id_p: Int? = null,
    val ticket_id_t: Int? = null,
    val date_ouverture: Date? = null,
    val resolution: String? = null,
    val clos: Int? = null,
    val date_clos: Date? = null
)
