package com.projekt.zespolowy.fun_organizer.navigation

import android.app.AlertDialog
import android.app.Dialog
import android.app.Fragment
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import android.preference.PreferenceManager.getDefaultSharedPreferences
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.core.content.edit
import com.projekt.zespolowy.fun_organizer.MyApplication
import com.projekt.zespolowy.fun_organizer.R
import com.projekt.zespolowy.fun_organizer.eventList.EventListFragment
import com.projekt.zespolowy.fun_organizer.eventList.EventsAcceptedFragment
import com.projekt.zespolowy.fun_organizer.friends.FriendsListFragment
import com.projekt.zespolowy.fun_organizer.friendsListEvent.AddFriendsToEventActivity
import com.projekt.zespolowy.fun_organizer.invitations.InvitationsFragment
import com.projekt.zespolowy.fun_organizer.login.LoginActivity
import com.projekt.zespolowy.fun_organizer.utils.ApiProvider
import com.projekt.zespolowy.fun_organizer.utils.SchedulersProvider
import kotlinx.android.synthetic.main.activity_navigation.*
import kotlinx.android.synthetic.main.app_bar_navigation.*
import kotlinx.android.synthetic.main.nav_header_navigation.*


class NavigationActivity : AppCompatActivity(),
        NavigationView.OnNavigationItemSelectedListener {

    private val defaultSharedPreferences = getDefaultSharedPreferences(MyApplication.appContext)
    private lateinit var navigatorViewPresenter: NavigatorViewPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        navigatorViewPresenter = NavigatorViewPresenter(NavigationActivityUseCase(ApiProvider.instance), SchedulersProvider())
    }

    override fun onStart() {
        super.onStart()
        var fragment = EventListFragment()
        val ft = fragmentManager.beginTransaction()
        ft.replace(R.id.fragment_placeholder, fragment)
        ft.commit()

        var nr = defaultSharedPreferences.getString("phoneNumber", "Missing")
        var name = defaultSharedPreferences.getString("name", "Missing")
        var surname = defaultSharedPreferences.getString("surname", "Missing")
        var email = defaultSharedPreferences.getString("email", "Missing")

        if (email.equals("") || email.equals("Missing")) {
            navigatorViewPresenter.getUserData()
            nr = defaultSharedPreferences.getString("phoneNumber", "Missing")
            name = defaultSharedPreferences.getString("name", "Missing")
            surname = defaultSharedPreferences.getString("surname", "Missing")
            email = defaultSharedPreferences.getString("email", "Missing")
        }

        val navigationView = findViewById<View>(R.id.nav_view) as NavigationView
        val headerView = navigationView.getHeaderView(0)
        val userEmail = headerView.findViewById(R.id.navigator_email) as TextView
        val userName = headerView.findViewById(R.id.navigator_user_name) as TextView

        userName.text = name + " " + surname
        userEmail.text = email

        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            //drawer is open
            if (userEmail.text.equals("") || userEmail.text.equals("Missing")) {
                navigatorViewPresenter.getUserData()
                nr = defaultSharedPreferences.getString("phoneNumber", "Missing")
                name = defaultSharedPreferences.getString("name", "Missing")
                surname = defaultSharedPreferences.getString("surname", "Missing")
                email = defaultSharedPreferences.getString("email", "Missing")

                userName.text = name + " " + surname
                userEmail.text = email
            }
        }
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

        //to poniżej trzeba podpiąć pod wysunięcie naavigator bar, ale nie wiem jak
        if (navigator_email.text.equals("") || navigator_email.text.equals("Missing")) {
            navigatorViewPresenter.getUserData()
            val nr = defaultSharedPreferences.getString("phoneNumber", "Missing")
            val name = defaultSharedPreferences.getString("name", "Missing")
            val surname = defaultSharedPreferences.getString("surname", "Missing")
            val email = defaultSharedPreferences.getString("email", "Missing")

            navigator_user_name.text = name + " " + surname
            navigator_email.text = email
        }

        when (item.itemId) {
            R.id.nav_news -> fragment = InvitationsFragment()

            R.id.nav_upcoming_events -> fragment = EventsAcceptedFragment()
        /*{
        val navigatorActivity = Intent(this, AddFriendsToEventActivity::class.java)
        startActivity(navigatorActivity)
    }*/

            R.id.nav_friends -> {
                fragment = FriendsListFragment()
            }

            R.id.nav_your_events -> fragment = EventListFragment()

            /*R.id.nav_share -> {
                val navigatorActivity = Intent(this, AddFriendsToEventActivity::class.java)
                startActivity(navigatorActivity)
            }*/
            R.id.logout -> {
                var builder = onCreateDialog()
                builder.show()
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

    fun onCreateDialog(): Dialog {

        //builder dialogu
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Are you sure to log out?")
                .setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, id ->
                    var sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MyApplication.appContext)
                    sharedPreferences.edit {
                        putString("Authorization", "not")
                    }
                    var userData = PreferenceManager.getDefaultSharedPreferences(MyApplication.appContext)
                    userData.edit {
                        putString("name", "")
                        putString("surname", "")
                        putString("phoneNumber", "")
                        putString("email", "")
                    }

                    val logoutActivity = Intent(this, LoginActivity::class.java)
                    startActivity(logoutActivity)
                })
                .setNegativeButton("no", DialogInterface.OnClickListener { dialog, id ->
                    // Anuluj
                })
        return builder.create()
    }

}
