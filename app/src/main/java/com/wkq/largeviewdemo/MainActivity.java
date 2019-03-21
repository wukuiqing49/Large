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
//        largeImageView.setImageView("http://wjjtest.ys2.cnliveimg.com/802/img/1/2018/12/19/154521021792539770995.gif", "");
        largeImageView.setImageView("http://imgsize.ph.126.net/?imgurl=https://cms-bucket.ws.126.net/2019/02/15/b50f023245b1440dbf481acebc0b9d8d.jpeg_600x250x1x85.jpg", "");

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
