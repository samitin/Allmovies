package ru.samitin.allmovies.view

import android.content.Context
import android.content.res.Resources
import android.content.res.TypedArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import ru.samitin.allmovies.R
import ru.samitin.allmovies.databinding.ItemMovieBinding
import ru.samitin.allmovies.model.data.Movie

class MoviesAdapter():RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {

    private lateinit var pictures:TypedArray
    private var movies: List<Movie> = listOf()

    private var onClickMovie:(Movie) -> Unit={}
    fun setOnClickView(onClickMovie:(Movie)->Unit){
        this.onClickMovie=onClickMovie
    }

    fun setMoviesData(data: List<Movie>) {
        movies = data
        notifyDataSetChanged()
    }

    inner class MoviesViewHolder(val binding: ItemMovieBinding):RecyclerView.ViewHolder(binding.root){

        fun bind(movie: Movie){
            with(binding){
              movie.image?.let { image.setImageResource(it) }
              name.setText(movie.title)
              date.setText(movie.release_date)
              rating.setText(""+movie.vote_average)
              image.setOnClickListener {
                onClickMovie(movie)
              }
            }
        }
    }

override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
    pictures=parent.context.resources.obtainTypedArray(R.array.images)
    return MoviesViewHolder(ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false))
}

override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
      movies[position].image=pictures.getResourceId(position,0)
      holder.bind(movies[position])
}

override fun getItemCount() = movies.size

}