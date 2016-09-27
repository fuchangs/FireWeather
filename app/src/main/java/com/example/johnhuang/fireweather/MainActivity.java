package com.example.johnhuang.fireweather;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOError;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    TextView mTextFieldCondition;
    Button mButtonSunny;
    Button mButtonFoggy;
    Firebase mRef;
    ImageView imageView;
    File localfile;

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

        FirebaseStorage storage = FirebaseStorage.getInstance();
        // Create a storage reference from our app
        StorageReference storageRef = storage.getReferenceFromUrl("gs://fire-weather-6d65b.appspot.com");
        // Child references can also take paths
        // spaceRef now points to "users/me/profile.png
        // imagesRef still points to "images"
        StorageReference spaceRef = storageRef.child("opd1f_2.jpg");
        try{
            localfile =File.createTempFile("tmp","jpg");
            spaceRef.getFile(localfile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    imageView=(ImageView) findViewById(R.id.imageView);
                    Bitmap myBitmap = BitmapFactory.decodeFile(localfile.getPath());
                    imageView.setImageBitmap(myBitmap);
                }
            });
        }catch (IOException e){
            e.printStackTrace();
        }





    }
}
