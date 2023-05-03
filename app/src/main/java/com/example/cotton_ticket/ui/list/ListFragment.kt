package com.example.cotton_ticket.ui.list

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
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

        apiService.lire_ticket(id_utilisateur = 1)?.enqueue(object : Callback<Ticket> {
            override fun onResponse(call: Call<Ticket>, response: Response<Ticket>) {
                if (response.isSuccessful) {
                    val ticket = response.body()
                    if (ticket != null) {
                        Log.d(TAG, "Le ticket à pu être récupéré")
                    } else {
                        Log.e(TAG, "Corps de la réponse vide")
                    }
                } else {
                    Log.e(TAG, "Réponse non réussie")
                }
            }

            override fun onFailure(call: Call<Ticket>, t: Throwable) {
                Log.e(TAG, "Échec de la requête: ${t.message}")
            }
        })

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
