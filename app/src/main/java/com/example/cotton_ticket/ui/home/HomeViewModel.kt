package com.example.cotton_ticket.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Bienvenue sur notre page de dépôt de ticket technique et pédagogique." +
                "Cette page a été créée pour vous aider à signaler les problèmes techniques" +
                "et pédagogiques que vous rencontrez en utilisant nos services."
    }
    val text: LiveData<String> = _text
}