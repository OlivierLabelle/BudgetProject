package com.example.android.budgetproject


import android.arch.persistence.room.*
import com.example.android.budgetproject.Transaction
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Dao

@Dao
interface TransactionDao {

    @Query("SELECT * FROM `transaction`")
    fun getAllTransaction(): List<Transaction>

    @Query("SELECT * FROM `transaction` where uid IN (:transactionId)")
    fun findTransactionById(transactionId: IntArray): List<Transaction>

    @Insert(onConflict = 1)
    fun insertTransaction(transaction: Transaction)

    @Delete
    fun deleteTransaction(transaction: Transaction)

    @Update
    fun updateTransaction(transaction: Transaction)
}