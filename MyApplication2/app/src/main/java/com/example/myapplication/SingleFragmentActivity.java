package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public abstract class SingleFragmentActivity extends AppCompatActivity {
    abstract Fragment createFragment() ;

    Button mButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        getSupportFragmentManager()
                .beginTransaction()
                .replace( R.id.home_frm,createFragment() )
                .commit();



    }




}
