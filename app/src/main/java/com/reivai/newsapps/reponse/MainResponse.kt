package com.reivai.newsapps.reponse

import com.google.gson.annotations.SerializedName
import com.reivai.newsapps.model.ArticlesModel
import java.io.Serializable

data class MainResponse (
    @SerializedName("status")
    var status: String,

    @SerializedName("totalResults")
    var totalResults: Integer,

    @SerializedName("articles")
    var articles: List<ArticlesModel>
) : Serializable