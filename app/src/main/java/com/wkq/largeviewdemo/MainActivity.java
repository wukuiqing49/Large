package com.wkq.largeviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.cnlive.largeimage.CustomPreviewClickListener;
import com.cnlive.largeimage.CustomPreviewImageView;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CustomPreviewImageView largeImageView = findViewById(R.id.largeView);
//        largeImageView.setImageView("http://img0.imgtn.bdimg.com/it/u=3577340855,977795531&fm=26&gp=0.jpg", "");
        largeImageView.setImageView("https://image.tmdb.org/t/p/w200//dCB7d4l0mfpsISZvr6aPE2z5QF6.jpg", "");
        largeImageView.setClickLisetner(new CustomPreviewClickListener() {
            @Override
            public void longClick(String file) {
                Toast.makeText(MainActivity.this, file, Toast.LENGTH_LONG).show();
            }

            @Override
            public void click() {
                Toast.makeText(MainActivity.this, "单击", Toast.LENGTH_LONG).show();
            }
        });


    }
}
