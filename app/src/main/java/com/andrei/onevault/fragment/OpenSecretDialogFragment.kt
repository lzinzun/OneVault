package com.andrei.onevault.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.andrei.onevault.R
import com.andrei.onevault.constant.ModelConstants
import com.andrei.onevault.model.Account
import com.andrei.onevault.service.impl.AccountDataServiceImpl
import com.andrei.onevault.util.AESEncryptionUtil

class OpenSecretDialogFragment : DialogFragment() {

    private lateinit var secretTV: TextView
    private lateinit var accountDataService: AccountDataServiceImpl
    private lateinit var aesEncryptionUtil: AESEncryptionUtil

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var rootView: View =
            inflater.inflate(R.layout.open_secret_dialog_fragment, container, false)
        var doneButton = rootView.findViewById<Button>(R.id.done_btn)
        var acctUserId: String = arguments?.get(ModelConstants.ACCOUNT_ID).toString()
        secretTV = rootView.findViewById(R.id.revealed_secret_tv)

        val revealedData = getEncryptedData(acctUserId)
        secretTV.setText(revealedData)

        doneButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                dismiss()
            }
        })

        return rootView
    }

    private fun getEncryptedData(acctUserId: String): String {

        accountDataService = AccountDataServiceImpl()
        var account: Account = accountDataService.getAccount(acctUserId)
        var encryptedData = account.desc

        aesEncryptionUtil = AESEncryptionUtil()
        var decryptedData =
            aesEncryptionUtil.decrypt("662ede816988e58fb6d057d9d85605e0", encryptedData)

        return decryptedData!!
    }
}