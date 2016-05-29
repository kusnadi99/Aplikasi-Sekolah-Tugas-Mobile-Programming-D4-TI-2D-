package com.example.kusnadi.testing;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Kusnadi on 5/23/2016.
 */
public class DBHelperGuru extends SQLiteOpenHelper{
    public static final String TABLE_GURU = "guru";
    public static final String COLUMN_GURU_ID = "guru_id";
    public static final String COLUMN_GURU_NAME = "nama_guru";
    public static final String COLUMN_ALAMAT = "alamat";
    public static final String COLUMN_UMUR = "umur";
    public static final String COLUMN_STATUS = "status";
    private static final String db_name = "sekolah.db";
    private static final int db_version=1;

    private static final String db_create = "create table "
            + TABLE_GURU + "("
            + COLUMN_GURU_ID + " integer primary key autoincrement, "
            + COLUMN_GURU_NAME + " varchar(50) not null, "
            + COLUMN_ALAMAT + " varchar(50) not null, "
            + COLUMN_UMUR + " varchar(50) not null, "
            + COLUMN_STATUS + " varchar(50) not null);";

    public DBHelperGuru(Context context) {
        super(context, db_name, null, db_version);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(db_create);
    }

    @Override
    public void onUpgrade (SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(DBHelperSiswa.class.getName(),"Upgrading database from version " + oldVersion + " to "
                + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GURU);
        onCreate(db);
    }
}
