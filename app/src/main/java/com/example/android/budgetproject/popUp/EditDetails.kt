package com.example.android.budgetproject.popUp

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.WindowManager
import android.widget.EditText
import android.widget.RelativeLayout
import com.example.android.budgetproject.R
import com.example.android.budgetproject.TransactionCreation

object EditDetails {

    @SuppressLint("InflateParams")
    fun editTransaction(context: Activity): AlertDialog {
        val alertDialog = AlertDialog.Builder(context)
        val layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val alertLayout = layoutInflater.inflate(R.layout.new_transaction, null, false) as RelativeLayout
        alertDialog.setView(alertLayout)
        context.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE)
        val edDepense = alertLayout.findViewById<EditText>(R.id.ed_montant)
        val edDescription = alertLayout.findViewById<EditText>(R.id.ed_description)
        val edDate = alertLayout.findViewById<EditText>(R.id.ed_date)
        alertDialog.setPositiveButton(R.string.create_new, { dialog, which ->
            val depense = edDepense.text.toString()
            val description = edDescription.text.toString()
            val date = edDate.text.toString()
            TransactionCreation().transactionCreation(depense, description, date)
            //Todo Assurer 2 champs sont valide.
        })
        alertDialog.setNegativeButton(R.string.cancel, { dialog, which ->
        })
        alertDialog.setNeutralButton(R.string.delete, { dialog, which ->
            TransactionCreation().transactionDeletation()
        })
        alertDialog.show()
        return alertDialog.create()
    }
}