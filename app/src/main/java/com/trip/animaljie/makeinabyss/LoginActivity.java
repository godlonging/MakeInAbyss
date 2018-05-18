package com.trip.animaljie.makeinabyss;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    private EditText accout;
    private EditText password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        accout = (EditText)findViewById(R.id.login_id);
        password = (EditText)findViewById(R.id.login_password);


    }
}

