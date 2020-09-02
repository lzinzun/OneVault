package com.andrei.onevault

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.andrei.onevault.constant.FragmentConstants
import com.andrei.onevault.constant.ModelConstants
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
        accountTV.setText(intent.getStringExtra(ModelConstants.ACCOUNT_TITLE))

        passwordTV = findViewById(R.id.textView2)
        passwordTV.setText((intent.getStringExtra(ModelConstants.ACCOUNT_DATA)))

        val fm = supportFragmentManager
        bundle = Bundle()
        acctUserId = intent.getStringExtra(ModelConstants.ACCOUNT_ID).toString()

        delete_btn.setOnClickListener {
            bundle.putString(ModelConstants.ACCOUNT_ID, acctUserId)
            delFragment = DeleteDialogFragment()
            delFragment.arguments = bundle
            delFragment.show(fm, FragmentConstants.DELETE_FRAGMENT)
        }

        reveal_btn.setOnClickListener {
            bundle.putString(ModelConstants.ACCOUNT_ID, acctUserId)
            verifyUserFragment = VerifyUserDialogFragment()
            verifyUserFragment.arguments = bundle
            verifyUserFragment.show(fm, FragmentConstants.VERIFY_USER_FRAGMENT)
        }

    }

}