package com.example.android.budgetproject;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase

@Database(entities = arrayOf(Transaction::class), version = 1)
abstract class MyDatabase : RoomDatabase() {

    abstract fun transactionDao(): TransactionDao

    companion object {
        private var mInstance: MyDatabase? = null
        @JvmStatic
        fun getInstance(): MyDatabase {
            if (mInstance == null) {
                mInstance = Room.databaseBuilder(BudgetApplication.getContext(), MyDatabase::class.java, "user")
                        .fallbackToDestructiveMigration()
                        .build()
            }
            return mInstance!!
        }
    }
}