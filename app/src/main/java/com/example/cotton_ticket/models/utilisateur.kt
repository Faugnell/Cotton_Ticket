package com.example.cotton_ticket.models

import com.google.gson.annotations.SerializedName

data class utilisateur(
    val id_utilisateur: Int? = null,
    @SerializedName("mail")
    val mail: String? = null,
    @SerializedName("pass")
    val pass: String? = null,
    val nom: String? = null,
    val prenom: String? = null,
    val statut: String? = null,
    val admin: Int? = 0
)
