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
import com.example.ebook_system.helper.User;

public class LoginActivity extends AppCompatActivity {
    private EditText emailEt, passwordEt;
    private Button SignInButton;
    private TextView signUpTv;
    private ProgressDialog progressDialog;
    private TextView forgot_pass;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        emailEt = findViewById(R.id.email1);
        passwordEt = findViewById(R.id.password1);
        SignInButton = findViewById(R.id.login);
        signUpTv = findViewById(R.id.registerTv);
        DB = new DBHelper(this);
        progressDialog = new ProgressDialog(this);
        forgot_pass = findViewById(R.id.forgot_password);

        SignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailEt.getText().toString();
                String password = passwordEt.getText().toString();
                if(TextUtils.isEmpty(email)){
                    emailEt.setError("Email field is required");
                    return;
                }
                else if(TextUtils.isEmpty(password)){
                    passwordEt.setError("Please enter your password");
                }
                else if(!isValidEmail(email)){
                    emailEt.setError("Please enter a valid Email");
                    return;
                }
                else {
                    User user_email_password = new User(email, password);
                    Boolean checkEmailPassword = DB.checkEmailPassword(user_email_password);
                    if(checkEmailPassword == true) {
                        if (email.equals("mango@gmail.com") && password.equals("Mango123")) {
                            Toast.makeText(LoginActivity.this, "Signed in successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginActivity.this, ShowLanguagesActivity.class);
                            //intent.putExtra("email", "Welcome " + email);
                            startActivity(intent);
                        } else {
                            Toast.makeText(LoginActivity.this, "Signed in successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
                            intent.putExtra("email", email);
                            startActivity(intent);
                        }
                    }
                    else {
                        Toast.makeText(LoginActivity.this, "Invalid credentials", Toast.LENGTH_LONG).show();
                    }

                }


            }
        });

        signUpTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);

            }
        });
        forgot_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
                startActivity(intent);
            }
        });
    }
    private boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
}