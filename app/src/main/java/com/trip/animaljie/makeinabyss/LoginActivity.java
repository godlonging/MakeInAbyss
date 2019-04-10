package com.trip.animaljie.makeinabyss;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.trip.animaljie.makeinabyss.Userdatabase.SqliteDB;
import com.trip.animaljie.makeinabyss.Userdatabase.User;

public class LoginActivity extends AppCompatActivity {

    private EditText accout;
    private EditText password;
    private Button sign_in;
    private Button sign_up;
    private CheckBox show;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        accout = (EditText)findViewById(R.id.login_id);
        password = (EditText)findViewById(R.id.login_password);
        sign_in = (Button)findViewById(R.id.login_button);
        sign_up = (Button)findViewById(R.id.sign_button);
        show = (CheckBox)findViewById(R.id.show_password);


        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=accout.getText().toString().trim();
                String pass=password.getText().toString().trim();
                //userList=SqliteDB.getInstance(getApplicationContext()).loadUser();
                int result= SqliteDB.getInstance(getApplicationContext()).Quer(pass,name);
                if (result==1)
                {
                    Toast.makeText(LoginActivity.this,"success",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    intent.putExtra("username",name);
                    startActivity(intent);
                }
                else if (result==0){
                    Toast.makeText(LoginActivity.this,"invail",Toast.LENGTH_SHORT).show();

                }
                else if(result==-1)
                {
                    Toast.makeText(LoginActivity.this,"error",Toast.LENGTH_SHORT).show();
                }
            }
        });

        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=accout.getText().toString().trim();
                String pass=password.getText().toString().trim();

                User user=new User();
                user.setUsername(name);
                user.setUserpwd(pass);

                int result= SqliteDB.getInstance(getApplicationContext()).saveUser(user);
                if (result==1){
                    Toast.makeText(LoginActivity.this,"success",Toast.LENGTH_LONG).show();
                    System.out.println("success");
                }else  if (result==-1)
                {
                    Toast.makeText(LoginActivity.this,"already",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(LoginActivity.this,"!",Toast.LENGTH_SHORT).show();
                }

            }

        });

    }
}

