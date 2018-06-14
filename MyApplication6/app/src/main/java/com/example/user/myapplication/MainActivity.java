package com.example.user.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends Activity {


    public static boolean bFirst = true;

    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


        // 처음 시작하면 인트로 화면을 띄워준다

        if (bFirst) {

            setContentView(R.layout.activity_loading);

            Handler han = new Handler();

            han.postDelayed(new Runnable() {

                public void run() {

                    // 시작 플레그를 바꿔주고

                    bFirst = false;

                    // 메인액티비티를 다시 띄워주고 현재 액티비티를 종료한다.

                    startActivity(new Intent(LoadingActivity.this, MainActivity.class));

                    finish();

                }

            }, 2000); // 인트로화면이 떠 있을 시간..

        } else {

            // 두번째 실행될때 메인화면을 띄운다.

            bFirst = true;

            setContentView(R.layout.activity_main);

            // 요기서 버튼, 뷰설정등 findViewById 로 초기화를 실시한다.


        }
    }
}
