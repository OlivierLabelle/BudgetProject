package com.example.android.budgetproject

import android.content.Context
import android.content.SharedPreferences
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.android.budgetproject.databinding.ActivityMainBinding
import com.example.android.budgetproject.popUp.DepenseDetails
import com.example.android.budgetproject.popUp.EditDetails
import com.example.android.budgetproject.popUp.NewBudget


class MainActivity : AppCompatActivity(), SharedPreferences.OnSharedPreferenceChangeListener, BudgetVM.NewButtonClick, TransactionAdapter.TransactionClick{
    lateinit var budget : SharedPreferences
    var budgetTotal: String? = null
    val vm = BudgetVM()
    lateinit var adapter: TransactionAdapter
    var  recyclerView: RecyclerView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        binding.vm = vm

        //Get the Total Budget in the shared pref and display it
        budget = getSharedPreferences("Budget", Context.MODE_PRIVATE)
        budget.registerOnSharedPreferenceChangeListener(this)
        budgetTotal = budget.getString("BudgetTotal", null)
        adapter = TransactionAdapter(this)
        recyclerView = findViewById(R.id.rv_transaction)
        recyclerView?.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView?.adapter = adapter
        //Open Dialog if new user.
        if(budgetTotal == null) {
            NewBudget.createPopUp(this)
        } else {
            vm.budgetTotal.set(budgetTotal)
        }
        vm.listener = this
        //Room
        //Adapter
        Thread({
            val transactionFromDb = MyDatabase.getInstance().transactionDao().getAllTransaction()
            runOnUiThread({
                adapter.submitList(transactionFromDb)
                adapter.notifyDataSetChanged()
            })
        }).start()


    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        vm.budgetTotal.set(budgetTotal)
    }

    override fun newTransactionClicked(){
        DepenseDetails.createTransaction(this)
    }

    override fun transactionClicked(uid: Long) {
        EditDetails.editTransaction(this, uid)
    }
}
