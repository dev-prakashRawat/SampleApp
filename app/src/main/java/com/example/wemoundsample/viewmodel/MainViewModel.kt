package com.example.wemoundsample.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wemoundsample.data.MoviesApiResult
import com.example.wemoundsample.network.NetworkServiceImplementation
import com.example.wemoundsample.network.NetworkState
import kotlinx.coroutines.launch

private const val TAG = "MainViewModel"

class MainViewModel (val networkServiceImplementation: NetworkServiceImplementation): ViewModel() {

    private var _showLoading: MutableLiveData<Boolean> = MutableLiveData(true)
    val showLoading: LiveData<Boolean> get() = _showLoading

    private var _moviesApiResult: MutableLiveData<MoviesApiResult?> = MutableLiveData()
    val moviesLiveData: LiveData<MoviesApiResult?> get() = _moviesApiResult

    fun getMovies() {
        viewModelScope.launch {
            when(val response = networkServiceImplementation.getMovies()) {
                is NetworkState.Success -> {
                    _showLoading.postValue(false)
                    Log.d(TAG, "getMovies: $response")
                    _moviesApiResult.postValue(response.data)
                }
                is NetworkState.Error -> {
                    _showLoading.postValue(false)
                    Log.d(TAG, "getMovies: $response")
                }
            }
        }
    }
}