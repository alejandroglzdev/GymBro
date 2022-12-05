package com.example.gymbro.ui.workoutInProgress.adapterWorkoutExercise

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gymbro.R
import com.example.gymbro.classes.Post

class AdapterWorkoutExercise(private val dataSet: Array<String>) :
    RecyclerView.Adapter<AdapterWorkoutExercise.AdapterWorkoutExerciseViewHolder>() {

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): AdapterWorkoutExerciseViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.adapter_workout_progress_exercise, viewGroup, false)

        return AdapterWorkoutExerciseViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: AdapterWorkoutExerciseViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element



    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class AdapterWorkoutExerciseViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        init {
            // Define click listener for the ViewHolder's View.

        }
    }
}
