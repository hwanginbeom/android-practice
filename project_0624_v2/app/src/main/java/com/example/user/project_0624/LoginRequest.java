package com.example.user.project_0624;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class LoginRequest extends StringRequest { //회원가입 요청을 시켜라는 클래스

    final static private String URL = "http://dyrnfmxm9.cafe24.com/UserLogin.php"; // 내 도메인 주소와 php 파일이름을 넣는다
    private final String userID;
    private final String userPassword;
    private Map<String, String> parameters;    // 맵을 하나 추가

    public LoginRequest(String userID , String userPassword , Response.Listener<String> listener){// 퍼블릭으로 생성자를 만든다. 네가지를 받는다.
        super(Method.POST, URL, listener , null);   //해당 url을 post 방식으로 전달한다.
        this.userID = userID;
        this.userPassword = userPassword;
        parameters = new HashMap<>();  // 해쉬맵을 이용 초기화
        parameters.put("userID" , userID);
        parameters.put("userPassword" , userPassword);

    }

    @Override
    public Map<String, String> getParams(){
        return parameters;
    }
}
