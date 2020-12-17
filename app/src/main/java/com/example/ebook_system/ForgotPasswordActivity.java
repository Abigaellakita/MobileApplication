package com.example.ebook_system;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ebook_system.helper.DBHelper;

public class ForgotPasswordActivity extends AppCompatActivity {
    private EditText emailEt;
    private Button reset;
    private DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        emailEt = findViewById(R.id.email);
        reset = findViewById(R.id.reset);
        DB= new DBHelper(this);

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailEt.getText().toString();


               if(!isValidEmail(email)){
                    emailEt.setError("Please Enter a valid Email");
                    return;
                }
               else
                {
                    if(!TextUtils.isEmpty(email) && isValidEmail(email) ) {
                        Boolean checkEmail = DB.checkEmail(email);
                        if (checkEmail == true) {
                            Intent intent = new Intent(ForgotPasswordActivity.this, ResetPasswordActivity.class);
                            intent.putExtra("email", email);
                            startActivity(intent);

                        } else {
                            Toast.makeText(ForgotPasswordActivity.this, "We are unable to find this email address in our system", Toast.LENGTH_LONG).show();

                        }
                    }
                    else{
                        Toast.makeText(ForgotPasswordActivity.this, "Enter valid Email", Toast.LENGTH_LONG).show();
                    }
                }

            }
        });

    }
    private Boolean isValidEmail(CharSequence target){
        return(!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
}