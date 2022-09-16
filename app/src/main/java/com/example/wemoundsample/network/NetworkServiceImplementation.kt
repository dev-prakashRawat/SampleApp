package com.example.wemoundsample.network

import com.example.wemoundsample.data.MoviesApiResult

class NetworkServiceImplementation(private val networkService: NetworkService) {

    suspend fun getMovies(): NetworkState<MoviesApiResult> {
        val response = networkService.postMovies()
        return if (response.isSuccessful) {
            val responseBody = response.body()
            if (responseBody != null) {
                NetworkState.Success(responseBody)
            } else {
                NetworkState.Error(response)
            }
        } else {
            NetworkState.Error(response)
        }
    }
}