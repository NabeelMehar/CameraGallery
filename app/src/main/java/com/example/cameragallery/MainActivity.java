package com.example.cameragallery;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    ImageView iv;
    Button gallerybtn,camerabtn;
    int CAMERA=1;
    int GALLERY=2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv=findViewById(R.id.iv);
        gallerybtn=findViewById(R.id.gallerybtn);
        camerabtn=findViewById(R.id.camerabtn);

        camerabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ci=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(ci,CAMERA);
            }
        });

        gallerybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gi=new Intent(Intent.ACTION_GET_CONTENT);

                gi.setType("image/*");
                startActivityForResult(gi,GALLERY);

            }
        });


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==RESULT_OK){
            if (requestCode==CAMERA){
                Bitmap bitmap= (Bitmap) data.getExtras().get("data");
                iv.setImageBitmap(bitmap);
            }else {

                try {
                    Bitmap bitmap=MediaStore.Images.Media.getBitmap(getContentResolver(),data.getData());
                    iv.setImageBitmap(bitmap);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
