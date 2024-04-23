package com.example.studysync

import Fragments.Home_Page_Fragment
import Fragments.Profile_Page_Fragment
import Fragments.Resources_Page_Fragment
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import screens.Report_Bug_Feature

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    lateinit var bottomBar: BottomNavigationView
    lateinit var drawer : DrawerLayout
    lateinit var navigationView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // policy
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        // fetch toolbar (header)
        var toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        // footer (bottombar, drawer and and main activity
        bottomBar=findViewById(R.id.bottomBar)
        drawer=findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.drawer)

        // onclick on drawer and bottom bar
        navigationView.setNavigationItemSelectedListener(this)
        bottomBar.setOnNavigationItemSelectedListener(bottomNavListner)
        // drawer toggle
        val drawerToggle = ActionBarDrawerToggle(this, drawer, toolbar, R.string.drawer_open, R.string.drawer_close)

        // drawer add listner
        drawer.addDrawerListener(drawerToggle)
        drawerToggle.syncState()

        // by default fragment (home screen)
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, Home_Page_Fragment()).commit()

    }
    // handle when user press back button on main screen
    override fun onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START)
        }else{
            super.onBackPressed()
        }
    }
    // handle when user selects any item in drawer
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.nav_report -> startActivity(Intent(this, Report_Bug_Feature::class.java))
        }
        drawer.closeDrawer(GravityCompat.START)
        return true
    }
    // bottom nav listner
    private val bottomNavListner = BottomNavigationView.OnNavigationItemSelectedListener { item->
        var selectedFragment: Fragment? =null
        when(item.itemId){
            R.id.home_btn -> selectedFragment = Home_Page_Fragment()

            R.id.resources_btn -> selectedFragment = Resources_Page_Fragment()

            R.id.profile_btn -> selectedFragment = Profile_Page_Fragment()
        }
        // replace fragment
        selectedFragment?.let {
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container, it).commit()
            return@OnNavigationItemSelectedListener true
        }
        false
    }
}