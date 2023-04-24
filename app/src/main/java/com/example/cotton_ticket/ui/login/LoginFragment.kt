package com.example.cotton_ticket.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.cotton_ticket.MainActivity
import com.example.cotton_ticket.R
import com.example.cotton_ticket.StartActivity
import com.example.cotton_ticket.databinding.FragmentLoginBinding

@Suppress("DEPRECATION")
class LoginFragment : Fragment() {

    companion object {
        fun newInstance() = LoginFragment()
    }

    private var _binding: FragmentLoginBinding? = null

    private val binding get() = _binding!!
    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val root: View = binding.root
        binding.buttonConnexion.setOnClickListener { connexion() }
        return root
    }

    private fun connexion(){
        Toast.makeText(requireActivity(), "Connexion réussi", Toast.LENGTH_SHORT).show()
        val intent = Intent(requireActivity(), StartActivity::class.java)

        requireActivity().run{
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
    @Deprecated("Deprecated in Java")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        // TODO: Use the ViewModel

    }

}