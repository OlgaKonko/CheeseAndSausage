package com.olga_kondratenko.cheeseandsausage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.olga_kondratenko.cheeseandsausage.constants.Environments;

public class Screensaver extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        com.olga_kondratenko.cheeseandsausage.env.Environment.env = Environments.ANDROID;
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
        finish();
    }
}
