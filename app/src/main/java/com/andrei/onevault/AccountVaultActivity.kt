package com.andrei.onevault

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.andrei.onevault.fragment.DeleteDialogFragment
import kotlinx.android.synthetic.main.account_vault_layout.*

class AccountVaultActivity : AppCompatActivity() {

    private lateinit var acctUserId:String

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.account_vault_layout)

        val textView1: TextView = findViewById(R.id.textView1)
        textView1.setText(intent.getStringExtra("TITLE"))

        val textView2: TextView = findViewById(R.id.textView2)
        textView2.setText((intent.getStringExtra("DESC")))

        val fm = supportFragmentManager
        val bundle:Bundle = Bundle()

        acctUserId = intent.getStringExtra("ACCT_ID").toString()

        deleteBtn.setOnClickListener {
            bundle.putString("ACCT_ID", acctUserId)
            val delFragment = DeleteDialogFragment()
            delFragment.arguments = bundle
            delFragment.show(fm, "Delete Fragment")
            //startActivity(Intent(this, OpenVaultActivity::class.java))
            //finish()
        }

    }

}