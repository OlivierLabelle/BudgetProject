package com.example.android.budgetproject

import android.arch.persistence.room.RoomDatabase
import android.content.Context
import android.content.SharedPreferences
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.android.budgetproject.databinding.ActivityMainBinding
import com.example.android.budgetproject.popUp.DepenseDetails
import com.example.android.budgetproject.popUp.NewBudget


class MainActivity : AppCompatActivity(), SharedPreferences.OnSharedPreferenceChangeListener, BudgetVM.NewButtonClick {
    lateinit var budget : SharedPreferences
    var budgetTotal: String? = null
    val vm = BudgetVM()
    lateinit var myDatabase: RoomDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        binding.vm = vm

        //Get the Total Budget in the shared pref and display it
        budget = getSharedPreferences("Budget", Context.MODE_PRIVATE)
        budget.registerOnSharedPreferenceChangeListener(this)
        budgetTotal = budget.getString("BudgetTotal", null)
        //Open Dialog if new user.
        if(budgetTotal == null) {
            NewBudget.createPopUp(this)
        } else {
            vm.budgetTotal.set(budgetTotal)
        }
        //Room DB
        myDatabase = MyDatabase.getInstance()
        vm.listener = this
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        vm.budgetTotal.set(budgetTotal)
    }

    override fun newTransactionClicked(){
        DepenseDetails.createTransaction(this)
    }
}
