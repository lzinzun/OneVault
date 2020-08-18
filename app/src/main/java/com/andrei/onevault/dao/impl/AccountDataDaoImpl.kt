package com.andrei.onevault.dao.impl

import com.andrei.onevault.dao.AccountDataDao
import com.andrei.onevault.model.Account
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
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
            //realm.copyToRealmOrUpdate(account)
            realm.commitTransaction()

            realm.close()
            true

        }catch (ex:Exception){
            realm.close()
            false
        }
    }
}