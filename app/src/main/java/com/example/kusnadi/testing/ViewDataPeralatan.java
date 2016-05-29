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

public class ViewDataPeralatan extends ListActivity implements AdapterView.OnItemLongClickListener{
    private DataSourcePeralatan dataSourcePeralatan;

    private ArrayList<Peralatan> values;
    private Button editButton;
    private Button delButton;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_data_peralatan);

        dataSourcePeralatan = new DataSourcePeralatan(this);

        try {
            dataSourcePeralatan.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        values = dataSourcePeralatan.getAllPeralatan();

        ArrayAdapter<Peralatan> adapter = new ArrayAdapter<Peralatan>(this,
                android.R.layout.simple_list_item_1, values);

        setListAdapter(adapter);

        ListView lv = (ListView) findViewById(android.R.id.list);
        lv.setOnItemLongClickListener(this);
    }

    public boolean onItemLongClick(final AdapterView<?> adapter, View v, int pos, final long id) {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_view_peralatan);
        dialog.setTitle("Pilih Aksi");
        dialog.show();
        final Peralatan p = (Peralatan) getListAdapter().getItem(pos);
        editButton = (Button) dialog.findViewById(R.id.button_edit_data_peralatan);
        delButton = (Button) dialog.findViewById(R.id.button_delete_data_peralatan);

        editButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        switchToEdit(p.getPeralatan_id());
                        dialog.dismiss();
                    }
                }
        );

        delButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataSourcePeralatan.deletePeralatan(p.getPeralatan_id());
                dialog.dismiss();
                finish();
                startActivity(getIntent());
            }
        });

        return true;
    }

    public void switchToEdit(long id) {
        Peralatan p = dataSourcePeralatan.getPeralatan(id);
        Intent i3 = new Intent(this, EditDataPeralatan.class);
        Bundle bun = new Bundle();
        bun.putLong("peralatan_id", p.getPeralatan_id());
        bun.putString("nama_peralatan", p.getNama_alat());
        bun.putString("stok", p.getStok());
        i3.putExtras(bun);
        finale();
        startActivity(i3);
    }

    public void finale() {
        ViewDataPeralatan.this.finish();
        dataSourcePeralatan.close();
    }

    @Override
    protected void onResume() {
        try {
            dataSourcePeralatan.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        super.onResume();
    }

    @Override
    protected void onPause() {
        dataSourcePeralatan.close();
        super.onPause();
    }
}
