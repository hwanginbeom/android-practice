package com.example.user.project_0611;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextView registerButton = (TextView) findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                LoginActivity.this.startActivity(registerIntent);//해당 인텐트 넘어가서 실행


            }
        });

        final EditText idText = (EditText)findViewById(R.id.idText); //매칭 시켜준다 아이디를 입력받는 부분
        final EditText passwordText = (EditText)findViewById(R.id.passwordText);
        final Button loginButton = (Button) findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener(){ //로그인버튼을 눌렀을 때 이벤트처리


            @Override
            public void onClick(View view) {
                String userID = idText.getText().toString(); //text를 받아오는 부분
                String userPassword = passwordText.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() { //결과를 받아온다

                    @Override
                    public void onResponse(String response) { //결과를 받아오는 부분
                        try
                        {
                            JSONObject jsonResponse = new JSONObject(response); //해당결과를 받아온다
                            boolean success = jsonResponse.getBoolean("success"); // boolean 방식으로 볼린값을 처리한다
                            if(success) {
                                AlertDialog.Builder  builder = new AlertDialog.Builder(LoginActivity.this);
                                dialog = builder.setMessage("로그인에 성공했습니다.")
                                        .setPositiveButton("확인",null)
                                        .create();
                                dialog.show();
                                Intent intent = new Intent(LoginActivity.this , MainActivity.class); // 로그인에서 메인으로 넘어가게 한다.
                                LoginActivity.this.startActivity(intent);
                                finish(); // 현재 액티비티를 닫는다.

                            }
                            else {
                                AlertDialog.Builder  builder = new AlertDialog.Builder(LoginActivity.this);
                                dialog = builder.setMessage("계정을 다시 확인하세요.")
                                        .setNegativeButton("다시시도",null)
                                        .create();
                                dialog.show();

                            }
                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();

                        }
                    }
                } ;
                LoginRequest loginRequest = new LoginRequest(userID , userPassword , responseListener); //실질적으로 로그인을 보낸다 .
                RequestQueue queue = Volley.newRequestQueue(LoginActivity.this); // requset를 queueu에 담아 실행한다.
                queue.add(loginRequest); // 정상적으로 보내지고 이게 jsonRespon으로 간다

            }
        });

    }

    @Override
    protected  void onStop(){
        super .onStop();
        if(dialog != null)
        {
            dialog.dismiss();
            dialog = null;
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