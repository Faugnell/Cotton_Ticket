package com.example.cotton_ticket.ui.list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.BasicNetwork
import com.android.volley.toolbox.DiskBasedCache
import com.android.volley.toolbox.HurlStack
import com.android.volley.toolbox.JsonArrayRequest
import com.example.cotton_ticket.R
import com.example.cotton_ticket.databinding.FragmentListBinding
import com.example.cotton_ticket.models.MyGlobal

class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val _response = MutableLiveData<String>()

    val response: LiveData<String>
        get() = _response

    lateinit var requestQueue: RequestQueue

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        fetchData(MyGlobal.utilisateur.id_utilisateur)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val listViewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        _binding = FragmentListBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val appnetwork = BasicNetwork(HurlStack())
        val appcache = DiskBasedCache(requireActivity().cacheDir, 1024 * 1024) // 1MB cap
        requestQueue = RequestQueue(appcache, appnetwork).apply {
            start()
        }

        return root
    }

    fun fetchData(idUtilisateur: Int) {
        val url = "https://slam.cipecma.net/2123/vpetit/api/ticket/lire.php?id_utilisateur=${idUtilisateur}"

        Log.i("ListFragment URL", url)
        val ticketContainer = binding.ticketContainer

        val jsonArrayRequest = JsonArrayRequest(
            Request.Method.GET, url, null,
            { response ->
                // Traitement de la réponse JSON
                for (i in 0 until response.length()) {
                    val ticket = response.getJSONObject(i)
                    val idTicket = ticket.getInt("id_ticket")
                    val dateOuverture = ticket.getString("date_ouverture")
                    val resolution = ticket.getString("resolution")
                    val clos = ticket.getInt("clos")
                    val dateClos = ticket.getString("date_clos")

                    Log.i("Ticket", "ID Ticket: $idTicket")
                    Log.i("Ticket", "Date d'ouverture: $dateOuverture")
                    Log.i("Ticket", "Résolu: $resolution")
                    Log.i("Ticket", "Clos: $clos")
                    Log.i("Ticket", "Date de cloture: $dateClos")

                    val cardView = createTicketCard(idTicket, dateOuverture, resolution, clos, dateClos)
                    ticketContainer.addView(cardView)
                }
            },
            { error ->
                // Gérer l'erreur en cas d'échec de la requête
                Log.d("vol", error.toString())
                Toast.makeText(requireActivity(), "Récupération échouée", Toast.LENGTH_SHORT).show()
            }
        )
        requestQueue.add(jsonArrayRequest)
    }

    private fun createTicketCard(idTicket: Int, dateOuverture: String, resolution: String, clos: Int, dateClos: String): CardView {
        val inflater = LayoutInflater.from(requireContext())
        val cardView = inflater.inflate(R.layout.ticket_card, null) as CardView

        // Obtenir les références des TextView dans la vue de carte
        val idTextView = cardView.findViewById<TextView>(R.id.idTextView)
        val dateTextView = cardView.findViewById<TextView>(R.id.dateTextView)
        val resolutionTextView = cardView.findViewById<TextView>(R.id.resolutionTextView)
        val closTextView = cardView.findViewById<TextView>(R.id.closTextView)
        val dateClosTextView = cardView.findViewById<TextView>(R.id.dateClosTextView)

        // Configurer les données du ticket dans les TextView
        idTextView.text = "ID Ticket: $idTicket"
        dateTextView.text = "Date d'ouverture: $dateOuverture"
        resolutionTextView.text = "Résolu: $resolution"
        closTextView.text = "Clos: $clos"
        dateClosTextView.text = "Date de clôture: $dateClos"

        return cardView
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
