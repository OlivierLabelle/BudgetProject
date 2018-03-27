package com.example.android.budgetproject;

import android.provider.BaseColumns;

/**
 * Created by olivier on 2018-03-12.
 */

public final class TransactionContract{

    private TransactionContract() {}

    public static class TransactionTable implements BaseColumns{
        public static final String TABLE_NAME = "TN_Transaction";
        public static final String DEPENSE = "Depense";
        public static final String DESCRIPTION = "Description";
        public static final String DATE = "Date";
    }
}
