package com.example.submissionexpert.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.submissionexpert.R
import com.example.submissionexpert.databinding.ActivityDetailGameBinding

class DetailGameActivity : AppCompatActivity() {
    private var _binding: ActivityDetailGameBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}