package ru.samitin.allmovies.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.ScrollView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.samitin.allmovies.R
import ru.samitin.allmovies.databinding.FragmentHomeBinding
import ru.samitin.allmovies.model.repository.Repository
import ru.samitin.allmovies.model.repository.RepositoryImpl
import ru.samitin.allmovies.viewmodel.MainViewModel

class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }
    private var _bainding:FragmentHomeBinding?=null
    private val binding
        get()=_bainding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        _bainding= FragmentHomeBinding.inflate(inflater,container,false)
        initListViewView()
        return binding.root
    }
    private fun initListViewView(){
        binding.parentLayout.addView(createCategory(RepositoryImpl("Комедии",resources)))
        binding.parentLayout.addView(createCategory(RepositoryImpl("Боевики",resources)))
        binding.parentLayout.addView(createCategory(RepositoryImpl("Ужасы",resources)))
        binding.parentLayout.addView(createCategory(RepositoryImpl("Драмма",resources)))
        binding.parentLayout.addView(createCategory(RepositoryImpl("Мультфильмы",resources)))
    }
    private fun createCategory(repository: Repository):LinearLayout{
        val recyclerView= context?.let { RecyclerView(it) }

        if (recyclerView != null) {
            recyclerView.setHasFixedSize(true)
            recyclerView.layoutManager=LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
            recyclerView.adapter=MoviesAdapter(repository)
        }
        val layout=LinearLayout(context)
        layout.orientation=LinearLayout.VERTICAL
        val tv=TextView(context)
        tv.textSize= 30F
        tv.setText(repository.getCategoryName())
        layout.addView(tv)
        layout.addView(recyclerView)
        return layout
    }

    override fun onDestroy() {
        super.onDestroy()
        _bainding=null
    }

}