package com.example.user.registeration;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class RegisterActivity extends AppCompatActivity {

    private ArrayAdapter adapter;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        spinner = (Spinner) findViewById(R.id.majorSpinner); //초기화 부분 디자인에잇는 메이저 스피너를 가져오고
        adapter = ArrayAdapter.createFromResource(this, R.array.major , android.R.layout.simple_spinner_dropdown_item); //학과명을 가져온다
        spinner.setAdapter(adapter); //어댑터추가
    }
}
