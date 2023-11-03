package com.reivai.newsapps

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.reivai.newsapps.databinding.ActivityAboutMeBinding

class AboutMeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAboutMeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutMeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}