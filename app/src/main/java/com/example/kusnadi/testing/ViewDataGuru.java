package com.example.kusnadi.testing;

/**
 * Created by Kusnadi on 5/24/2016.
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

public class ViewDataGuru extends ListActivity implements AdapterView.OnItemLongClickListener {
    private DBDataSourceGuru dataSourceGuru;

    private ArrayList<Guru> values;
    private Button editButton;
    private Button delButton;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_data_guru);

        dataSourceGuru = new DBDataSourceGuru(this);
        dataSourceGuru.open();
        values = dataSourceGuru.getAllGuru();

        ArrayAdapter<Guru> adapter = new ArrayAdapter<Guru>(this,
                android.R.layout.simple_list_item_1, values);

        setListAdapter(adapter);

        ListView lv = (ListView) findViewById(android.R.id.list);
        lv.setOnItemLongClickListener(this);
    }

    public boolean onItemLongClick(final AdapterView<?> adapter, View v, int pos, final long id) {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_view_guru);
        dialog.setTitle("Pilih Aksi");
        dialog.show();
        final Guru g = (Guru) getListAdapter().getItem(pos);
        editButton = (Button) dialog.findViewById(R.id.button_edit_data_guru);
        delButton = (Button) dialog.findViewById(R.id.button_delete_data_guru);

        editButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        switchToEdit(g.getGuru_id());
                        dialog.dismiss();
                    }
                }
        );

        delButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataSourceGuru.deleteGuru(g.getGuru_id());
                dialog.dismiss();
                finish();
                startActivity(getIntent());
            }
        });

        return true;
    }



    public void switchToEdit(long id) {
        Guru g = dataSourceGuru.getGuru(id);
        Intent i = new Intent(this, EditDataGuru.class);
        Bundle bun = new Bundle();
        bun.putLong("guru_id", g.getGuru_id());
        bun.putString("nama_guru", g.getNama_guru());
        bun.putString("alamat", g.getAlamat());
        bun.putString("umur", g.getUmur());
        bun.putString("status", g.getStatus());
        i.putExtras(bun);
        finale();
        startActivity(i);
    }

    public void finale() {
        ViewDataGuru.this.finish();
        dataSourceGuru.close();
    }

    @Override
    protected void onResume() {
        dataSourceGuru.open();
        super.onResume();
    }

    @Override
    protected void onPause() {
        dataSourceGuru.close();
        super.onPause();
    }

}
