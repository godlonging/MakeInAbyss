package com.trip.animaljie.makeinabyss.ListViewAdapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.trip.animaljie.makeinabyss.R;

import java.util.List;

public class FunctionAdapter extends ArrayAdapter {
    private final int resourceID;

    public FunctionAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
        resourceID = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        FunctionBean functionBean=(FunctionBean)getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceID,null);
        ImageView functionimage = (ImageView)view.findViewById(R.id.function_image);
        functionimage.setImageResource(functionBean.getImageID());
        return view;
    }
}
