package ru.samitin.allmovies.view

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.samitin.allmovies.databinding.FragmentFavoritesBinding
import ru.samitin.allmovies.model.data.Category
import ru.samitin.allmovies.service.*


class FavoritesFragment :Fragment(){


    private var _bainding: FragmentFavoritesBinding?=null
    private val binding
        get()=_bainding!!

    private var listCategory=ArrayList<Category>()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        _bainding= FragmentFavoritesBinding.inflate(inflater, container, false)
        initListCategoryes()
        return binding.root
    }


    private fun initListCategoryes() {
        context?.startService(Intent(context, HomeService::class.java).putExtra(TEST_SERVICE_KEY, LIST_CATEGORIES))

        val intentFilter = IntentFilter(ACTION_MYINTENTSERVICE)
        intentFilter.addCategory(Intent.CATEGORY_DEFAULT)
        context?.registerReceiver(loadResultsReceiver, intentFilter)

    }
    private val loadResultsReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if (intent?.getStringExtra(TEST_SERVICE_KEY) == LIST_CATEGORIES) {
                listCategory = intent?.extras?.get(LIST_CATEGORIES) as ArrayList<Category>
                for (category in listCategory) {
                    val text = binding.favoritesTextView.text.toString() + "\n" + category.categoryName + " " + category.items.size
                    binding.favoritesTextView.text = text
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        context?.unregisterReceiver(loadResultsReceiver)
    }
}