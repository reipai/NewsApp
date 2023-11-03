package com.reivai.newsapps.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ArticlesModel (
    @SerializedName("source")
    var source: SourceModel?,

    @SerializedName("author")
    var author: String?,
    
    @SerializedName("title")
    var title: String?,
    
    @SerializedName("description")
    var description: String?,

    @SerializedName("url")
    var url: String?,
    
    @SerializedName("urlToImage")
    var urlToImage: String?,
    
    @SerializedName("publishedAt")
    var publishedAt: String?,
    
    @SerializedName("content")
    var content: String?,
) : Serializable
