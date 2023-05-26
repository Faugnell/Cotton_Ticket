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
import com.android.volley.toolbox.JsonArrayRequest
import com.example.cotton_ticket.databinding.FragmentListBinding
import com.example.cotton_ticket.models.MyGlobal
import com.example.cotton_ticket.models.Ticket

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
            fetchData(MyGlobal.utilisateur.id_utilisateur) { tickets ->
                if (tickets != null) {
                    // Récupération réussie, utilisez la liste de tickets
                    val ticketText = StringBuilder()
                    for (ticket in tickets) {
                        ticketText.append("ID du ticket: ${ticket.idTicket}\n")
                        ticketText.append("Date d'ouverture: ${ticket.dateOuverture}\n")
                        ticketText.append("Résolution: ${ticket.resolution}\n")
                        ticketText.append("Clos: ${ticket.clos}\n")
                        ticketText.append("Date de clôture: ${ticket.dateClos}\n\n")
                    }
                    // Utilisez ticketText comme vous le souhaitez
                } else {
                    // Récupération échouée
                    Toast.makeText(requireActivity(), "Récupération échouée", Toast.LENGTH_SHORT).show()
                }
            }
        }
        catch (e : Exception){
            textViewTicket.text = "${e.message}"
        }

        return root
    }

    fun fetchData(idUtilisateur: Int?, callback: (List<Ticket>?) -> Unit) {
        val url = "https://slam.cipecma.net/2123/vpetit/api/ticket/lire.php?id_utilisateur=$idUtilisateur"

        Log.i("ListFragment URL", url)

        val jsonArrayRequest = JsonArrayRequest(
            Request.Method.GET, url, null,
            { response ->
                val ticketList = mutableListOf<Ticket>()

                for (i in 0 until response.length()) {
                    val ticketObj = response.getJSONObject(i)
                    val idUtilisateur = ticketObj.optInt("id_utilisateur")
                    val idTicket = ticketObj.optInt("id_ticket")
                    val dateOuverture = ticketObj.optString("date_ouverture")
                    val resolution = ticketObj.optString("resolution")
                    val clos = ticketObj.optInt("clos")
                    val dateClos = ticketObj.optString("date_clos")

                    val ticket = Ticket(idUtilisateur, idTicket, dateOuverture, resolution, clos, dateClos)
                    ticketList.add(ticket)
                }

                // Appeler le callback avec la liste de tickets
                callback(ticketList)
            },
            { error ->
                // Gérer l'erreur en cas d'échec de la requête
                Log.d("vol", error.toString())
                callback(null)
            }
        )
        requestQueue.add(jsonArrayRequest)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}

