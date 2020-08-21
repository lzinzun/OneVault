package com.andrei.onevault

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.andrei.onevault.adapter.AccountAdapter
import com.andrei.onevault.model.Account
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.RealmQuery
import io.realm.RealmResults

class OpenVaultActivity : AppCompatActivity(){

    private lateinit var addAccount: FloatingActionButton
    private lateinit var accountRV: RecyclerView
    private lateinit var accountList: ArrayList<Account>
    private lateinit var realm: Realm

    private lateinit var firebaseUser: FirebaseUser
    private lateinit var firebaseAuth:FirebaseAuth




    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.open_vault_layout)

        //init Realm
        Realm.init(this)
        val conf = RealmConfiguration.Builder()
            .name("Accounts.db")
            .deleteRealmIfMigrationNeeded()
            .schemaVersion(0)
            .build()
        Realm.setDefaultConfiguration(conf)
        //you can add encryption key here too with .encryptionKey()


        //init Views
        realm = Realm.getDefaultInstance()
        addAccount = findViewById(R.id.addAccountBtn)
        accountRV = findViewById(R.id.accountRV)

        addAccount.setOnClickListener {
            realm.close()
            startActivity(Intent(this, AddAccountActivity::class.java))
            finish()
        }

        accountRV.layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        getAllAccounts()
    }

    private fun getAllAccounts(){

        accountList = ArrayList()

        firebaseAuth = FirebaseAuth.getInstance()
        firebaseUser = firebaseAuth.currentUser!!
        val query: RealmQuery<Account> = realm.where<Account>(Account::class.java).equalTo("userID", firebaseUser.uid)
        val results:RealmResults<Account> = query.findAll()


        accountRV.adapter = AccountAdapter(this, results)

        accountRV.adapter!!.notifyDataSetChanged()


    }

}