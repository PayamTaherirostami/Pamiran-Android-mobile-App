package com.pamiranindustries.pamiran;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class CamActivity extends AppCompatActivity {
    ImageView imageView;
    Button btnCam;
    Button btnSend;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cam);

        btnSend = findViewById(R.id.SendPhoto);
        btnCam = findViewById(R.id.btnCam);
        imageView = findViewById(R.id.image);

        if (ContextCompat.checkSelfPermission(CamActivity.this,
                Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(CamActivity.this,
            new String[]{
                    Manifest.permission.CAMERA
            },
            100);
        }
        btnCam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                Intent intent= new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                startActivityForResult(intent,100);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode ==100){
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(bitmap);
        }
        }

}