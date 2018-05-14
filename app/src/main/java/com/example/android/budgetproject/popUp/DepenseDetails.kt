package com.example.android.budgetproject.popUp

import android.annotation.SuppressLint
import android.app.Activity
import android.content.ContentValues
import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
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

    var list_of_items = (BudgetApplication.getContext().resources.getStringArray(R.array.description))

    @SuppressLint("InflateParams")
    fun createTransaction(context: Activity, uid: Long? = null): AlertDialog {
        val alertDialog = AlertDialog.Builder(context)
        val layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val alertLayout = layoutInflater.inflate(R.layout.new_transaction, null, false) as LinearLayout
        alertDialog.setView(alertLayout)
        val edDepense = alertLayout.findViewById<EditText>(R.id.ed_montant)
        val edDate = alertLayout.findViewById<EditText>(R.id.ed_date)
        val spinner = alertLayout.findViewById<Spinner>(R.id.spinner)
        val spinnerAdapter = ArrayAdapter(BudgetApplication.getContext(), R.layout.custom_spinner, list_of_items)
        spinnerAdapter.setDropDownViewResource(R.layout.row_spinner)
        spinner!!.adapter = spinnerAdapter
        alertDialog.setPositiveButton(R.string.save, { dialog, which ->
            val depense = edDepense.text.toString()
            val description = spinner.selectedItemPosition
            val date = edDate.text.toString()
            TransactionCreation().transactionCreation(depense, description, date)
            //Todo Assurer 2 champs sont valide.
        })
        alertDialog.setNegativeButton(R.string.cancel, { dialog, which ->
        })
        if (uid != null){
            alertDialog.setNeutralButton(R.string.delete, { dialog, which ->
                TransactionCreation().transactionDeletation(uid!!)
                MainActivity().recyclerView?.adapter?.notifyDataSetChanged()
            })
        }
        return alertDialog.create()
    }
}