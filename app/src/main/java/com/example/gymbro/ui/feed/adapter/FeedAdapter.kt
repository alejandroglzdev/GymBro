package com.example.gymbro.ui.feed.adapter

import android.app.Activity
import android.content.ContentValues
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.gymbro.R
import com.example.gymbro.classes.Post
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import com.example.gymbro.ui.feed.fragment.FeedFragment
import com.example.gymbro.ui.feed.fragment.FeedFragmentDirections

class FeedAdapter(
    val frag: FeedFragment,
    private val dataSet: Array<Post>,
) :
    RecyclerView.Adapter<FeedAdapter.FeedViewHolder>() {

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): FeedViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.adapter_feed_item, viewGroup, false)

        return FeedViewHolder(view)
    }


    // Replace the contents of a view (invoked by the layout manager)

    override fun onBindViewHolder(viewHolder: FeedViewHolder, position: Int) {
        // Get element from your dataset at this position and replace the
        // contents of the view with that element

        viewHolder.usernameTextView.text = dataSet[position].username
        viewHolder.usernameLocation.text = dataSet[position].location
        viewHolder.likes.text = dataSet[position].numberOfLikes + " likes"
        viewHolder.usernameAndDescription.text = dataSet[position].username + " | " + dataSet[position].description
        viewHolder.numberOfComments.text = "Show " + dataSet[position].numberOfComments + " comments"

        var favButtonStatus = false


        viewHolder.commentImageView.setOnClickListener {
            val safeArgsString = "Hello world!"
            val directions = FeedFragmentDirections.actionFeedFragmentToCommentFragment(safeArgsString)
            findNavController(frag).navigate(directions)
            //findNavController(frag).navigate(R.id.action_feedFragment_to_commentFragment)
        }
        
        viewHolder.likeImageView.setOnClickListener {
            if (favButtonStatus) {
                viewHolder.likeImageView.setImageResource(R.drawable.ic_favorite_off)
                favButtonStatus = false

            } else {
                viewHolder.likeImageView.setImageResource(R.drawable.ic_favorite_on)
                favButtonStatus = true
            }

        }

    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class FeedViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val usernameTextView: TextView
        val usernameLocation: TextView
        val likes: TextView
        val usernameAndDescription: TextView
        val numberOfComments: TextView
        val commentImageView: ImageView
        val likeImageView: ImageView

        init {
            // Define click listener for the ViewHolder's View.
            usernameTextView = view.findViewById(R.id.usernameTextView)
            usernameLocation = view.findViewById(R.id.usernameLocationTextView)
            likes = view.findViewById(R.id.numberLikesTextView)
            usernameAndDescription = view.findViewById(R.id.userNameAndDescriptionTextView)
            numberOfComments = view.findViewById(R.id.numberOfComments)
            commentImageView = view.findViewById(R.id.commentImageView)
            likeImageView = view.findViewById(R.id.likeImageView)


        }


    }

}
