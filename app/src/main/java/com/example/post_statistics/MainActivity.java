package com.example.post_statistics;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;




public class MainActivity extends AppCompatActivity {


    public PostStat postStat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        postStat = new PostStat(this);
        postStat.getBlocks();


    }


}