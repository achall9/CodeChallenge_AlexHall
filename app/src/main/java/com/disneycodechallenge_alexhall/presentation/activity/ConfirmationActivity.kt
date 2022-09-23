package com.disneycodechallenge_alexhall.presentation.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.disneycodechallenge_alexhall.databinding.ActivityConfirmationBinding

class ConfirmationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityConfirmationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfirmationBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}