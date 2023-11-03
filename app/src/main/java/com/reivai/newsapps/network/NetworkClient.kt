package com.reivai.newsapps.network

import android.app.Application
import android.content.Context
import android.os.SystemClock
import android.system.SystemCleaner
import com.google.gson.GsonBuilder
import com.reivai.newsapps.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object NetworkClient : Application() {

    fun getNetworkClient(context: Context): NetworkApi {
        val gson = GsonBuilder().setPrettyPrinting().serializeNulls().setLenient().create()
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        return retrofit.create(NetworkApi::class.java)
    }

    val client: OkHttpClient
        get() {
            val interceptor = Interceptor { chain: Interceptor.Chain ->
                SystemClock.sleep(1000)
                chain.proceed(chain.request())
            }

            return OkHttpClient().newBuilder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(1, TimeUnit.MINUTES)
                .writeTimeout(1, TimeUnit.MINUTES)
                .addInterceptor(loggingInterceptor)
                .addNetworkInterceptor(interceptor)
                .build()
    }

    val interceptorLevel: HttpLoggingInterceptor.Level
        get() = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
    val loggingInterceptor: HttpLoggingInterceptor
        get() {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.level = interceptorLevel
            return httpLoggingInterceptor
        }
}