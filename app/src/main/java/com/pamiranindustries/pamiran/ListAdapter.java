package com.pamiranindustries.pamiran;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

public class ListAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Camera> cameras;

    public ListAdapter(Context context, ArrayList<Camera> cameras) {

        this.context = context;
        this.cameras = cameras;
    }

    @Override
    public int getViewTypeCount() {
        return getCount();
    }
    @Override
    public int getItemViewType(int position) {

        return position;
    }

    @Override
    public int getCount() {
        return cameras.size();
    }

    @Override
    public Object getItem(int position) {
        return cameras.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context
            .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.activity_camera, null, true);
            holder.ImageUrl = (ImageView) convertView.findViewById(R.id.ImageUrl);
            holder.Description = (TextView) convertView.findViewById(R.id.Description);
            convertView.setTag(holder);
        }else {
            // the getTag returns the viewHolder object set as a tag to the view
            holder = (ViewHolder)convertView.getTag();
        }
//        Log.d("strPayam", ">>" + ImageUrl);
        Picasso.get().load(cameras.get(position).getimageurl()).into(holder.ImageUrl);
//        Log.d("strPayam", ">>" + cameras.get(position).getimageurl());
        holder.Description.setText("Description: "+cameras.get(position).description);
        return convertView;
    }

    private class ViewHolder {
        protected TextView Description;
        protected ImageView ImageUrl;
    }

}