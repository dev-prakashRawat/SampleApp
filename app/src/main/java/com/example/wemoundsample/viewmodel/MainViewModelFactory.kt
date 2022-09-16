package com.example.wemoundsample.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.wemoundsample.network.NetworkServiceImplementation

class MainViewModelFactory (val networkServiceImplementation: NetworkServiceImplementation): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            MainViewModel(this.networkServiceImplementation) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}