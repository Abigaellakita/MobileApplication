package com.example.ebook_system;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.example.ebook_system.helper.DBHelper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class MainActivity extends AppCompatActivity {
    private EditText emailEt, passwordEt1, passwordEt2;
    private Button SignUpButton;
    private TextView signInTv;
    private ProgressDialog progressDialog;
    DBHelper DB;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        emailEt=findViewById(R.id.email);
        passwordEt1=findViewById(R.id.password);
        passwordEt2=findViewById(R.id.confirm_password);
        progressDialog=new ProgressDialog(this);
        SignUpButton=findViewById(R.id.register);
        signInTv=findViewById(R.id.signInTv);
        DB = new DBHelper(this);

        SignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Register();

            }
        });

        signInTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);

            }
        });


    }
    private void Register(){
        String email = emailEt.getText().toString();
        String password = passwordEt1.getText().toString();
        String confirm_pass = passwordEt2.getText().toString();

        if(TextUtils.isEmpty(email)) {
            emailEt.setError("Email Field is Required");
            return;
        }
        else if(TextUtils.isEmpty(password)) {
            passwordEt1.setError("Password is required");
            return;
        }
        else if(TextUtils.isEmpty(confirm_pass)){
            passwordEt2.setError("Please Confirm your Password");
            return;
        }
        else if(!password.equals(confirm_pass)){
            passwordEt2.setError("Your Password does not match");
            return;

        }
        else if(!isValidEmail(email)){
            emailEt.setError("Please Enter Valid Email");
            return;
        }
        else if(!isValidPassword(password)){
            passwordEt1.setError("Please Enter valid Password(with at least 1 upper case letter, 1 lower case letter and one digit : min 8 characters long, max 15");
            return;
        }

        else if(password.length()<8){
            passwordEt1.setError("Required Length should be more or equal to 8 letters");
            return;
        }
        else {
            if(password.equals(confirm_pass)){
                Boolean checkUser = DB.checkEmail(email);
                if(checkUser == false){
                    Boolean insert = DB.insertData(email, password);
                    if(insert==true){
                        Toast.makeText(MainActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                        intent.putExtra("email", email);
                        startActivity(intent);

                    }
                    else {
                       Toast.makeText(MainActivity.this, "Registration failed", Toast.LENGTH_SHORT ).show();

                    }
                }
                else {
                    Toast.makeText(MainActivity.this, "User already exists! Please sign in", Toast.LENGTH_LONG).show();
                }
            }
        }

    }
    private Boolean isValidEmail(CharSequence target){
        return(!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
    private Boolean isValidPassword(final String password){
        Pattern pattern;
        Matcher matcher;
        final String password_pattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,15}$";
        pattern = Pattern.compile(password_pattern);
        matcher = pattern.matcher(password);

        return matcher.matches();
    }




}