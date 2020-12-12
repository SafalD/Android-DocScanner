package com.example.docscanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static java.lang.String.valueOf;

public class Register extends AppCompatActivity {
    DatabaseHelper db;
    EditText username, emailid, passwor, cnfpass ;
    RadioGroup occupation;
    TextView text_goto_login;
    Button reg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        username = findViewById(R.id.redit_user);
        emailid= findViewById(R.id.redit_mail);
        passwor = findViewById(R.id.redit_pass);
        cnfpass = findViewById(R.id.edit_cnf_pass);
        occupation=findViewById(R.id.occupation);
        db= new DatabaseHelper(this);
        text_goto_login= findViewById(R.id.text_login);
        text_goto_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(Register.this,Login.class);
                startActivity(registerIntent);
            }
        });
        reg = findViewById(R.id.but_register);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1 = username.getText().toString();
                String s2 = emailid.getText().toString();
                String s3 = passwor.getText().toString();
                String s4 = cnfpass.getText().toString();
                RadioButton checkedBtn = findViewById(occupation.getCheckedRadioButtonId());
                String s5 = checkedBtn.getText().toString();
                if (s1.equals("") || s2.equals("") || s3.equals("") || s4.equals("") ||s5.equals("") ){
                    Toast.makeText(getApplicationContext(), "Fields are empty" , Toast.LENGTH_SHORT).show();
                }
                else{
                    if(s3.equals(s4)){
                        Boolean checkemail = db.checkemail(s2);
                        if(checkemail==true){
                            Boolean insert = db.insert(s1,s2,s3,s5);
                            if(insert==true){
                                Toast.makeText(getApplicationContext(), "Registered Successfully" , Toast.LENGTH_SHORT).show();
                                Intent registerIntent = new Intent(Register.this,Login.class);
                                startActivity(registerIntent);
                            }
                            else{
                                Toast.makeText(getApplicationContext(), "Error in Registration" , Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "Email Already Exists" , Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Passwords do not match" , Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }


}