package ru.samitin.allmovies.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.samitin.allmovies.R
import ru.samitin.allmovies.viewmodel.MainViewModel

class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }



}