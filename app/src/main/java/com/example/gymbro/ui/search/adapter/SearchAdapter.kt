package com.example.gymbro.ui.search.adapter

import android.service.autofill.Dataset
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.gymbro.R
import com.example.gymbro.classes.Post

class SearchAdapter(private val dataSet: Array<Post>) :
    RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): SearchViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.adapter_search_item, viewGroup, false)

        return SearchViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: SearchViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element

        viewHolder.colorPhotoImageView.setImageResource(dataSet[position].postPhoto)
        viewHolder.colorPhotoImageView.setBackgroundColor(dataSet[position].photoBackground)
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class SearchViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val colorPhotoImageView: ImageView

        init {
            // Define click listener for the ViewHolder's View.
            colorPhotoImageView = view.findViewById(R.id.photoPostImageView)

        }
    }
}
