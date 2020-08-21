package com.andrei.onevault.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.andrei.onevault.AddAccountActivity
import com.andrei.onevault.R
import com.andrei.onevault.model.Account
import io.realm.RealmResults
import kotlinx.android.synthetic.main.account_rv_layout.view.*

class AccountAdapter(private val context: Context?, private val accountList:RealmResults<Account>)
    :RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private lateinit var mContext:Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        if (context != null) {
            this.mContext = context
        }

        val v = LayoutInflater.from(parent.context).inflate(R.layout.account_rv_layout, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return accountList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.itemView.title_tv.text = accountList[position]!!.title
        holder.itemView.desc_tv.text = accountList[position]!!.desc

        holder.itemView.cardAccounts.setOnClickListener{
            this.mContext.startActivity(Intent(mContext, AddAccountActivity::class.java))
        }

    }

    class ViewHolder(v:View?): RecyclerView.ViewHolder(v!!){
        val title = itemView.findViewById<TextView>(R.id.title_tv)
        val desc = itemView.findViewById<TextView>(R.id.desc_tv)
        val cardView = itemView.findViewById<CardView>(R.id.cardAccounts)

    }


}