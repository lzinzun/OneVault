package com.andrei.onevault

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.andrei.onevault.fragment.DeleteDialogFragment
import kotlinx.android.synthetic.main.account_vault_layout.*

class AccountVaultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.account_vault_layout)

        val textView1: TextView = findViewById(R.id.textView1)
        textView1.setText(intent.getStringExtra("TITLE"))

        val textView2: TextView = findViewById(R.id.textView2)
        textView2.setText((intent.getStringExtra("DESC")))

        val fm = supportFragmentManager
        val delFragment = DeleteDialogFragment()

        deleteBtn.setOnClickListener {
            delFragment.show(fm, "Delete Fragment")
        }

    }

}