package ru.samitin.allmovies.view

import android.content.res.TypedArray
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.item_movie.view.*
import ru.samitin.allmovies.R
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
            val typed= context?.resources?.obtainTypedArray(R.array.images)
            movie.image?.let {
                if (typed != null) {
                    binding.desImage.setImageResource(typed.getResourceId(it,0))
                }
            }
            binding.desName.setText(movie.title)
            binding.desDate.setText(movie.release_date)
            binding.desRating.setText("" + movie.vote_average + "*")
            binding.desDescription.setText(movie.overview)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _bainding=null
    }
}