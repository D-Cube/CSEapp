package com.dcube.myapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterFaculty extends AppCompatActivity {

    private static final String TAG = "EmailPassword";

    private EditText facultyPassword;
    private EditText facultyEmail;

    private Button registerFaculty;
    private Button loginFaculty;

    private ProgressBar progressBar;

    private FirebaseAuth cseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_faculty);

        cseAuth = FirebaseAuth.getInstance();

        facultyEmail = (EditText) findViewById(R.id.facultyEmail);
        facultyPassword = (EditText) findViewById(R.id.facultyPassword);
        registerFaculty = (Button) findViewById(R.id.registerFaculty);
        loginFaculty = (Button) findViewById(R.id.loginFaculty);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        loginFaculty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(RegisterFaculty.this, FacultyLogin.class);
                startActivity(intent);
            }
        });

        registerFaculty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = facultyEmail.getText().toString().trim();
                String password = facultyPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {

                    Toast.makeText(RegisterFaculty.this, "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;

                }

                if (TextUtils.isEmpty(password)) {

                    Toast.makeText(RegisterFaculty.this, "Enter password!", Toast.LENGTH_SHORT).show();
                    return;

                }

                if (password.length() < 6) {

                    Toast.makeText(RegisterFaculty.this, "Password too short, enter atleast 6 characters", Toast.LENGTH_SHORT).show();
                    return;

                }

                progressBar.setVisibility(View.VISIBLE);

                //creating user
                cseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(RegisterFaculty.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        Toast.makeText(RegisterFaculty.this, "creatUserWithEmail:onComplete" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                        if (!task.isSuccessful()) {

                            Toast.makeText(RegisterFaculty.this, "Authentication failed.", Toast.LENGTH_SHORT).show();

                        }  else {

                            startActivity(new Intent(RegisterFaculty.this, FacultyProfile.class));
                            finish();

                        }
                        
                    }
                });

            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }

}


