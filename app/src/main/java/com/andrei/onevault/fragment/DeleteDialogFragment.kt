package com.andrei.onevault.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.andrei.onevault.R

class DeleteDialogFragment : DialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var rootView:View = inflater.inflate(R.layout.delete_dialog_fragment, container, false)
        var confirmButton = rootView.findViewById<Button>(R.id.confirmBtn)
        var cancelButton = rootView.findViewById<Button>(R.id.cancelBtn)

        confirmButton.setOnClickListener(object:View.OnClickListener{
            override fun onClick(p0: View?) {
                //
                Log.e("Whoo we here!", "Oh no?")
                dismiss()
            }
        })

        cancelButton.setOnClickListener(object:View.OnClickListener{
            override fun onClick(p0: View?) {
                dismiss()
            }
        })

        return rootView
    }
}