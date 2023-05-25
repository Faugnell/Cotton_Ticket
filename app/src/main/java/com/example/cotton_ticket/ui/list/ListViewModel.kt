package com.example.cotton_ticket.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cotton_ticket.models.MyGlobal

class ListViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = MyGlobal.utilisateur.id_utilisateur.toString()
    }
    val text: LiveData<String> = _text
}