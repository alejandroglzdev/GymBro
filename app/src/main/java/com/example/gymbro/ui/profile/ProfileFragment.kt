package com.example.gymbro.ui.profile

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.gymbro.R
import com.example.gymbro.SignInActivity
import com.example.gymbro.SignUpActivity
import com.example.gymbro.classes.Post
import com.example.gymbro.databinding.FragmentProfileBinding
import com.example.gymbro.databinding.FragmentSearchBinding
import com.example.gymbro.ui.search.adapter.SearchAdapter
import com.google.firebase.auth.FirebaseAuth

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfileFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(layoutInflater)

        setHasOptionsMenu(true)

        binding.menuImageView.setOnClickListener(){
            showPopup(binding.menuImageView)
        }


        return binding.root
    }

    fun showPopup(v : View){
        val popup = PopupMenu(context, v)
        val inflater: MenuInflater = popup.menuInflater
        inflater.inflate(R.menu.profile_menu, popup.menu)
        popup.setOnMenuItemClickListener { menuItem ->
            when(menuItem.itemId){
                R.id.about_menu-> {

                }
                R.id.signout_menu-> {
                    FirebaseAuth.getInstance().signOut()
                    val intent = Intent(context, SignInActivity::class.java)
                    startActivity(intent)
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

            R.id.signout_menu -> {

            }
        }
        return true
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        val data = arrayOf(
            Post(
                "photo",
                "AlejandroG",
                "Terrassa",
                R.drawable.logo_app_negro,
                "1000",
                "Description...",
                "32",
                R.color.white
            ),
            Post(
                "photo",
                "AdriF",
                "Terrassa",
                R.drawable.logo_app_blanco,
                "32" + " likes",
                "Description...",
                "12",
                R.color.black
            ),
            Post(
                "photo",
                "Adam",
                "Barcelona",
                R.drawable.logo_app_negro,
                "345",
                "Description...",
                "100",
                R.color.white
            ),
            Post(
                "photo",
                "AlejandroG",
                "Terrassa",
                R.drawable.logo_app_blanco,
                "1000",
                "Description...",
                "32",
                R.color.black
            ),
            Post(
                "photo",
                "AlejandroG",
                "Terrassa",
                R.drawable.logo_app_negro,
                "1000",
                "Description...",
                "32",
                R.color.white
            ),
            Post(
                "photo",
                "AdriF",
                "Terrassa",
                R.drawable.logo_app_blanco,
                "32" + " likes",
                "Description...",
                "12",
                R.color.black
            ),
            Post(
                "photo",
                "Adam",
                "Barcelona",
                R.drawable.logo_app_negro,
                "345",
                "Description...",
                "100",
                R.color.white
            ),
            Post(
                "photo",
                "AlejandroG",
                "Terrassa",
                R.drawable.logo_app_blanco,
                "1000",
                "Description...",
                "32",
                R.color.black
            )

        )

        binding.profileRecyclerView.layoutManager = GridLayoutManager(context,3)
        binding.profileRecyclerView.adapter = SearchAdapter(data)

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ProfileFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ProfileFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}