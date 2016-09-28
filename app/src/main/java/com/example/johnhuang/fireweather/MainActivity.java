package com.example.johnhuang.fireweather;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private MainRecyclerViewAdapter recyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();

        findViews();
        initToolbar();
        initRecyclerView();

    }

    private void initRecyclerView() {
        LinearLayoutManager linearLayoutManager =new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);

        if (recyclerViewAdapter == null){
             recyclerViewAdapter = new MainRecyclerViewAdapter(this);
            recyclerView.setAdapter(recyclerViewAdapter);
        }

        recyclerViewAdapter.SetOnItemClickListener(new MainRecyclerViewAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = null;
                switch (position){
                    case 0:
                        intent = new Intent(getApplicationContext(),RealtimeDatabaseActivity.class);
                        break;
                    case 1:
                        intent = new Intent(getApplicationContext(),StorageActivity.class);
                        break;
                }

                if (intent != null){
                    startActivity(intent);
                }
            }
        });
    }

    private void findViews() {
        toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.main_recyclerview);

    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Firebase");
    }
}
