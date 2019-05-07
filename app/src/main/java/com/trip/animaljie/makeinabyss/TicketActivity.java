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
            String address = "https://api.jisuapi.com/train/station2s?appkey=ea9f93cead9e01e8&start="+ from + "&end="+to+"&ishigh=0"+"&date="+Date;
            //System.out.println(address);
            //String address = "http://api.jisuapi.com/train/ticket?appkey=c250b96183f718f2&start=杭州&end=北京&date=2018-5-29";
            HttpDownloader httpDownloader = new HttpDownloader();
            String JsonString = httpDownloader.download(address);
            //System.out.println(JsonString);
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

        //System.out.println(jsonstring);
        JSONObject jsonObject = new JSONObject(jsonstring);
        //JSONObject data = jsonObject.getJSONObject("result");
        //String json = "{\"status\":\"0\",\"msg\":\"ok\",\"result\":{\"start\":\"上海\",\"end\":\"北京\",\"ishigh\":\"0\",\"date\":\"2019-05-07\",\"list\":[{\"trainno\":\"G102\",\"type\":\"G\",\"station\":\"上海虹桥\",\"endstation\":\"北京南\",\"departuretime\":\"06:26\",\"arrivaltime\":\"12:29\",\"sequenceno\":\"1\",\"costtime\":\"6时3分\",\"distance\":\"0\",\"isend\":\"1\",\"trainno12306\":\"5l0000G10200\",\"pricesw\":\"1748\",\"pricetd\":\"\",\"pricegr1\":\"\",\"pricegr2\":\"\",\"pricerw1\":\"\",\"pricerw2\":\"\",\"priceyw1\":\"\",\"priceyw2\":\"\",\"priceyw3\":\"\",\"typename\":\"高铁\",\"priceyd\":\"933.0\",\"priceed\":\"553.0\"},{\"trainno\":\"G104\",\"type\":\"G\",\"station\":\"上海虹桥\",\"endstation\":\"北京南\",\"departuretime\":\"06:38\",\"arrivaltime\":\"12:33\",\"sequenceno\":\"1\",\"costtime\":\"5时55分\",\"distance\":\"0\",\"isend\":\"1\",\"trainno12306\":\"5l0000G10470\",\"pricesw\":\"1748\",\"pricetd\":\"\",\"pricegr1\":\"\",\"pricegr2\":\"\",\"pricerw1\":\"\",\"pricerw2\":\"\",\"priceyw1\":\"\",\"priceyw2\":\"\",\"priceyw3\":\"\",\"typename\":\"高铁\",\"priceyd\":\"933.0\",\"priceed\":\"553.0\"},{\"trainno\":\"G6\",\"type\":\"G\",\"station\":\"上海\",\"endstation\":\"北京南\",\"departuretime\":\"07:00\",\"arrivaltime\":\"11:38\",\"sequenceno\":\"1\",\"costtime\":\"4时38分\",\"distance\":\"0\",\"isend\":\"1\",\"trainno12306\":\"55000000G601\",\"pricesw\":\"1762.5\",\"pricetd\":\"\",\"pricegr1\":\"\",\"pricegr2\":\"\",\"pricerw1\":\"\",\"pricerw2\":\"\",\"priceyw1\":\"\",\"priceyw2\":\"\",\"priceyw3\":\"\",\"typename\":\"高铁\",\"priceyd\":\"939.0\",\"priceed\":\"558.0\"},{\"trainno\":\"G106\",\"type\":\"G\",\"station\":\"上海虹桥\",\"endstation\":\"北京南\",\"departuretime\":\"07:12\",\"arrivaltime\":\"13:13\",\"sequenceno\":\"1\",\"costtime\":\"6时1分\",\"distance\":\"0\",\"isend\":\"1\",\"trainno12306\":\"5l0000G106A3\",\"pricesw\":\"1748\",\"pricetd\":\"\",\"pricegr1\":\"\",\"pricegr2\":\"\",\"pricerw1\":\"\",\"pricerw2\":\"\",\"priceyw1\":\"\",\"priceyw2\":\"\",\"priceyw3\":\"\",\"typename\":\"高铁\",\"priceyd\":\"933.0\",\"priceed\":\"553.0\"},{\"trainno\":\"G108\",\"type\":\"G\",\"station\":\"上海虹桥\",\"endstation\":\"北京南\",\"departuretime\":\"07:22\",\"arrivaltime\":\"13:23\",\"sequenceno\":\"1\",\"costtime\":\"6时1分\",\"distance\":\"0\",\"isend\":\"1\",\"trainno12306\":\"5l0000G10880\",\"pricesw\":\"1748\",\"pricetd\":\"\",\"pricegr1\":\"\",\"pricegr2\":\"\",\"pricerw1\":\"\",\"pricerw2\":\"\",\"priceyw1\":\"\",\"priceyw2\":\"\",\"priceyw3\":\"\",\"typename\":\"高铁\",\"priceyd\":\"933.0\",\"priceed\":\"553.0\"},{\"trainno\":\"G110\",\"type\":\"G\",\"station\":\"上海虹桥\",\"endstation\":\"北京南\",\"departuretime\":\"07:28\",\"arrivaltime\":\"13:38\",\"sequenceno\":\"1\",\"costtime\":\"6时10分\",\"distance\":\"0\",\"isend\":\"1\",\"trainno12306\":\"5l0000G110C0\",\"pricesw\":\"1748\",\"pricetd\":\"\",\"pricegr1\":\"\",\"pricegr2\":\"\",\"pricerw1\":\"\",\"pricerw2\":\"\",\"priceyw1\":\"\",\"priceyw2\":\"\",\"priceyw3\":\"\",\"typename\":\"高铁\",\"priceyd\":\"933.0\",\"priceed\":\"553.0\"},{\"trainno\":\"G120\",\"type\":\"G\",\"station\":\"上海虹桥\",\"endstation\":\"北京南\",\"departuretime\":\"07:51\",\"arrivaltime\":\"13:33\",\"sequenceno\":\"1\",\"costtime\":\"5时42分\",\"distance\":\"0\",\"isend\":\"1\",\"trainno12306\":\"5l0000G120S0\",\"pricesw\":\"1748\",\"pricetd\":\"\",\"pricegr1\":\"\",\"pricegr2\":\"\",\"pricerw1\":\"\",\"pricerw2\":\"\",\"priceyw1\":\"\",\"priceyw2\":\"\",\"priceyw3\":\"\",\"typename\":\"高铁\",\"priceyd\":\"933.0\",\"priceed\":\"553.0\"},{\"trainno\":\"G8\",\"type\":\"G\",\"station\":\"上海虹桥\",\"endstation\":\"北京南\",\"departuretime\":\"08:00\",\"arrivaltime\":\"12:24\",\"sequenceno\":\"1\",\"costtime\":\"4时24分\",\"distance\":\"0\",\"isend\":\"1\",\"trainno12306\":\"5l000000G815\",\"pricesw\":\"1748\",\"pricetd\":\"\",\"pricegr1\":\"\",\"pricegr2\":\"\",\"pricerw1\":\"\",\"pricerw2\":\"\",\"priceyw1\":\"\",\"priceyw2\":\"\",\"priceyw3\":\"\",\"typename\":\"高铁\",\"priceyd\":\"933.0\",\"priceed\":\"553.0\"},{\"trainno\":\"G112\",\"type\":\"G\",\"station\":\"上海虹桥\",\"endstation\":\"北京南\",\"departuretime\":\"08:05\",\"arrivaltime\":\"14:08\",\"sequenceno\":\"1\",\"costtime\":\"6时3分\",\"distance\":\"0\",\"isend\":\"1\",\"trainno12306\":\"5l0000G11295\",\"pricesw\":\"1748\",\"pricetd\":\"\",\"pricegr1\":\"\",\"pricegr2\":\"\",\"pricerw1\":\"\",\"pricerw2\":\"\",\"priceyw1\":\"\",\"priceyw2\":\"\",\"priceyw3\":\"\",\"typename\":\"高铁\",\"priceyd\":\"933.0\",\"priceed\":\"553.0\"},{\"trainno\":\"G114\",\"type\":\"G\",\"station\":\"上海虹桥\",\"endstation\":\"北京南\",\"departuretime\":\"08:15\",\"arrivaltime\":\"14:13\",\"sequenceno\":\"1\",\"costtime\":\"5时58分\",\"distance\":\"0\",\"isend\":\"1\",\"trainno12306\":\"5l0000G11480\",\"pricesw\":\"1748\",\"pricetd\":\"\",\"pricegr1\":\"\",\"pricegr2\":\"\",\"pricerw1\":\"\",\"pricerw2\":\"\",\"priceyw1\":\"\",\"priceyw2\":\"\",\"priceyw3\":\"\",\"typename\":\"高铁\",\"priceyd\":\"933.0\",\"priceed\":\"553.0\"},{\"trainno\":\"G2\",\"type\":\"G\",\"station\":\"上海虹桥\",\"endstation\":\"北京南\",\"departuretime\":\"09:00\",\"arrivaltime\":\"13:28\",\"sequenceno\":\"1\",\"costtime\":\"4时28分\",\"distance\":\"0\",\"isend\":\"1\",\"trainno12306\":\"5l000000G240\",\"pricesw\":\"1748\",\"pricetd\":\"\",\"pricegr1\":\"\",\"pricegr2\":\"\",\"pricerw1\":\"\",\"pricerw2\":\"\",\"priceyw1\":\"\",\"priceyw2\":\"\",\"priceyw3\":\"\",\"typename\":\"高铁\",\"priceyd\":\"933.0\",\"priceed\":\"553.0\"},{\"trainno\":\"G116\",\"type\":\"G\",\"station\":\"上海虹桥\",\"endstation\":\"北京南\",\"departuretime\":\"09:34\",\"arrivaltime\":\"15:23\",\"sequenceno\":\"1\",\"costtime\":\"5时49分\",\"distance\":\"0\",\"isend\":\"1\",\"trainno12306\":\"5l0000G11680\",\"pricesw\":\"1748\",\"pricetd\":\"\",\"pricegr1\":\"\",\"pricegr2\":\"\",\"pricerw1\":\"\",\"pricerw2\":\"\",\"priceyw1\":\"\",\"priceyw2\":\"\",\"priceyw3\":\"\",\"typename\":\"高铁\",\"priceyd\":\"933.0\",\"priceed\":\"553.0\"},{\"trainno\":\"G10\",\"type\":\"G\",\"station\":\"上海虹桥\",\"endstation\":\"北京南\",\"departuretime\":\"10:00\",\"arrivaltime\":\"14:28\",\"sequenceno\":\"1\",\"costtime\":\"4时28分\",\"distance\":\"0\",\"isend\":\"1\",\"trainno12306\":\"5l00000G1002\",\"pricesw\":\"1748\",\"pricetd\":\"\",\"pricegr1\":\"\",\"pricegr2\":\"\",\"pricerw1\":\"\",\"pricerw2\":\"\",\"priceyw1\":\"\",\"priceyw2\":\"\",\"priceyw3\":\"\",\"typename\":\"高铁\",\"priceyd\":\"933.0\",\"priceed\":\"553.0\"},{\"trainno\":\"G42\",\"type\":\"G\",\"station\":\"上海虹桥\",\"endstation\":\"北京南\",\"departuretime\":\"10:26\",\"arrivaltime\":\"16:08\",\"sequenceno\":\"4\",\"costtime\":\"5时42分\",\"distance\":\"0\",\"isend\":\"1\",\"trainno12306\":\"5600000G4200\",\"pricesw\":\"1748\",\"pricetd\":\"\",\"pricegr1\":\"\",\"pricegr2\":\"\",\"pricerw1\":\"\",\"pricerw2\":\"\",\"priceyw1\":\"\",\"priceyw2\":\"\",\"priceyw3\":\"\",\"typename\":\"高铁\",\"priceyd\":\"933.0\",\"priceed\":\"553.0\"},{\"trainno\":\"G122\",\"type\":\"G\",\"station\":\"上海虹桥\",\"endstation\":\"北京南\",\"departuretime\":\"10:41\",\"arrivaltime\":\"16:43\",\"sequenceno\":\"1\",\"costtime\":\"6时2分\",\"distance\":\"0\",\"isend\":\"1\",\"trainno12306\":\"5l0000G122B0\",\"pricesw\":\"1748\",\"pricetd\":\"\",\"pricegr1\":\"\",\"pricegr2\":\"\",\"pricerw1\":\"\",\"pricerw2\":\"\",\"priceyw1\":\"\",\"priceyw2\":\"\",\"priceyw3\":\"\",\"typename\":\"高铁\",\"priceyd\":\"933.0\",\"priceed\":\"553.0\"},{\"trainno\":\"G124\",\"type\":\"G\",\"station\":\"上海虹桥\",\"endstation\":\"北京南\",\"departuretime\":\"10:59\",\"arrivaltime\":\"16:18\",\"sequenceno\":\"1\",\"costtime\":\"5时19分\",\"distance\":\"0\",\"isend\":\"1\",\"trainno12306\":\"5l0000G124W0\",\"pricesw\":\"1748\",\"pricetd\":\"\",\"pricegr1\":\"\",\"pricegr2\":\"\",\"pricerw1\":\"\",\"pricerw2\":\"\",\"priceyw1\":\"\",\"priceyw2\":\"\",\"priceyw3\":\"\",\"typename\":\"高铁\",\"priceyd\":\"933.0\",\"priceed\":\"553.0\"},{\"trainno\":\"G126\",\"type\":\"G\",\"station\":\"上海虹桥\",\"endstation\":\"北京南\",\"departuretime\":\"11:05\",\"arrivaltime\":\"17:05\",\"sequenceno\":\"1\",\"costtime\":\"6时0分\",\"distance\":\"0\",\"isend\":\"1\",\"trainno12306\":\"5l0000G12601\",\"pricesw\":\"1748\",\"pricetd\":\"\",\"pricegr1\":\"\",\"pricegr2\":\"\",\"pricerw1\":\"\",\"pricerw2\":\"\",\"priceyw1\":\"\",\"priceyw2\":\"\",\"priceyw3\":\"\",\"typename\":\"高铁\",\"priceyd\":\"933.0\",\"priceed\":\"553.0\"},{\"trainno\":\"G130\",\"type\":\"G\",\"station\":\"上海\",\"endstation\":\"北京南\",\"departuretime\":\"11:15\",\"arrivaltime\":\"17:29\",\"sequenceno\":\"1\",\"costtime\":\"6时14分\",\"distance\":\"0\",\"isend\":\"1\",\"trainno12306\":\"550000G13000\",\"pricesw\":\"1762.5\",\"pricetd\":\"\",\"pricegr1\":\"\",\"pricegr2\":\"\",\"pricerw1\":\"\",\"pricerw2\":\"\",\"priceyw1\":\"\",\"priceyw2\":\"\",\"priceyw3\":\"\",\"typename\":\"高铁\",\"priceyd\":\"939.0\",\"priceed\":\"558.0\"},{\"trainno\":\"G12\",\"type\":\"G\",\"station\":\"上海\",\"endstation\":\"北京南\",\"departuretime\":\"12:00\",\"arrivaltime\":\"16:38\",\"sequenceno\":\"1\",\"costtime\":\"4时38分\",\"distance\":\"0\",\"isend\":\"1\",\"trainno12306\":\"5500000G1201\",\"pricesw\":\"1762.5\",\"pricetd\":\"\",\"pricegr1\":\"\",\"pricegr2\":\"\",\"pricerw1\":\"\",\"pricerw2\":\"\",\"priceyw1\":\"\",\"priceyw2\":\"\",\"priceyw3\":\"\",\"typename\":\"高铁\",\"priceyd\":\"939.0\",\"priceed\":\"558.0\"},{\"trainno\":\"G132\",\"type\":\"G\",\"station\":\"上海虹桥\",\"endstation\":\"北京南\",\"departuretime\":\"12:17\",\"arrivaltime\":\"18:32\",\"sequenceno\":\"1\",\"costtime\":\"6时15分\",\"distance\":\"0\",\"isend\":\"1\",\"trainno12306\":\"5l0000G132E0\",\"pricesw\":\"1748\",\"pricetd\":\"\",\"pricegr1\":\"\",\"pricegr2\":\"\",\"pricerw1\":\"\",\"pricerw2\":\"\",\"priceyw1\":\"\",\"priceyw2\":\"\",\"priceyw3\":\"\",\"typename\":\"高铁\",\"priceyd\":\"933.0\",\"priceed\":\"553.0\"},{\"trainno\":\"1462\",\"type\":\"O\",\"station\":\"上海\",\"endstation\":\"北京\",\"departuretime\":\"12:18\",\"arrivaltime\":\"10:50\",\"sequenceno\":\"1\",\"costtime\":\"22时32分\",\"distance\":\"0\",\"isend\":\"1\",\"trainno12306\":\"550000146200\",\"pricesw\":\"\",\"pricetd\":\"\",\"pricerz\":\"\",\"priceyz\":\"156.5\",\"pricegr1\":\"\",\"pricegr2\":\"\",\"pricerw1\":\"455.5\",\"pricerw2\":\"\",\"priceyw1\":\"283.5\",\"priceyw2\":\"\",\"priceyw3\":\"\",\"typename\":\"空车体返回列车\"},{\"trainno\":\"G412\",\"type\":\"G\",\"station\":\"上海虹桥\",\"endstation\":\"北京南\",\"departuretime\":\"12:28\",\"arrivaltime\":\"18:48\",\"sequenceno\":\"1\",\"costtime\":\"6时20分\",\"distance\":\"0\",\"isend\":\"1\",\"trainno12306\":\"5l0000G41260\",\"pricesw\":\"1748\",\"pricetd\":\"\",\"pricegr1\":\"\",\"pricegr2\":\"\",\"pricerw1\":\"\",\"pricerw2\":\"\",\"priceyw1\":\"\",\"priceyw2\":\"\",\"priceyw3\":\"\",\"typename\":\"高铁\",\"priceyd\":\"933.0\",\"priceed\":\"553.0\"},{\"trainno\":\"G134\",\"type\":\"G\",\"station\":\"上海虹桥\",\"endstation\":\"北京南\",\"departuretime\":\"13:00\",\"arrivaltime\":\"18:58\",\"sequenceno\":\"1\",\"costtime\":\"5时58分\",\"distance\":\"0\",\"isend\":\"1\",\"trainno12306\":\"5l0000G134B0\",\"pricesw\":\"1748\",\"pricetd\":\"\",\"pricegr1\":\"\",\"pricegr2\":\"\",\"pricerw1\":\"\",\"pricerw2\":\"\",\"priceyw1\":\"\",\"priceyw2\":\"\",\"priceyw3\":\"\",\"typename\":\"高铁\",\"priceyd\":\"933.0\",\"priceed\":\"553.0\"},{\"trainno\":\"G138\",\"type\":\"G\",\"station\":\"上海虹桥\",\"endstation\":\"北京南\",\"departuretime\":\"13:29\",\"arrivaltime\":\"19:28\",\"sequenceno\":\"1\",\"costtime\":\"5时59分\",\"distance\":\"0\",\"isend\":\"1\",\"trainno12306\":\"5l0000G13861\",\"pricesw\":\"1748\",\"pricetd\":\"\",\"pricegr1\":\"\",\"pricegr2\":\"\",\"pricerw1\":\"\",\"pricerw2\":\"\",\"priceyw1\":\"\",\"priceyw2\":\"\",\"priceyw3\":\"\",\"typename\":\"高铁\",\"priceyd\":\"933.0\",\"priceed\":\"553.0\"},{\"trainno\":\"G140\",\"type\":\"G\",\"station\":\"上海虹桥\",\"endstation\":\"北京南\",\"departuretime\":\"13:34\",\"arrivaltime\":\"19:41\",\"sequenceno\":\"1\",\"costtime\":\"6时7分\",\"distance\":\"0\",\"isend\":\"1\",\"trainno12306\":\"5l0000G14051\",\"pricesw\":\"1748\",\"pricetd\":\"\",\"pricegr1\":\"\",\"pricegr2\":\"\",\"pricerw1\":\"\",\"pricerw2\":\"\",\"priceyw1\":\"\",\"priceyw2\":\"\",\"priceyw3\":\"\",\"typename\":\"高铁\",\"priceyd\":\"933.0\",\"priceed\":\"553.0\"},{\"trainno\":\"G4\",\"type\":\"G\",\"station\":\"上海虹桥\",\"endstation\":\"北京南\",\"departuretime\":\"14:00\",\"arrivaltime\":\"18:28\",\"sequenceno\":\"1\",\"costtime\":\"4时28分\",\"distance\":\"0\",\"isend\":\"1\",\"trainno12306\":\"5l000000G433\",\"pricesw\":\"1748\",\"pricetd\":\"\",\"pricegr1\":\"\",\"pricegr2\":\"\",\"pricerw1\":\"\",\"pricerw2\":\"\",\"priceyw1\":\"\",\"priceyw2\":\"\",\"priceyw3\":\"\",\"typename\":\"高铁\",\"priceyd\":\"933.0\",\"priceed\":\"553.0\"},{\"trainno\":\"G142\",\"type\":\"G\",\"station\":\"上海虹桥\",\"endstation\":\"北京南\",\"departuretime\":\"14:10\",\"arrivaltime\":\"20:18\",\"sequenceno\":\"1\",\"costtime\":\"6时8分\",\"distance\":\"0\",\"isend\":\"1\",\"trainno12306\":\"5l0000G14261\",\"pricesw\":\"1748\",\"pricetd\":\"\",\"pricegr1\":\"\",\"pricegr2\":\"\",\"pricerw1\":\"\",\"pricerw2\":\"\",\"priceyw1\":\"\",\"priceyw2\":\"\",\"priceyw3\":\"\",\"typename\":\"高铁\",\"priceyd\":\"933.0\",\"priceed\":\"553.0\"},{\"trainno\":\"G146\",\"type\":\"G\",\"station\":\"上海虹桥\",\"endstation\":\"北京南\",\"departuretime\":\"14:52\",\"arrivaltime\":\"20:48\",\"sequenceno\":\"1\",\"costtime\":\"5时56分\",\"distance\":\"0\",\"isend\":\"1\",\"trainno12306\":\"5l0000G146F0\",\"pricesw\":\"1748\",\"pricetd\":\"\",\"pricegr1\":\"\",\"pricegr2\":\"\",\"pricerw1\":\"\",\"pricerw2\":\"\",\"priceyw1\":\"\",\"priceyw2\":\"\",\"priceyw3\":\"\",\"typename\":\"高铁\",\"priceyd\":\"933.0\",\"priceed\":\"553.0\"},{\"trainno\":\"G14\",\"type\":\"G\",\"station\":\"上海虹桥\",\"endstation\":\"北京南\",\"departuretime\":\"15:00\",\"arrivaltime\":\"19:36\",\"sequenceno\":\"1\",\"costtime\":\"4时36分\",\"distance\":\"0\",\"isend\":\"1\",\"trainno12306\":\"5l00000G1443\",\"pricesw\":\"1748\",\"pricetd\":\"\",\"pricegr1\":\"\",\"pricegr2\":\"\",\"pricerw1\":\"\",\"pricerw2\":\"\",\"priceyw1\":\"\",\"priceyw2\":\"\",\"priceyw3\":\"\",\"typename\":\"高铁\",\"priceyd\":\"933.0\",\"priceed\":\"553.0\"},{\"trainno\":\"G148\",\"type\":\"G\",\"station\":\"上海虹桥\",\"endstation\":\"北京南\",\"departuretime\":\"15:23\",\"arrivaltime\":\"21:13\",\"sequenceno\":\"1\",\"costtime\":\"5时50分\",\"distance\":\"0\",\"isend\":\"1\",\"trainno12306\":\"5l0000G148D0\",\"pricesw\":\"1748\",\"pricetd\":\"\",\"pricegr1\":\"\",\"pricegr2\":\"\",\"pricerw1\":\"\",\"pricerw2\":\"\",\"priceyw1\":\"\",\"priceyw2\":\"\",\"priceyw3\":\"\",\"typename\":\"高铁\",\"priceyd\":\"933.0\",\"priceed\":\"553.0\"},{\"trainno\":\"G170\",\"type\":\"G\",\"station\":\"上海虹桥\",\"endstation\":\"北京南\",\"departuretime\":\"15:52\",\"arrivaltime\":\"21:18\",\"sequenceno\":\"1\",\"costtime\":\"5时26分\",\"distance\":\"0\",\"isend\":\"1\",\"trainno12306\":\"5l0000G17004\",\"pricesw\":\"1748\",\"pricetd\":\"\",\"pricegr1\":\"\",\"pricegr2\":\"\",\"pricerw1\":\"\",\"pricerw2\":\"\",\"priceyw1\":\"\",\"priceyw2\":\"\",\"priceyw3\":\"\",\"typename\":\"高铁\",\"priceyd\":\"933.0\",\"priceed\":\"553.0\"},{\"trainno\":\"G150\",\"type\":\"G\",\"station\":\"上海虹桥\",\"endstation\":\"北京南\",\"departuretime\":\"16:05\",\"arrivaltime\":\"22:00\",\"sequenceno\":\"1\",\"costtime\":\"5时55分\",\"distance\":\"0\",\"isend\":\"1\",\"trainno12306\":\"5l0000G15062\",\"pricesw\":\"1748\",\"pricetd\":\"\",\"pricegr1\":\"\",\"pricegr2\":\"\",\"pricerw1\":\"\",\"pricerw2\":\"\",\"priceyw1\":\"\",\"priceyw2\":\"\",\"priceyw3\":\"\",\"typename\":\"高铁\",\"priceyd\":\"933.0\",\"priceed\":\"553.0\"},{\"trainno\":\"G152\",\"type\":\"G\",\"station\":\"上海虹桥\",\"endstation\":\"北京南\",\"departuretime\":\"16:18\",\"arrivaltime\":\"22:12\",\"sequenceno\":\"1\",\"costtime\":\"5时54分\",\"distance\":\"0\",\"isend\":\"1\",\"trainno12306\":\"5l0000G152E0\",\"pricesw\":\"1748\",\"pricetd\":\"\",\"pricegr1\":\"\",\"pricegr2\":\"\",\"pricerw1\":\"\",\"pricerw2\":\"\",\"priceyw1\":\"\",\"priceyw2\":\"\",\"priceyw3\":\"\",\"typename\":\"高铁\",\"priceyd\":\"933.0\",\"priceed\":\"553.0\"},{\"trainno\":\"G16\",\"type\":\"G\",\"station\":\"上海虹桥\",\"endstation\":\"北京南\",\"departuretime\":\"17:00\",\"arrivaltime\":\"21:36\",\"sequenceno\":\"1\",\"costtime\":\"4时36分\",\"distance\":\"0\",\"isend\":\"1\",\"trainno12306\":\"5l00000G1614\",\"pricesw\":\"1748\",\"pricetd\":\"\",\"pricegr1\":\"\",\"pricegr2\":\"\",\"pricerw1\":\"\",\"pricerw2\":\"\",\"priceyw1\":\"\",\"priceyw2\":\"\",\"priceyw3\":\"\",\"typename\":\"高铁\",\"priceyd\":\"933.0\",\"priceed\":\"553.0\"},{\"trainno\":\"G154\",\"type\":\"G\",\"station\":\"上海虹桥\",\"endstation\":\"北京南\",\"departuretime\":\"17:13\",\"arrivaltime\":\"22:48\",\"sequenceno\":\"1\",\"costtime\":\"5时35分\",\"distance\":\"0\",\"isend\":\"1\",\"trainno12306\":\"5l0000G15481\",\"pricesw\":\"1748\",\"pricetd\":\"\",\"pricegr1\":\"\",\"pricegr2\":\"\",\"pricerw1\":\"\",\"pricerw2\":\"\",\"priceyw1\":\"\",\"priceyw2\":\"\",\"priceyw3\":\"\",\"typename\":\"高铁\",\"priceyd\":\"933.0\",\"priceed\":\"553.0\"},{\"trainno\":\"G44\",\"type\":\"G\",\"station\":\"上海虹桥\",\"endstation\":\"北京南\",\"departuretime\":\"17:23\",\"arrivaltime\":\"23:08\",\"sequenceno\":\"4\",\"costtime\":\"5时45分\",\"distance\":\"0\",\"isend\":\"1\",\"trainno12306\":\"5600000G4400\",\"pricesw\":\"1748\",\"pricetd\":\"\",\"pricegr1\":\"\",\"pricegr2\":\"\",\"pricerw1\":\"\",\"pricerw2\":\"\",\"priceyw1\":\"\",\"priceyw2\":\"\",\"priceyw3\":\"\",\"typename\":\"高铁\",\"priceyd\":\"933.0\",\"priceed\":\"553.0\"},{\"trainno\":\"G158\",\"type\":\"G\",\"station\":\"上海虹桥\",\"endstation\":\"北京南\",\"departuretime\":\"17:34\",\"arrivaltime\":\"23:29\",\"sequenceno\":\"1\",\"costtime\":\"5时55分\",\"distance\":\"0\",\"isend\":\"1\",\"trainno12306\":\"5l0000G158D1\",\"pricesw\":\"1748\",\"pricetd\":\"\",\"pricegr1\":\"\",\"pricegr2\":\"\",\"pricerw1\":\"\",\"pricerw2\":\"\",\"priceyw1\":\"\",\"priceyw2\":\"\",\"priceyw3\":\"\",\"typename\":\"高铁\",\"priceyd\":\"933.0\",\"priceed\":\"553.0\"},{\"trainno\":\"G18\",\"type\":\"G\",\"station\":\"上海\",\"endstation\":\"北京南\",\"departuretime\":\"17:55\",\"arrivaltime\":\"22:36\",\"sequenceno\":\"1\",\"costtime\":\"4时41分\",\"distance\":\"0\",\"isend\":\"1\",\"trainno12306\":\"5500000G1800\",\"pricesw\":\"1762.5\",\"pricetd\":\"\",\"pricegr1\":\"\",\"pricegr2\":\"\",\"pricerw1\":\"\",\"pricerw2\":\"\",\"priceyw1\":\"\",\"priceyw2\":\"\",\"priceyw3\":\"\",\"typename\":\"高铁\",\"priceyd\":\"939.0\",\"priceed\":\"558.0\"},{\"trainno\":\"T110\",\"type\":\"T\",\"station\":\"上海\",\"endstation\":\"北京\",\"departuretime\":\"17:57\",\"arrivaltime\":\"10:08\",\"sequenceno\":\"1\",\"costtime\":\"16时11分\",\"distance\":\"0\",\"isend\":\"1\",\"trainno12306\":\"550000T11062\",\"pricesw\":\"\",\"pricetd\":\"\",\"pricerz\":\"\",\"priceyz\":\"177.5\",\"pricegr1\":\"879.5\",\"pricegr2\":\"\",\"pricerw1\":\"476.5\",\"pricerw2\":\"\",\"priceyw1\":\"304.5\",\"priceyw2\":\"\",\"priceyw3\":\"\",\"typename\":\"特快\"},{\"trainno\":\"G22\",\"type\":\"G\",\"station\":\"上海虹桥\",\"endstation\":\"北京南\",\"departuretime\":\"19:00\",\"arrivaltime\":\"23:18\",\"sequenceno\":\"1\",\"costtime\":\"4时18分\",\"distance\":\"0\",\"isend\":\"1\",\"trainno12306\":\"5l00000G221A\",\"pricesw\":\"1748\",\"pricetd\":\"\",\"pricegr1\":\"\",\"pricegr2\":\"\",\"pricerw1\":\"\",\"pricerw2\":\"\",\"priceyw1\":\"\",\"priceyw2\":\"\",\"priceyw3\":\"\",\"typename\":\"高铁\",\"priceyd\":\"933.0\",\"priceed\":\"553.0\"},{\"trainno\":\"D702\",\"type\":\"D\",\"station\":\"上海\",\"endstation\":\"北京\",\"departuretime\":\"19:09\",\"arrivaltime\":\"07:12\",\"sequenceno\":\"1\",\"costtime\":\"12时3分\",\"distance\":\"0\",\"isend\":\"1\",\"trainno12306\":\"550000D70200\",\"pricesw\":\"\",\"pricetd\":\"\",\"pricegr1\":\"\",\"pricegr2\":\"\",\"pricerw1\":\"858\",\"pricerw2\":\"\",\"priceyw1\":\"677\",\"priceyw2\":\"\",\"priceyw3\":\"\",\"typename\":\"动车\",\"priceyd\":\"\",\"priceed\":\"451.0\"},{\"trainno\":\"Z283\",\"type\":\"Z\",\"station\":\"上海南\",\"endstation\":\"北京\",\"departuretime\":\"19:30\",\"arrivaltime\":\"10:22\",\"sequenceno\":\"4\",\"costtime\":\"14时52分\",\"distance\":\"0\",\"isend\":\"0\",\"trainno12306\":\"560000Z28230\",\"typename\":\"直达特快\"},{\"trainno\":\"Z282\",\"type\":\"Z\",\"station\":\"上海南\",\"endstation\":\"北京\",\"departuretime\":\"19:30\",\"arrivaltime\":\"10:22\",\"sequenceno\":\"4\",\"costtime\":\"14时52分\",\"distance\":\"0\",\"isend\":\"0\",\"trainno12306\":\"560000Z28230\",\"typename\":\"直达特快\"},{\"trainno\":\"D706\",\"type\":\"D\",\"station\":\"上海\",\"endstation\":\"北京\",\"departuretime\":\"21:18\",\"arrivaltime\":\"09:24\",\"sequenceno\":\"1\",\"costtime\":\"12时6分\",\"distance\":\"0\",\"isend\":\"1\",\"trainno12306\":\"550000D70600\",\"pricesw\":\"\",\"pricetd\":\"\",\"pricegr1\":\"\",\"pricegr2\":\"\",\"pricerw1\":\"858\",\"pricerw2\":\"\",\"priceyw1\":\"677\",\"priceyw2\":\"\",\"priceyw3\":\"\",\"typename\":\"动车\",\"priceyd\":\"\",\"priceed\":\"451.0\"},{\"trainno\":\"D710\",\"type\":\"D\",\"station\":\"上海\",\"endstation\":\"北京南\",\"departuretime\":\"21:24\",\"arrivaltime\":\"09:22\",\"sequenceno\":\"1\",\"costtime\":\"11时58分\",\"distance\":\"0\",\"isend\":\"1\",\"trainno12306\":\"550000D71000\",\"pricesw\":\"\",\"pricetd\":\"\",\"pricegr1\":\"\",\"pricegr2\":\"\",\"pricerw1\":\"853\",\"pricerw2\":\"\",\"priceyw1\":\"673\",\"priceyw2\":\"\",\"priceyw3\":\"\",\"typename\":\"动车\",\"priceyd\":\"\",\"priceed\":\"449.0\"}]}}";
        //JSONObject jsonObject = new JSONObject(json);
        JSONArray result = jsonObject.getJSONObject("result").getJSONArray("list");

        for(int i=0;i<result.length();i++){
            JSONObject obj = (JSONObject)result.opt(i);
            String trainno = obj.getString("trainno");
            train.add(trainno);
            //String departstation = obj.getString("departstation");
            //depart.add(departstation);
            String station = obj.getString("station");
            startstation.add(station);
            String endstation = obj.getString("endstation");
            stration.add(endstation);
            String costtime = obj.getString("costtime");
            time.add(costtime);
            String numsw = obj.getString("pricesw");
            numsw1.add(numsw);
            String numtd = obj.getString("pricetd");
            numtd1.add(numtd);
            String numyd = obj.getString("priceyd");
            numyd1.add(numyd);
            String numed = obj.getString("pricegr1");
            numed1.add(numed);
            String numrz = obj.getString("pricegr2");
            numrz1.add(numrz);
            String numyz = obj.getString("pricerw1");
            numyz1.add(numyz);
            String numgr = obj.getString("pricerw2");
            numgr1.add(numgr);
            String numrw = obj.getString("priceyw1");
            numrw1.add(numrw);
            String numyw = obj.getString("priceyw2");
            numyw1.add(numyw);
            String numwz = obj.getString("priceyw3");
            numwz1.add(numwz);
            String numqt = obj.getString("priceed");
            numqt1.add(numqt);
            TicketBean ticketBean = new TicketBean(trainno,station,endstation,costtime,numsw,numtd,numyd,numed,numrz,numyz,numgr,numrw,numyw,numwz,numqt);
            ticketBeanList.add(ticketBean);
        }
        System.out.println("finish");
    }

}


