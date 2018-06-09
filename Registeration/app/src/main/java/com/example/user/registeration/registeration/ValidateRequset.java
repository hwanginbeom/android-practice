package com.example.user.registeration.registeration;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class ValidateRequset extends StringRequest{ // ID가 사용 가능한 아이디인지 요청 확인하는 클래스

    final static private String URL = "http:rydn2002.cafe24.com/UserValidate.php"; // 내 도메인 주소와 php 파일이름을 넣는다
    private Map<String, String> parameters;    // 맵을 하나 추가

    public ValidateRequset(String userID , Response.Listener<String> listener){// 퍼블릭으로 생성자를 만든다. 네가지를 받는다.
        super(Method.POST, URL, listener , null);   //해당 url을 post 방식으로 전달한다.
        parameters = new HashMap<>();  // 해쉬맵을 이용 초기화
        parameters.put("userID" , userID);

    }

    @Override
    public Map<String, String> getParams(){
        return parameters;
    }
}
