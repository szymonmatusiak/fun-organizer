package com.projekt.zespolowy.fun_organizer.navigation

import android.app.Fragment
import android.content.Context
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.widget.TextView
import com.projekt.zespolowy.fun_organizer.R
import com.projekt.zespolowy.fun_organizer.eventList.EventListFragment
import com.projekt.zespolowy.fun_organizer.friends.FriendsListFragment
import kotlinx.android.synthetic.main.activity_navigation.*
import kotlinx.android.synthetic.main.app_bar_navigation.*
import kotterknife.bindView


class NavigationActivity : AppCompatActivity(),
        NavigationView.OnNavigationItemSelectedListener {

    private val userEmail: TextView by bindView(R.id.navigator_email)
    private val userName: TextView by bindView(R.id.navigator_user_name)

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

        //userEmail.text = "TEST"
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
                val prefs = getSharedPreferences("userData", Context.MODE_PRIVATE)
                /*toast(prefs.getString("name", "Cannot read name") +
                        prefs.getString("surname", "Cannot read surname") +
                prefs.getString("email", "Cannot read email") +
                prefs.getString("phoneNumber", "Cannot read phoneNumber")
                )*/

                userEmail.text = prefs.getString("email", "Cannot read email")
                userName.text = prefs.getString("name", "Missing") + " " + prefs.getString("surname", "Missing")

            }

            R.id.nav_friends -> {
                fragment = FriendsListFragment()
            }

            R.id.nav_your_events -> fragment = EventListFragment()

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
