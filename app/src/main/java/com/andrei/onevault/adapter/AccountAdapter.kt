package com.andrei.onevault.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.andrei.onevault.AccountVaultActivity
import com.andrei.onevault.R
import com.andrei.onevault.model.Account
import io.realm.RealmResults
import kotlinx.android.synthetic.main.account_rv_layout.view.*

class AccountAdapter(
    private val context: Context?,
    private val accountList: RealmResults<Account>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private lateinit var mContext: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        if (context != null) {
            this.mContext = context
        }

        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.account_rv_layout, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return accountList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.itemView.title_tv.text = accountList[position]!!.title
        holder.itemView.desc_tv.text = accountList[position]!!.desc

        holder.itemView.cardAccounts.setOnClickListener {
            var intent: Intent = Intent(mContext, AccountVaultActivity::class.java)
            intent.putExtra("TITLE", accountList[position]!!.title)
            intent.putExtra("DESC", accountList[position]!!.desc)
            intent.putExtra("ACCT_ID", accountList[position]!!.id.toString())
            this.mContext.startActivity(intent)
        }

    }

    class ViewHolder(v: View?) : RecyclerView.ViewHolder(v!!) {
        val title = itemView.findViewById<TextView>(R.id.title_tv)
        val desc = itemView.findViewById<TextView>(R.id.desc_tv)
        val cardView = itemView.findViewById<CardView>(R.id.cardAccounts)

    }


}