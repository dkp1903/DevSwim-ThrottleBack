package app.dkp1903.throttleback.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.widget.Toolbar
import app.dkp1903.throttleback.fragments.MainScreenFragment
import kotlinx.android.synthetic.main.activity_main.view.*
//import app.dkp1903.throttleback.R

class MainActivity : AppCompatActivity() {
    var drawerLayout: DrawerLayout?=null
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)//this is an object of Toolbar class
        setSupportActionBar(toolbar)//setting toolbar as the default toolbar
        drawerLayout = findViewById(R.id.drawer_layout)
        val toggle = ActionBarDrawerToggle(this@MainActivity, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        //responsible for the hamburger icon
        drawerLayout?.setDrawerListener(toggle)
        toggle.syncState()//animating the hamburger icon when it is clicked

        val mainScreenFragment = MainScreenFragment()//an object of MSF class
        //to make a fragment appear on the screen we need a manager, their inceptions their layouts etc
        this.supportFragmentManager()
                .beginTransaction()
                .add(R.id.details_fragment, mainScreenFragment, "MainScreenFragment")
                .commit()
        var navigation_recycler_view = findViewById<RecyclerView>(R.id.navigation_recycler_view)
        navigation_recycler_view.LayoutManager = LinearLayoutManager(this)
        navigation_recycler_view.itemAnimator = DefaultItemAnimator()

    }

    override fun onStart() {
        super.onStart()
    }
}
