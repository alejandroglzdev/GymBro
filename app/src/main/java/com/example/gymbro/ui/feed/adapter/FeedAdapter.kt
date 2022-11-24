package com.example.gymbro.ui.feed.adapter

import android.app.Activity
import android.content.ContentValues
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.gymbro.R
import com.example.gymbro.classes.Post
import com.example.gymbro.ui.comment.fragment.CommentFragment
import com.example.gymbro.ui.feed.fragment.FeedFragment
import com.example.gymbro.ui.main.MainActivity

class FeedAdapter(
    val frag: Fragment,
    private val dataSet: Array<Post>,
    val callback: (Post) -> Unit
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
        viewHolder.bindData(frag, user = dataSet[position], callback = callback)

        viewHolder.usernameTextView.text = dataSet[position].username
        viewHolder.usernameLocation.text = dataSet[position].location
        viewHolder.likes.text = dataSet[position].numberOfLikes + " likes"
        viewHolder.usernameAndDescription.text =
            dataSet[position].username + " | " + dataSet[position].description
        viewHolder.numberOfComments.text =
            "Show " + dataSet[position].numberOfComments + " comments"

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
        var favButtonStatus = false

        fun bindData(frag: Fragment,user: Post, callback: (Post) -> Unit) {
            commentImageView.setOnClickListener {
                findNavController(frag.view).navigate(R.id.action_feedFragment_to_commentFragment)

                /*
                val activity = it.context as MainActivity
                activity.supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragmentContainer, CommentFragment.newInstance("", ""))
                    .commitNow()

                 */

            }

            likeImageView.setOnClickListener {
                if (favButtonStatus) {
                    likeImageView.setImageResource(R.drawable.ic_favorite_off)
                    favButtonStatus = false

                } else {
                    likeImageView.setImageResource(R.drawable.ic_favorite_on)
                    favButtonStatus = true
                }

            }
        }

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
