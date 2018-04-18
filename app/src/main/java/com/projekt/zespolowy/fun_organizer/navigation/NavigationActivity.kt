package com.projekt.zespolowy.fun_organizer.navigation

import android.app.Fragment
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.projekt.zespolowy.fun_organizer.R
import com.projekt.zespolowy.fun_organizer.eventList.EventListFragment
import com.projekt.zespolowy.fun_organizer.friends.FriendsListFragment
import kotlinx.android.synthetic.main.activity_navigation.*
import kotlinx.android.synthetic.main.app_bar_navigation.*

class NavigationActivity : AppCompatActivity(),
        NavigationView.OnNavigationItemSelectedListener {

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
            R.id.nav_camera -> fragment = BlankFragment2()

            R.id.nav_gallery -> fragment = EventListFragment()

            R.id.nav_slideshow -> fragment = FriendsListFragment()
            R.id.nav_manage -> {
            }
            R.id.nav_share -> {
            }
            R.id.nav_send -> {
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
