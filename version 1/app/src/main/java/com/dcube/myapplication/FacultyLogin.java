package com.dcube.myapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
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

public class FacultyLogin extends AppCompatActivity {

    private EditText facultyEmail;
    private EditText facultyPassword;

    private Button loginFaculty;
    private Button resetPass;
    private Button registerFaculty;

    private ProgressBar progressBar;

    private FirebaseAuth cseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        cseAuth = FirebaseAuth.getInstance();

        if (cseAuth.getCurrentUser() != null) {

            startActivity(new Intent(FacultyLogin.this, FacultyProfile.class));
            finish();

        }

        setContentView(R.layout.activity_faculty_login);

        facultyEmail = (EditText) findViewById(R.id.facultyEmail);
        facultyPassword = (EditText) findViewById(R.id.facultyPassword);
        loginFaculty = (Button) findViewById(R.id.loginFaculty);
        resetPass = (Button) findViewById(R.id.resetPass);
        registerFaculty = (Button) findViewById(R.id.registerFaculty);

        cseAuth = FirebaseAuth.getInstance();

        registerFaculty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FacultyLogin.this, RegisterFaculty.class));
            }
        });

        resetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(RegisterFaculty.this, ResetPassword.class));
            }
        });

        loginFaculty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = facultyEmail.getText().toString();
                final String password = facultyPassword.getText().toString();

                if (TextUtils.isEmpty(email)) {

                    Toast.makeText(FacultyLogin.this, "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;

                }

                if (TextUtils.isEmpty(password)) {

                    Toast.makeText(FacultyLogin.this, "Enter password!", Toast.LENGTH_SHORT).show();
                    return;

                }

                progressBar.setVisibility(View.VISIBLE);

                //authenticate user
                cseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(FacultyLogin.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        progressBar.setVisibility(View.GONE);

                        if (!task.isSuccessful()) {

                            if (password.length() < 6) {

                                Toast.makeText(FacultyLogin.this, "Password too short, enter atleast 6 characters", Toast.LENGTH_SHORT).show();

                            } else {

                                Toast.makeText(FacultyLogin.this, "Authentication failed.", Toast.LENGTH_LONG).show();

                            }

                        } else {

                            Intent intent = new Intent(FacultyLogin.this, FacultyProfile.class);
                            startActivity(intent);
                            finish();

                        }
                    }
                });
            }
        });
    }
}
