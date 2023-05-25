package com.example.cotton_ticket.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.cotton_ticket.databinding.FragmentListBinding
import com.example.cotton_ticket.ui.home.HomeViewModel

class ListFragment() : Fragment() {

    private var _binding: FragmentListBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?,
    ): View {
        val ListViewModel =
            ViewModelProvider(this)[HomeViewModel::class.java]
        _binding = FragmentListBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val textViewTicket: TextView = binding.textViewTicket
        ListViewModel.text.observe(viewLifecycleOwner) {
            textViewTicket.text = it
        }
        try {

        }
        catch (e : Exception){
            textViewTicket.text = "${e.message}"
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}

