package com.app.gymbro.ui.feed.fragment

import User
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.gymbro.databinding.FragmentFeedBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase


/**
 * A simple [Fragment] subclass.
 * This fragment is used to display information about the user's profile and personal records in a gym training app.
 * it uses Firebase library to retrieve data from the database.
 *
 */
class FeedFragment : Fragment() {
    private lateinit var binding: FragmentFeedBinding
    val db = Firebase.firestore
    private lateinit var auth: FirebaseAuth
    val firebaseAuth = FirebaseAuth.getInstance()
    private val database = FirebaseFirestore.getInstance()
    private lateinit var userTextView: TextView

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
        binding = FragmentFeedBinding.inflate(layoutInflater)

        userTextView = binding.usernameTextView

        setUI()

        return binding.root
    }

    /**
     * Retrieve user data and personal records from Firebase database and update the UI.
     *
     */

    private fun setUI() {
        auth = FirebaseAuth.getInstance()
        val user: FirebaseUser = auth.currentUser!!
        val docRef = db.collection("users").document(firebaseAuth.currentUser!!.uid)
        println("current user: " + firebaseAuth.currentUser!!.uid)
        docRef.get().addOnSuccessListener { documentSnapshot ->
            val user = documentSnapshot.toObject<User>()
            if (user != null) {
                userTextView.setText(user.username)
            }
        }

        db.collection("users").document(firebaseAuth.currentUser!!.uid).collection("personal_record").get().addOnSuccessListener { snap ->
            binding.totalPRTextView.text = snap.size().toString()
        }

    }

}