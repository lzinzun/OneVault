package com.andrei.onevault.dao.impl

import com.andrei.onevault.dao.AccountDataDao
import com.andrei.onevault.model.Account
import io.realm.Realm
import io.realm.RealmQuery
import io.realm.RealmResults
import java.util.*

class AccountDataDaoImpl : AccountDataDao {

    private lateinit var realm: Realm

    override fun addAccount(account: Account): Boolean {

        realm = Realm.getDefaultInstance()

        return try {

            account.id = UUID.randomUUID().mostSignificantBits.toInt()

            realm.beginTransaction()
            realm.insertOrUpdate(account)
            realm.commitTransaction()
            true
        } catch (ex: Exception) {
            false
        } finally {
            realm.close()
        }
    }

    override fun deleteAccount(accountId: String): Boolean {

        realm = Realm.getDefaultInstance()

        return try {

            var query: RealmQuery<Account> =
                realm.where<Account>(Account::class.java).equalTo("id", accountId.toInt())
            var result: RealmResults<Account> = query.findAll()
            var account: RealmResults<Account> = result

            realm.executeTransaction { realm ->
                account.deleteAllFromRealm()
            }

            true
        } catch (ex: Exception) {

            false
        } finally {
            realm.close()
        }
    }

    override fun getAccount(accountId: String): Account {

        realm = Realm.getDefaultInstance()

        try {

            var query: RealmQuery<Account> =
                realm.where<Account>(Account::class.java).equalTo("id", accountId.toInt())
            var result: RealmResults<Account> = query.findAll()
            var account: RealmResults<Account> = result

            return account[0]!!

        } catch (ex: Exception) {
            return null!!

        } finally {
            realm.close()
        }
    }
}