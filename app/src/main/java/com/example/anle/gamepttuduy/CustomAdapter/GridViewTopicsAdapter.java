package com.example.anle.gamepttuduy.CustomAdapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.example.anle.gamepttuduy.Models.Topic;
import com.example.anle.gamepttuduy.R;

import java.util.List;

public class GridViewTopicsAdapter extends ArrayAdapter<Topic> {
    private Context context;
    private int resource;
    private List<Topic> topicList;

    public GridViewTopicsAdapter(Context context,int resource, List<Topic> topicList){
        super(context,resource,topicList);
        this.context=context;
        this.resource=resource;
        this.topicList=topicList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView==null){
            convertView=LayoutInflater.from(context).inflate(resource,parent,false);
            viewHolder=new ViewHolder();
            viewHolder.img_anhTopic=convertView.findViewById(R.id.img_anhTopic);
            convertView.setTag(viewHolder);
        }else {
            viewHolder=(ViewHolder) convertView.getTag();
        }
        Topic topic=topicList.get(position);
        viewHolder.img_anhTopic.setImageResource(topic.getImgResource());
        return convertView;
    }


    class ViewHolder{
        ImageView img_anhTopic;
    }
}
