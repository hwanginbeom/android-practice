package com.example.user.imagebutton;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Adapter extends PagerAdapter {

    private int[] images = {R.drawable.one, R.drawable.two, R.drawable.three}; // 이미지
    private LayoutInflater inflater;
    private Context context;

    public Adapter(Context context){ //생성자
        this.context = context;
    }

    @Override
    public int getCount() {
        return images.length; //이미지 갯수
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container , int position) {  // 각각의 아이템 인스턴스화  컨테이너와 가르치는 포지션을 위치
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE); // 포지션 초기화
        View v = inflater.inflate(R.layout.slider, container, false);//view 초기화
        ImageView imageView = (ImageView) v.findViewById(R.id.imageView); //imageview todtjd
        TextView textView = (TextView) v.findViewById(R.id.textView);// 초기화
        imageView.setImageResource(images[position]);//이미지가 지금 몇번째인지 말해준다
        textView.setText((position + 1) + "번째 이미지 입니다.");//컨테이너에 해당뷰 추가
        container.addView(v);//뷰 반환
        return v;
    }

}

//아이템객체를 반환 하는 것