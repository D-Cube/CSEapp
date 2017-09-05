package com.dcube.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public void facultyLogin(View view) {

        Intent intent = new Intent(getApplicationContext(), RegisterFaculty.class);
        startActivity(intent);

    }

    public void studentLogin(View view) {

        Intent intent = new Intent(getApplicationContext(), StudentLogin.class);
        startActivity(intent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button facultyLogin = (Button) findViewById(R.id.facultyLoginButton);
        facultyLogin.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                Intent intent = new Intent(getApplicationContext(), AdminLogin.class);
                startActivity(intent);
                return true;
            }
        });
    }
}
