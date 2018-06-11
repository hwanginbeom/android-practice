package com.example.user.registeration;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView noticeListView;
    private NoticeAdapter adapter;
    private List<Notice> noticeList;

    @Override
    protected  void onCreate(Bundle savedInstanceState){
        super .onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        noticeListView = (ListView) findViewById(R.id.noticeListView);
        noticeList = new ArrayList<Notice>();

        adapter = new NoticeAdapter(getApplicationContext(),noticeList);
        noticeListView.setAdapter(adapter);

        new BackgroundTask().execute(); //정상적으로 데이터베이서 접근해서 추가한다.



    }
    class BackgroundTask extends AsyncTask<Void, Void, String>
    {
        String  target;

        @Override
        protected  void onPreExecute(){
            target= "http://rydn2002.cafe24.com/ParisDB.php"; //해당 웹서버에 접속
        }

        @Override
        protected String doInBackground(Void... voids) {
            try { //실질적으로 데이터를 얻어오는 곳
                URL url = new URL(target); //해당 url 로 들어가는것
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection(); //접속
                InputStream inputStream = httpURLConnection.getInputStream(); //넘어오는 값 저장
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));//버퍼에 담아서 읽는다
                String temp;
                StringBuilder stringBuilder = new StringBuilder();
                while ((temp = bufferedReader.readLine()) !=null) // null 값이 아닐 때 까지 계속 돌게
                {
                    stringBuilder.append(temp + "\n"); // 한 줄씩 추가
                }
                bufferedReader.close(); // 처리가 끝나면 닫고 연결 끊어준다.
                inputStream.close();
                httpURLConnection.disconnect();
                return  stringBuilder.toString().trim();//다들어간 문자열 반환
            } catch (Exception e) {
                e.printStackTrace();

            }
            return null;
        }

        @Override
        public void onProgressUpdate(Void... values){
            super.onProgressUpdate();
        }
        @Override
        public void onPostExecute(String result){  //해당 결과 처리 하는 것
            try {
                JSONObject jsonObject = new JSONObject(result); //응답 부분 처리
                JSONArray jsonArray = jsonObject.getJSONArray("response"); // 각각의 공지사항
                int count = 0;
                String parisTitle , parisTag , parisReview1, parisReview2;
                while (count < jsonArray.length())
                {
                    JSONObject object = jsonArray.getJSONObject(count); // 현재 배열의 원소값저장
                    parisTitle = object.getString("parisTitle"); // 해당하는 값을 가져와라
                    parisTag = object.getString("parisTag");
                    parisReview1 = object.getString("parisReview1");
                    parisReview2 = object.getString("parisReview2");
                    Notice notice = new Notice(parisTitle , parisTag , parisReview1, parisReview2); //하나의 생성자를 위해 객체를 할당
                    noticeList.add(notice); // 공지사항 리스트에 추가
                    count++;

                }
            }
           catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
