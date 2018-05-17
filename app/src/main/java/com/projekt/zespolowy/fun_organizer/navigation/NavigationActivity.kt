package com.projekt.zespolowy.fun_organizer.navigation

import android.app.Fragment
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager.getDefaultSharedPreferences
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import com.projekt.zespolowy.fun_organizer.MyApplication
import com.projekt.zespolowy.fun_organizer.R
import com.projekt.zespolowy.fun_organizer.eventList.EventListFragment
import com.projekt.zespolowy.fun_organizer.friends.FriendsListFragment
import com.projekt.zespolowy.fun_organizer.friendsListEvent.AddFriendsToEventActivity
import kotlinx.android.synthetic.main.activity_navigation.*
import kotlinx.android.synthetic.main.app_bar_navigation.*


class NavigationActivity : AppCompatActivity(),
        NavigationView.OnNavigationItemSelectedListener {

    private val defaultSharedPreferences = getDefaultSharedPreferences(MyApplication.appContext)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun onStart() {
        super.onStart()
        var fragment = EventListFragment()
        val ft = fragmentManager.beginTransaction()
        ft.replace(R.id.fragment_placeholder, fragment)
        ft.commit()

        val navigationView = findViewById<View>(R.id.nav_view) as NavigationView
        val headerView = navigationView.getHeaderView(0)
        val userEmail = headerView.findViewById(R.id.navigator_email) as TextView
        val userName = headerView.findViewById(R.id.navigator_user_name) as TextView

        //val prefs = getSharedPreferences("userData", Context.MODE_PRIVATE)
        val nr = defaultSharedPreferences.getString("phoneNumber", "Missing")
        val name = defaultSharedPreferences.getString("name", "Missing")
        val surname = defaultSharedPreferences.getString("surname", "Missing")
        val email = defaultSharedPreferences.getString("email", "Missing")

        userName.text =  name + " " + surname
        userEmail.text =  email
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        var fragment: Fragment? = null

        when (item.itemId) {
            R.id.nav_news -> fragment = BlankFragment2()

            R.id.nav_upcoming_events -> {
                val navigatorActivity = Intent(this, AddFriendsToEventActivity::class.java)
                startActivity(navigatorActivity)
            }

            R.id.nav_friends -> {
                fragment = FriendsListFragment()
            }

            R.id.nav_your_events -> fragment = EventListFragment()

            R.id.nav_share -> {
                val navigatorActivity = Intent(this, AddFriendsToEventActivity::class.java)
                startActivity(navigatorActivity)
            }
            R.id.nav_send -> {
                val navigatorActivity = Intent(this, AddFriendsToEventActivity::class.java)
                startActivity(navigatorActivity)
            }
        }
        if (fragment != null) {
            val ft = fragmentManager.beginTransaction()
            ft.replace(R.id.fragment_placeholder, fragment)
            ft.commit()
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
