package com.example.johnhuang.fireweather;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class RealtimeDatabaseActivity extends AppCompatActivity {

    Firebase mRef;
    private TextView textView;
    private Button buttonSunny;
    private Button buttonFoggy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realtime_database);
    }

    @Override
    protected void onStart() {
        super.onStart();

        findViews();

        mRef = new Firebase("https://fire-weather-6d65b.firebaseio.com/condition");
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String text = dataSnapshot.getValue(String.class);
                textView.setText(text);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });

        buttonSunny.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRef.setValue("Sunny");
            }
        });

        buttonFoggy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRef.setValue("Foggy");
            }
        });
    }

    private void findViews() {
        textView = (TextView) findViewById(R.id.realtime_database_tvCondition);
        buttonSunny = (Button) findViewById(R.id.realtime_database_btnSunny);
        buttonFoggy = (Button) findViewById(R.id.realtime_database_btnFoggy);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
