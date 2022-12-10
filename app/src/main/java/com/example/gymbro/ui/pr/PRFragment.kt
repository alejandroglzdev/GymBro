package com.example.gymbro.ui.pr

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gymbro.R
import com.example.gymbro.classes.PR
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot

class PRFragment : Fragment() {

    private val database = FirebaseFirestore.getInstance()
    var prs = ArrayList<PR>()
    lateinit var prAdapter: AdapterPR
    val firebaseAuth = FirebaseAuth.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_p_r, container, false)

        val savePrButton: Button = view.findViewById(R.id.addPrButton)
        savePrButton.setOnClickListener{
            val intent = Intent(requireContext(), IntroPrActivity::class.java)
            startActivity(intent)
        }

        val recyclerView: RecyclerView = view.findViewById(R.id.prRecyclerView)
        recyclerView.layoutManager=LinearLayoutManager(activity, RecyclerView.VERTICAL, false)

        prAdapter = AdapterPR(prs)

        recyclerView.adapter = prAdapter

        loadPRData()

        initViews()

        return view
    }

    private fun initViews() {

    }

    private fun loadPRData() {
        database.collection("users").document(firebaseAuth.currentUser!!.uid).collection("personal_record")
            .addSnapshotListener(object : EventListener<QuerySnapshot>{
                override fun onEvent(
                    value: QuerySnapshot?,
                    error: FirebaseFirestoreException?
                )
                {
                    if (error != null){
                        Log.e("Firestore error:  ", error.message.toString())
                        return
                    }

                    for (dc: DocumentChange in value?.documentChanges!!){
                        if (dc.type == DocumentChange.Type.ADDED){
                            prs.add(dc.document.toObject(PR::class.java))
                        }
                    }
                    prAdapter.notifyDataSetChanged()
                    Log.d("Documento: ", "$prs")
                }
            })
    }


}