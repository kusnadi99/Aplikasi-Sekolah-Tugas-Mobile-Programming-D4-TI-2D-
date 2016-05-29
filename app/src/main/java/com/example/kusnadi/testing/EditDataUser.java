package com.example.kusnadi.testing;

/**
 * Created by Kusnadi on 5/26/2016.
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

public class EditDataUser extends Activity implements OnClickListener{
    private DBDataSourceUser dataSource;

    private long user_id;

    private String username;
    private String password;

    private EditText edUsername;
    private EditText edPassword;

    private TextView txId;

    private Button btnSave;
    private Button btnCancel;

    private Register register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_data_user);

        edUsername = (EditText) findViewById(R.id.editText_edusername);
        edPassword = (EditText) findViewById(R.id.editText_edpassword);
        txId = (TextView) findViewById(R.id.text_id_user);

        dataSource = new DBDataSourceUser(this);
        try {
            dataSource.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Bundle bun = this.getIntent().getExtras();
        user_id = bun.getLong("user_id");
        username=bun.getString("username");
        password= bun.getString("password");

        txId.append(String.valueOf(user_id));

        edUsername.setText(username);
        edPassword.setText(password);

        btnSave = (Button) findViewById(R.id.button_save_update_user);
        btnSave.setOnClickListener(this);
        btnCancel = (Button) findViewById(R.id.button_cancel_update_user);
        btnCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.button_save_update_user:
                register = new Register();
                register.setUsername(edUsername.getText().toString());
                register.setPassword(edPassword.getText().toString());

                register.setUser_id(user_id);
                dataSource.updateRegister(register);
                Intent i4 = new Intent(this, ViewDataUser.class);
                startActivity(i4);
                EditDataUser.this.finish();
                dataSource.close();
                break;

            case R.id.button_cancel_update_user:
                finish();
                dataSource.close();
                break;
        }
    }
}
