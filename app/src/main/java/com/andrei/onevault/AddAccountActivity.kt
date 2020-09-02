package com.andrei.onevault

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.andrei.onevault.model.Account
import com.andrei.onevault.service.impl.AccountDataServiceImpl
import com.andrei.onevault.util.AESEncryptionUtil
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class AddAccountActivity : AppCompatActivity() {

    private lateinit var accountED: EditText
    private lateinit var passwordED: EditText
    private lateinit var savePasswordButton: Button

    private lateinit var firebaseUser: FirebaseUser
    private lateinit var firebaseAuth: FirebaseAuth

    private lateinit var account: Account
    private lateinit var accountDataService: AccountDataServiceImpl
    private lateinit var aesEncryptor: AESEncryptionUtil

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_account_layout)

        accountED = findViewById(R.id.account_title_et)
        passwordED = findViewById(R.id.bare_data_et)
        savePasswordButton = findViewById(R.id.save_password_btn)

        savePasswordButton.setOnClickListener {
            addPasswordToDB()
        }
    }

    private fun addPasswordToDB() {

        try {

            firebaseAuth = FirebaseAuth.getInstance()
            firebaseUser = firebaseAuth.currentUser!!

            account = Account()
            account.title = accountED.text.toString()
            aesEncryptor = AESEncryptionUtil()
            account.desc =
                aesEncryptor.encrypt(passwordED.text.toString(), "662ede816988e58fb6d057d9d85605e0")
            account.userID = firebaseUser.uid


            accountDataService = AccountDataServiceImpl()
            var isStored: Boolean = accountDataService.addAccount(account)

            if (isStored) {
                Toast.makeText(this, "Account Added Successfully!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Failed account insertion.", Toast.LENGTH_SHORT).show()
            }

            startActivity(Intent(this, OpenVaultActivity::class.java))
            finish()

        } catch (ex: Exception) {
            Toast.makeText(this, "Failed $ex ", Toast.LENGTH_SHORT).show()
        }
    }
}