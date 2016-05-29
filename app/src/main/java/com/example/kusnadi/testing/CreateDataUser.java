package com.example.kusnadi.testing;

/**
 * Created by Kusnadi on 5/26/2016.
 */
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.view.View.OnClickListener;
import android.widget.Toast;

import java.sql.SQLException;

public class CreateDataUser extends Activity implements OnClickListener{
    private Button buttonSubmit;
    private EditText edUsername;
    private EditText edPassword;
    private DBDataSourceUser dataSource;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        buttonSubmit = (Button) findViewById(R.id.button_submit_user);
        buttonSubmit.setOnClickListener(this);
        edUsername = (EditText) findViewById(R.id.editText_tambah_username);
        edPassword = (EditText) findViewById(R.id.editText_tambah_password);

        dataSource = new DBDataSourceUser(this);
        try {
            dataSource.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        String username = null;
        String password = null;
        @SuppressWarnings("unused")

        Register register = null;
        if(edUsername.getText()!=null && edPassword.getText()!=null) {
            username = edUsername.getText().toString();
            password = edPassword.getText().toString();
        }

        switch (v.getId()) {
            case R.id.button_submit_user :
                register = dataSource.createUser(username, password);

                Toast.makeText(this, "Input Data User \n" +
                                "Username" + username + "\n" +
                                "Password" + password + "\n",
                        Toast.LENGTH_LONG).show();
                break;
        }
    }
}
