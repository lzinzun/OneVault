package com.andrei.onevault.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.andrei.onevault.OpenVaultActivity
import com.andrei.onevault.R
import com.andrei.onevault.RegisterActivity
import com.andrei.onevault.service.impl.AccountDataServiceImpl

class DeleteDialogFragment : DialogFragment() {

    private lateinit var accountDataService: AccountDataServiceImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var rootView: View = inflater.inflate(R.layout.delete_dialog_fragment, container, false)
        var confirmButton = rootView.findViewById<Button>(R.id.confirm_btn)
        var cancelButton = rootView.findViewById<Button>(R.id.cancel_btn)

        var acctId: String = arguments?.get("ACCT_ID").toString()

        confirmButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                accountDataService = AccountDataServiceImpl()
                var deleted: Boolean = accountDataService.deleteAccount(acctId)
                if (deleted) {
                    Log.e("Result", "Deleted!")
                } else {
                    Log.e("Result", "Sorry not the case")
                }

                val intent = Intent(context, OpenVaultActivity::class.java)
                context?.startActivity(intent)
            }
        })

        cancelButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                dismiss()
            }
        })

        return rootView
    }
}