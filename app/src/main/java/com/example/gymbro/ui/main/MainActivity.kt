package com.example.gymbro.ui.main

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils.replace
import android.util.Log
import com.example.gymbro.R
import com.example.gymbro.ui.feed.fragment.FeedFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getSupportFragmentManager()
            .beginTransaction()
            .add(R.id.fragmentContainer, FeedFragment()).commit()

        Log.d(TAG, "MainActivity:onCreate: ")
    }
}