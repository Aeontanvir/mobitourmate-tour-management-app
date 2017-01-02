package aeontanvir.com.mobitourmate.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import aeontanvir.com.mobitourmate.pojo.Baggage;

/**
 * Created by aeon on 25 Nov, 2016.
 */

public class DBBaggageManager {
    Context context;
    DBHelper helper;
    SQLiteDatabase database;

    public DBBaggageManager(Context context) {
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

    public boolean addBaggageItem(Baggage baggage){
        this.openDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(DBHelper.BAGE_COL_TOUR_ID, baggage.getTourId());
        contentValues.put(DBHelper.BAGE_COL_NAME, baggage.getBageName());
        contentValues.put(DBHelper.BAGE_COL_NO, baggage.getBageNo());

        long inserted = database.insert(DBHelper.TABLE_BAGGAGE, null, contentValues);

        this.closeDatabase();
        if(inserted > 0){
            return true;
        }else{return false;}
    }

    public ArrayList<Baggage> getAllBaggageByTourId(int tourId){
        this.openDatabase();
        ArrayList<Baggage> baggageList = new ArrayList<>();
        Baggage baggage;

        Cursor cursor = database.query(DBHelper.TABLE_BAGGAGE,
                new String[]{DBHelper.BAGE_COL_ID, DBHelper.BAGE_COL_TOUR_ID, DBHelper.BAGE_COL_NAME, DBHelper.BAGE_COL_NO},
                DBHelper.BAGE_COL_TOUR_ID + "=" + tourId, null, null, null, null);

        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            for (int i = 0; i < cursor.getCount(); i++) {

                int id = cursor.getInt(cursor.getColumnIndex(DBHelper.BAGE_COL_ID));
                int tId = cursor.getInt(cursor.getColumnIndex(DBHelper.BAGE_COL_TOUR_ID));
                String item = cursor.getString(cursor.getColumnIndex(DBHelper.BAGE_COL_NAME));
                int no = cursor.getInt(cursor.getColumnIndex(DBHelper.BAGE_COL_NO));

                baggage = new Baggage(id, tId, item, no);
                baggageList.add(baggage);
                cursor.moveToNext();
            }
            this.closeDatabase();

            return baggageList;
        }
        return null;
    }
}
