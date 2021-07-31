package ru.samitin.allmovies.view

import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.button.MaterialButton
import ru.samitin.allmovies.R
import ru.samitin.allmovies.databinding.MainActivityBinding


class MainActivity : AppCompatActivity() {


    private lateinit var binder:MainActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binder= MainActivityBinding.inflate(layoutInflater)
        setContentView(binder.root)


        if (savedInstanceState == null) {
            showFragment(HomeFragment())
            setColorBackgrountButton(binder.buttonHome)
        }
        initView()

    }
    private fun initView(){
        setSupportActionBar(binder.toolbar)
        clickNavigateButtons()
    }
    private fun clickNavigateButtons(){
        binder.buttonHome.setOnClickListener {
            showFragment(HomeFragment())
            setColorBackgrountButton(binder.buttonHome)}
        binder.buttonFavorite.setOnClickListener {
            showFragment(FavoritesFragment())
            setColorBackgrountButton(binder.buttonFavorite)}
        binder.buttonRatings.setOnClickListener {
            showFragment(RatingsFragment())
            setColorBackgrountButton(binder.buttonRatings)}


    }

        fun setColorBackgrountButton(button:MaterialButton){
                    binder.buttonHome.backgroundTintList = ContextCompat.getColorStateList(this, R.color.white)
                    binder.buttonFavorite.backgroundTintList = ContextCompat.getColorStateList(this, R.color.white)
                    binder.buttonRatings.backgroundTintList = ContextCompat.getColorStateList(this, R.color.white)
                    button.backgroundTintList = ContextCompat.getColorStateList(this, R.color.buttons_bugrount_color)
        }






    private fun showFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
       menuInflater.inflate(R.menu.main, menu)
        val search= menu?.findItem(R.id.action_search)
        (search?.actionView as SearchView).also {
            it.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    Toast.makeText(this@MainActivity, query, Toast.LENGTH_SHORT).show()
                    return true
                }

                override fun onQueryTextChange(p0: String?): Boolean {
                    return true
                }
            })
        }
        return true
    }


}

