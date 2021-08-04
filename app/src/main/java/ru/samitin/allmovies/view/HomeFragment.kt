package ru.samitin.allmovies.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import ru.samitin.allmovies.R
import ru.samitin.allmovies.databinding.FragmentHomeBinding
import ru.samitin.allmovies.model.AppState
import ru.samitin.allmovies.model.data.Category
import ru.samitin.allmovies.model.data.Movie
import ru.samitin.allmovies.model.repository.Repository
import ru.samitin.allmovies.model.repository.RepositoryImpl
import ru.samitin.allmovies.viewmodel.MainViewModel


class HomeFragment : Fragment() {

    private val adapter:MoviesAdapter = MoviesAdapter()

    private var _bainding:FragmentHomeBinding?=null
    private val binding
        get()=_bainding!!

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }
    private lateinit var repository:Repository

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        _bainding= FragmentHomeBinding.inflate(inflater,container,false)
        repository=RepositoryImpl()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter.setOnClickView {movie->
            activity?.supportFragmentManager?.let { manager->
                val bundle: Bundle =Bundle()
                bundle.putParcelable(DescriptionMovieFragment.BUNDLE_EXTRA,movie)
                manager.beginTransaction().add(R.id.container,DescriptionMovieFragment.newInstance(bundle))
                        .addToBackStack("")
                        .commitAllowingStateLoss()
            }
        }
        val observer= Observer<AppState> {renderData(it)  }
        viewModel.getLifeData().observe(viewLifecycleOwner,observer)
        viewModel.getWeatherFromLocalSource()
    }
    private fun renderData(appState: AppState) {
        when(appState){
            is AppState.Success ->{
                binding.loadingLayout.visibility=View.GONE
                initListCategory(appState.listCategory)
            }
            is AppState.Loading ->{
                binding.loadingLayout.visibility=View.VISIBLE
            }
            is AppState.Error  ->{
                binding.loadingLayout.visibility=View.GONE
                Snackbar
                        .make(binding.parentLayout, "Error", Snackbar.LENGTH_INDEFINITE)
                        .setAction("Reload") { viewModel.getWeatherFromLocalSource() }
                        .show()

            }
        }
    }
    private fun initListCategory(listCategery:List<Category>){
        for (categegory in listCategery)
            binding.parentLayout.addView(createCategory(categegory))
    }
    private fun createCategory(category: Category):LinearLayout{

        val recyclerView= context?.let { RecyclerView(it) }?.let {recyclerView->
            adapter.setMoviesData(category.movies)
            recyclerView.setHasFixedSize(true)
            recyclerView.layoutManager=LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
            recyclerView.adapter=adapter
            recyclerView
        }
        val layout=LinearLayout(context)
        layout.orientation=LinearLayout.VERTICAL
        val tv=TextView(context)
        tv.textSize= 30F
        tv.text = category.categoryName
        layout.addView(tv)
        layout.addView(recyclerView)
        return layout
    }

    override fun onDestroy() {
        super.onDestroy()
        _bainding=null
    }
}