package com.example.android.budgetproject

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "transaction")
data class Transaction(
        @ColumnInfo(name = "depense") var depense: String = "",
        @ColumnInfo(name = "description") var description: Int = 0,
        @ColumnInfo(name = "date") var date: String = "",
        @PrimaryKey(autoGenerate = true) var uid: Long = 0)
