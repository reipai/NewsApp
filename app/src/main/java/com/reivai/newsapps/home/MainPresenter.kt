package com.reivai.newsapps.home

import android.content.Context
import com.reivai.newsapps.network.NetworkClient.getNetworkClient
import com.reivai.newsapps.reponse.MainResponse
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

class MainPresenter(var context: Context, private val view: MainView) : MainPresenterImp {
    override fun getTopHeadlines(country: String, apiKey: String) {
        val hisApi: Observable<MainResponse>? = getNetworkClient(context)
            .getTopHeadlines(country, apiKey)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

        val ha: DisposableObserver<MainResponse>? = hisApi?.subscribeWith(object : DisposableObserver<MainResponse> () {
            override fun onNext(t: MainResponse) {
                view.success(t)
            }

            override fun onError(e: Throwable) {
                view.error(e.message)
            }

            override fun onComplete() {

            }

        })
    }
}