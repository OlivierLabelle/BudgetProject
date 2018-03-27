package com.example.android.budgetproject

import android.app.Application
import android.content.Context

/**
 * Created by olivier on 2018-03-05.
 */

class BudgetApplication : Application(){

    override fun onCreate() {
        super.onCreate()
        application = this
    }

    companion object {
        lateinit var application: BudgetApplication

        fun getContext(): Context {
        return application.applicationContext
        }
    }
}
