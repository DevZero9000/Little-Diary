package com.lab.littlediary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    EditText nameFormRegister, emailFormRegister, passwordFormRegister;
    Button btnRegister;
    TextView toLoginTextView;
    ProgressBar progressBarRegister;

    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().hide();

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_register);

        nameFormRegister = findViewById(R.id.nameRegister);
        emailFormRegister = findViewById(R.id.emailRegister);
        passwordFormRegister = findViewById(R.id.passwordRegister);

        btnRegister = findViewById(R.id.btnRegister);

        toLoginTextView = findViewById(R.id.loginTextRegister);

        progressBarRegister = findViewById(R.id.progressBarRegister);

        fAuth = FirebaseAuth.getInstance();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailFormRegister.getText().toString().trim();
                String password = passwordFormRegister.getText().toString().trim();

                if (TextUtils.isEmpty(email)){
                    emailFormRegister.setError("Email is required");
                    return;
                }

                if (TextUtils.isEmpty(password)){
                    passwordFormRegister.setError("Password is required");
                    return;
                }

                if (password.length() < 6) {
                    passwordFormRegister.setError("Password must be more than 6 characters long");
                    return;
                }

                progressBarRegister.setVisibility(View.VISIBLE);

                fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(RegisterActivity.this, "Registration successful", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        } else {
                            Toast.makeText(RegisterActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        toLoginTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                finish();
            }
        });
    }
}
