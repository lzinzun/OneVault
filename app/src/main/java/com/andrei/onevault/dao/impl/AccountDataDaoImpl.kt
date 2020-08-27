package com.andrei.onevault.dao.impl

import com.andrei.onevault.dao.AccountDataDao
import com.andrei.onevault.model.Account
import io.realm.Realm
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

    override fun deleteAccount(account: Account): Boolean {

        realm = Realm.getDefaultInstance()

        return try{

            realm.executeTransaction{realm ->
                account?.deleteFromRealm()
            }
            realm.close()
            true

        }catch(ex:Exception){

            realm.close()
            false
        }
    }
}