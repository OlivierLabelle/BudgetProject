package com.example.android.budgetproject

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import android.view.View
import android.view.ViewAnimationUtils


/**
 * Created by olivier on 2018-02-05.
 */
class BudgetVM : ViewModel() {

    val budgetTotal = ObservableField<String>()
    val depenseTotal = ObservableField<String>()
    val budgetRestant = ObservableField<String>()

    var listener: NewButtonClick? = null

    interface NewButtonClick{
        fun newTransactionClicked()
        fun transactionClicked()
    }

    fun newTransactionOnClick(): View.OnClickListener{
        return View.OnClickListener {
            listener?.newTransactionClicked()
        }
    }

    fun updateBudgetOnClick(): View.OnClickListener{
        return View.OnClickListener {

        }
    }

    fun transactionClicked(): View.OnClickListener{
        return View.OnClickListener {
            listener?.transactionClicked()
        }
    }
}