package com.example.cotton_ticket.ui.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.cotton_ticket.MainActivity
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
        val mail = binding.mail.text.toString()
        val pwd = binding.password.text.toString()
        Toast.makeText(requireActivity(), "Connexion réussi$mail / $pwd", Toast.LENGTH_SHORT).show()
        val intent = Intent(requireActivity(), StartActivity::class.java)
        // Récupérer des informations pour les envoyer à la page Main lors du changement d'Activity
        intent.putExtra("Texte", "Du texte")
        Log.i("LoginFragment Intent MailPwd", "$mail / $pwd")
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