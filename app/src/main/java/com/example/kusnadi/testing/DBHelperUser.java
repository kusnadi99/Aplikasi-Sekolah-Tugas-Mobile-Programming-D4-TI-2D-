package com.example.kusnadi.testing;

/**
 * Created by Kusnadi on 5/26/2016.
 */
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.sql.SQLException;

public class DBHelperUser extends SQLiteOpenHelper {
    public static final String TABLE_USER = "user";
    public static final String COLUMN_USER_ID = "user_id";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PASSWORD = "password";
    private static final String db_name = "register.db";
    private static final int db_version = 1;

    private static final String db_create = "create table "
            + TABLE_USER + "("
            + COLUMN_USER_ID + " integer primary key autoincrement, "
            + COLUMN_USERNAME + " varchar(50) not null, "
            + COLUMN_PASSWORD + " varchar(50) not null);";

    public DBHelperUser(Context context) {
        super(context, db_name, null, db_version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(db_create);
    }

    public String searchPass(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "select username, password from "+TABLE_USER;
        Cursor cursor = db.rawQuery(query, null);
        String a, b;
        b = "Not Found";
        if (cursor.moveToFirst()) {
            do {
                a = cursor.getString(0);

                if (a.equals(username)) {
                    b = cursor.getString(1);
                    break;
                }
            }
            while (cursor.moveToNext());
        }
        return b;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(DBHelperSiswa.class.getName(), "Upgrading database from version " + oldVersion + " to "
                + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        onCreate(db);
    }


}

    
