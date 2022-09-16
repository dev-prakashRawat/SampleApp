package com.example.wemoundsample.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.wemoundsample.R
import com.example.wemoundsample.data.MoviesApiResult

class MovieListAdapter(private val moviesDataSet: MoviesApiResult):
RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleTextView: TextView

        init {
            titleTextView =  view.findViewById(R.id.title_text_view)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_list_adapter_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.titleTextView.text = moviesDataSet.output.homeOnes[position].name
    }

    override fun getItemCount(): Int {
        return moviesDataSet.output.homeOnes.size
    }
}