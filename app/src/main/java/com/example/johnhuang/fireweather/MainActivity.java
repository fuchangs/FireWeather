package com.example.johnhuang.fireweather;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    TextView mTextFieldCondition;
    Button mButtonSunny;
    Button mButtonFoggy;
    Firebase mRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mTextFieldCondition = (TextView) findViewById(R.id.textViewCondition);
        mButtonSunny=(Button) findViewById(R.id.buttonSunny);
        mButtonFoggy=(Button) findViewById(R.id.button2Foggy);
        mRef = new Firebase("https://fire-weather-6d65b.firebaseio.com/condition");
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String text = dataSnapshot.getValue(String.class);
                mTextFieldCondition.setText(text);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        mButtonSunny.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRef.setValue("Sunny");
            }
        });
        mButtonFoggy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRef.setValue("Foggy");
            }
        });

    }
}
