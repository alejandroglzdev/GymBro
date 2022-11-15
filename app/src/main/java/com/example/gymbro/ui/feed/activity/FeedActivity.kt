package com.example.gymbro.ui.feed.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gymbro.R
import com.example.gymbro.classes.Post
import com.example.gymbro.databinding.ActivityFeedBinding
import com.example.gymbro.ui.feed.adapter.FeedAdapter

class FeedActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFeedBinding
    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFeedBinding.inflate(layoutInflater)
        linearLayoutManager = LinearLayoutManager(this)

        setContentView(binding.root)

        configureUI()
    }


    private fun configureUI() {
        // Esto devuelve un Drawable? asociado al ID que se le pase por parametro
        // ResourcesCompat.getDrawable(resources, R.color.white, null)
        val data = arrayOf(
            Post("photo", "AlejandroG", "Terrassa", R.drawable.logo_app_negro, "1000", "Description...","32", R.color.white),
            Post("photo", "AdriF", "Terrassa", R.drawable.logo_app_blanco, "32" + " likes", "Description...","12", R.color.black),
            Post("photo", "Adam", "Barcelona", R.drawable.logo_app_negro, "345", "Description...","100", R.color.white),
            Post("photo", "AlejandroG", "Terrassa", R.drawable.logo_app_blanco, "1000", "Description...","32", R.color.black)
        )
        binding.feedRecyclerView.layoutManager = linearLayoutManager
      //  binding.feedRecyclerView.adapter = FeedAdapter(data, callback = { user ->
     //       Toast.makeText(this,"efefefe", Toast.LENGTH_SHORT).show()
   //     })
    }

}