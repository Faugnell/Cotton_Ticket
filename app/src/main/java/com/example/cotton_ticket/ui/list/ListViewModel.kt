package com.example.cotton_ticket.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ListViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Ici s'affiche la liste de tout les tickets"
    }
    val text: LiveData<String> = _text
}