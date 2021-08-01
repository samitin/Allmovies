package ru.samitin.allmovies.view

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import androidx.fragment.app.Fragment
import ru.samitin.allmovies.databinding.FragmentDescriptionMovieBinding

import ru.samitin.allmovies.model.data.Movie


class DescriptionMovieFragment :Fragment(){
    companion object {

        const val BUNDLE_EXTRA = "Movies"

        fun newInstance(bundle: Bundle): DescriptionMovieFragment {
            val fragment = DescriptionMovieFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
    private var _bainding: FragmentDescriptionMovieBinding?=null
    private val binding
        get()=_bainding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _bainding= FragmentDescriptionMovieBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val movie=arguments?.getParcelable<Movie>(BUNDLE_EXTRA)
        if (movie!=null){
            binding.desImage.setImageResource(movie.image)
            binding.desName.setText(movie.name)
            binding.desDate.setText(movie.date)
            binding.desRating.setText("" + movie.reting + "%")
            binding.desDescription.setText(movie.descreotion)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _bainding=null
    }
}