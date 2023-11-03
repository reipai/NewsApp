package com.reivai.newsapps.home.detailnews

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.LinkMovementMethod
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.reivai.newsapps.databinding.ActivityDetailNewsBinding
import com.reivai.newsapps.model.ArticlesModel

class DetailNewsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailNewsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        val articleData = Gson().fromJson(intent?.extras?.getString("articleData"), ArticlesModel::class.java)

        Glide.with(this)
            .load(articleData.urlToImage)
            .into(binding.ivDetail)

        binding.tvTitle.text = articleData.title
        binding.tvDescription.text = articleData.description
        binding.tvContent.text = articleData.content
        binding.tvShowMore.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("${articleData.url}"))
            startActivity(browserIntent)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}