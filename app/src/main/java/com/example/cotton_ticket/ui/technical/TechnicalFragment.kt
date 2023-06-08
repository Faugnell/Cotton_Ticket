package com.example.cotton_ticket.ui.technical

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.android.volley.RequestQueue
import com.android.volley.toolbox.BasicNetwork
import com.android.volley.toolbox.DiskBasedCache
import com.android.volley.toolbox.HurlStack
import com.android.volley.toolbox.JsonObjectRequest
import com.example.cotton_ticket.R
import com.example.cotton_ticket.databinding.FragmentTechnicalBinding
import com.example.cotton_ticket.models.MyGlobal
import org.json.JSONException
import org.json.JSONObject

class TechnicalFragment : Fragment() {

    companion object{
        fun newInstance() = TechnicalFragment()
    }
    private var _binding: FragmentTechnicalBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var viewModel: TechnicalViewModel
    lateinit var requestQueue: RequestQueue

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        val technicalViewModel =
                ViewModelProvider(this)[TechnicalViewModel::class.java]
        _binding = FragmentTechnicalBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val appnetwork = BasicNetwork(HurlStack())
        val appcach = DiskBasedCache(requireActivity().cacheDir, 1024 * 1024)
        requestQueue = RequestQueue(appcach, appnetwork).apply {
            start()
        }
        val spinner: Spinner = binding.spinner
        ArrayAdapter.createFromResource(
            requireActivity(),
            R.array.type_array,
            android.R.layout.simple_spinner_item
        ).also {adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }

        binding.buttonCommentaire.setOnClickListener { createTicketT() }

        return root
    }

    private fun createTicketT() {
        val editTextCommentaire = binding.commentaire.text.toString()
        val idUtilisateur = MyGlobal.utilisateur.id_utilisateur
        val spinner: Spinner = binding.spinner
        val typeTechnique = spinner.onItemSelectedListener.toString()
        var idTypeTechnique: Int = 1
        when (typeTechnique){
                "Defaut" -> idTypeTechnique = 1
                "Espace collaboratif" -> idTypeTechnique = 2
                "Solution erp" -> idTypeTechnique = 3
                "Plateforme elearning" -> idTypeTechnique = 4
        }
        sendPutRequest(editTextCommentaire, idUtilisateur, idTypeTechnique)
    }

    private fun sendPutRequest(editTextCommentaire: String, idUtilisateur: Int, idTypeTechnique: Int) {
        val url = "https://slam.cipecma.net/2123/vpetit/api/ticket-technique/ajouter.php"
        val jsonObject = JSONObject()
        try {
            jsonObject.put("commentaire", editTextCommentaire)
            idUtilisateur?.let { jsonObject.put("id_utilisateur", it.toString()) }
            jsonObject.put("id_type", idTypeTechnique)
        } catch (e: JSONException) {
            Log.i("json_error", "$e")
        }

        val putRequest: JsonObjectRequest =
            object : JsonObjectRequest(
                Method.PUT, url, jsonObject,
                { response ->
                    Log.i("response", "$response")
                    Toast.makeText(requireActivity(), "Le ticket a été créé avec succès", Toast.LENGTH_SHORT).show()
                },
                { error ->
                    Log.i("error: ", "$error")
                    Toast.makeText(requireActivity(), "Une erreur s'est produite lors de la création du ticket", Toast.LENGTH_SHORT).show()
                }
            ) {
                override fun getHeaders(): MutableMap<String, String> {
                    val headers: MutableMap<String, String> = HashMap()
                    headers["Content-Type"] = "application/json"
                    headers["Accept"] = "application/json"
                    return headers
                }
            }

        requestQueue.add(putRequest)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}