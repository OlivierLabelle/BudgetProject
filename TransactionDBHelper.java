package com.example.android.budgetproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.android.budgetproject.TransactionContract.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by olivier on 2018-03-12.
 */

public class TransactionDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "TransactionDatabase.db";
    private static final int DATABASE_VERSION = 1;

    public SQLiteDatabase db;


    public TransactionDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        this.db = db;

        final String SQL_CREATE_TRANSACTION_TABLE = "CREATE TABLE " +
                TransactionTable.TABLE_NAME + " ( " +
                TransactionTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TransactionTable.DEPENSE + " TEXT, " +
                TransactionTable.DESCRIPTION + " TEXT, " +
                TransactionTable.DATE + " TEXT" +
                ")";

        db.execSQL(SQL_CREATE_TRANSACTION_TABLE);
        fillTransactionTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TransactionTable.TABLE_NAME);
        onCreate(db);
    }

    private void fillTransactionTable(){
        Transaction transaction1 = new Transaction(10.00, "poula", 90);
        addTransaction(transaction1);

        Transaction transaction2 = new Transaction(11.00, "poula", 90);
        addTransaction(transaction2);

        Transaction transaction3 = new Transaction(12.00, "poula", 90);
        addTransaction(transaction3);

        Transaction transaction4 = new Transaction(13.00, "poula", 90);
        addTransaction(transaction4);
    }

    private void addTransaction(Transaction transaction){
        ContentValues cv = new ContentValues();
        cv.put(TransactionTable.DEPENSE, transaction.getDepense());
        cv.put(TransactionTable.DESCRIPTION, transaction.getDescription());
        cv.put(TransactionTable.DATE, transaction.getDate());
        db.insert(TransactionTable.TABLE_NAME, null, cv);
    }

    public List<Transaction> getAllTransaction(){
        List<Transaction> transactionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TransactionTable.TABLE_NAME, null);

        if(cursor.moveToFirst()){
            do {
                Transaction transaction = new Transaction();
                transaction.setDepense(cursor.getDouble(cursor.getColumnIndex(TransactionTable.DEPENSE)));
                transaction.setDescription(cursor.getString(cursor.getColumnIndex(TransactionTable.DESCRIPTION)));
                transaction.setDate(cursor.getInt(cursor.getColumnIndex(TransactionTable.DATE)));
                transactionList.add(transaction);

            }while(cursor.moveToNext());{
                }
        }
        cursor.close();
        return transactionList;
    }

}
