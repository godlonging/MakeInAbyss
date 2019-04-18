package com.trip.animaljie.makeinabyss;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.trip.animaljie.makeinabyss.InfoListViewAdapter.InfoAdapter;
import com.trip.animaljie.makeinabyss.InfoListViewAdapter.InfoBean;
import com.trip.animaljie.makeinabyss.ListViewAdapter.FunctionAdapter;
import com.trip.animaljie.makeinabyss.ListViewAdapter.FunctionBean;

import java.util.ArrayList;
import java.util.List;

public class InfoActivity extends AppCompatActivity {
    private List<FunctionBean> functionBeanList=new ArrayList<>();
    private List<InfoBean>infoBeanList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        initfunction();
        InfoAdapter adapter = new InfoAdapter(InfoActivity.this,R.layout.info_layout,infoBeanList);
        ListView listView = (ListView)findViewById(R.id.listview_1);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:

                        break;
                    case 1:

                        break;
                    case 2:

                        break;
                    case 3:

                        break;


                }
            }
        });
    }
    private void initfunction(){
        InfoBean menu = new InfoBean(R.mipmap.list_my_menu,"我的订单");
        infoBeanList.add(menu);
        InfoBean collection = new InfoBean(R.mipmap.list_save,"我的收藏");
        infoBeanList.add(collection);
        InfoBean service = new InfoBean(R.mipmap.list_service,"帮助客服");
        infoBeanList.add(service);
        InfoBean setting = new InfoBean(R.mipmap.list_setting,"设置");
        infoBeanList.add(setting);

    }
}
