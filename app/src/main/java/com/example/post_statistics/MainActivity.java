package com.example.post_statistics;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;



public class MainActivity extends AppCompatActivity {

    public BlocksRecyclerView adapter;
    public ArrayList<Block> mBlocks  = new ArrayList<Block>(6);
    public PostStat postStat;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        postStat = new  PostStat(this);
        mBlocks = postStat.getBlocks();



        if (mBlocks!=null){
            RecyclerView recyclerView = findViewById(R.id.recyclerView_allBlocks);
            int numberOfColumns = 1;
            recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, numberOfColumns));
            adapter = new BlocksRecyclerView(MainActivity.this, mBlocks);
            recyclerView.setAdapter(adapter);
        }





    }
}
