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

    lateinit var transactionAdapter: TransactionAdapter

    @SuppressLint("InflateParams")
    fun createTransaction(context: Activity): AlertDialog {
        val alertDialog = AlertDialog.Builder(context)
        val layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val alertLayout = layoutInflater.inflate(R.layout.new_transaction, null, false) as RelativeLayout
        alertDialog.setView(alertLayout)
        context.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE)
        val edMontant = alertLayout.findViewById<EditText>(R.id.ed_montant)
        val edDescription = alertLayout.findViewById<EditText>(R.id.ed_description)
        val edDate = alertLayout.findViewById<EditText>(R.id.ed_date)
        val transaction = ContentValues()
        val db = TransactionDBHelper(BudgetApplication.getContext()).writableDatabase
        alertDialog.setPositiveButton(R.string.create_new, DialogInterface.OnClickListener { dialog, which ->
            val montant = edMontant.text.toString()
            val description = edDescription.text.toString()
            val date = edDate.text.toString()
            transaction.put("Depense", montant)
            transaction.put("Description", description)
            transaction.put("Date", date)
            db.insert("TN_Transaction", null, transaction)
            transactionAdapter.notfier()

            //Todo Assurer 2 champs sont valide.
            //Todo Saver les donners dans la DB
        })
        alertDialog.setNegativeButton(R.string.cancel, DialogInterface.OnClickListener{ dialog, which ->
        })
        alertDialog.show()
        return alertDialog.create()
    }
}