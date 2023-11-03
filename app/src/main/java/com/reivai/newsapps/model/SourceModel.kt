package com.reivai.newsapps.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class SourceModel {
    @Expose
    @SerializedName("id")
    lateinit var id: String

    @Expose
    @SerializedName("name")
    lateinit var name: String
}