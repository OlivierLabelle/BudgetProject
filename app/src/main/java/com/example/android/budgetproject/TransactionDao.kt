package com.example.android.budgetproject;


import android.arch.persistence.room.*
import android.arch.persistence.room.Transaction

@Dao interface TransactionDao {

    @Query("select * from `transaction`")
    fun getAllTransaction(): List<Transaction>

    @Query("select * from `transaction` where id = id")
    fun findTransactionById(id: Long): Transaction

    @Insert(onConflict = 1)
    fun insertTransaction(transaction: Transaction)

    @Update(onConflict = 1)
    fun updateTransaction(task: Transaction)

    @Delete
    fun deleteTransaction(transaction: Transaction)
}
