package ru.samitin.allmovies.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import ru.samitin.allmovies.R
import ru.samitin.allmovies.model.data.Movie
import ru.samitin.allmovies.model.repository.Repository

class MoviesAdapter(private val repository:Repository):RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {

    class MoviesViewHolder(item:View):RecyclerView.ViewHolder(item){
        private val images:AppCompatImageView
        private val name:TextView
        private val date:TextView
        private val rating:TextView
        init {
            images=item.findViewById(R.id.image)
            name=item.findViewById(R.id.name)
            date=item.findViewById(R.id.date)
            rating=item.findViewById(R.id.rating)
        }
        fun bind(movie: Movie){
            images.setImageResource(movie.image)
            name.setText(movie.name)
            date.setText(movie.date)
            rating.setText(""+movie.reting)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.item_movie,parent,false)
        return MoviesViewHolder(view)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.bind(repository.getMoviesFromLocalStorage()[position])
    }

    override fun getItemCount() = repository.getMoviesFromLocalStorage().size

}