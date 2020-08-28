package com.andrei.onevault.dao

import com.andrei.onevault.model.Account
import io.realm.Realm

interface AccountDataDao {
    fun addAccount(account: Account): Boolean
    fun deleteAccount(accountId: String): Boolean
}