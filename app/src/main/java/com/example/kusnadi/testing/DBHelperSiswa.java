package com.example.kusnadi.testing;

/**
 * Created by Kusnadi on 5/24/2016.
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelperSiswa extends SQLiteOpenHelper{
    public static final String TABLE_SISWA = "siswa";
    public static final String COLUMN_SISWA_ID = "siswa_id";
    public static final String COLUMN_SISWA_NAME = "nama_siswa";
    public static final String COLUMN_KELAS = "kelas";
    private static final String db_name="siswa.db";
    private static final int db_version=1;

    private static final String db_create = "create table "
            + TABLE_SISWA + "("
            + COLUMN_SISWA_ID + " integer primary key autoincrement, "
            + COLUMN_SISWA_NAME + " varchar(50) not null, "
            + COLUMN_KELAS + " varchar(50) not null);";

    public DBHelperSiswa(Context context) {
        super(context, db_name, null, db_version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(db_create);
    }

    @Override
    public void onUpgrade (SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(DBHelperSiswa.class.getName(),"Upgrading database from version " + oldVersion + " to "
                + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SISWA);
        onCreate(db);
    }


}
