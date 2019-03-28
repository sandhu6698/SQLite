package com.example.lenovo.sqlite;

import android.database.Cursor;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class dbadapter extends BaseAdapter {
    MainActivity myContext;
    TextView t1,t2,t3,t4,t5;
    Cursor result;

    public dbadapter(MainActivity myContext, Cursor result) {
        this.myContext = myContext;
        this.result = result;
    }

    @Override
    public int getCount() {
        return result.getCount()+1;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView=myContext.getLayoutInflater().inflate(R.layout.showdb,null);
        t1=(TextView)convertView.findViewById(R.id.name);
        t2=(TextView)convertView.findViewById(R.id.age);
        t3=(TextView)convertView.findViewById(R.id.gender);
        t4=(TextView)convertView.findViewById(R.id.city);
        t5=(TextView)convertView.findViewById(R.id.mobile);
        if(position==0){
            t1.setText("Name");
            t2.setText("Age");
            t3.setText("Gender");
            t4.setText("City");
            t5.setText("Mobile");
        }else {
            result.moveToPosition(position-1);
            t1.setText(result.getString(0));
            t2.setText(result.getString(1));
            t3.setText(result.getString(2));
            t4.setText(result.getString(3));
            t5.setText(result.getString(4));

        }
        return convertView;
    }
}
