package com.trip.animaljie.makeinabyss;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.trip.animaljie.makeinabyss.TicketAdapter.HttpDownloader;
import com.trip.animaljie.makeinabyss.TicketListView.TicketAdapter;
import com.trip.animaljie.makeinabyss.TicketListView.TicketBean;
import com.yalantis.phoenix.PullToRefreshView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TicketActivity extends AppCompatActivity {
    private Button pay;
    public String to;
    public String from;
    public String Date;
    private static final int SET = 1;
    private static final int COMPLETED = 0;
    public static List<TicketBean> ticketBeanList = new ArrayList<>();
    private PullToRefreshView mPullToRefreshView;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what == COMPLETED){
                TicketAdapter adapter = new TicketAdapter(TicketActivity.this,R.layout.ticket_layout,ticketBeanList);
                ListView listView=findViewById(R.id.lv);
                listView.setAdapter(adapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent intent = new Intent(TicketActivity.this,PayActivity.class);
                        startActivity(intent);
                    }
                });
                adapter.notifyDataSetChanged();

                System.out.println("listviewfinish");
            }
        }
    };
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket);
        Intent intent = getIntent();
        to = intent.getStringExtra("to");
        from = intent.getStringExtra("from");
        Date = intent.getStringExtra("date");
        char words[] = to.toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0;i<words.length-1;i++){
            stringBuilder.append(words[i]);
        }
        to = stringBuilder.toString();

        char word1[] = from.toCharArray();
        StringBuilder stringBuilder1 = new StringBuilder();
        for(int i = 0;i<word1.length-1;i++){
            stringBuilder1.append(word1[i]);
        }
        from = stringBuilder1.toString();

        mPullToRefreshView = (PullToRefreshView) findViewById(R.id.pull_to_refresh);
        mPullToRefreshView.setOnRefreshListener(new PullToRefreshView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPullToRefreshView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        new Thread(new NewThread()).start();
                        mPullToRefreshView.setRefreshing(false);

                    }
                }, 500);
            }
        });
        new Thread(new NewThread()).start();

        //handler = new Handler();



    }

    private class NewThread implements Runnable {
        @Override
        public void run() {
            String address = "http://api.jisuapi.com/train/ticket?appkey=c250b96183f718f2&start="+ from + "&end="+to+"&date="+Date;
            System.out.println(address);
            //String address = "http://api.jisuapi.com/train/ticket?appkey=c250b96183f718f2&start=杭州&end=北京&date=2018-5-29";
            HttpDownloader httpDownloader = new HttpDownloader();
            String JsonString = httpDownloader.download(address);
            List<TicketBean> ticketBeanList = new ArrayList<>();
            try {
                get(JsonString);

            } catch (Exception e) {
                e.printStackTrace();
            }
            Message msg =  new Message();
            msg.what = COMPLETED;
            handler.sendMessage(msg);

        }
    }

    public static void get(String jsonstring) throws Exception{
        List<String> train = new ArrayList<>();
        List<String> depart = new ArrayList<>();
        List<String> startstation = new ArrayList<>();
        List<String> stration = new ArrayList<>();
        List<String> time = new ArrayList<>();
        List<String> numsw1 = new ArrayList<>();
        List<String> numtd1  = new ArrayList<>();
        List<String> numyd1  = new ArrayList<>();
        List<String> numed1  = new ArrayList<>();
        List<String> numrz1  = new ArrayList<>();
        List<String> numyz1  = new ArrayList<>();
        List<String> numgr1  = new ArrayList<>();
        List<String> numrw1  = new ArrayList<>();
        List<String> numyw1  = new ArrayList<>();
        List<String> numwz1   = new ArrayList<>();
        List<String> numqt1   = new ArrayList<>();


        JSONObject jsonObject = new JSONObject(jsonstring);
        JSONArray result = jsonObject.optJSONArray("result");
        for(int i=0;i<result.length();i++){
            JSONObject obj = (JSONObject)result.opt(i);
            String trainno = obj.getString("trainno");
            train.add(trainno);
            String departstation = obj.getString("departstation");
            depart.add(departstation);
            String station = obj.getString("station");
            startstation.add(station);
            String endstation = obj.getString("endstation");
            stration.add(endstation);
            String costtime = obj.getString("costtime");
            time.add(costtime);
            String numsw = obj.getString("sw");
            numsw1.add(numsw);
            String numtd = obj.getString("td");
            numtd1.add(numtd);
            String numyd = obj.getString("yd");
            numyd1.add(numyd);
            String numed = obj.getString("ed");
            numed1.add(numed);
            String numrz = obj.getString("rz");
            numrz1.add(numrz);
            String numyz = obj.getString("yz");
            numyz1.add(numyz);
            String numgr = obj.getString("gr");
            numgr1.add(numgr);
            String numrw = obj.getString("rw");
            numrw1.add(numrw);
            String numyw = obj.getString("yw");
            numyw1.add(numyw);
            String numwz = obj.getString("wz");
            numwz1.add(numwz);
            String numqt = obj.getString("qt");
            numqt1.add(numqt);
            TicketBean ticketBean = new TicketBean(trainno,departstation,station,endstation,costtime,numsw,numtd,numyd,numed,numrz,numyz,numgr,numrw,numyw,numwz,numqt);
            ticketBeanList.add(ticketBean);
        }
        System.out.println("finish");
    }

}


