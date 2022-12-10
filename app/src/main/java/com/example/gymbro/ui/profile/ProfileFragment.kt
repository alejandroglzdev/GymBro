package com.example.gymbro.ui.profile

import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import android.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.gymbro.R
import com.example.gymbro.SignInActivity
import com.example.gymbro.classes.User
import com.example.gymbro.databinding.FragmentProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class ProfileFragment : Fragment() {

    val storageRef = Firebase.storage.reference
    val db = Firebase.firestore
    private lateinit var auth: FirebaseAuth
    val firebaseAuth = FirebaseAuth.getInstance()
    private val database = FirebaseFirestore.getInstance()

    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(layoutInflater)

        setHasOptionsMenu(true)

        setUI()
        configureUI()

        return binding.root
    }

    private fun configureUI() {


    }

    private fun setUI() {
        auth = FirebaseAuth.getInstance()
        val user: FirebaseUser = auth.currentUser!!
        val docRef = db.collection("users").document(firebaseAuth.currentUser!!.uid)
        println("current user: " + firebaseAuth.currentUser!!.uid)
        docRef.get().addOnSuccessListener { documentSnapshot ->
            val user = documentSnapshot.toObject<User>()
            if (user != null) {
                binding.usernameEditText.setText(user.username)
                binding.phoneEditText.setText(user.phone)
                binding.emailEditText.setText(user.email)
            }
        }

        binding.saveButton.setOnClickListener {
            var phone = binding.phoneEditText.text.toString()
            var username = binding.usernameEditText.text.toString()
            database.collection("users").document(user.uid).set(
                hashMapOf(
                    "phone" to phone,
                    "username" to username
                ), SetOptions.merge()
            )

        }

        binding.aboutMenuButton.setOnClickListener{
            showPopup(it)
        }

        binding.exitAccountButton.setOnClickListener{
            exitAccount()
        }

    }

    private fun exitAccount() {
        AlertDialog.Builder(requireContext(), R.style.MyAlertDialogStyle)
            .setMessage("You're about to sign out, you will need to log in again later.")
            .setPositiveButton("Proceed") { _, _ ->

                FirebaseAuth.getInstance().signOut()
                val intent = Intent(context, SignInActivity::class.java)
                startActivity(intent)
            }
            .setNegativeButton("Cancel") { _, _ -> }
            .show()
    }

    fun showPopup(v : View){
        val url = "https://git.copernic.cat/gonzalez.espejo.alejandro/gymbro.git"
        val popup = PopupMenu(context, v)
        val inflater: MenuInflater = popup.menuInflater
        inflater.inflate(R.menu.profile_menu, popup.menu)
        popup.setOnMenuItemClickListener { menuItem ->
            when(menuItem.itemId){
                R.id.about_menu-> {
                    AlertDialog.Builder(requireContext(), R.style.MyAlertDialogStyle)
                        .setTitle("Gymbro App" + " Development phase")
                        .setMessage("OpenSource project made by Alejandro Espejo & Adrià Fernández")
                        .setPositiveButton("Check our GitLab") { _, _ ->
                            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
                        }
                        .setNegativeButton("Resume") {_, _ ->}
                        .show()
                }
            }
            true
        }
        popup.show()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.profile_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.about_menu -> {

            }
        }
        return true
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}