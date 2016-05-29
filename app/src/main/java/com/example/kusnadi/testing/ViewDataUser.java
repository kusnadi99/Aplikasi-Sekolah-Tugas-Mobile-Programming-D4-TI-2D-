package com.example.kusnadi.testing;

/**
 * Created by Kusnadi on 5/26/2016.
 */
import android.app.Dialog;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.sql.SQLException;
import java.util.ArrayList;

public class ViewDataUser extends ListActivity implements AdapterView.OnItemLongClickListener{
    private DBDataSourceUser dataSourceUser;

    private ArrayList<Register> values;
    private Button editButton;
    private Button delButton;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_data_user);

        dataSourceUser = new DBDataSourceUser(this);

        try {
            dataSourceUser.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        values = dataSourceUser.getAllRegister();

        ArrayAdapter<Register> adapter = new ArrayAdapter<Register>(this,
                android.R.layout.simple_list_item_1, values);

        setListAdapter(adapter);

        ListView lv = (ListView) findViewById(android.R.id.list);
        lv.setOnItemLongClickListener(this);
    }

    public boolean onItemLongClick(final AdapterView<?> adapter, View v, int pos, final long id) {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_view_user);
        dialog.setTitle("Pilih Aksi");
        dialog.show();
        final Register r = (Register) getListAdapter().getItem(pos);
        editButton = (Button) dialog.findViewById(R.id.button_edit_data_user);
        delButton = (Button) dialog.findViewById(R.id.button_delete_data_user);

        editButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        switchToEdit(r.getUser_id());
                        dialog.dismiss();
                    }
                }
        );
        delButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataSourceUser.deleteRegister(r.getUser_id());
                dialog.dismiss();
                finish();
                startActivity(getIntent());
            }
        });
        return true;
    }

    public void switchToEdit(long id) {
        Register r = dataSourceUser.getRegister(id);
        Intent i4 = new Intent(this, EditDataUser.class);
        Bundle bun = new Bundle();
        bun.putLong("user_id", r.getUser_id());
        bun.putString("username", r.getUsername());
        bun.putString("password", r.getPassword());
        i4.putExtras(bun);
        finale();
        startActivity(i4);
    }

    public void finale() {
        ViewDataUser.this.finish();
        dataSourceUser.close();
    }

    @Override
    protected void onResume() {
        try {
            dataSourceUser.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        super.onResume();
    }

    @Override
    protected void onPause() {
        dataSourceUser.close();
        super.onPause();
    }
}
