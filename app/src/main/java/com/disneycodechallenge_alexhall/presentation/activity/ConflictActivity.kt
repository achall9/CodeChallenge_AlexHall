package com.disneycodechallenge_alexhall.presentation.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.disneycodechallenge_alexhall.databinding.ActivityConflictBinding

class ConflictActivity : AppCompatActivity() {
    private lateinit var binding: ActivityConflictBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConflictBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}