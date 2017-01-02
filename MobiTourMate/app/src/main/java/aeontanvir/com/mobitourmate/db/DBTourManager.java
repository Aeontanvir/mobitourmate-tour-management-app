package aeontanvir.com.mobitourmate.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import aeontanvir.com.mobitourmate.pojo.Tour;

/**
 * Created by aeon on 24 Nov, 2016.
 */

public class DBTourManager {
    Context context;
    DBHelper helper;
    SQLiteDatabase database;

    public DBTourManager(Context context) {
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

    public boolean addTour(Tour tour){
        this.openDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(DBHelper.TOUR_COL_DESTINATION, tour.getTourDestination());
        contentValues.put(DBHelper.TOUR_COL_BUDGET, tour.getTourBudget());
        contentValues.put(DBHelper.TOUR_COL_START_DATE, tour.getTourStartDate());
        contentValues.put(DBHelper.TOUR_COL_END_DATE, tour.getTourEndDate());

        long inserted = database.insert(DBHelper.TABLE_TOUR, null, contentValues);

        this.closeDatabase();
        if(inserted > 0){
            return true;
        }else{return false;}
    }

    public Tour getTour(int id){
        this.openDatabase();
        Tour tour;

        Cursor cursor = database.query(DBHelper.TABLE_TOUR,
                new String[]{DBHelper.TOUR_COL_ID, DBHelper.TOUR_COL_DESTINATION, DBHelper.TOUR_COL_BUDGET, DBHelper.TOUR_COL_START_DATE, DBHelper.TOUR_COL_END_DATE},
                DBHelper.TOUR_COL_ID + "=" + id, null, null, null, null);

        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            int tourId = cursor.getInt(cursor.getColumnIndex(DBHelper.TOUR_COL_ID));
            String des = cursor.getString(cursor.getColumnIndex(DBHelper.TOUR_COL_DESTINATION));
            float budget = cursor.getFloat(cursor.getColumnIndex(DBHelper.TOUR_COL_BUDGET));
            String stDate = cursor.getString(cursor.getColumnIndex(DBHelper.TOUR_COL_START_DATE));
            String edDate = cursor.getString(cursor.getColumnIndex(DBHelper.TOUR_COL_END_DATE));

            tour = new Tour(tourId, des, budget, stDate, edDate);
            this.closeDatabase();
            return tour;
        }
        return null;
    }


    public ArrayList<Tour> getAllTour(){
        this.openDatabase();
        ArrayList<Tour> tourList = new ArrayList<>();
        Tour tour;

        Cursor cursor = database.query(DBHelper.TABLE_TOUR,
                new String[]{DBHelper.TOUR_COL_ID,DBHelper.TOUR_COL_DESTINATION,DBHelper.TOUR_COL_BUDGET,DBHelper.TOUR_COL_START_DATE,DBHelper.TOUR_COL_END_DATE},
                null, null, null, null, null);

        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            for (int i = 0; i < cursor.getCount(); i++) {

                int id = cursor.getInt(cursor.getColumnIndex(DBHelper.TOUR_COL_ID));
                String des = cursor.getString(cursor.getColumnIndex(DBHelper.TOUR_COL_DESTINATION));
                float budget = cursor.getFloat(cursor.getColumnIndex(DBHelper.TOUR_COL_BUDGET));
                String stDate = cursor.getString(cursor.getColumnIndex(DBHelper.TOUR_COL_START_DATE));
                String edDate = cursor.getString(cursor.getColumnIndex(DBHelper.TOUR_COL_END_DATE));


                tour = new Tour(id, des, budget, stDate, edDate);
                tourList.add(tour);
                cursor.moveToNext();
            }
            this.closeDatabase();
            return tourList;
        }

        return null;
    }


}
