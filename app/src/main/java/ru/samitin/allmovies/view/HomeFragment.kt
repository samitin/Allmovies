package ru.samitin.allmovies.view

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.samitin.allmovies.R
import ru.samitin.allmovies.databinding.FragmentHomeBinding
import ru.samitin.allmovies.model.AppState
import ru.samitin.allmovies.model.repository.Repository
import ru.samitin.allmovies.model.repository.RepositoryImpl
import ru.samitin.allmovies.service.*
import ru.samitin.allmovies.viewmodel.MainViewModel
import ru.samitin.allmovies.model.data.Category as Category



class HomeFragment : Fragment() {

    private val adapter:MoviesAdapter = MoviesAdapter()



    private var _bainding:FragmentHomeBinding?=null
    private val binding
        get()=_bainding!!
    //private var listCategory=ArrayList<Category>()

    private val loadResultsReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if (intent?.getStringExtra(TEST_SERVICE_KEY) == LIST_CATEGORIES) {
                val listCategory = intent?.extras?.get(LIST_CATEGORIES) as ArrayList<Category>
                val size=listCategory.size
                initListCategory(listCategory)
            }
        }
    }

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }
    private lateinit var repository: Repository

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        _bainding= FragmentHomeBinding.inflate(inflater,container,false)
        //repository=RepositoryImpl()
        loadListCategoryes()
        return binding.root
    }

    private fun loadListCategoryes() {
        context?.startService(Intent(context, HomeService::class.java).putExtra(TEST_SERVICE_KEY, LIST_CATEGORIES))

        val intentFilter = IntentFilter(ACTION_MYINTENTSERVICE)
        intentFilter.addCategory(Intent.CATEGORY_DEFAULT)
        context?.registerReceiver(loadResultsReceiver, intentFilter)

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
     /*val observer= Observer<AppState> {renderData(it)  }
     viewMol.getLifeData().observe(viewLifecycleOwner,observer)
      viewModel.getMoviesFromLocalSource()*/
        val connectionLiveData = context?.let { ConnectionLiveData(it) }
        connectionLiveData?.observe(viewLifecycleOwner, Observer { isConnected ->
            isConnected?.let {
                if (it)
                    Toast.makeText(context,"Есть связь!!!",Toast.LENGTH_SHORT).show()
                else
                    Toast.makeText(context,"Нет связи!!!",Toast.LENGTH_SHORT).show()
            }
        })
    }
    private fun renderData(appState: AppState) {
       when(appState){
           is AppState.Success ->{
               binding.loadingLayout.hide()

//            initListCategory(appState.listCategory)
           }
           is AppState.Loading ->{
               binding.loadingLayout.show()
           }
           is AppState.Error  ->{
               binding.loadingLayout.hide()
               binding.parentLayout.showSnackBar(R.string.snackBarError,R.string.snackBarReload){
                   viewModel.getMoviesFromLocalSource()
               }
           }
       }
    }
    private fun initListCategory(listCategery: ArrayList<Category>){
        for (categegory in listCategery)
            binding.parentLayout.addView(createCategory(categegory))
    }
    private fun createCategory(category: Category):LinearLayout{
        category
        val recyclerView= context?.let { RecyclerView(it) }?.let {recyclerView->
            adapter.setMoviesData(category.items)
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
        context?.unregisterReceiver(loadResultsReceiver)
        _bainding=null
    }
}