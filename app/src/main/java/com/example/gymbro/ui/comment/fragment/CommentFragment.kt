package com.example.gymbro.ui.comment.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.setFragmentResultListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gymbro.R
import com.example.gymbro.classes.Comment
import com.example.gymbro.classes.WorkoutCard
import com.example.gymbro.databinding.FragmentCommentBinding
import com.example.gymbro.databinding.FragmentWorkoutMenuBinding
import com.example.gymbro.ui.comment.adapter.CommentAdapter
import com.example.gymbro.ui.workoutMenu.adapter.WorkoutAdapter

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CommentFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CommentFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var binding: FragmentCommentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCommentBinding.inflate(layoutInflater)
        // Use the Kotlin extension in the fragment-ktx artifact
        setFragmentResultListener("requestKey") { requestKey, bundle ->
            // We use a String here, but any type that can be put in a Bundle is supported
            val result = bundle.getString("bundleKey")
            // Do something with the result
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle = arguments

        if (bundle == null){
            Log.d("ads","ads")
            return
        }
        val args = CommentFragmentArgs.fromBundle(bundle)

        Toast.makeText(context, args.example,Toast.LENGTH_SHORT).show()

        val data = arrayOf(
            Comment(
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin rutrum tempus sodales. In eget laoreet est. Sed interdum dui est, eu tincidunt nisi viverra quis. Cras vel justo eleifend, ullamcorper libero in, accumsan nunc. Fusce pharetra arcu mi, ut pretium magna sagittis sit amet. Etiam pharetra eget turpis eget facilisis. Etiam tincidunt risus nec cursus dictum.",
                R.drawable.ic_person
            ),Comment(
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin rutrum tempus sodales. In eget laoreet est. Sed interdum dui est, eu tincidunt nisi viverra quis. Cras vel justo eleifend, ullamcorper libero in, accumsan nunc. Fusce pharetra arcu mi, ut pretium magna sagittis sit amet. Etiam pharetra eget turpis eget facilisis. Etiam tincidunt risus nec cursus dictum.",
                R.drawable.ic_person
            ),Comment(
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin rutrum tempus sodales. In eget laoreet est. Sed interdum dui est, eu tincidunt nisi viverra quis. Cras vel justo eleifend, ullamcorper libero in, accumsan nunc. Fusce pharetra arcu mi, ut pretium magna sagittis sit amet. Etiam pharetra eget turpis eget facilisis. Etiam tincidunt risus nec cursus dictum.",
                R.drawable.ic_person
            ),Comment(
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin rutrum tempus sodales. In eget laoreet est. Sed interdum dui est, eu tincidunt nisi viverra quis. Cras vel justo eleifend, ullamcorper libero in, accumsan nunc. Fusce pharetra arcu mi, ut pretium magna sagittis sit amet. Etiam pharetra eget turpis eget facilisis. Etiam tincidunt risus nec cursus dictum.",
                R.drawable.ic_person
            ),Comment(
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin rutrum tempus sodales. In eget laoreet est. Sed interdum dui est, eu tincidunt nisi viverra quis. Cras vel justo eleifend, ullamcorper libero in, accumsan nunc. Fusce pharetra arcu mi, ut pretium magna sagittis sit amet. Etiam pharetra eget turpis eget facilisis. Etiam tincidunt risus nec cursus dictum.",
                R.drawable.ic_person
            ),Comment(
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin rutrum tempus sodales. In eget laoreet est. Sed interdum dui est, eu tincidunt nisi viverra quis. Cras vel justo eleifend, ullamcorper libero in, accumsan nunc. Fusce pharetra arcu mi, ut pretium magna sagittis sit amet. Etiam pharetra eget turpis eget facilisis. Etiam tincidunt risus nec cursus dictum.",
                R.drawable.ic_person
            ),Comment(
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin rutrum tempus sodales. In eget laoreet est. Sed interdum dui est, eu tincidunt nisi viverra quis. Cras vel justo eleifend, ullamcorper libero in, accumsan nunc. Fusce pharetra arcu mi, ut pretium magna sagittis sit amet. Etiam pharetra eget turpis eget facilisis. Etiam tincidunt risus nec cursus dictum.",
                R.drawable.ic_person
            )
        )

        binding.commentRecyclerView.adapter = CommentAdapter(data)
        binding.commentRecyclerView.layoutManager = LinearLayoutManager(activity)

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CommentFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CommentFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}