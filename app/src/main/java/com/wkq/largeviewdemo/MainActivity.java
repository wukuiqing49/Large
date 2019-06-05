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
        largeImageView.setImageView("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1559717106052&di=dda3dc390234e93ecd62a691e2b222bc&imgtype=0&src=http%3A%2F%2Fpic37.nipic.com%2F20140113%2F8800276_184927469000_2.png","");
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
