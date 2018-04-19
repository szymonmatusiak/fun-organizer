package com.projekt.zespolowy.fun_organizer.newFriend

import com.projekt.zespolowy.fun_organizer.utils.ApiProvider

class NewFriendUseCase(private val apiProvider: ApiProvider) {
    fun searchWithPhoneNumber(phone: String) = apiProvider.searchWithPhoneNumber(phone)
    fun searchWithMail(mail: String) = apiProvider.searchWithMail(mail)
    fun searchWithContactList(contactList: List<String>) = apiProvider.searchWithContactList(contactList)
}
