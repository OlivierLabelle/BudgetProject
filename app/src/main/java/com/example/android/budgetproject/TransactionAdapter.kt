package com.example.android.budgetproject

import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.row_depense_fragment.view.*

/**
 * Created by olivier on 2018-03-13.
 */

class TransactionAdapter(private val clickListener: ButtonClick? = null):
        ListAdapter<Transaction, TransactionAdapter.ViewHolder>(TransactionDiffCallback()) {

    interface ButtonClick{
        fun clicked(id: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater.inflate(R.layout.row_depense_fragment, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        fun bind(transaction: Transaction){
            itemView.tv_depense_description.text = transaction.description
            itemView.tv_depense.text = transaction.depense
            itemView.tv_depense_date.text = transaction.date
            itemView.setOnClickListener{
               clickListener?.clicked(adapterPosition)
            }
        }
    }

    private var transactionList = ArrayList<Transaction>()

    fun addTransaction(list: List<Transaction>){
        transactionList.clear()
        transactionList.addAll(list)
        notifyDataSetChanged()
    }
}


