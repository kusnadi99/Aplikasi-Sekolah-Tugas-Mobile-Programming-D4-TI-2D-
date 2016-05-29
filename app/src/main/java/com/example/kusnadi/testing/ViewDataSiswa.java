package com.example.kusnadi.testing;

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

/**
 * Created by Kusnadi on 5/24/2016.
 */
public class ViewDataSiswa extends ListActivity implements AdapterView.OnItemLongClickListener{
    private DBDataSourceSiswa dataSourceSiswa;

    private ArrayList<Siswa> values;
    private Button editButton;
    private Button delButton;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_data_siswa);

        dataSourceSiswa = new DBDataSourceSiswa(this);

        try {
            dataSourceSiswa.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        values = dataSourceSiswa.getAllSiswa();

        ArrayAdapter<Siswa> adapter = new ArrayAdapter<Siswa>(this,
                android.R.layout.simple_list_item_1, values);

        setListAdapter(adapter);

        ListView lv = (ListView) findViewById(android.R.id.list);
        lv.setOnItemLongClickListener(this);
    }

    public boolean onItemLongClick(final AdapterView<?> adapter, View v, int pos, final long id) {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_view_siswa);
        dialog.setTitle("Pilih Aksi");
        dialog.show();
        final Siswa s = (Siswa) getListAdapter().getItem(pos);
        editButton = (Button) dialog.findViewById(R.id.button_edit_data_siswa);
        delButton = (Button) dialog.findViewById(R.id.button_delete_data_siswa);

        editButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        switchToEdit(s.getSiswa_id());
                        dialog.dismiss();
                    }
                }
        );
        delButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataSourceSiswa.deleteSiswa(s.getSiswa_id());
                dialog.dismiss();
                finish();
                startActivity(getIntent());
            }
        });
        return true;
    }

    public void switchToEdit(long id) {
        Siswa s = dataSourceSiswa.getSiswa(id);
        Intent i2 = new Intent(this, EditDataSiswa.class);
        Bundle bun = new Bundle();
        bun.putLong("siswa_id", s.getSiswa_id());
        bun.putString("nama_siswa", s.getNama_siswa());
        bun.putString("kelas", s.getKelas());
        i2.putExtras(bun);
        finale();
        startActivity(i2);
    }

    public void finale() {
        ViewDataSiswa.this.finish();
        dataSourceSiswa.close();
    }

    @Override
    protected void onResume() {
        try {
            dataSourceSiswa.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        super.onResume();
    }

    @Override
    protected void onPause() {
        dataSourceSiswa.close();
        super.onPause();
    }
}
