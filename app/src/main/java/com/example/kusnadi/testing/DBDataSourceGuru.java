package com.example.kusnadi.testing;

/**
 * Created by Kusnadi on 5/23/2016.
 */
import java.util.ArrayList;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DBDataSourceGuru {
    private SQLiteDatabase database;
    private DBHelperGuru dbHelperGuru;

    private String[] allColumns = { DBHelperGuru.COLUMN_GURU_ID,
            DBHelperGuru.COLUMN_GURU_NAME, DBHelperGuru.COLUMN_ALAMAT,
            DBHelperGuru.COLUMN_STATUS, DBHelperGuru.COLUMN_UMUR};
    public DBDataSourceGuru(Context context) {
        dbHelperGuru = new DBHelperGuru(context);
    }
    public void open() throws SQLException {
        database = dbHelperGuru.getWritableDatabase();
    }
    public void close() {
        dbHelperGuru.close();
    }

    public Guru createGuru(String nama_guru, String alamat, String umur, String status) {
        ContentValues values = new ContentValues();
        values.put(DBHelperGuru.COLUMN_GURU_NAME, nama_guru);
        values.put(DBHelperGuru.COLUMN_ALAMAT, alamat);
        values.put(DBHelperGuru.COLUMN_UMUR, umur);
        values.put(DBHelperGuru.COLUMN_STATUS, status);

        long insertId = database.insert(DBHelperGuru.TABLE_GURU, null, values);

        Cursor cursor = database.query(DBHelperGuru.TABLE_GURU, allColumns, DBHelperGuru.COLUMN_GURU_ID + " = " + insertId, null,
                null, null, null);

        cursor.moveToFirst();

        Guru newGuru = cursorToGuru(cursor);
        cursor.close();

        return newGuru;
    }

    private Guru cursorToGuru(Cursor cursor) {
        Guru guru = new Guru();
        Log.v("info", "The getLONG " +cursor.getLong(0));
        Log.v("info", "The setLatLng " +cursor.getString(1)+","+cursor.getString(2));

        guru.setGuru_id(cursor.getLong(0));
        guru.setNama_guru(cursor.getString(1));
        guru.setAlamat(cursor.getString(2));
        guru.setUmur(cursor.getString(3));
        guru.setStatus(cursor.getString(4));

        return guru;
    }

    public ArrayList<Guru> getAllGuru() {
        ArrayList<Guru> daftarGuru = new ArrayList<Guru>();

        Cursor cursor = database.query(DBHelperGuru.TABLE_GURU, allColumns, null, null, null, null, null);

        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            Guru guru = cursorToGuru(cursor);
            daftarGuru.add(guru);
            cursor.moveToNext();
        }
        cursor.close();
        return daftarGuru;
    }

    public Guru getGuru(long id) {
        Guru guru = new Guru();
        Cursor cursor = database.query(DBHelperGuru.TABLE_GURU, allColumns, "guru_id="+id, null, null, null, null);
        cursor.moveToFirst();

        guru = cursorToGuru(cursor);

        cursor.close();
        return guru;
    }

    public void updateGuru (Guru g) {
        String strFilter = "guru_id=" + g.getGuru_id();

        ContentValues args = new ContentValues();

        args.put(DBHelperGuru.COLUMN_GURU_NAME, g.getNama_guru());
        args.put(DBHelperGuru.COLUMN_ALAMAT, g.getAlamat());
        args.put(DBHelperGuru.COLUMN_UMUR, g.getUmur());
        args.put(DBHelperGuru.COLUMN_STATUS, g.getStatus());

        database.update(DBHelperGuru.TABLE_GURU, args, strFilter, null);
    }

    public void deleteGuru(long id) {
        String strFilter = "guru_id=" + id;
        database.delete(DBHelperGuru.TABLE_GURU, strFilter, null);
    }

}
