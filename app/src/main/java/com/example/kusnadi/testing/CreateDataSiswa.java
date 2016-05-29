package com.example.kusnadi.testing;

/**
 * Created by Kusnadi on 5/24/2016.
 */

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.view.View.OnClickListener;
import android.widget.Toast;

import java.sql.SQLException;

public class CreateDataSiswa extends Activity implements OnClickListener{
    private Button buttonSubmit;
    private EditText edNamaSiswa;
    private EditText edKelas;
    private DBDataSourceSiswa dataSource;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_data_siswa);

        buttonSubmit = (Button) findViewById(R.id.button_submit_siswa);
        buttonSubmit.setOnClickListener(this);
        edNamaSiswa = (EditText) findViewById(R.id.nama_siswa);
        edKelas = (EditText) findViewById(R.id.kelas);

        dataSource = new DBDataSourceSiswa(this);
        try {
            dataSource.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        String nama_siswa = null;
        String kelas = null;
        @SuppressWarnings("unused")

        Siswa siswa = null;
        if(edNamaSiswa.getText()!=null && edKelas.getText()!=null) {
            nama_siswa = edNamaSiswa.getText().toString();
            kelas = edKelas.getText().toString();
        }

        switch (v.getId()) {
            case R.id.button_submit_siswa :
                siswa = dataSource.createSiswa(nama_siswa, kelas);

                Toast.makeText(this, "Input Data Siswa \n" +
                                "Nama Siswa" + nama_siswa + "\n" +
                                "Kelas" + kelas + "\n",
                        Toast.LENGTH_LONG).show();
                break;
        }
    }
}
