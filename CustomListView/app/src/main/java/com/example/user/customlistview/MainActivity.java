package com.example.user.customlistview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] items = {"망고 쥬스" , "토마토 쥬스" , "포도 쥬스"};
        ListAdapter adapter = new ImageAdapter(this,items);
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener( //이벤트 발생
                new AdapterView.OnItemClickListener() { //아이템을 클릭했을 경우에 대한 리스너 발생
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                        String item = String.valueOf(parent.getItemAtPosition(i)); // 클릭한 위치에 아이템을 받아서 스트링으로 받는다
                        Toast.makeText(MainActivity.this , item, Toast.LENGTH_SHORT).show(); // 출력을 토스트로 보여준다 .
                    }
                }


        );

    }
}
