package com.example.cotton_ticket.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.cotton_ticket.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    //private val navController = findNavController()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this)[HomeViewModel::class.java]

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        binding.buttonTechnique.setOnClickListener { ajouterTechnique() }
        binding.buttonPedagogique.setOnClickListener { ajouterPedagogique() }

        return root
    }

    private fun ajouterPedagogique() {
        //navController.navigate(R.id.action_nav_home_to_nav_peda)
    }

    private fun ajouterTechnique() {
        //navController.navigate(R.id.action_nav_home_to_nav_technique)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}