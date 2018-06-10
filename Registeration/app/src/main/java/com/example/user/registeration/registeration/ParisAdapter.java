//package com.example.user.registeration.registeration;
//
//import android.content.Context;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.TextView;
//
//import com.example.user.registeration.ParisActivity;
//import com.example.user.registeration.R;
//
//import java.util.List;
//
//public class ParisAdapter extends BaseAdapter{
//
//
//    private Context context;
//    private List<ParisActivity> noticeList;
//
//    public ParisAdapter(Context context, List<ParisActivity> noticeList) { //생성자
//        this.context = context;
//        this.noticeList = noticeList;
//    }
//
//
//    @Override
//    public int getCount() {
//        return noticeList.size();
//    }
//
//    @Override
//    public Object getItem(int i) {
//        return noticeList.get(i);
//    }
//
//    @Override
//    public long getItemId(int i) {
//        return i;
//    }
//
//    @Override
//    public View getView(int i, View view, ViewGroup viewGroup) {
//        View v = View.inflate(context, R.layout.notice, null);
//        TextView titleText = (TextView) v.findViewById(R.id.titleText);
//    }
//
//
//}
