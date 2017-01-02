package aeontanvir.com.mobitourmate.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import aeontanvir.com.mobitourmate.pojo.Expense;

/**
 * Created by aeon on 24 Nov, 2016.
 */

public class DBExpensesManager {
    Context context;
    DBHelper helper;
    SQLiteDatabase database;

    public DBExpensesManager(Context context) {
        this.context = context;
        helper = new DBHelper(context);
    }
    private void openDatabase(){
        database = helper.getWritableDatabase();
    }
    private void closeDatabase(){
        helper.close();
        database.close();
    }

    public boolean addExpense(Expense expense){
        this.openDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(DBHelper.EXPN_COL_TOUR_ID, expense.getTourId());
        contentValues.put(DBHelper.EXPN_COL_NAME, expense.getExpnName());
        contentValues.put(DBHelper.EXPN_COL_TIMESTAMP, expense.getExpnTime());
        contentValues.put(DBHelper.EXPN_COL_AMOUNT, expense.getExpnAmount());

        long inserted = database.insert(DBHelper.TABLE_EXPENSE, null, contentValues);

        this.closeDatabase();
        if(inserted > 0){
            return true;
        }else{return false;}
    }


    public ArrayList<Expense> getAllExpenseByTourId(int tourId){
        this.openDatabase();
        ArrayList<Expense> expenseList = new ArrayList<>();
        Expense expense;

        Cursor cursor = database.query(DBHelper.TABLE_EXPENSE,
                new String[]{DBHelper.EXPN_COL_ID, DBHelper.EXPN_COL_TOUR_ID, DBHelper.EXPN_COL_NAME, DBHelper.EXPN_COL_TIMESTAMP, DBHelper.EXPN_COL_AMOUNT},
                DBHelper.EXPN_COL_TOUR_ID + "=" + tourId, null, null, null, null);

        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            for (int i = 0; i < cursor.getCount(); i++) {

                int expenseId = cursor.getInt(cursor.getColumnIndex(DBHelper.EXPN_COL_ID));
                int tourId_Temp = cursor.getInt(cursor.getColumnIndex(DBHelper.EXPN_COL_TOUR_ID));
                String name = cursor.getString(cursor.getColumnIndex(DBHelper.EXPN_COL_NAME));
                String time = cursor.getString(cursor.getColumnIndex(DBHelper.EXPN_COL_TIMESTAMP));
                float amount = cursor.getFloat(cursor.getColumnIndex(DBHelper.EXPN_COL_AMOUNT));

                expense = new Expense(expenseId, tourId_Temp, name, time, amount);
                expenseList.add(expense);
                cursor.moveToNext();
            }
            this.closeDatabase();

            return expenseList;
        }
        return null;
    }

    public float getSumOfAmountByTourId(int tourId){
        this.openDatabase();
        float total = 0;

        Cursor cursor = database.rawQuery("SELECT IFNULL(SUM(" + DBHelper.EXPN_COL_AMOUNT + "), 0)  as Total  FROM " + DBHelper.TABLE_EXPENSE + " WHERE "+ DBHelper.EXPN_COL_TOUR_ID +"=" + tourId, null);
        if (cursor.moveToFirst()) {
            total = cursor.getFloat(cursor.getColumnIndex("Total"));
        }

        this.closeDatabase();
        return total;
    }

}

