package com.example.docscanner;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Bundle;

public class Login extends AppCompatActivity {
    EditText logemail;
    EditText logpassword;
    Button log;
    DatabaseHelper db;
    TextView text_goto_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        db= new DatabaseHelper(this);
        logemail=findViewById(R.id.edit_mail);
        logpassword=findViewById(R.id.edit_pass);
        text_goto_register=findViewById(R.id.text_register);
        text_goto_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(Login.this,Register.class);
                startActivity(registerIntent);
            }
        });
        log= findViewById(R.id.but_login);
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1email = logemail.getText().toString();
                String s2pass = logpassword.getText().toString();
                Boolean checkemailpasswordexists = db.emailpass(s1email,s2pass);
                if(checkemailpasswordexists==true) {
                    Toast.makeText(getApplicationContext(), "Successfully Logged In", Toast.LENGTH_SHORT).show();
                    Intent registerIntent = new Intent(Login.this, MainActivity.class);
                    startActivity(registerIntent);
                }
                else
                    Toast.makeText(getApplicationContext(), "Invalid Credentials", Toast.LENGTH_SHORT).show();
            }
        });
    }
}