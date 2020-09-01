package com.andrei.onevault

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import io.realm.Realm
import io.realm.RealmConfiguration
import kotlinx.android.synthetic.main.login_main.*

class LoginActivity : AppCompatActivity() {

    private lateinit var username: String
    private lateinit var password: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_main)

        login_btn.setOnClickListener {
            username = email_login_et.text.toString()
            password = password_login_et.text.toString()

            FirebaseAuth.getInstance().signInWithEmailAndPassword(username, password)
                .addOnCompleteListener {
                    val intent = Intent(this, OpenVaultActivity::class.java)
                    startActivity(intent)
                }
                .addOnFailureListener {
                    Toast.makeText(
                        this,
                        "Either email or password is wrong. Please try again.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
        }

        signup_tv.setOnClickListener() {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)

        }
    }
}