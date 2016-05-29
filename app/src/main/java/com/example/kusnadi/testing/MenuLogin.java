package com.example.kusnadi.testing;

/**
 * Created by Kusnadi on 5/27/2016.
 */
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class MenuLogin extends Activity{
    private Button bLogin;
    private EditText editUsername;
    private EditText editPassword;
    private DBDataSourceUser dataSourceUser;

    DBHelperUser helper = new DBHelperUser(this);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
    }

    public void onButtonClick(View v) {
        if(v.getId() == R.id.button_login_masuk){
            EditText a = (EditText) findViewById(R.id.editText_username);
            String username = a.getText().toString();
            EditText b = (EditText) findViewById(R.id.editText_password);
            String pass = b.getText().toString();

            String password = helper.searchPass(username);
            if(pass.equals(password)) {
                Intent ilogin = new Intent(this, MenuGuru.class);
                ilogin.putExtra("username",username);
                startActivity(ilogin);
            }else{
                Toast temp = Toast.makeText(MenuLogin.this, "Username dan Password Salah", Toast.LENGTH_SHORT);
                temp.show();
            }
        }

    }


}
