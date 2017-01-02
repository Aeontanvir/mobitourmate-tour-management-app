package aeontanvir.com.mobitourmate.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import aeontanvir.com.mobitourmate.pojo.Photo;

/**
 * Created by aeon on 25 Nov, 2016.
 */

public class DBPhotoManager {
    Context context;
    DBHelper helper;
    SQLiteDatabase database;

    public DBPhotoManager(Context context) {
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



    public boolean addPhoto(Photo photo){
        this.openDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(DBHelper.PHOT_COL_TOUR_ID, photo.getTourId());
        contentValues.put(DBHelper.PHOT_COL_NAME, photo.getPhotoName());
        contentValues.put(DBHelper.PHOT_COL_TIME, photo.getPhotoTime());

        long inserted = database.insert(DBHelper.TABLE_PHOTO, null, contentValues);

        this.closeDatabase();
        if(inserted > 0){
            return true;
        }else{return false;}
    }

    public ArrayList<Photo> getAllPhotoByTourId(int tourId){
        this.openDatabase();
        ArrayList<Photo> photoList = new ArrayList<>();
        Photo photo;

        Cursor cursor = database.query(DBHelper.TABLE_PHOTO,
                new String[]{DBHelper.PHOT_COL_ID, DBHelper.PHOT_COL_TOUR_ID, DBHelper.PHOT_COL_NAME, DBHelper.PHOT_COL_TIME},
                DBHelper.PHOT_COL_TOUR_ID + "=" + tourId, null, null, null, null);

        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            for (int i = 0; i < cursor.getCount(); i++) {

                int photoId = cursor.getInt(cursor.getColumnIndex(DBHelper.PHOT_COL_NAME));
                int tourId_Temp = cursor.getInt(cursor.getColumnIndex(DBHelper.PHOT_COL_TOUR_ID));
                String name = cursor.getString(cursor.getColumnIndex(DBHelper.PHOT_COL_NAME));
                String time = cursor.getString(cursor.getColumnIndex(DBHelper.PHOT_COL_TIME));

                photo = new Photo(photoId, tourId_Temp, name, time);
                photoList.add(photo);
                cursor.moveToNext();
            }
            this.closeDatabase();

            return photoList;
        }
        return null;
    }
}
