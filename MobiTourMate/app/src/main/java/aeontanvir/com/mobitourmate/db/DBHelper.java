package aeontanvir.com.mobitourmate.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
/**
 * Created by aeon on 13 Nov, 2016.
 */

public class DBHelper extends SQLiteOpenHelper{
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "mobi_tour_mate";

    // Table Name
    public static final String TABLE_USER = "mtm_user";
    public static final String TABLE_TOUR = "mtm_tour";
    public static final String TABLE_EXPENSE = "mtm_expense";
    public static final String TABLE_BAGGAGE = "mtm_baggage";
    public static final String TABLE_PHOTO = "mtm_photo";

    // User Table Details
    public static final String USER_COL_ID = "user_id";
    public static final String USER_COL_FULLNAME = "user_fullname";
    public static final String USER_COL_USERNAME = "user_username";
    public static final String USER_COL_PASSWORD = "user_password";
    public static final String USER_COL_CONTACTNO = "user_contact_no";
    public static final String USER_COL_ADDRESS = "user_address";

    public static final String STMT_CREATE_USER =  "CREATE TABLE " + TABLE_USER + " (" +
            USER_COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            USER_COL_FULLNAME + " TEXT, " +
            USER_COL_USERNAME + " TEXT, "+
            USER_COL_PASSWORD + " TEXT, "+
            USER_COL_CONTACTNO + " TEXT, "+
            USER_COL_ADDRESS + " TEXT )";

    // Tour Table Details
    public static final String TOUR_COL_ID = "tour_id";
    public static final String TOUR_COL_DESTINATION = "tour_destination";
    public static final String TOUR_COL_BUDGET = "tour_budget";
    public static final String TOUR_COL_START_DATE = "tour_startdate";
    public static final String TOUR_COL_END_DATE = "tour_enddate";

    public static final String STMT_CREATE_TOUR =  "CREATE TABLE " + TABLE_TOUR + "(" +
            TOUR_COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            TOUR_COL_DESTINATION + " TEXT, " +
            TOUR_COL_BUDGET + " FLOAT, "+
            TOUR_COL_START_DATE + " TEXT, "+
            TOUR_COL_END_DATE + " TEXT )";


    // Expense Table Details
    public static final String EXPN_COL_ID = "expn_id";
    public static final String EXPN_COL_TOUR_ID = "tour_id";
    public static final String EXPN_COL_NAME = "expn_type";
    public static final String EXPN_COL_TIMESTAMP = "expn_timestamp";
    public static final String EXPN_COL_AMOUNT = "expn_amount";

    public static final String STMT_CREATE_EXPENSE =  "CREATE TABLE " + TABLE_EXPENSE + "(" +
            EXPN_COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            EXPN_COL_TOUR_ID + " INTEGER, " +
            EXPN_COL_NAME + " TEXT, "+
            EXPN_COL_TIMESTAMP + " TEXT, "+
            EXPN_COL_AMOUNT + " FLOAT )";


    // Baggage Table Details
    public static final String BAGE_COL_ID = "bage_id";
    public static final String BAGE_COL_TOUR_ID = "tour_id";
    public static final String BAGE_COL_NAME = "bage_name";
    public static final String BAGE_COL_NO = "bage_no";

    public static final String STMT_CREATE_BAGGAGE =  "CREATE TABLE " + TABLE_BAGGAGE + "(" +
            BAGE_COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            BAGE_COL_TOUR_ID + " INTEGER, " +
            BAGE_COL_NAME + " TEXT, "+
            BAGE_COL_NO + " INTEGER )";


    // Photo Table Details
    public static final String PHOT_COL_ID = "phot_id";
    public static final String PHOT_COL_TOUR_ID = "tour_id";
    public static final String PHOT_COL_NAME = "phot_name";
    public static final String PHOT_COL_TIME = "phot_time";

    public static final String STMT_CREATE_PHOTO =  "CREATE TABLE " + TABLE_PHOTO + "(" +
            PHOT_COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            PHOT_COL_TOUR_ID + " INTEGER, " +
            PHOT_COL_NAME + " TEXT, "+
            PHOT_COL_TIME + " TEXT )";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(STMT_CREATE_USER);
        db.execSQL(STMT_CREATE_TOUR);
        db.execSQL(STMT_CREATE_EXPENSE);
        db.execSQL(STMT_CREATE_BAGGAGE);
        db.execSQL(STMT_CREATE_PHOTO);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
