package com.example.ebook_system;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ebook_system.helper.DBHelper;
import com.example.ebook_system.helper.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ResetPasswordActivity extends AppCompatActivity {
    private TextView emailTv;
    private EditText new_password, confirm_password;
    private Button change_pass;
    private DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        emailTv = findViewById(R.id.email);
        Intent intent = getIntent();
        emailTv.setText(intent.getStringExtra("email"));

        new_password = findViewById(R.id.new_password);
        confirm_password = findViewById(R.id.confirm_password);
        change_pass = findViewById(R.id.change_password);
        DB = new DBHelper(this);

        change_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailTv.getText().toString();
                String password = new_password.getText().toString();
                String confirm_pass = confirm_password.getText().toString();

                if(TextUtils.isEmpty(password)) {
                    new_password.setError("Password is required");
                    return;
                }
                else if(TextUtils.isEmpty(confirm_pass)){
                    confirm_password.setError("Please Confirm your Password");
                    return;
                }
                else if(!password.equals(confirm_pass)){
                    confirm_password.setError("Your Password does not match");
                    return;

                }
                else if(!isValidPassword(password)){
                    new_password.setError("Please Enter valid Password with at least on digit, one upper case and one lower case letter : min 8 characters long, max 15");
                    return;
                }

                else if(password.length()<8){
                    new_password.setError("Required Length should be more or equal to 8 letters");
                    return;
                }
                else {
                    if (password.equals(confirm_pass)) {
                        User user_email_password = new User(email, password);
                        Boolean checkPassUpdate = DB.updatePassword(user_email_password);
                        if (checkPassUpdate == true) {
                            Toast.makeText(ResetPasswordActivity.this, "Password Updated successfully", Toast.LENGTH_SHORT).show();
                            Intent intent1 = new Intent(ResetPasswordActivity.this, LoginActivity.class);
                            startActivity(intent1);


                        } else {
                            Toast.makeText(ResetPasswordActivity.this, "Update failed", Toast.LENGTH_LONG).show();
                        }
                    }else {
                        Toast.makeText(ResetPasswordActivity.this, "Password does not match", Toast.LENGTH_LONG).show();
                    }
                }

            }
        });
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