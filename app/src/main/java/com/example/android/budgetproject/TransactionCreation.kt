package com.example.android.budgetproject

import android.os.AsyncTask
import android.widget.Toast

class TransactionCreation {

    fun transactionCreation(depense: String, description: String, date: String){
        val newTransaction = Transaction(depense, description, date)
        val db = MyDatabase

        object : AsyncTask<Void, Void, Void>() {
            override fun doInBackground(vararg params: Void?): Void? {
                db.getInstance().transactionDao().insertTransaction(newTransaction)
                return null
            }

            override fun onPostExecute(result: Void?) {
                super.onPostExecute(result)
                Toast.makeText(BudgetApplication.getContext(), "Transaction saved", Toast.LENGTH_SHORT)
            }
        }.execute()
    }
}