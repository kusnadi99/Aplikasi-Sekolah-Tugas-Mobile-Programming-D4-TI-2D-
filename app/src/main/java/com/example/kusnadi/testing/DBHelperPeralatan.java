package com.example.kusnadi.testing;

/**
 * Created by Kusnadi on 5/24/2016.
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelperPeralatan extends SQLiteOpenHelper{
    public static final String TABLE_PERALATAN = "peralatan";
    public static final String COLUMN_PERALATAN_ID = "peralatan_id";
    public static final String COLUMN_PERALATAN_NAME = "nama_peralatan";
    public static final String COLUMN_STOK = "stok";
    private static final String db_name="peralatan.db";
    private static final int db_version=1;

    private static final String db_create = "create table "
            + TABLE_PERALATAN + "("
            + COLUMN_PERALATAN_ID + " integer primary key autoincrement, "
            + COLUMN_PERALATAN_NAME + " varchar(50) not null, "
            + COLUMN_STOK + " varchar(50) not null);";

    public DBHelperPeralatan(Context context) {
        super(context, db_name, null, db_version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(db_create);
    }

    @Override
    public void onUpgrade (SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(DBHelperPeralatan.class.getName(),"Upgrading database from version " + oldVersion + " to "
                + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PERALATAN);
        onCreate(db);
    }

}
