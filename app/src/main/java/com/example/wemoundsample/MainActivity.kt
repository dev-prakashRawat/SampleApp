package com.example.wemoundsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.widget.AppCompatTextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wemoundsample.adapter.MovieListAdapter
import com.example.wemoundsample.network.NetworkService
import com.example.wemoundsample.network.NetworkServiceImplementation
import com.example.wemoundsample.viewmodel.MainViewModel
import com.example.wemoundsample.viewmodel.MainViewModelFactory
import com.google.android.material.progressindicator.CircularProgressIndicator

class MainActivity : AppCompatActivity() {

    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val networkService = NetworkService.getInstance()
        val networkServiceImplementation = NetworkServiceImplementation(networkService)
        mainViewModel = ViewModelProvider(this, MainViewModelFactory(networkServiceImplementation)).get(MainViewModel::class.java)
        mainViewModel.getMovies()
        mainViewModel.moviesLiveData.observe(this) {
            if(it != null) {
                val adapter = MovieListAdapter(it)
                val layoutManager = LinearLayoutManager(this)
                val moviesRecyclerView = findViewById<RecyclerView>(R.id.movies_recycler_view)
                moviesRecyclerView.layoutManager = layoutManager
                moviesRecyclerView.adapter = adapter
            } else {
                findViewById<AppCompatTextView>(R.id.error_text_view).visibility = View.VISIBLE
            }
        }
        val circularProgress = findViewById<ProgressBar>(R.id.progress_circular)
        mainViewModel.showLoading.observe(this) {
            if(!it)
                circularProgress.visibility = View.GONE
        }
    }
}