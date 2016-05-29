package com.example.kusnadi.testing;

/**
 * Created by Kusnadi on 5/23/2016.
 */
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MenuGuru extends Activity implements OnClickListener{
    private Button bTambahGuru;
    private Button bViewGuru;
    private Button bTambahSiswa;
    private Button bViewSiswa;
    private Button bTambahPeralatan;
    private Button bViewPeralatan;
    private Button bTambahUser;
    private Button bViewUser;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);


        bViewGuru = (Button) findViewById(R.id.button_view_guru);
        bViewGuru.setOnClickListener(this);
        bTambahSiswa = (Button) findViewById(R.id.button_tambah_siswa);
        bTambahSiswa.setOnClickListener(this);
        bViewSiswa = (Button) findViewById(R.id.button_view_siswa);
        bViewSiswa.setOnClickListener(this);
        bTambahPeralatan = (Button) findViewById(R.id.button_tambah_peralatan);
        bTambahPeralatan.setOnClickListener(this);
        bViewPeralatan = (Button) findViewById(R.id.button_view_peralatan);
        bViewPeralatan.setOnClickListener(this);
        bTambahUser = (Button) findViewById(R.id.button_tambah_user);
        bTambahUser.setOnClickListener(this);
        bViewUser = (Button) findViewById(R.id.button_view_user);
        bViewUser.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.button_tambah_guru :
                Intent i = new Intent(this, CreateDataGuru.class);
                startActivity(i);
                break;
            case R.id.button_view_guru :
                Intent iguru = new Intent(this, ViewDataGuru.class);
                startActivity(iguru);
                break;

            case R.id.button_tambah_siswa :
                Intent i2 = new Intent(this, CreateDataSiswa.class);
                startActivity(i2);
                break;
            case R.id.button_view_siswa :
                Intent isiswa = new Intent(this, ViewDataSiswa.class);
                startActivity(isiswa);
                break;

            case R.id.button_tambah_peralatan :
                Intent i3 = new Intent(this, CreateDataPeralatan.class);
                startActivity(i3);
                break;
            case R.id.button_view_peralatan :
                Intent iperalatan = new Intent(this, ViewDataPeralatan.class);
                startActivity(iperalatan);
                break;

            case R.id.button_tambah_user :
                Intent i4 = new Intent(this, CreateDataUser.class);
                startActivity(i4);
                break;
            case R.id.button_view_user :
                Intent iuser = new Intent(this, ViewDataUser.class);
                startActivity(iuser);
                break;
        }
    }
}
