package com.projekt.zespolowy.fun_organizer.newFriend

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.ContactsContract
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.projekt.zespolowy.fun_organizer.R
import com.projekt.zespolowy.fun_organizer.navigation.NavigationActivity
import com.projekt.zespolowy.fun_organizer.utils.ApiProvider
import com.projekt.zespolowy.fun_organizer.utils.SchedulersProvider
import kotlinx.android.synthetic.main.activity_new_friend.*


class NewFriendActivity : AppCompatActivity(), NewFriendView {

    companion object {
        private const val MY_PERMISSIONS_REQUEST_READ_CONTACTS = 11

    }

    private lateinit var newFriendPresenter: NewFriendPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_friend)
        newFriendPresenter = NewFriendPresenter(NewFriendUseCase(ApiProvider.instance), SchedulersProvider())

    }

    override fun onStart() {
        super.onStart()
        askForPermission()

        newFriendPresenter.onStart(this)
        phoneButton.setOnClickListener {
            newFriendPresenter.searchWithPhoneNumber(phoneInput.text.toString())
        }
        mailButton.setOnClickListener {
            newFriendPresenter.searchWithMail(mailInput.text.toString())
        }
        getAllContacts.setOnClickListener {
            newFriendPresenter.searchWithContactList(getContactList())
        }
    }

    override fun toast(it: String) {
        Toast.makeText(this, it, Toast.LENGTH_LONG).show()
    }

    override fun exitActivity() {
        startActivity(Intent(this, NavigationActivity::class.java))
    }

    //TODO fix this to cover all cases
    private fun askForPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    arrayOf(Manifest.permission.READ_CONTACTS),
                    MY_PERMISSIONS_REQUEST_READ_CONTACTS)

        } else {
            var string = String()
            for (phoneNumber in getContactList()) {
                string += "$phoneNumber "

            }
            Toast.makeText(this, string, Toast.LENGTH_LONG).show()
        }
    }

    //TODO this can't stay like this
    private fun getContactList(): List<String> {
        var list: MutableList<String> = arrayListOf()
        val cr = contentResolver
        val cur = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null)

        if (cur?.count ?: 0 > 0) {
            while (cur != null && cur.moveToNext()) {
                val id = cur.getString(
                        cur.getColumnIndex(ContactsContract.Contacts._ID))
                val name = cur.getString(cur.getColumnIndex(
                        ContactsContract.Contacts.DISPLAY_NAME))

                if (cur.getInt(cur.getColumnIndex(
                                ContactsContract.Contacts.HAS_PHONE_NUMBER)) > 0) {
                    val pCur = cr.query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                            arrayOf(id), null)
                    while (pCur!!.moveToNext()) {
                        val phoneNo = pCur.getString(pCur.getColumnIndex(
                                ContactsContract.CommonDataKinds.Phone.NUMBER))
                        Log.i("1", "Name: $name")
                        Log.i("2", "Phone Number: $phoneNo")
                        list.add(phoneNo)
                    }
                    pCur.close()
                }
            }
        }
        cur?.close()
        return list
    }

}
