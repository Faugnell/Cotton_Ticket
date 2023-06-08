package com.example.cotton_ticket.models

import android.app.Application

class MyGlobal : Application() {
    companion object {
        var utilisateur: Utilisateur = Utilisateur(id_utilisateur = 0,
            mail = "",
            pass = "",
            nom = "",
            prenom = "",
            statut = "",
            admin = 0)
        var ticket: Ticket = Ticket(
            id_ticket = 0,
            id_ticket_p = 0,
            id_ticket_t = 0,
            date_ouverture = "",
            resolution = "",
            clos = false,
            date_clos = ""
        )
    }
}