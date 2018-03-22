package com.example.android.budgetproject

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import android.view.View


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
}