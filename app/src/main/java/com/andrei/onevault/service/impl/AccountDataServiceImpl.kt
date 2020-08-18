package com.andrei.onevault.service.impl

import android.content.Intent
import android.widget.Toast
import com.andrei.onevault.OpenVaultActivity
import com.andrei.onevault.dao.AccountDataDao
import com.andrei.onevault.model.Account
import com.andrei.onevault.service.AccountDataService
import com.andrei.onevault.dao.impl.AccountDataDaoImpl
import io.realm.Realm

class AccountDataServiceImpl:AccountDataService {

    private lateinit var acctDataDao :AccountDataDaoImpl

    override fun addAccount(account: Account): Boolean {

        acctDataDao = AccountDataDaoImpl()
        return acctDataDao.addAccount(account)

    }


}