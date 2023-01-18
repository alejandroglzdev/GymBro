package com.app.gymbro.ui.pr

import PR
import android.content.Context
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
import com.app.gymbro.R
import com.google.android.play.core.review.ReviewManager
import com.google.android.play.core.review.ReviewManagerFactory
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.*

/**
 * A simple [Fragment] subclass.
 * This fragment is used to display information about your personal records.
 */
class PRFragment : Fragment() {

    private val database = FirebaseFirestore.getInstance()
    var prs = ArrayList<PR>()
    lateinit var prAdapter: AdapterPR
    val firebaseAuth = FirebaseAuth.getInstance()
    //val manager = getContext()?.let { ReviewManagerFactory.create(it) }
    lateinit var manager: ReviewManager
    /**
     * Inflates the layout for this fragment, sets the UI and returns the root view.
     *
     * @param inflater LayoutInflater object used to inflate the layout.
     * @param container ViewGroup object that contains this fragment.
     * @param savedInstanceState Bundle object that saves the state of this fragment.
     *
     * @return The root view of this fragment
     */

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

        if (container != null) {
            manager = ReviewManagerFactory.create(container.context)
        }

        initViews()

        return view
    }

    override fun onResume() {
        super.onResume()
        showReviewCard()
    }

    private fun initViews() {

    }

    /**
     * This method load all the data from the Firebase Database.
     */

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

    private fun showReviewCard() {
        var queryCount = database.collection("users").document(firebaseAuth.currentUser!!.uid).collection("personal_record").count()
        queryCount.get(AggregateSource.SERVER).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val count = task.result.count
                if (count > 0) {
                    val sharedPreference = this.activity?.getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
                    var showReviewCard = sharedPreference?.getBoolean("showReviewCard", true)
                    if (showReviewCard == true) {
                        val request = manager?.requestReviewFlow()
                        request?.addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                // We got the ReviewInfo object
                                val reviewInfo = task.result
                                val flow = activity?.let { manager?.launchReviewFlow(it, reviewInfo) }
                                flow?.addOnCompleteListener { _ ->
                                    // The flow has finished. The API does not indicate whether the user
                                    // reviewed or not, or even whether the review dialog was shown. Thus, no
                                    // matter the result, we continue our app flow.
                                }
                            } else {
                                // There was some problem, log or handle the error code.
                            }
                        }
                    }
                }
            }
        }
    }


}