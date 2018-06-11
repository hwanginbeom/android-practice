package com.example.test



import java.io.BufferedInputStream;
import java.net.URL;
import java.net.URLConnection;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.SyncStateContract.Constants;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.example.user.myapplication.R;

public class s_imgViewMain extends Activity {

    ImageView imgView;
    ViewFlipper flipper;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgView = (ImageView)findViewById(R.id.imgView);
        flipper = (ViewFlipper)findViewById(R.id.flipper);

        try {
            URL url = new URL("이미지 주소");
            URLConnection conn = url.openConnection();
            conn.connect();
            BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
            Bitmap bm = BitmapFactory.decodeStream(bis);
            bis.close();
            imgView.setImageBitmap(bm);
        } catch (Exception e) {
        }

    }
}



출처: http://it77.tistory.com/67 [시원한물냉의 사람사는 이야기]