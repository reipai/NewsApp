package com.reivai.newsapps.home

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.reivai.newsapps.AboutMeActivity
import com.reivai.newsapps.R
import com.reivai.newsapps.databinding.ActivityMainBinding
import com.reivai.newsapps.home.detailnews.DetailNewsActivity
import com.reivai.newsapps.model.ArticlesModel
import com.reivai.newsapps.reponse.MainResponse

class MainActivity : AppCompatActivity(), MainView {
    private lateinit var binding: ActivityMainBinding
    private lateinit var presenter: MainPresenter
    private lateinit var adapter: AdapterMain
    private val listArticles = ArrayList<ArticlesModel>()
    lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val country = "us"
        val apiKey = "374517cd873b4310acb4c402eece320b"

        setSupportActionBar(binding.toolbar)

        context = this
        presenter = MainPresenter(context, this)
//        adapter = listArticles?.let { AdapterMain(this@MainActivity, it) }!!
        adapter = AdapterMain(context, listArticles)

        binding.rvNews.layoutManager = LinearLayoutManager(context)
        binding.rvNews.setHasFixedSize(true)
        binding.rvNews.adapter = adapter

        presenter.getTopHeadlines(country, apiKey)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.about_page -> {
                val intent = Intent(this@MainActivity, AboutMeActivity::class.java)
                startActivity(intent)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun success(response: MainResponse) {
        if (response.articles.isNotEmpty()) {
            listArticles.clear()
            response.articles.let { listArticles.addAll(it) }
            adapter.notifyDataSetChanged()
        }

    }

    override fun error(error: String?) {
        Log.d("wakacaw", "error: $error")
    }
}