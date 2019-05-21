package com.wd.tech.data.adapter;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.tech.R;
import com.wd.tech.ui.activity.FangActivity;

import java.util.ArrayList;
import java.util.List;

public class ImageAdapter extends BaseAdapter {

    private List<String> mList = new ArrayList<>();

    public void addAll(List<String> list){
        mList.addAll(list);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        MyHodler myHodler ;
        if (convertView==null){
            convertView = View.inflate(parent.getContext(), R.layout.circle_image_item,null);
            myHodler = new MyHodler();
            myHodler.image = convertView.findViewById(R.id.community_image);
            convertView.setTag(myHodler);
        }
        myHodler = (MyHodler) convertView.getTag();
        myHodler.image.setImageURI(Uri.parse(mList.get(position)));
        myHodler.image.setImageURI(Uri.parse(mList.get(position)));
        //点击图片查看大图
        myHodler.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(parent.getContext(),FangActivity.class);
                intent.putExtra("currentPosition",position);
                Bundle bundle = new Bundle();
                bundle.putStringArrayList("image", (ArrayList<String>) mList);
                intent.putExtras(bundle);
                parent.getContext().startActivity(intent);
            }
        });

        return convertView;
    }

    public void clear() {
        mList.clear();
    }

    class MyHodler {
        SimpleDraweeView image;
    }
}
