package com.example.gymbro.ui.workout.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gymbro.R
import com.example.gymbro.classes.Post
import com.example.gymbro.classes.WorkoutCard

class WorkoutAdapter(private val dataSet: Array<WorkoutCard>) :
    RecyclerView.Adapter<WorkoutAdapter.SearchViewHolder>() {

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

        viewHolder.workoutNameTextView.text = dataSet[position].workoutName
        viewHolder.workoutDescriptionTextView.text = dataSet[position].workoutDescription

    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class SearchViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val workoutNameTextView: TextView
        val workoutDescriptionTextView: TextView

        init {
            // Define click listener for the ViewHolder's View.
            workoutNameTextView = view.findViewById(R.id.workoutNameTextView)
            workoutDescriptionTextView = view.findViewById(R.id.workoutDescriptionTextView)

        }
    }
}
