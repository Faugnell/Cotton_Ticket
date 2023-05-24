package com.example.cotton_ticket.models

import com.google.gson.annotations.SerializedName

data class Ticket(
    @SerializedName(value = "id_ticket")
    val idTicket: Int,
    @SerializedName(value = "id_utilisateur")
    val idUtilisateur: Int,
    @SerializedName(value = "date_ouverture")
    val dateOuverture: String,
    @SerializedName(value = "resolution")
    val resolution: String?,
    @SerializedName(value = "clos")
    val clos: Int,
    @SerializedName(value = "date_clos")
    val dateClos: String?
)