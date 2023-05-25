package com.example.cotton_ticket.models

import android.app.Application

class MyGlobal : Application() {
    companion object {
        var utilisateur : Utilisateur = Utilisateur()
    }
}