package com.example.cotton_ticket.ui.list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.BasicNetwork
import com.android.volley.toolbox.DiskBasedCache
import com.android.volley.toolbox.HurlStack
import com.android.volley.toolbox.JsonObjectRequest
import com.example.cotton_ticket.databinding.FragmentListBinding
import com.example.cotton_ticket.models.MyGlobal

class ListFragment() : Fragment() {

    private var _binding: FragmentListBinding? = null


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    lateinit var requestQueue: RequestQueue

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?,
    ): View {
        val ListViewModel =
            ViewModelProvider(this)[ListViewModel::class.java]
        _binding = FragmentListBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val appnetwork = BasicNetwork(HurlStack())
        val appcache = DiskBasedCache(requireActivity().cacheDir, 1024 * 1024)
        requestQueue = RequestQueue(appcache, appnetwork).apply {
            start()
        }
        val textViewTicket: TextView = binding.textViewTicket

        try {
            fetchData(MyGlobal.utilisateur.id_utilisateur)
            ListViewModel.text.observe(viewLifecycleOwner) {
                textViewTicket.text = it
            }
        }
        catch (e : Exception){
            textViewTicket.text = "${e.message}"
        }

        return root
    }

    fun fetchData(idUtilisateur: Int?) {
        val url = "https://slam.cipecma.net/2123/vpetit/api/ticket/lire.php?id_utilisateur=$idUtilisateur"

        Log.i("ListFragment URL", url)

        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->

                if(response != null){
                    Toast.makeText(requireActivity(), "Récupération réussie", Toast.LENGTH_SHORT).show()
                } else {
                    // Gérer la réponse en cas d'échec
                    Toast.makeText(requireActivity(), "Récupération échouée", Toast.LENGTH_SHORT).show()
                }
            },
            { error ->
                // Gérer l'erreur en cas d'échec de la requête
                Log.d("vol", error.toString())
                Toast.makeText(requireActivity(), "Récupération échouée", Toast.LENGTH_SHORT).show()
            }
        )
        requestQueue.add(jsonObjectRequest)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}

