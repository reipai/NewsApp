package com.reivai.newsapps.home

import com.reivai.newsapps.reponse.MainResponse

interface MainView {
    fun success(response: MainResponse)
    fun error(error: String?)
}