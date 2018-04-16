package com.example.android.budgetproject

import android.support.v7.util.DiffUtil

class TransactionDiffCallback : DiffUtil.ItemCallback<Transaction>() {
    override fun areItemsTheSame(oldItem: Transaction?, newItem: Transaction?): Boolean {
        return oldItem?.uid == newItem?.uid
    }

    override fun areContentsTheSame(oldItem: Transaction?, newItem: Transaction?): Boolean {
        return oldItem == newItem
    }
}