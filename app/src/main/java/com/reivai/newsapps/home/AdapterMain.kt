package com.reivai.newsapps.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.reivai.newsapps.R
import com.reivai.newsapps.home.detailnews.DetailNewsActivity
import com.reivai.newsapps.model.ArticlesModel
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class AdapterMain(var context: Context, private val mArticles: List<ArticlesModel>) :
    RecyclerView.Adapter<AdapterMain.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_news, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var articlesList = mArticles[position]
        holder.title.text = articlesList.title

        Glide.with(holder.itemView.context)
            .load(articlesList.urlToImage)
            .placeholder(R.drawable.ic_photo_error)
            .into(holder.image)

        val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        val formatter = SimpleDateFormat("dd-MM-yyyy HH:mm")
        val formatedDate = formatter.format(parser.parse(articlesList.publishedAt))
        holder.dateTime.text = formatedDate

        holder.description.text = articlesList.description

        holder.itemView.setOnClickListener {
            val intentDetail = Intent(holder.itemView.context, DetailNewsActivity::class.java).apply {
                putExtra("articleData", Gson().toJson(articlesList))
            }
            holder.itemView.context.startActivity(intentDetail)
        }
    }

    override fun getItemCount(): Int {
        return mArticles.size
    }

    inner class ViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        val image: ImageView = itemview.findViewById(R.id.ivHeadline)
        val title: TextView = itemview.findViewById(R.id.tvTitle)
        val dateTime: TextView = itemview.findViewById(R.id.tvDateTime)
        val description: TextView = itemview.findViewById(R.id.tvDescription)
    }
}
