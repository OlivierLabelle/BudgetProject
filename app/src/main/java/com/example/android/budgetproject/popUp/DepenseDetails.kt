package com.example.android.budgetproject.popUp

import android.annotation.SuppressLint
import android.app.Activity
import android.content.ContentValues
import android.content.Context
import android.content.DialogInterface
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.WindowManager
import android.widget.*
import com.example.android.budgetproject.*
import org.w3c.dom.Text
import java.text.NumberFormat

/**
 * Created by olivier on 2018-02-07.
 */
object DepenseDetails {

    @SuppressLint("InflateParams")
    fun createTransaction(context: Activity): AlertDialog {
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
        alertDialog.show()
        return alertDialog.create()
    }
}