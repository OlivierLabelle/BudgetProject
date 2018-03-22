package com.example.android.budgetproject

import android.content.Context
import android.content.DialogInterface
import android.content.SharedPreferences
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.android.budgetproject.databinding.ActivityMainBinding
import android.support.v7.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.example.android.budgetproject.popUp.DepenseDetails
import com.example.android.budgetproject.popUp.NewBudget


class MainActivity : AppCompatActivity(), SharedPreferences.OnSharedPreferenceChangeListener, BudgetVM.NewButtonClick {
    lateinit var budget : SharedPreferences
    var budgetTotal: String? = null
    val vm = BudgetVM()
    lateinit var rv: RecyclerView
    lateinit var transactionAdapter: TransactionAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        binding.vm = vm
        budget = getSharedPreferences("Budget", Context.MODE_PRIVATE)
        budget.registerOnSharedPreferenceChangeListener(this)
        budgetTotal = budget.getString("BudgetTotal", null)
        if(budgetTotal == null) {
            val budgetEditor = this.getSharedPreferences("Budget", Context.MODE_PRIVATE).edit()
            budgetEditor.putString("Depense", "0").apply()
            NewBudget.createPopUp(this)
        } else {
            vm.budgetTotal.set(budgetTotal)
        }
        val dbHelper = TransactionDBHelper(this)
        var transactionList = dbHelper.allTransaction
        rv = rv_transaction
        transactionAdapter = TransactionAdapter(transactionList)
        val mLayoutManager = LinearLayoutManager(applicationContext)
        rv.layoutManager = mLayoutManager
        rv.itemAnimator = DefaultItemAnimator()
        rv.adapter = transactionAdapter
        vm.listener = this
    }

    fun notifyDataChanged(){
        transactionAdapter.notifyDataSetChanged()
        Toast.makeText(this, "data updated", Toast.LENGTH_SHORT).show()
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        vm.budgetTotal.set(budgetTotal)
    }

    override fun newTransactionClicked(){
        DepenseDetails.createTransaction(this).setOnDismissListener(DialogInterface.OnDismissListener {
        })
    }
}
