package com.example.cotton_ticket.ui.pedagogy

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cotton_ticket.R

class PedagogyFragment : Fragment() {

    companion object {
        fun newInstance() = PedagogyFragment()
    }

    private lateinit var viewModel: PedagogyViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_pedagogy, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PedagogyViewModel::class.java)
        // TODO: Use the ViewModel
    }

}