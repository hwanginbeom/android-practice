package com.example.user.project_0624;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {

    private ArrayAdapter adapter;
    private Spinner spinner;
    private String userID;
    private String userPassword;
    private String userGender;
    private String userMajor;
    private String userEmail;
    private AlertDialog dialog;
    private boolean validate = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        spinner = (Spinner) findViewById(R.id.majorSpinner); //초기화 부분 디자인에잇는 메이저 스피너를 가져오고
        adapter = ArrayAdapter.createFromResource(this, R.array.major , android.R.layout.simple_spinner_dropdown_item); //학과명을 가져온다
        spinner.setAdapter(adapter); //어댑터추가

        final EditText idText =(EditText)findViewById(R.id.idText); // 초기화 부분
        final EditText passwordText =(EditText)findViewById(R.id.passwordText); // 초기화 부분
        final EditText emailText =(EditText)findViewById(R.id.emailText); // 초기화 부분

        RadioGroup genderGroup = (RadioGroup) findViewById(R.id.genderGroup);  // gender 그룹은 라디오 그룹으로 만든다.
        int genderGroupID = genderGroup.getCheckedRadioButtonId();   // ID값을 의미
        userGender = ((RadioButton) findViewById(genderGroupID)).getText().toString(); // 현재 선택되어있는 genderID를 매칭시켜준다.


        genderGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int i) {
                RadioButton genderButton = (RadioButton) findViewById(i);  //라디오 버튼은 젠더버튼이라는 변수를 만들고 현재선택되어있는 라디오 버튼을 찾고
                userGender = genderButton.getText().toString(); // usergender 의 값을 바꿔준다.


            }
        });

        final Button validateButton = (Button) findViewById(R.id.validateButton);
        validateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userID = idText.getText().toString();
                if(validate){
                    return;
                }
                if(userID.equals("")){
                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                    dialog = builder.setMessage("아이디는 빈 칸 일수 없습니다.")
                            .setPositiveButton("확인",null)
                            .create();
                    dialog.show();
                    return;
                }
                Response.Listener<String> responseListener  = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try
                        {
                            JSONObject JsonResponse = new JSONObject(response);
                            boolean success = JsonResponse.getBoolean("success");
                            if(success){
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                dialog = builder.setMessage("사용 할 수 있는 아이디입니다.")
                                        .setPositiveButton("확인",null)
                                        .create();
                                dialog.show();
                                idText.setEnabled(false);
                                validate = true;
                                idText.setBackgroundColor(getResources().getColor(R.color.colorGray));
                                validateButton.setBackgroundColor(getResources().getColor(R.color.colorGray));


                            }
                            else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                dialog = builder.setMessage("사용 할 수 없는 아이디입니다.")
                                        .setNegativeButton("확인",null)
                                        .create();
                                dialog.show();

                            }
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };
                ValidateRequest validateRequset = new ValidateRequest(userID,responseListener);
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                queue.add(validateRequset);
            }
        });

        Button registerButton = (Button) findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userID = idText.getText().toString();
                String userPassword = passwordText.getText().toString();
                String userMajor = spinner.getSelectedItem().toString();
                String userEmail = emailText.getText().toString();

                if(!validate){
                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                    dialog = builder.setMessage("먼저 중복 체크를 해주세요")
                            .setNegativeButton("확인",null)
                            .create();
                    dialog.show();
                    return;

                }
                if(userID.equals("") || userEmail.equals("") || userPassword.equals("") || userMajor.equals("") || userGender.equals("")){
                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                    dialog = builder.setMessage("빈 칸 없이 입력해주세요.")
                            .setNegativeButton("확인",null)
                            .create();
                    dialog.show();
                    return;
                }
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try
                        {
                            JSONObject JsonResponse = new JSONObject(response);
                            boolean success = JsonResponse.getBoolean("success");                             if(success){
                            AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                            dialog = builder.setMessage("회원 등록에 성공 하였습니다.")
                                    .setPositiveButton("확인",null)
                                    .create();
                            dialog.show();
                            finish();

                        }
                        else{
                            AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                            dialog = builder.setMessage("회원 등록에 실패 하였습니다.")
                                    .setNegativeButton("확인",null)
                                    .create();
                            dialog.show();

                        }
                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                RegisterRequest registerRequest = new RegisterRequest(userID , userPassword, userGender, userEmail, userMajor,responseListener);
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                queue.add(registerRequest);

            }
        });



    }
    @Override
    protected void onStop(){
        super.onStop();
        if(dialog != null)
        {
            dialog.dismiss();
            dialog = null;
        }
    }
}
