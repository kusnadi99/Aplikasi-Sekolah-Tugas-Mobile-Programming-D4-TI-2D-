package com.example.kusnadi.testing;

/**
 * Created by Kusnadi on 5/23/2016.
 */
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateDataGuru extends Activity implements OnClickListener{
    private Button buttonSubmit;
    private EditText edNamaGuru;
    private EditText edAlamat;
    private EditText edUmur;
    private EditText edStatus;
    private DBDataSourceGuru dataSource;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_data_guru);

        buttonSubmit = (Button) findViewById(R.id.button_submit_guru);
        buttonSubmit.setOnClickListener(this);
        edNamaGuru = (EditText) findViewById(R.id.nama_guru);
        edAlamat = (EditText) findViewById(R.id.alamat);
        edUmur = (EditText) findViewById(R.id.umur);
        edStatus = (EditText) findViewById(R.id.status);

        dataSource = new DBDataSourceGuru(this);
        dataSource.open();
    }
        @Override
        public void onClick (View v) {
            String nama_guru = null;
            String alamat = null;
            String umur = null;
            String status = null;
            @SuppressWarnings("unused")

            Guru guru = null;
            if(edNamaGuru.getText() !=null && edAlamat.getText() !=null && edUmur.getText() !=null && edStatus.getText() !=null) {
                nama_guru = edNamaGuru.getText().toString();
                alamat = edAlamat.getText().toString();
                umur = edUmur.getText().toString();
                status = edStatus.getText().toString();
            }

            switch (v.getId()) {
                case R.id.button_submit_guru :
                    guru = dataSource.createGuru(nama_guru, alamat, umur, status);

                    Toast.makeText(this, "Input Data Guru \n" +
                            "Nama Guru " +nama_guru +"\n" +
                            "Alamat "+alamat + "\n" +
                            "Umur "+umur + "\n" +
                            "Status "+status + "\n",
                            Toast.LENGTH_LONG).show();
                    break;
            }
        }

    }

