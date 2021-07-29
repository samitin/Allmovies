package ru.samitin.allmovies.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.samitin.allmovies.R
import ru.samitin.allmovies.model.repository.Repository
import ru.samitin.allmovies.model.repository.RepositoryImpl
import ru.samitin.allmovies.viewmodel.MainViewModel

class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val view=inflater.inflate(R.layout.fragment_home, container, false)
        val reciclerView=view.findViewById<RecyclerView>(R.id.recycler_view_lines)
        initReciclerView(reciclerView,RepositoryImpl(resources))
        return view
    }
    private fun initReciclerView(recyclerView: RecyclerView,repository: Repository){
        val text=repository.getMoviesFromLocalStorage()[0].name
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager=LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        recyclerView.adapter=MoviesAdapter(repository)
    }


}