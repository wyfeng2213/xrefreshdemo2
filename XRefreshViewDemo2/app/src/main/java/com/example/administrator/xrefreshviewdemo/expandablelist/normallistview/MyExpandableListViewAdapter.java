package com.example.administrator.xrefreshviewdemo.expandablelist.normallistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.xrefreshviewdemo.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description:
 * Date：2017/04/20 10:27
 * Author: wangyong
 */

public class MyExpandableListViewAdapter extends BaseExpandableListAdapter {
    private Map<String, List<String>> dataset = new HashMap<>();
    private String[] parentList;
    private Context context;

    public MyExpandableListViewAdapter(Context context, Map<String, List<String>> dataset, String[] parentList) {
        this.context = context;
        this.dataset = dataset;
        this.parentList = parentList;
    }

    //  获得某个父项的某个子项
    @Override
    public Object getChild(int parentPos, int childPos) {
        return dataset.get(parentList[parentPos]).get(childPos);
    }

    //  获得父项的数量
    @Override
    public int getGroupCount() {
        if (dataset == null) {
            Toast.makeText(context, "dataset为空", Toast.LENGTH_SHORT).show();
            return 0;
        }
        return dataset.size();
    }

    //  获得某个父项的子项数目
    @Override
    public int getChildrenCount(int parentPos) {
        if (dataset.get(parentList[parentPos]) == null) {
            Toast.makeText(context, "\" + parentList[parentPos] + \" + 数据为空", Toast.LENGTH_SHORT).show();
            return 0;
        }
        return dataset.get(parentList[parentPos]).size();
    }

    //  获得某个父项
    @Override
    public Object getGroup(int parentPos) {
        return dataset.get(parentList[parentPos]);
    }

    //  获得某个父项的id
    @Override
    public long getGroupId(int parentPos) {
        return parentPos;
    }

    //  获得某个父项的某个子项的id
    @Override
    public long getChildId(int parentPos, int childPos) {
        return childPos;
    }

    //  按函数的名字来理解应该是是否具有稳定的id，这个函数目前一直都是返回false，没有去改动过
    @Override
    public boolean hasStableIds() {
        return false;
    }

    //  获得父项显示的view
    @Override
    public View getGroupView(int parentPos, boolean b, View view, ViewGroup viewGroup) {
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.parent_item, null);
        }
        view.setTag(R.layout.parent_item, parentPos);
        view.setTag(R.layout.child_item, -1);
        TextView text = (TextView) view.findViewById(R.id.parent_title);
        text.setText(parentList[parentPos]);
        ImageView img_arround = (ImageView) view.findViewById(R.id.img_around);
        if (b) {
            img_arround.setImageResource(R.mipmap.patient_pull);
        } else {
            img_arround.setImageResource(R.mipmap.patient_arrow);
        }
        return view;
    }

    //  获得子项显示的view
    @Override
    public View getChildView(int parentPos, int childPos, boolean b, View view, ViewGroup viewGroup) {
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.child_item, null);
        }
        view.setTag(R.layout.parent_item, parentPos);
        view.setTag(R.layout.child_item, childPos);
        TextView text = (TextView) view.findViewById(R.id.child_title);
        text.setText(dataset.get(parentList[parentPos]).get(childPos));
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "点到了内置的textview",
                        Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    //  子项是否可选中，如果需要设置子项的点击事件，需要返回true
    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
