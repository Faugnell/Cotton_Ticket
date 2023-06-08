package com.example.cotton_ticket.ui.pedagogy

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.android.volley.RequestQueue
import com.android.volley.toolbox.BasicNetwork
import com.android.volley.toolbox.DiskBasedCache
import com.android.volley.toolbox.HurlStack
import com.android.volley.toolbox.JsonObjectRequest
import com.example.cotton_ticket.databinding.FragmentPedagogyBinding
import com.example.cotton_ticket.models.MyGlobal
import org.json.JSONException
import org.json.JSONObject

class PedagogyFragment : Fragment() {

    companion object {
        fun newInstance() = PedagogyFragment()
    }

    private var _binding: FragmentPedagogyBinding? = null


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var viewModel: PedagogyViewModel
    lateinit var requestQueue: RequestQueue

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val PedagogyViewModel =
            ViewModelProvider(this)[PedagogyViewModel::class.java]
        _binding = FragmentPedagogyBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val appnetwork = BasicNetwork(HurlStack())
        val appcach = DiskBasedCache(requireActivity().cacheDir, 1024 * 1024)
        requestQueue = RequestQueue(appcach, appnetwork).apply {
            start()
        }

        binding.buttonQuestion.setOnClickListener { createTicketP() }

        return root
    }

    private fun createTicketP(){
        val editTextRep1 = binding.idReponse1.text.toString()
        val editTextRep2 = binding.idReponse2.text.toString()
        val editTextRep3 = binding.idReponse3.text.toString()
        val editTextRep4 = binding.idReponse4.text.toString()
        val editTextRep5 = binding.idReponse5.text.toString()
        val editTextRep6 = binding.idReponse6.text.toString()
        val editTextRep7 = binding.idReponse7.text.toString()
        val idUtilisateur = MyGlobal.utilisateur.id_utilisateur
        sendPutRequest(editTextRep1, editTextRep2, editTextRep3, editTextRep4, editTextRep5, editTextRep6, editTextRep7, idUtilisateur)
    }

    private fun sendPutRequest(
        editTextRep1: String,
        editTextRep2: String,
        editTextRep3: String,
        editTextRep4: String,
        editTextRep5: String,
        editTextRep6: String,
        editTextRep7: String,
        idUtilisateur: Int
    ) {
        val url = "https://slam.cipecma.net/2123/vpetit/api/ticket-pedagogique/ajouter.php"
        val jsonObject = JSONObject()
        try {
            jsonObject.put("rep1", editTextRep1)
            jsonObject.put("rep2", editTextRep2)
            jsonObject.put("rep3", editTextRep3)
            jsonObject.put("rep4", editTextRep4)
            jsonObject.put("rep5", editTextRep5)
            jsonObject.put("rep6", editTextRep6)
            jsonObject.put("rep7", editTextRep7)
            jsonObject.put("id_utilisateur", idUtilisateur)
        } catch (e: JSONException) {
            // Gérer l'exception
            Log.i("json_error: ", "$e")
        }

        val putRequest: JsonObjectRequest =
            object : JsonObjectRequest(
                Method.PUT, url, jsonObject,
                { response ->
                    // Succès de la requête
                    Log.i("response: ", "$response")
                    Toast.makeText(requireActivity(), "Le ticket a été créé avec succès", Toast.LENGTH_SHORT).show()
                },
                { error ->
                    // Erreur de la requête
                    Log.i("error: ", "$error")
                    Toast.makeText(requireActivity(), "Une erreur s'est produite lors de la création du ticket", Toast.LENGTH_SHORT).show()
                }
            ) {
                override fun getHeaders(): Map<String, String> {
                    val headers: MutableMap<String, String> = HashMap()
                    headers["Content-Type"] = "application/json"
                    headers["Accept"] = "application/json"
                    return headers
                }

                override fun getBody(): ByteArray {
                    Log.i("json", jsonObject.toString())
                    return jsonObject.toString().toByteArray(charset("UTF-8"))
                }
            }
        requestQueue.add(putRequest)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[PedagogyViewModel::class.java]
        // TODO: Use the ViewModel
    }

}