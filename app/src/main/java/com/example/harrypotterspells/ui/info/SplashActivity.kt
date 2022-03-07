package com.example.harrypotterspells.ui.info

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.harrypotterspells.databinding.ActivitySplahBinding

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplahBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplahBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}