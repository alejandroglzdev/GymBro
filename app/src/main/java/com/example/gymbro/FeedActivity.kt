package com.example.gymbro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gymbro.databinding.ActivityFeedBinding

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

        val data = arrayOf(Post("photo", "AlejandroG", "Terrassa", "photoP", 1000, "Description...",32),
            Post("photo", "AdriF", "Terrassa", "photoP", 32, "Description...",12),
            Post("photo", "Adam", "Barcelona", "photoP", 345, "Description...",100),
            Post("photo", "AlejandroG", "Terrassa", "photoP", 1000, "Description...",32))
        binding.feedRecyclerView.layoutManager = linearLayoutManager
        binding.feedRecyclerView.adapter = FeedAdapter(data)
    }
}