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

public class DBDataSourceSiswa {
    private SQLiteDatabase database;
    private DBHelperSiswa dbHelperSiswa;

    private String[] allColumns = { DBHelperSiswa.COLUMN_SISWA_ID,
            DBHelperSiswa.COLUMN_SISWA_NAME, DBHelperSiswa.COLUMN_KELAS};
    public DBDataSourceSiswa(Context context) {
        dbHelperSiswa = new DBHelperSiswa(context);
    }

    public void open() throws SQLException {
        database = dbHelperSiswa.getWritableDatabase();
    }

    public void close() {
        dbHelperSiswa.close();
    }

    public Siswa createSiswa(String nama_siswa, String kelas) {
        ContentValues values = new ContentValues();
        values.put(DBHelperSiswa.COLUMN_SISWA_NAME, nama_siswa);
        values.put(DBHelperSiswa.COLUMN_KELAS, kelas);

        long insertId = database.insert(DBHelperSiswa.TABLE_SISWA, null, values);

        Cursor cursor = database.query(DBHelperSiswa.TABLE_SISWA, allColumns, DBHelperSiswa.COLUMN_SISWA_ID + " = " + insertId, null,
                null, null, null);

        cursor.moveToFirst();

        Siswa newSiswa = cursorToSiswa(cursor);
        cursor.close();

        return newSiswa;
    }
    private Siswa cursorToSiswa(Cursor cursor) {
        Siswa siswa = new Siswa();
        Log.v("info", "The getLONG "+cursor.getLong(0));
        Log.v("info", "The setLatLng"+cursor.getString(1)+","+cursor.getString(2));

        siswa.setSiswa_id(cursor.getLong(0));
        siswa.setNama_siswa(cursor.getString(1));
        siswa.setKelas(cursor.getString(2));

        return siswa;
    }

    public ArrayList<Siswa> getAllSiswa() {
        ArrayList<Siswa> daftarSiswa = new ArrayList<Siswa>();

        Cursor cursor = database.query(DBHelperSiswa.TABLE_SISWA, allColumns, null, null, null, null, null);

        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            Siswa siswa = cursorToSiswa(cursor);
            daftarSiswa.add(siswa);
            cursor.moveToNext();
        }
        cursor.close();
        return daftarSiswa;
    }

    public Siswa getSiswa(long id) {
        Siswa siswa = new Siswa();
        Cursor cursor = database.query(DBHelperSiswa.TABLE_SISWA, allColumns, "siswa_id="+id, null, null, null, null);
        cursor.moveToFirst();

        siswa = cursorToSiswa(cursor);

        cursor.close();
        return siswa;
    }

    public void updateSiswa (Siswa s) {
        String strFilter = "siswa_id=" + s.getSiswa_id();

        ContentValues args = new ContentValues();

        args.put(DBHelperSiswa.COLUMN_SISWA_NAME, s.getNama_siswa());
        args.put(DBHelperSiswa.COLUMN_KELAS, s.getKelas());

        database.update(DBHelperSiswa.TABLE_SISWA, args, strFilter, null);
    }

    public void deleteSiswa(long id) {
        String strFilter = "siswa_id=" + id;
        database.delete(DBHelperSiswa.TABLE_SISWA, strFilter, null);
    }

}
