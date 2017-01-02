package aeontanvir.com.mobitourmate.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import aeontanvir.com.mobitourmate.pojo.User;

/**
 * Created by aeon on 23 Nov, 2016.
 */

public class DBLoginManager {
    Context context;
    DBHelper helper;
    SQLiteDatabase database;

    public DBLoginManager(Context context) {
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


    public boolean addUser(User user){
        this.openDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.USER_COL_FULLNAME, user.getFullname());
        contentValues.put(DBHelper.USER_COL_USERNAME, user.getUsername());
        contentValues.put(DBHelper.USER_COL_PASSWORD, user.getPassword());
        contentValues.put(DBHelper.USER_COL_CONTACTNO, user.getContactno());
        contentValues.put(DBHelper.USER_COL_ADDRESS, user.getAddress());

        long inserted = database.insert(DBHelper.TABLE_USER, null, contentValues);

        this.closeDatabase();
        if(inserted > 0){
            return true;
        }else{return false;}
    }


    public ArrayList<User> getAllUser(){
        this.openDatabase();
        ArrayList<User> userList = new ArrayList<>();
        User user;

        Cursor cursor = database.query(DBHelper.TABLE_USER,
                new String[]{DBHelper.USER_COL_ID, DBHelper.USER_COL_FULLNAME, DBHelper.USER_COL_USERNAME, DBHelper.USER_COL_PASSWORD, DBHelper.USER_COL_CONTACTNO, DBHelper.USER_COL_ADDRESS},
                null, null, null, null, null);

        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            for (int i = 0; i < cursor.getCount(); i++) {
                int userId = cursor.getInt(cursor.getColumnIndex(DBHelper.USER_COL_ID));
                String userFullname = cursor.getString(cursor.getColumnIndex(DBHelper.USER_COL_FULLNAME));
                String userUsername = cursor.getString(cursor.getColumnIndex(DBHelper.USER_COL_USERNAME));
                String userPassword = cursor.getString(cursor.getColumnIndex(DBHelper.USER_COL_PASSWORD));
                String userContactNo = cursor.getString(cursor.getColumnIndex(DBHelper.USER_COL_CONTACTNO));
                String userAdress = cursor.getString(cursor.getColumnIndex(DBHelper.USER_COL_ADDRESS));

                user = new User(userId, userFullname, userUsername, userPassword, userContactNo, userAdress);
                userList.add(user);
                cursor.moveToNext();
            }
            this.closeDatabase();
            return userList;
        }

        return null;
    }



    public User getUser(int id){
        this.openDatabase();
        User user;

        Cursor cursor = database.query(DBHelper.TABLE_USER,
                new String[]{DBHelper.USER_COL_ID, DBHelper.USER_COL_FULLNAME, DBHelper.USER_COL_USERNAME, DBHelper.USER_COL_PASSWORD, DBHelper.USER_COL_CONTACTNO, DBHelper.USER_COL_ADDRESS},
                DBHelper.USER_COL_ID + "=" + id, null, null, null, null);

        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            int userId = cursor.getInt(cursor.getColumnIndex(DBHelper.USER_COL_ID));
            String userFullname = cursor.getString(cursor.getColumnIndex(DBHelper.USER_COL_FULLNAME));
            String userUsername = cursor.getString(cursor.getColumnIndex(DBHelper.USER_COL_USERNAME));
            String userPassword = cursor.getString(cursor.getColumnIndex(DBHelper.USER_COL_PASSWORD));
            String userContactNo = cursor.getString(cursor.getColumnIndex(DBHelper.USER_COL_CONTACTNO));
            String userAdress = cursor.getString(cursor.getColumnIndex(DBHelper.USER_COL_ADDRESS));
            user = new User(userId, userFullname, userUsername, userPassword, userContactNo, userAdress);
            this.closeDatabase();
            return user;
        }
        return null;
    }
}
