package com.andrei.onevault.service

import com.andrei.onevault.model.Account
import io.realm.Realm

internal interface AccountDataService {
    fun addAccount(account: Account): Boolean
    fun deleteAccount(accountId: String): Boolean
    fun getAccount(accountId: String): Account
}