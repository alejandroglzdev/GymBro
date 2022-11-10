package com.example.gymbro.ui.feed.adapter

import android.content.ContentValues
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gymbro.R
import com.example.gymbro.classes.Post
import com.example.gymbro.ui.comment.fragment.CommentFragment
import com.example.gymbro.ui.feed.fragment.FeedFragment

class FeedAdapter(private val dataSet: Array<Post>) :
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
        val likes : TextView
        val usernameAndDescription: TextView
        val numberOfComments: TextView

        init {
            // Define click listener for the ViewHolder's View.
            usernameTextView = view.findViewById(R.id.usernameTextView)
            usernameLocation = view.findViewById(R.id.usernameLocationTextView)
            likes = view.findViewById(R.id.numberLikesTextView)
            usernameAndDescription = view.findViewById(R.id.userNameAndDescriptionTextView)
            numberOfComments = view.findViewById(R.id.numberOfComments)

        }
    }

}
