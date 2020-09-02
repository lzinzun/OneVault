package com.andrei.onevault.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.andrei.onevault.R
import com.andrei.onevault.constant.FragmentConstants
import com.andrei.onevault.constant.ModelConstants
import com.andrei.onevault.util.AESEncryptionUtil
import com.google.firebase.auth.FirebaseAuth

class VerifyUserDialogFragment : DialogFragment() {

    private lateinit var openSecretFragment: OpenSecretDialogFragment

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var rootView: View =
            inflater.inflate(R.layout.verify_user_dialog_fragment, container, false)
        var passwordRevealButton = rootView.findViewById<Button>(R.id.reveal_password_btn)
        var cancelButton = rootView.findViewById<Button>(R.id.cancelRevealBtn)
        var acctUserId: String = arguments?.get(ModelConstants.ACCOUNT_ID).toString()

        passwordRevealButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {

                val bundle: Bundle = Bundle()
                bundle.putString(ModelConstants.ACCOUNT_ID, acctUserId)

                openSecretFragment = OpenSecretDialogFragment()
                openSecretFragment.arguments = bundle
                openSecretFragment.show(activity!!.supportFragmentManager, FragmentConstants.OPEN_SECRET_FRAGMENT_TAG)
                dismiss()

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