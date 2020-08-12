package com.andrei.onevault

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.andrei.onevault.model.Account
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import io.realm.Realm

class AddAccountActivity : AppCompatActivity() {

    private lateinit var titleED:EditText
    private lateinit var descED:EditText
    private  lateinit var savePsswdBtn:Button
    private lateinit var realm:Realm
    private lateinit var firebaseUser:FirebaseUser
    private lateinit var firebaseAuth:FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?){

        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_account_layout)

        //init views
        realm = Realm.getDefaultInstance()
        titleED = findViewById(R.id.title_edittext)
        descED = findViewById(R.id.desc_edittext)
        savePsswdBtn = findViewById(R.id.savePasswdBtn)

        savePsswdBtn.setOnClickListener {
            addPasswordToDB()
        }
    }

    private fun addPasswordToDB(){

        try{

            realm.beginTransaction()

            firebaseAuth = FirebaseAuth.getInstance()
            firebaseUser = firebaseAuth.currentUser!!

            val account  = Account()
            account.title = titleED.text.toString()
            account.desc = descED.text.toString()
            account.id = firebaseUser.uid

            //copy to DB
            realm.copyToRealmOrUpdate(account)
            realm.commitTransaction()

            Toast.makeText(this, "Account Added Successfully!", Toast.LENGTH_SHORT).show()

            startActivity(Intent(this, OpenVaultActivity::class.java))
            finish()

        }catch (ex:Exception){
            Toast.makeText(this, "Failed $ex ", Toast.LENGTH_SHORT).show()
        }
    }
}