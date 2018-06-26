package com.example.android.budgetproject

import android.annotation.SuppressLint
import android.os.AsyncTask
import android.widget.Toast

@SuppressLint("StaticFieldLeak")
class TransactionCreation {

    val db = MyDatabase

    fun transactionCreation(depense: Float, description: Int, date: String){
        val newTransaction = Transaction(depense, description, date)

        object : AsyncTask<Void, Void, Void>() {
            override fun doInBackground(vararg params: Void?): Void? {
                db.getInstance().transactionDao().insertTransaction(newTransaction)
                return null
            }

            override fun onPostExecute(result: Void?) {
                super.onPostExecute(result)
                Toast.makeText(BudgetApplication.getContext(), "Transaction saved", Toast.LENGTH_SHORT).show()
            }
        }.execute()
    }

    fun transactionDeletation(uid: Long){
        val currentTransaction = Transaction(10F,0,"", uid)

        object : AsyncTask<Void, Void, Void>() {
            override fun doInBackground(vararg params: Void?): Void? {
                db.getInstance().transactionDao().deleteTransaction(currentTransaction)
                return null
            }

            override fun onPostExecute(result: Void?) {
                super.onPostExecute(result)
                Toast.makeText(BudgetApplication.getContext(), "Transaction deleted", Toast.LENGTH_SHORT).show()
            }
        }.execute()
    }

    fun transactionUpdatation(depense: Float, description: Int, date: String, uid: Long){
        val oldTransaction = Transaction(depense, description, date, uid)

        object : AsyncTask<Void, Void, Void>() {
            override fun doInBackground(vararg params: Void?): Void? {
                db.getInstance().transactionDao().updateTransaction(oldTransaction)
                return null
            }

            override fun onPostExecute(result: Void?) {
                super.onPostExecute(result)
                Toast.makeText(BudgetApplication.getContext(), "Transaction updated", Toast.LENGTH_SHORT).show()
            }
        }.execute()
    }
}