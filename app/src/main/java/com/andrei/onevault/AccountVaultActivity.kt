package com.andrei.onevault

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.andrei.onevault.fragment.DeleteDialogFragment
import com.andrei.onevault.fragment.VerifyUserDialogFragment
import kotlinx.android.synthetic.main.account_vault_layout.*

class AccountVaultActivity : AppCompatActivity() {

    private lateinit var accountTV: TextView
    private lateinit var passwordTV: TextView   //temp textView for testing purposes

    private lateinit var bundle: Bundle
    private lateinit var acctUserId: String

    private lateinit var delFragment: DeleteDialogFragment
    private lateinit var verifyUserFragment: VerifyUserDialogFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.account_vault_layout)

        accountTV = findViewById(R.id.account_name_tv)
        accountTV.setText(intent.getStringExtra("TITLE"))

        passwordTV = findViewById(R.id.textView2)
        passwordTV.setText((intent.getStringExtra("DESC")))

        val fm = supportFragmentManager
        bundle = Bundle()
        acctUserId = intent.getStringExtra("ACCT_ID").toString()

        delete_btn.setOnClickListener {
            bundle.putString("ACCT_ID", acctUserId)
            delFragment = DeleteDialogFragment()
            delFragment.arguments = bundle
            delFragment.show(fm, "Delete Fragment")
        }

        reveal_btn.setOnClickListener {
            bundle.putString("ACCT_ID", acctUserId)
            verifyUserFragment = VerifyUserDialogFragment()
            verifyUserFragment.arguments = bundle
            verifyUserFragment.show(fm, "User Verify Fragment")
        }

    }

}