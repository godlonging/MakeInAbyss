package com.trip.animaljie.makeinabyss.InfoListViewAdapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.trip.animaljie.makeinabyss.ListViewAdapter.FunctionBean;
import com.trip.animaljie.makeinabyss.R;

import java.util.List;

public class InfoAdapter extends ArrayAdapter {
    private final int resourceID;

    public InfoAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
        resourceID = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        InfoBean infoBean=(InfoBean)getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceID,null);
        ImageView functionimage = (ImageView)view.findViewById(R.id.headimage);
        TextView infortext = (TextView)view.findViewById(R.id.headtext) ;
        functionimage.setImageResource(infoBean.getImageID());
        infortext.setText(infoBean.getTextID());
        return view;
    }
}
