package ru.samitin.allmovies.view

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton
import ru.samitin.allmovies.R
import ru.samitin.allmovies.R.color.buttons_bugrount_color
import ru.samitin.allmovies.databinding.MainActivityBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binder:MainActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binder= MainActivityBinding.inflate(layoutInflater)
        setContentView(binder.root)

        if (savedInstanceState == null) {
            showFragment(HomeFragment())
            setColorClickButton(binder.buttonHome)
        }
        initView()
    }
    private fun initView(){
        setSupportActionBar(binder.toolbar)
        clickNavigateButtons()
    }
    private fun clickNavigateButtons(){
        binder.buttonHome.setOnClickListener {
            setColorClickButton(binder.buttonHome)
            showFragment(HomeFragment()) }
        binder.buttonFavorite.setOnClickListener {
            setColorClickButton( binder.buttonFavorite)
            showFragment(FavoritesFragment()) }
        binder.buttonRatings.setOnClickListener {
            setColorClickButton(binder.buttonRatings)
            showFragment(RatingsFragment()) }
    }

        private fun setColorClickButton(button: MaterialButton){
            binder.buttonHome.backgroundTintList=ContextCompat.getColorStateList(this, R.color.white)
            binder.buttonFavorite.backgroundTintList=ContextCompat.getColorStateList(this,R.color.white)
            binder.buttonRatings.backgroundTintList=ContextCompat.getColorStateList(this,R.color.white)
            button.backgroundTintList=ContextCompat.getColorStateList(this,R.color.buttons_bugrount_color)
        }

    private fun showFragment(fragment:Fragment){
        supportFragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commitNow()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
       menuInflater.inflate(R.menu.main,menu)
        val search= menu?.findItem(R.id.action_search)
        (search?.actionView as SearchView).also {
            it.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean{
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

