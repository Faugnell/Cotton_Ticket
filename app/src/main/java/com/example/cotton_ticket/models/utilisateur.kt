package com.example.cotton_ticket.models

data class Utilisateur(
    var id_utilisateur: Int,
    val mail: String,
    val pass: String,
    val nom: String,
    val prenom: String,
    val statut: String,
    val admin: Int
)