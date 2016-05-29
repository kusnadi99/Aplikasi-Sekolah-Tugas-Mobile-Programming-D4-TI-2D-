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

public class EditDataPeralatan extends Activity implements OnClickListener{
    private DataSourcePeralatan dataSource;

    private long peralatan_id;

    private String nama_peralatan;
    private String stok;

    private EditText edNamaPeralatan;
    private EditText edStok;

    private TextView txId;

    private Button btnSave;
    private Button btnCancel;

    private Peralatan peralatan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_data_peralatan);

        edNamaPeralatan = (EditText) findViewById(R.id.editText_nama_peralatan);
        edStok = (EditText) findViewById(R.id.editText_stok);
        txId = (TextView) findViewById(R.id.text_id_peralatan);

        dataSource = new DataSourcePeralatan(this);
        try {
            dataSource.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Bundle bun = this.getIntent().getExtras();
        peralatan_id = bun.getLong("peralatan_id");
        nama_peralatan =bun.getString("nama_peralatan");
        stok= bun.getString("stok");

        txId.append(String.valueOf(peralatan_id));

        edNamaPeralatan.setText(nama_peralatan);
        edStok.setText(stok);

        btnSave = (Button) findViewById(R.id.button_save_update_peralatan);
        btnSave.setOnClickListener(this);
        btnCancel = (Button) findViewById(R.id.button_cancel_update_peralatan);
        btnCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.button_save_update_peralatan:
                peralatan = new Peralatan();
                peralatan.setNama_alat(edNamaPeralatan.getText().toString());
                peralatan.setStok(edStok.getText().toString());

                peralatan.setPeralatan_id(peralatan_id);
                dataSource.updatePeralatan(peralatan);
                Intent i3 = new Intent(this, ViewDataPeralatan.class);
                startActivity(i3);
                EditDataPeralatan.this.finish();
                dataSource.close();
                break;

            case R.id.button_cancel_update_peralatan:
                finish();
                dataSource.close();
                break;
        }
    }
}
