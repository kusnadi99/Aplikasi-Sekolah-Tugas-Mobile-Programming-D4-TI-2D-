package com.example.kusnadi.testing;

/**
 * Created by Kusnadi on 5/26/2016.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.sql.SQLException;
import java.util.ArrayList;

public class DBDataSourceUser {
    private SQLiteDatabase database;
    private DBHelperUser dbHelperUser;

    private String[] allColumns = { DBHelperUser.COLUMN_USER_ID,
            DBHelperUser.COLUMN_USERNAME, DBHelperUser.COLUMN_PASSWORD};
    public DBDataSourceUser(Context context) {
        dbHelperUser = new DBHelperUser(context);
    }

    public void open() throws SQLException {
        database = dbHelperUser.getWritableDatabase();
    }

    public void close() {
        dbHelperUser.close();
    }

    public Register createUser(String username, String password) {
        ContentValues values = new ContentValues();
        values.put(DBHelperUser.COLUMN_USERNAME, username);
        values.put(DBHelperUser.COLUMN_PASSWORD, password);

        long insertId = database.insert(DBHelperUser.TABLE_USER, null, values);

        Cursor cursor = database.query(DBHelperUser.TABLE_USER, allColumns, DBHelperUser.COLUMN_USER_ID + " = " + insertId, null,
                null, null, null);

        cursor.moveToFirst();

        Register newRegister = cursorToRegister(cursor);
        cursor.close();

        return newRegister;
    }

    private Register cursorToRegister(Cursor cursor) {
        Register register = new Register();
        Log.v("info", "The getLONG "+cursor.getLong(0));
        Log.v("info", "The setLatLng"+cursor.getString(1)+","+cursor.getString(2));

        register.setUser_id(cursor.getLong(0));
        register.setUsername(cursor.getString(1));
        register.setPassword(cursor.getString(2));

        return register;
    }

    public ArrayList<Register> getAllRegister() {
        ArrayList<Register> daftarRegister = new ArrayList<Register>();

        Cursor cursor = database.query(DBHelperUser.TABLE_USER, allColumns, null, null, null, null, null);

        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            Register register = cursorToRegister(cursor);
            daftarRegister.add(register);
            cursor.moveToNext();
        }
        cursor.close();
        return daftarRegister;
    }

    public Register getRegister(long id) {
        Register register = new Register();
        Cursor cursor = database.query(DBHelperUser.TABLE_USER, allColumns, "user_id="+id, null, null, null, null);
        cursor.moveToFirst();

        register = cursorToRegister(cursor);

        cursor.close();
        return register;
    }

    public void updateRegister (Register r) {
        String strFilter = "user_id=" + r.getUser_id();

        ContentValues args = new ContentValues();

        args.put(DBHelperUser.COLUMN_USERNAME, r.getUsername());
        args.put(DBHelperUser.COLUMN_PASSWORD, r.getPassword());

        database.update(DBHelperUser.TABLE_USER, args, strFilter, null);
    }

    public void deleteRegister(long id) {
        String strFilter = "user_id=" + id;
        database.delete(DBHelperUser.TABLE_USER, strFilter, null);
    }
}
