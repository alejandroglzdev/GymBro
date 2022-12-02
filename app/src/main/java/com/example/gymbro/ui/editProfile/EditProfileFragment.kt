package com.example.gymbro.ui.editProfile

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.gymbro.R
import com.example.gymbro.classes.User
import com.example.gymbro.databinding.FragmentEditProfileBinding
import com.example.gymbro.databinding.FragmentFeedBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
val firebaseAuth = FirebaseAuth.getInstance()
val db = Firebase.firestore

/**
 * A simple [Fragment] subclass.
 * Use the [EditProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EditProfileFragment : Fragment() {
    private lateinit var binding: FragmentEditProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentEditProfileBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        saveData()


    }

    private fun saveData() {
        binding.saveButton.setOnClickListener {

            /*
            val docRef = db.collection("users").document(firebaseAuth.currentUser!!.uid)
            docRef.get().addOnSuccessListener { documentSnapshot ->
                val user = documentSnapshot.toObject<User>()

                val userFirestore = hashMapOf(
                    "username" to user!!.username,
                    "phone" to user.phone,
                    "email" to user.email,
                    "name" to binding.nameEditText.text.toString(),
                    "surnames" to binding.surnamesEditText.text.toString(),
                    "description" to binding.descriptionEditText.text.toString(),
                    "birthDate" to binding.birthdateEditText.text.toString(),
                    "gender" to binding.genderEditText.text.toString()
                )

                db.collection("users")
                    .document(firebaseAuth.currentUser!!.uid)
                    .set(userFirestore)
                    .addOnSuccessListener { documentReference ->
                        Log.d(
                            ContentValues.TAG,
                            "DocumentSnapshot added with ID: ${documentReference}"
                        )
                    }
                    .addOnFailureListener { e ->
                        Log.w(ContentValues.TAG, "Error adding document", e)
                    }


            }
            */
            db.collection("users")
                .document(firebaseAuth.currentUser!!.uid)
                .update(mapOf(
                    "name" to binding.nameEditText.text.toString(),
                    "surnames" to binding.surnamesEditText.text.toString(),
                    "description" to binding.descriptionEditText.text.toString(),
                    "birthDate" to binding.birthdateEditText.text.toString(),
                    "gender" to binding.genderEditText.text.toString()
                ))
            findNavController().navigate(R.id.action_editProfileFragment_to_profileFragment)

        }


    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment EditProfileFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            EditProfileFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}