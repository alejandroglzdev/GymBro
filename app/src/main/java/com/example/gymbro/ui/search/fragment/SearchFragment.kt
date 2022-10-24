package com.example.gymbro.ui.search.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gymbro.R
import com.example.gymbro.classes.Post
import com.example.gymbro.databinding.FragmentSearchBinding
import com.example.gymbro.ui.feed.adapter.FeedAdapter

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SearchFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SearchFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var binding: FragmentSearchBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSearchBinding.inflate(layoutInflater)
        return binding.root
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
            )
        )

        binding.searchRecyclerView.layoutManager = LinearLayoutManager(activity)
        binding.searchRecyclerView.adapter = FeedAdapter(data)

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SearchFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SearchFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}