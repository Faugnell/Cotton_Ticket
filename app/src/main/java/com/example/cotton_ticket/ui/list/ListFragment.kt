package com.example.cotton_ticket.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.cotton_ticket.databinding.FragmentListBinding
import com.example.cotton_ticket.models.Ticket
import com.example.cotton_ticket.remote.ApiClient.apiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        val listViewModel =
            ViewModelProvider(this).get(ListViewModel::class.java)
        _binding = FragmentListBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val cardView = binding.cardView

        apiService.lire_ticket()?.enqueue(object : Callback<List<Ticket>> {
            override fun onResponse(call: Call<List<Ticket>>, response: Response<List<Ticket>>) {
                val ticketList = response.body()
                // Afficher les données dans la liste de cards

                // Création d'un tableau Int pour la cardView
                val tickets = arrayOfNulls<Int>(ticketList!!.size)

                // Boucle de tout les tickets et mettre l'id dans le tableau
                for (i in ticketList.indices) {
                    tickets[i] = ticketList[i].id_ticket
                }

                // Afficher le tableau dans la cardView

            }

            override fun onFailure(call: Call<List<Ticket>>, t: Throwable) {
                // Gérer l'erreur de récupération des données
            }
        })

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}

