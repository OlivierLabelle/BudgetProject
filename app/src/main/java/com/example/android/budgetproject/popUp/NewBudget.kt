package com.example.android.budgetproject.popUp
import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.support.v7.app.AlertDialog
import android.view.WindowManager
import android.widget.EditText
import com.example.android.budgetproject.R

/**
 * Created by olivier on 2018-02-03.
 */
object NewBudget{

    fun createPopUp(context: Activity): AlertDialog {
        val alertDialog = AlertDialog.Builder(context)
        alertDialog.setTitle(context.getString(R.string.pop_title_budget))
        alertDialog.setMessage(context.getString(R.string.pop_description_budget))
        val input = EditText(context)
        input.requestFocus()
        alertDialog.setView(input)
        alertDialog.setPositiveButton(R.string.create_new, DialogInterface.OnClickListener { dialog, which ->
            val budgetEditor = context.getSharedPreferences("Budget", Context.MODE_PRIVATE).edit()
            budgetEditor.putString("BudgetTotal", input.text.toString()).apply()
        })
        alertDialog.setNegativeButton(R.string.cancel, DialogInterface.OnClickListener{ dialog, which ->
        })
        alertDialog.show()
        return alertDialog.create()
    }
}