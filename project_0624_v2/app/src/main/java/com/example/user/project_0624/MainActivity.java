package com.example.user.project_0624;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

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

public class MainActivity extends AppCompatActivity {


    private ArrayAdapter tagAdapter;
    private Spinner tagSpinner;
    private String tag="";
    private ArrayAdapter nationAdapter;
    private Spinner nationSpinner;
    private String nation="";
    private ListView restaurantListView;
    private ListAdapter adapter;
    private List<Restaurant> restaurantList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button searchButton = (Button) findViewById(R.id.search_button);
        final ImageButton homeButton = (ImageButton) findViewById(R.id.home_button);
        final ImageButton franceButton = (ImageButton) findViewById(R.id.france);
        final ImageButton ukButton = (ImageButton) findViewById(R.id.uk);
        final ImageButton germanyButton = (ImageButton) findViewById(R.id.germany);
        final ImageButton espanaButton = (ImageButton) findViewById(R.id.espana);
        final ImageButton portugalButton = (ImageButton) findViewById(R.id.portugal);
        final ImageButton czechButton = (ImageButton) findViewById(R.id.czech);
        final RelativeLayout first = (RelativeLayout) findViewById(R.id.firstPage);
        final LinearLayout searchBar = (LinearLayout) findViewById(R.id.search_bar);
        final RelativeLayout listButton = (RelativeLayout) findViewById(R.id.list_button);
        final ListView view = (ListView) findViewById(R.id.list2);

        tagSpinner = (Spinner) findViewById(R.id.tagSpinner);
        tagAdapter = ArrayAdapter.createFromResource(this, R.array.tag, android.R.layout.simple_spinner_dropdown_item);
        tagSpinner.setAdapter(tagAdapter);
        nationSpinner = (Spinner) findViewById(R.id.nationSpinner);
        nationAdapter = ArrayAdapter.createFromResource(this, R.array.nation, android.R.layout.simple_spinner_dropdown_item);
        nationSpinner.setAdapter(nationAdapter);


                homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this , MainActivity.class);
                MainActivity.this.startActivity(intent);

            }
        });
        franceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this , FranceActivity.class);
                MainActivity.this.startActivity(intent);

            }
        });
        ukButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this , UKActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });
        germanyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this , GermanyActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });
        espanaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this , EspanaActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });
        portugalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this , PortugalActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });
        czechButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this , CzechActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });
        restaurantListView = (ListView) findViewById(R.id.list2);
        restaurantList = new ArrayList<Restaurant>();
        adapter = new ListAdapter(getApplicationContext(), restaurantList);
        restaurantListView.setAdapter(adapter);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nationSpinner.getSelectedItem().equals("국가 선택")){

                    AlertDialog dialog;
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    dialog = builder.setMessage("국가를 선택하세요.")
                            .setPositiveButton("확인",null)
                            .create();
                    dialog.show();
                }
                else{
                    searchBar.setVisibility(View.VISIBLE);
                    first.setVisibility(View.GONE);
                    new BackgroundTask().execute();
                }

            }
        });

    }

    class BackgroundTask extends AsyncTask<Void, Void, String> {
        String target;

        @Override
        protected void onPreExecute(){
            try{
                if(tagSpinner.getSelectedItem().equals("전체 보기")){
                    target = "http://dyrnfmxm9.cafe24.com/fwNation.php?resNation=" + URLEncoder.encode(nationSpinner.getSelectedItem().toString(), "UTF-8");

                }
                else{
                    target = "http://dyrnfmxm9.cafe24.com/fwNationTag.php?resNation=" + URLEncoder.encode(nationSpinner.getSelectedItem().toString(), "UTF-8")+
                            "&resTag="+ URLEncoder.encode(tagSpinner.getSelectedItem().toString(),"UTF-8");
                }
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }

        @Override
        protected String doInBackground(Void... voids) {

            try{
                URL url = new URL(target);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String temp;
                StringBuilder stringBuilder = new StringBuilder();
                while((temp = bufferedReader.readLine())!=null){
                    stringBuilder.append(temp+"\n");
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();
            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        public void onProgressUpdate(Void... values){
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
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
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

    private long lastTimeBackPressed;
    @Override
    public void onBackPressed(){
        if(System.currentTimeMillis() - lastTimeBackPressed <1500){
            finish();
            return;
        }
        Toast.makeText(this, "'뒤로' 버튼을 한 번 더 눌러 종료합니다.", Toast.LENGTH_SHORT).show();
        lastTimeBackPressed = System.currentTimeMillis();

    }


}
