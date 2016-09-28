package com.example.johnhuang.fireweather;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;

public class StorageActivity extends AppCompatActivity {

    private ImageView imageView;

    final long ONE_MEGABYTE = 1024 * 1024;
    File localfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage);
    }

    @Override
    protected void onStart() {
        super.onStart();

        findViews();

        FirebaseStorage storage = FirebaseStorage.getInstance();
        // Create a storage reference from our app
        StorageReference storageRef = storage.getReferenceFromUrl("gs://fire-weather-6d65b.appspot.com");
        // Points to "images"
        StorageReference imagesRef = storageRef.child("Floor");
        // Child references can also take paths
        // spaceRef now points to "users/me/profile.png
        // imagesRef still points to "images"
//        StorageReference spaceRef = storageRef.child("opd1f_2.jpg");
        // Points to "images/space.jpg"
        // Note that you can use variables to create child values
        String fileName = "pcs1f.jpg";
        StorageReference spaceRef = imagesRef.child(fileName);

//        downloadToLocalFile(spaceRef);

        downloadInMemory(spaceRef);
    }

    private void findViews() {
        imageView = (ImageView) findViewById(R.id.storage_imageview);
    }

    private void downloadToLocalFile(StorageReference spaceRef) {
        try{
            localfile = File.createTempFile("images","jpg");
            spaceRef.getFile(localfile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    // Local temp file has been created
                    Bitmap myBitmap = BitmapFactory.decodeFile(localfile.getPath());
                    imageView.setImageBitmap(myBitmap);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    // Handle any errors

                }
            });
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void downloadInMemory(StorageReference spaceRef) {
        spaceRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                // Use the bytes to display the image
                Bitmap bMap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                imageView.setImageBitmap(bMap);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                // Handle any errors
            }
        });
    }
}
