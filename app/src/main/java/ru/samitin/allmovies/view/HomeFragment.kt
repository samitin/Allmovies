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
import ru.samitin.allmovies.model.data.Category
import ru.samitin.allmovies.model.repository.Repository
import ru.samitin.allmovies.model.repository.RepositoryImpl


class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }
    private var _bainding:FragmentHomeBinding?=null
    private val binding
        get()=_bainding!!
    private lateinit var repository:Repository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        _bainding= FragmentHomeBinding.inflate(inflater,container,false)
        repository=RepositoryImpl()
        initRepository(repository)

        return binding.root
    }
    private fun initRepository(repository: Repository){
        for (categegory in repository.getMoviesFromLocalStorage())
            binding.parentLayout.addView(createCategory(categegory))
    }
    private fun createCategory(category: Category):LinearLayout{
        val recyclerView= context?.let { RecyclerView(it) }

        if (recyclerView != null) {
            recyclerView.setHasFixedSize(true)
            recyclerView.layoutManager=LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
            recyclerView.adapter=MoviesAdapter(category.movies,resources)
        }
        val layout=LinearLayout(context)
        layout.orientation=LinearLayout.VERTICAL
        val tv=TextView(context)
        tv.textSize= 30F
        tv.setText(category.categoryName)
        layout.addView(tv)
        layout.addView(recyclerView)
        return layout
    }

    override fun onDestroy() {
        super.onDestroy()
        _bainding=null
    }

}