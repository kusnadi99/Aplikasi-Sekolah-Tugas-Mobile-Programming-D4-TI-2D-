package com.example.kusnadi.testing;

/**
 * Created by Kusnadi on 5/24/2016.
 */
import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.sql.SQLException;

public class EditDataGuru extends Activity implements OnClickListener{
    private DBDataSourceGuru dataSource;

    private long id_guru;

    private String nama_guru;
    private String alamat;
    private String umur;
    private String status;

    private EditText edNamaGuru;
    private EditText edAlamat;
    private EditText edUmur;
    private EditText edStatus;

    private TextView txId;

    private Button btnSave;
    private Button btnCancel;

    private Guru guru;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_data_guru);

        edNamaGuru = (EditText) findViewById(R.id.editText_nama_guru);
        edAlamat = (EditText) findViewById(R.id.editText_alamat);
        edUmur = (EditText) findViewById(R.id.editText_umur);
        edStatus = (EditText) findViewById(R.id.editText_status);
        txId = (TextView) findViewById(R.id.text_id_guru);

        dataSource = new DBDataSourceGuru(this);
            dataSource.open();

        Bundle bun = this.getIntent().getExtras();
        id_guru = bun.getLong("guru_id");
        nama_guru=bun.getString("nama_guru");
        alamat= bun.getString("alamat");
        umur=bun.getString("umur");
        status=bun.getString("status");

        txId.append(String.valueOf(id_guru));

        edNamaGuru.setText(nama_guru);
        edAlamat.setText(alamat);
        edUmur.setText(umur);
        edStatus.setText(status);

        btnSave = (Button) findViewById(R.id.button_save_update_guru);
        btnSave.setOnClickListener(this);
        btnCancel = (Button) findViewById(R.id.button_cancel_update_guru);
        btnCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.button_save_update_guru:
                guru = new Guru();
                guru.setNama_guru(edNamaGuru.getText().toString());
                guru.setAlamat(edAlamat.getText().toString());
                guru.setUmur(edUmur.getText().toString());
                guru.setStatus(edStatus.getText().toString());

                guru.setGuru_id(id_guru);
                dataSource.updateGuru(guru);
                Intent i = new Intent(this, ViewDataGuru.class);
                startActivity(i);
                EditDataGuru.this.finish();
                dataSource.close();
                break;

            case R.id.button_cancel_update_guru:
                finish();
                dataSource.close();
                break;
        }
    }

}
