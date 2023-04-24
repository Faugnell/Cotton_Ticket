package com.example.cotton_ticket.`class`

import java.sql.Date

data class Ticket(
    val ticket_id: Int,
    val ticket_id_p: Int,
    val ticket_id_t: Int,
    val date_ouverture: Date,
    val resolution: String,
    val clos: Int,
    val date_clos: Date
)
