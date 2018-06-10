package com.example.user.registeration;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.user.registeration.ParisActivity;
import com.example.user.registeration.R;

import java.util.List;

public class NoticeAdapter extends BaseAdapter{


    private Context context;
    private List<Notice> noticeList;

    public NoticeAdapter(Context context, List<Notice> noticeList) { //생성자
        this.context = context;
        this.noticeList = noticeList;
    }


    @Override
    public int getCount() {
        return noticeList.size();
    }

    @Override
    public Object getItem(int i) {
        return noticeList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = View.inflate(context, R.layout.notice, null);
        TextView titleText = (TextView) v.findViewById(R.id.titleText);
        TextView ratingText = (TextView) v.findViewById(R.id.ratingText);
        TextView tagText = (TextView) v.findViewById(R.id.tagText);
        TextView linkText = (TextView) v.findViewById(R.id.linkText);

        titleText.setText(noticeList.get(i).getTitle());
        ratingText.setText(noticeList.get(i).getRating());
        tagText.setText(noticeList.get(i).getTag());
        linkText.setText(noticeList.get(i).getLink());

        v.setTag(noticeList.get(i).getTitle());
        return v;


    }


}
