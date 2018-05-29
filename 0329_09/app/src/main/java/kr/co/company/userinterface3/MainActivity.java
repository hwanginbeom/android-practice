package kr.co.company.userinterface3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.example.user.a0329_09.R;

public class    MainAcitivity extends AppCompatActivity{


    @Override
    public void onCreat(Bundle savedInstanceSate) {
        super.onCreate(savedInstanceSate);
        setContentView(R.layout.activity_main);

        Button b1 = (Button) findViewByid(R.id.button1);
        b1.setText("코드에서도 변경 가능 ");

        Button b2 = (Button) fintViewById(R.id.button2);
        b2.setEnabled(false)
    }
}