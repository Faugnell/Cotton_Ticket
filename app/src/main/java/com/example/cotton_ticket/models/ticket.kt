package com.example.cotton_ticket.models

import com.google.gson.annotations.SerializedName

data class Ticket(
    @SerializedName("id_ticket")
    val idTicket: Int,
    @SerializedName("id_utilisateur")
    val idUtilisateur: Int,
    @SerializedName("date_ouverture")
    val dateOuverture: String,
    @SerializedName("resolution")
    val resolution: String?,
    @SerializedName("clos")
    val clos: Int,
    @SerializedName("date_clos")
    val dateClos: String?
)