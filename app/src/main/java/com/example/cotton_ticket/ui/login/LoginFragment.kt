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

    private fun connexion(){
        val mail = binding.mail.text.toString()
        val password = binding.password.text.toString()
        fetchData(mail,password)
        requireActivity().run{
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    fun fetchData( username: String, password : String){
        //val url = "https://api.jeremysabbah.com/trainers/exist.php?mail=${username}&password=${password}"
        val url = "https://slam.cipecma.net/2123/vpetit/api/utilisateur/connexion.php?mail=cyriac.picart@gmail.com&password=mdp123"

        Toast.makeText(requireActivity(), url , Toast.LENGTH_SHORT).show();

        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
            //if(response.get("Response")=="False"){
            // Toast.makeText(requireActivity(), "A" ,Toast.LENGTH_SHORT).show();

            //Toast.makeText(requireActivity(), response.getString("message") ,Toast.LENGTH_SHORT).show();
            //}else {

                Toast.makeText(requireActivity(), "Connexion réussi" , Toast.LENGTH_SHORT).show();
                //Toast.makeText(requireActivity(), response.getString("id_user") , Toast.LENGTH_SHORT).show();
                val intent = Intent(requireActivity(), StartActivity::class.java)
                Log.i("LoginFragment Intent Username", response.getString("message"))
                /*requireActivity().run{
                startActivity(Intent(this, MainActivity::class.java))
                finish()
                }*/
            //}
            },
            { error ->
                Log.d("vol",error.toString())
                Toast.makeText(requireActivity(), "Connexion échouée" , Toast.LENGTH_SHORT).show();
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