package com.example.cotton_ticket.models

data class Utilisateur(
    var id_utilisateur: Int? = null,
    val mail: String? = null,
    val pass: String? = null,
    val nom: String? = null,
    val prenom: String? = null,
    val statut: String? = null,
    val admin: Int? = 0
)
