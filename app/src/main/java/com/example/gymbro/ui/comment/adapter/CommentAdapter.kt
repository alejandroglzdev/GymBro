package com.example.gymbro.ui.comment.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gymbro.R
import com.example.gymbro.classes.Comment
import com.example.gymbro.classes.WorkoutCard

class CommentAdapter(private val dataSet: Array<Comment>) :
    RecyclerView.Adapter<CommentAdapter.SearchViewHolder>() {

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): SearchViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.adapter_workout_item, viewGroup, false)

        return SearchViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: SearchViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element

        viewHolder.commentTextView.text = dataSet[position].commentText
        viewHolder.profilePhotoImageView.setImageResource(dataSet[position].profilePhoto)

    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class SearchViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val profilePhotoImageView: ImageView
        val commentTextView: TextView

        init {
            // Define click listener for the ViewHolder's View.
            profilePhotoImageView = view.findViewById(R.id.profilePhotoImageView)
            commentTextView = view.findViewById(R.id.commentTextView)

        }
    }
}
