package com.example.user.youtubeapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.youtube.player.YouTubeApiServiceUtil;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class MainActivity extends YouTubeBaseActivity {


    YouTubePlayerView youTubeView;  //객체 생성
    Button button;                 //버튼 객체
    YouTubePlayer.OnInitializedListener listener;  //이벤트처리를 위한 리스터

    @Override                            // 화면이 만들어 졌을 때의 위한 이벤트 처리
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.youtubeButton);   //버튼 아이디 찾아서 배치
        youTubeView = (YouTubePlayerView) findViewById(R.id.youtubeView); // 레이아웃에 잇던거 찾아서 매핑
        listener = new YouTubePlayer.OnInitializedListener() { //초기화가 되었을 때
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.loadVideo("QDJPk-QUEUQ"); //비디오로 보여주기 이부분이 원하는 영상
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }  //리스너 이벤트 처리 초기화 되어 있을때 대한 내용 처리


        };

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                youTubeView.initialize("AIzaSyDlemrvzaYF_TTSS_5ij0Gf7HCpYYozQW4 ",listener);
            }
        });
    }
}
