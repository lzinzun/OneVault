package com.andrei.onevault.dao.impl

import com.andrei.onevault.dao.AccountDataDao
import com.andrei.onevault.model.Account
import io.realm.Realm
import io.realm.RealmQuery
import io.realm.RealmResults
import java.util.*

class AccountDataDaoImpl: AccountDataDao {

    private lateinit var realm:Realm

    override fun addAccount(account: Account): Boolean {

        realm = Realm.getDefaultInstance()

        return try{

            realm.beginTransaction()

            account.id = UUID.randomUUID().mostSignificantBits.toInt()

            //copy to DB
            realm.insertOrUpdate(account)
            realm.commitTransaction()

            realm.close()
            true

        }catch (ex:Exception){
            realm.close()
            false
        }
    }

    override fun deleteAccount(accountId: String): Boolean {

        realm = Realm.getDefaultInstance()

        return try{

            val query: RealmQuery<Account> = realm.where<Account>(Account::class.java).equalTo("id", accountId.toInt())
            val result: RealmResults<Account> = query.findAll()

            val account: RealmResults<Account> = result


            realm.executeTransaction{realm ->
                account.deleteAllFromRealm()
            }
            realm.close()
            true

        }catch(ex:Exception){

            realm.close()
            false
        }
    }
}