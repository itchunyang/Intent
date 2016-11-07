package com.test.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ExtrasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extras);

        Bundle bundle = getIntent().getExtras();
        System.out.println("age="+bundle.getInt("age"));
        System.out.println("name="+bundle.getString("name"));
        System.out.println("char="+bundle.getChar("char"));
    }
}
