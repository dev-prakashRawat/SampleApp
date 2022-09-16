package com.example.wemoundsample.network

import com.example.wemoundsample.data.MoviesApiResult
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.POST

interface NetworkService {

    @POST("api/content/home")
    suspend fun postMovies() : Response<MoviesApiResult>

    companion object {
        var retrofitInstance: NetworkService? = null
        fun getInstance(): NetworkService {
            if(retrofitInstance == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://cinescapeapi.wemonde.co/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitInstance = retrofit.create(NetworkService::class.java)
            }
            return retrofitInstance!!
        }
    }
}