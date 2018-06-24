package com.example.user.project_0611;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class CzechActivity extends AppCompatActivity {

    public ListView restaurantListView;
    public ListAdapter adapter;
    public List<Restaurant> restaurantList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_czech);
        restaurantListView = (ListView) findViewById(R.id.list);

        restaurantList = new ArrayList<Restaurant>();
        adapter = new ListAdapter(getApplicationContext(), restaurantList);
        restaurantListView.setAdapter(adapter);
        final ListView view = (ListView) findViewById(R.id.list);
        new CzechActivity.BackgroundTask().execute();
        final ImageButton homeButton = (ImageButton) findViewById(R.id.home_button);

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CzechActivity.this , MainActivity.class);
                CzechActivity.this.startActivity(intent);

            }
        });
        view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent (CzechActivity.this, InfoActivity.class);
                startActivity(intent);
            }
        });


    }

    class BackgroundTask extends AsyncTask<Void, Void, String> {
        String target;

        @Override
        protected void onPreExecute() {
            try {
                target = "http://dyrnfmxm9.cafe24.com/fwNation.php?resNation=" + URLEncoder.encode("Czech", "UTF-8");

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        protected String doInBackground(Void... voids) {

            ListView restaurantListView;
            ListAdapter adapter;
            List<Restaurant> restaurantList;

            try {
                URL url = new URL(target);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String temp;
                StringBuilder stringBuilder = new StringBuilder();
                while ((temp = bufferedReader.readLine()) != null) {
                    stringBuilder.append(temp + "\n");
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        public void onProgressUpdate(Void... values) {
            super.onProgressUpdate();
        }

        public void onPostExecute(String result){
            try{
                restaurantList.clear();
                JSONObject jsonObject = new JSONObject(result);
                JSONArray jsonArray = jsonObject.getJSONArray("response");
                int count = 0;
                String resID;
                String resTitle;
                String resTag;
                String resRating;
                String resReview1;
                String resReview2;
                String resRank;
                String resImage;
                String resNation;
                String resLink;
                while(count < jsonArray.length()){
                    JSONObject object = jsonArray.getJSONObject(count);
                    resID = object.getString("resID");
                    resTitle = object.getString("resTitle");
                    resTag = object.getString("resTag");
                    resRating = object.getString("resRating");
                    resReview1 = object.getString("resReview1");
                    resReview2 = object.getString("resReview2");
                    resRank = object.getString("resRank");
                    resImage = object.getString("resImage");
                    resNation = object.getString("resNation");
                    resLink = object.getString("resLink");

                    Restaurant restaurant = new Restaurant(resID, resTitle, resTag, resRating,resReview1,resReview2,resRank,resImage,resNation,resLink);
                    restaurantList.add(restaurant);
                    count++;
                }
                adapter.notifyDataSetChanged();
                if(count==0){
                    AlertDialog dialog;
                    AlertDialog.Builder builder = new AlertDialog.Builder(CzechActivity.this);
                    dialog = builder.setMessage("검색 결과가 없습니다.")
                            .setPositiveButton("확인",null)
                            .create();
                    dialog.show();
                }

            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
