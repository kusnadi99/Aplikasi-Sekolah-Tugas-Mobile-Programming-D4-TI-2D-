package com.example.kusnadi.testing;

/**
 * Created by Kusnadi on 5/24/2016.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.sql.SQLException;
import java.util.ArrayList;

public class DataSourcePeralatan {
    private SQLiteDatabase database;
    private DBHelperPeralatan dbHelperPeralatan;

    private String[] allColumns = { DBHelperPeralatan.COLUMN_PERALATAN_ID, DBHelperPeralatan.COLUMN_PERALATAN_NAME, DBHelperPeralatan.COLUMN_STOK};

    public DataSourcePeralatan(Context context) {
        dbHelperPeralatan = new DBHelperPeralatan(context);
    }

    public void open() throws SQLException {
        database = dbHelperPeralatan.getWritableDatabase();
    }

    public void close() {
        dbHelperPeralatan.close();
    }

    public Peralatan createPeralatan(String nama_alat, String stok) {
        ContentValues values = new ContentValues();
        values.put(DBHelperPeralatan.COLUMN_PERALATAN_NAME, nama_alat);
        values.put(DBHelperPeralatan.COLUMN_STOK, stok);

        long insertId = database.insert(DBHelperPeralatan.TABLE_PERALATAN, null, values);

        Cursor cursor = database.query(DBHelperPeralatan.TABLE_PERALATAN, allColumns, DBHelperPeralatan.COLUMN_PERALATAN_ID + " = " + insertId, null, null, null, null);

        cursor.moveToFirst();

        Peralatan newPeralatan = cursorToPeralatan(cursor);

        cursor.close();

        return newPeralatan;
    }

    private Peralatan cursorToPeralatan(Cursor cursor) {
        Peralatan peralatan = new Peralatan();
        Log.v("info", "The getLONG " +cursor.getLong(0));
        Log.v("info", "The setLatLng" +cursor.getString(1)+","+cursor.getString(2));

        peralatan.setPeralatan_id(cursor.getLong(0));
        peralatan.setNama_alat(cursor.getString(1));
        peralatan.setStok(cursor.getString(2));

        return peralatan;
    }

    public ArrayList<Peralatan> getAllPeralatan() {
        ArrayList<Peralatan> daftarPeralatan = new ArrayList<Peralatan>();

        Cursor cursor = database.query(DBHelperPeralatan.TABLE_PERALATAN, allColumns, null, null, null, null, null);

        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            Peralatan peralatan = cursorToPeralatan(cursor);
            daftarPeralatan.add(peralatan);
            cursor.moveToNext();
        }
        cursor.close();
        return daftarPeralatan;
    }

    public Peralatan getPeralatan(long id) {
        Peralatan peralatan = new Peralatan();
        Cursor cursor = database.query(DBHelperPeralatan.TABLE_PERALATAN, allColumns, "peralatan_id="+id, null, null, null, null);
        cursor.moveToFirst();

        peralatan = cursorToPeralatan(cursor);

        cursor.close();
        return peralatan;
    }

    public void updatePeralatan (Peralatan p) {
        String strFilter = "peralatan_id=" + p.getPeralatan_id();

        ContentValues args = new ContentValues();

        args.put(DBHelperPeralatan.COLUMN_PERALATAN_NAME, p.getNama_alat());
        args.put(DBHelperPeralatan.COLUMN_STOK, p.getStok());

        database.update(DBHelperPeralatan.TABLE_PERALATAN, args, strFilter, null);
    }

    public void deletePeralatan(long id) {
        String strFilter = "peralatan_id=" + id;
        database.delete(DBHelperPeralatan.TABLE_PERALATAN, strFilter, null);
    }

}
