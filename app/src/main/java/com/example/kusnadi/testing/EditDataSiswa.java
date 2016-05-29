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

public class EditDataSiswa extends Activity implements OnClickListener{
    private DBDataSourceSiswa dataSource;

    private long siswa_id;

    private String nama_siswa;
    private String kelas;

    private EditText edNamaSiswa;
    private EditText edKelas;

    private TextView txId;

    private Button btnSave;
    private Button btnCancel;

    private Siswa siswa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_data_siswa);

        edNamaSiswa = (EditText) findViewById(R.id.editText_nama_siswa);
        edKelas = (EditText) findViewById(R.id.editText_kelas);
        txId = (TextView) findViewById(R.id.text_id_siswa);

        dataSource = new DBDataSourceSiswa(this);
        try {
            dataSource.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Bundle bun = this.getIntent().getExtras();
        siswa_id = bun.getLong("siswa_id");
        nama_siswa=bun.getString("nama_siswa");
        kelas= bun.getString("kelas");

        txId.append(String.valueOf(siswa_id));

        edNamaSiswa.setText(nama_siswa);
        edKelas.setText(kelas);

        btnSave = (Button) findViewById(R.id.button_save_update_siswa);
        btnSave.setOnClickListener(this);
        btnCancel = (Button) findViewById(R.id.button_cancel_update_siswa);
        btnCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.button_save_update_siswa:
                siswa = new Siswa();
                siswa.setNama_siswa(edNamaSiswa.getText().toString());
                siswa.setKelas(edKelas.getText().toString());

                siswa.setSiswa_id(siswa_id);
                dataSource.updateSiswa(siswa);
                Intent i2 = new Intent(this, ViewDataSiswa.class);
                startActivity(i2);
                EditDataSiswa.this.finish();
                dataSource.close();
                break;

            case R.id.button_cancel_update_siswa:
                finish();
                dataSource.close();
                break;
        }
    }
}
