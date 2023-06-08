package com.example.cotton_ticket.models

data class Ticket(
    val id_ticket: Int,
    val id_ticket_p: Int,
    val id_ticket_t: Int,
    val date_ouverture: String,
    val resolution: String,
    val clos: Boolean,
    val date_clos: String
)