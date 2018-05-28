package com.trip.animaljie.makeinabyss.TicketListView;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.trip.animaljie.makeinabyss.R;

import java.util.List;


public class TicketAdapter extends ArrayAdapter {
    private final int resourceId;
    public TicketAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        TicketBean ticketBean = (TicketBean) getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId,null);
        TextView trainno = view.findViewById(R.id.trainNO);
        TextView station = view.findViewById(R.id.start);
        TextView endstation = view.findViewById(R.id.end);
        TextView costtime = view.findViewById(R.id.costtime);
        TextView numsw = view.findViewById(R.id.numsw);
        TextView numtd = view.findViewById(R.id.numtd);
        TextView numyd = view.findViewById(R.id.numyd);
        TextView numed = view.findViewById(R.id.numed);
        TextView numrz = view.findViewById(R.id.numrz);
        TextView numyz = view.findViewById(R.id.numyz);
        TextView numgr = view.findViewById(R.id.numgr);
        TextView numrw = view.findViewById(R.id.numrw);
        TextView numyw = view.findViewById(R.id.numyw);
        TextView numwz = view.findViewById(R.id.numwz);
        TextView numqt = view.findViewById(R.id.numqt);

        trainno.setText(ticketBean.getTrain());
        station.setText(ticketBean.getStation());
        endstation.setText(ticketBean.getEndstation());
        costtime.setText(ticketBean.getCosttime());
        numsw.setText(ticketBean.getNumsw());
        numtd.setText(ticketBean.getNumtd());
        numyd.setText(ticketBean.getNumyd());
        numed.setText(ticketBean.getNumed());
        numrz.setText(ticketBean.getNumrz());
        numyz.setText(ticketBean.getNumyz());
        numgr.setText(ticketBean.getNumgr());
        numrw.setText(ticketBean.getNumsw());
        numyw.setText(ticketBean.getNumyw());
        numwz.setText(ticketBean.getNumwz());
        numqt.setText(ticketBean.getNumqt());





        return view;
    }
}
