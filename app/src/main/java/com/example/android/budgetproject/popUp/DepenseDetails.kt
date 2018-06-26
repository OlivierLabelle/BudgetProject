package com.example.android.budgetproject.popUp

import android.annotation.SuppressLint
import android.app.Activity
import android.app.DatePickerDialog
import android.content.Context
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.widget.*
import com.example.android.budgetproject.*
import com.example.android.budgetproject.DecimalInputTextWatcher
import com.example.android.budgetproject.R.id.onDateChanged
import com.example.android.budgetproject.R.id.spinner
import java.util.*


@SuppressLint("StaticFieldLeak")
/**
 * Created by olivier on 2018-02-07.
 */

object DepenseDetails{

    var list_of_items = (BudgetApplication.getContext().resources.getStringArray(R.array.description))

    var year = 0
    var month = 0
    var day = 0

    var completedDate: String? = null
    var displayedDate: String? = null

    var buttonDate: Button? = null

    @SuppressLint("InflateParams")
    fun createTransaction(context: Activity,depense: Float?= null, description: Int?= null, date: String?= null, uid: Long? = null): AlertDialog {
        val alertDialog = AlertDialog.Builder(context)
        val layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val alertLayout = layoutInflater.inflate(R.layout.new_transaction, null, false) as LinearLayout
        alertDialog.setView(alertLayout)
        val edDepense = alertLayout.findViewById<EditText>(R.id.ed_montant)
        val btDate = alertLayout.findViewById<Button>(R.id.bt_date)
        edDepense.addTextChangedListener(DecimalInputTextWatcher(edDepense, 2))

        //Category spinner
        val spinner = alertLayout.findViewById<Spinner>(R.id.spinner)
        val spinnerAdapter = ArrayAdapter(BudgetApplication.getContext(), R.layout.custom_spinner, list_of_items)
        spinnerAdapter.setDropDownViewResource(R.layout.row_spinner)
        spinner.adapter = spinnerAdapter

        //Date shizzle
        buttonDate = btDate
        val cal = Calendar.getInstance()
        Util.dateDisplayer(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH))
        btDate.text = displayedDate
        btDate.setOnClickListener({
            Util.currentDateForDatePicker()
            val mDatePicker = DatePickerDialog(it.context,DatePickerDialog.OnDateSetListener { view, mYear, mMonth, mDayOfMonth ->
                Util.dateDisplayer(mYear, mMonth, mDayOfMonth)
                buttonDate!!.text = displayedDate
            }, year, month, day)
            mDatePicker.show()
        })
        //Updating OR Creating ?
        if (uid == null){
            //Creating
            alertDialog.setPositiveButton(context.getString(R.string.create), { dialog, which ->
                val depenseCreation = edDepense.text.toString().toFloat()
                val descriptionCreation = spinner.selectedItemPosition
                val dateCreation = completedDate!!
                TransactionCreation().transactionCreation(depenseCreation, descriptionCreation, dateCreation)
                //Todo Assurer 2 champs sont valide.
            })
            alertDialog.setNegativeButton(R.string.cancel, { dialog, which ->
            })
        }else{
            //Updating
            edDepense.setText(depense.toString())
            spinner.setSelection(description!!)
            alertDialog.setPositiveButton(R.string.update, { dialog, which ->
                val depenseCreation = edDepense.text.toString().toFloat()
                val descriptionCreation = spinner.selectedItemPosition
                val dateCreation = "$year/$month/$day"
                TransactionCreation().transactionUpdatation(depenseCreation, descriptionCreation, dateCreation, uid)
            })
            alertDialog.setNegativeButton(R.string.cancel, { dialog, which ->
            })
            alertDialog.setNeutralButton(R.string.delete, { dialog, which ->
                TransactionCreation().transactionDeletation(uid)
                MainActivity().recyclerView?.adapter?.notifyDataSetChanged()
            })
        }
        return alertDialog.create()
    }
}