package ru.samitin.allmovies.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import ru.samitin.allmovies.R
import ru.samitin.allmovies.databinding.MainActivityBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binder:MainActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binder= MainActivityBinding.inflate(layoutInflater)
        setContentView(binder.root)
        /*if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow()
        }*/
        initView()
    }
    private fun initView(){
        setSupportActionBar(binder.toolbar)
       // initDrawer(binder.toolbar)
}
   /* private fun initDrawer(toolbar: Toolbar){
        val toggle=ActionBarDrawerToggle(this
            ,binder.drawerLayout
            ,toolbar
            ,R.string.navigation_drawer_open
            ,R.string.navigation_drawer_close)
        binder.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
    }*/

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

