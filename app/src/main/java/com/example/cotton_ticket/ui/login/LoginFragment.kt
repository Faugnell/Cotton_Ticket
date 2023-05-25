package com.example.cotton_ticket.ui.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.BasicNetwork
import com.android.volley.toolbox.DiskBasedCache
import com.android.volley.toolbox.HurlStack
import com.android.volley.toolbox.JsonObjectRequest
import com.example.cotton_ticket.MainActivity
import com.example.cotton_ticket.StartActivity
import com.example.cotton_ticket.databinding.FragmentLoginBinding
import com.example.cotton_ticket.models.MyGlobal

@Suppress("DEPRECATION")
class LoginFragment : Fragment() {

    companion object {
        fun newInstance() = LoginFragment()
    }

    private var _binding: FragmentLoginBinding? = null

    private val binding get() = _binding!!
    private lateinit var viewModel: LoginViewModel
    // The internal MutableLiveData String that stores the most recent response
    private val _response = MutableLiveData<String>()

    // The external immutable LiveData for the response String
    val response: LiveData<String>
        get() = _response

    lateinit var requestQueue: RequestQueue

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val appnetwork = BasicNetwork(HurlStack())
        val appcache = DiskBasedCache(requireActivity().cacheDir, 1024 * 1024) // 1MB cap
        requestQueue = RequestQueue(appcache, appnetwork).apply {
            start()
        }
        binding.buttonConnexion.setOnClickListener { connexion() }
        return root
    }

    private fun connexion() {
        val mail = binding.mail.text.toString()
        val password = binding.password.text.toString()

        fetchData(mail, password)
    }

    fun fetchData(username: String, password: String) {
        //val url = "https://slam.cipecma.net/2123/vpetit/api/utilisateur/connexion.php?mail=$username&password=$password"
        val url = "https://slam.cipecma.net/2123/vpetit/api/utilisateur/connexion.php?mail=victor.petit@gmail.com&password=mdp123!!"

        Log.i("LoginFragment URL", url)

        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->

                val idUtilisateur = response.optInt("id_utilisateur")
                MyGlobal.utilisateur.id_utilisateur = idUtilisateur
                val message = response.optString("message")
                // Gérer la réponse en cas de succès
                if (idUtilisateur != 0 && message.isNotEmpty()) {
                    Toast.makeText(requireActivity(), "Connexion réussie", Toast.LENGTH_SHORT).show()
                    val intent = Intent(requireActivity(), StartActivity::class.java)
                    requireActivity().run {
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    }
                } else {
                    // Gérer la réponse en cas d'échec
                    Toast.makeText(requireActivity(), "Connexion échouée", Toast.LENGTH_SHORT).show()
                }
            },
            { error ->
                // Gérer l'erreur en cas d'échec de la requête
                Log.d("vol", error.toString())
                Toast.makeText(requireActivity(), "Connexion échouée", Toast.LENGTH_SHORT).show()
            }
        )
        requestQueue.add(jsonObjectRequest)
    }
    @Deprecated("Deprecated in Java")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        // TODO: Use the ViewModel

    }

}