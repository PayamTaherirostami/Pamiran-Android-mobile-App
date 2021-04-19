package com.pamiranindustries.pamiran;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

public class BtnAdapter extends BaseAdapter {

   private Context context;
   private LayoutInflater inflater;
   private String [] myStringArray;
   private int position;

   public BtnAdapter(Context c, String[] myStringArray){
       context = c;
       this.myStringArray = myStringArray;

   }
    @Override
    public int getCount() {
        return myStringArray.length;
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
       if (inflater == null) {
           inflater= (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
       }
       if (convertView == null){
           convertView= inflater.inflate(R.layout.row_item,null);
       }
       Button btn= convertView.findViewById(R.id.button);
       btn.setText(myStringArray[position]);

       return convertView;
    }
}
