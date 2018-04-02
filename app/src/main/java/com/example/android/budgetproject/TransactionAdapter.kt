package com.example.android.budgetproject

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListAdapter
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.row_depense_fragment.view.*

/**
 * Created by olivier on 2018-03-13.
 */

class TransactionAdapter(private val clickListener: (Transaction) -> Unit):
        ListAdapter<Transaction>(TransactionDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater.inflate(R.layout.row_depense_fragment, parent, false)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        fun bind(transaction: Transaction, clickListener: (Transaction) -> Unit){
            itemView.tv_depense_description.text = transaction.description
            itemView.tv_depense.text = transaction.depense
            itemView.tv_depense_date.text = transaction.date
            itemView.setOnClickListener{clickListener(transaction)}
        }
    }
}


