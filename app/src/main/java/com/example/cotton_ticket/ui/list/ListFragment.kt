package com.example.cotton_ticket.ui.list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.cotton_ticket.databinding.FragmentListBinding
import com.example.cotton_ticket.remote.ApiClient.apiService

class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        val listViewModel =
            ViewModelProvider(this).get(ListViewModel::class.java)
        _binding = FragmentListBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val cardView = binding.cardView

        try {
            val listTicket = apiService.lire_ticket(id_utilisateur = 1)
            for (ticket in listTicket){
                Log.d(tag, ticket.toString())
            }
        }
        catch (e : Exception){

        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}

