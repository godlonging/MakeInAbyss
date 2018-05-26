package com.trip.animaljie.makeinabyss;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.trip.animaljie.makeinabyss.ListViewAdapter.FunctionAdapter;
import com.trip.animaljie.makeinabyss.ListViewAdapter.FunctionBean;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<FunctionBean> functionBeanList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       initfunction();
        FunctionAdapter adapter = new FunctionAdapter(MainActivity.this,R.layout.function_layout,functionBeanList);
        ListView listView = (ListView)findViewById(R.id.listview);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        Intent login = new Intent(MainActivity.this,TripActivity.class);
                        startActivity(login);
                        break;
                    case 1:
                        Intent train = new Intent(MainActivity.this,TrainActivity.class);
                        startActivity(train);
                        break;


                }
            }
        });

    }
    private void initfunction(){
        FunctionBean trip = new FunctionBean(R.drawable.trip);
        functionBeanList.add(trip);
        FunctionBean train = new FunctionBean(R.drawable.train);
        functionBeanList.add(train);
    }
}
