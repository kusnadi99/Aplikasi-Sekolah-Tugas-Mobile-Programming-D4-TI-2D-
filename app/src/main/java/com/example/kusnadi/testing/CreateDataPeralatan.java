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

public class CreateDataPeralatan extends Activity implements OnClickListener{
    private Button buttonSubmit;
    private EditText edNamaPeralatan;
    private EditText edStok;
    private DataSourcePeralatan dataSource;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_data_peralatan);

        buttonSubmit = (Button) findViewById(R.id.button_submit_peralatan);
        buttonSubmit.setOnClickListener(this);
        edNamaPeralatan = (EditText) findViewById(R.id.nama_peralatan);
        edStok = (EditText) findViewById(R.id.stok);

        dataSource = new DataSourcePeralatan(this);
        try {
            dataSource.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        String nama_peralatan = null;
        String stok = null;
        @SuppressWarnings("unused")

        Peralatan peralatan = null;
        if(edNamaPeralatan.getText()!=null && edStok.getText()!=null) {
            nama_peralatan = edNamaPeralatan.getText().toString();
            stok = edStok.getText().toString();
        }

        switch (v.getId()) {
            case R.id.button_submit_peralatan :
                peralatan = dataSource.createPeralatan(nama_peralatan, stok);

                Toast.makeText(this, "Input Data Peralatan \n" +
                                "Nama Peralatan" + nama_peralatan + "\n" +
                                "Stok" + stok + "\n",
                        Toast.LENGTH_LONG).show();
                break;
        }
    }

}
