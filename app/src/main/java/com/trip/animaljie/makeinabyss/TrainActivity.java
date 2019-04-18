package com.trip.animaljie.makeinabyss;

import android.content.Context;
import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.bumptech.glide.Glide;
import com.dd.morphingbutton.MorphingButton;
import com.donkingliang.banner.CustomBanner;
import com.lljjcoder.style.citylist.CityListSelectActivity;
import com.lljjcoder.style.citylist.bean.CityInfoBean;
import com.lljjcoder.style.citylist.utils.CityListLoader;

import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.Random;

public class TrainActivity extends AppCompatActivity {
    private CustomBanner<String> mBanner;
    private TextView form_text;
    private TextView to_text;
    private ImageButton swap;
    private TextView selectdate;
    private MorphingButton search;
    private boolean click;
    public String to;
    public String form;
    public String times;
    public String name = "default";
    private Button like_1;
    private Button like_2;
    private Button like_3;
    private Button like_4;
    private Button like_5;
    private Button like_6;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train);
        mBanner = (CustomBanner) findViewById(R.id.banner);
        form_text=(TextView)findViewById(R.id.Form);
        to_text=(TextView)findViewById(R.id.To);
        swap=(ImageButton)findViewById(R.id.swap);
        selectdate=(TextView)findViewById(R.id.select_date);
        search =(MorphingButton)findViewById(R.id.search);
        like_1 = (Button)findViewById(R.id.like_1);
        like_2 = (Button)findViewById(R.id.like_2);
        like_3 = (Button)findViewById(R.id.like_3);
        like_4 = (Button)findViewById(R.id.like_4);
        like_5 = (Button)findViewById(R.id.like_5);
        like_6 = (Button)findViewById(R.id.like_6);


        CityListLoader.getInstance().loadCityData(this);
        ArrayList<String> images = new ArrayList<>();
        images.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1526645656542&di=4006505c6b56f3c3499545c59b081f23&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimage%2Fc0%253Dshijue1%252C0%252C0%252C294%252C40%2Fsign%3Df8bb9038de2a283457ab3e4833dca39f%2F359b033b5bb5c9ea8d2c83c6df39b6003af3b33c.jpg");
        images.add("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3897167692,472943798&fm=27&gp=0.jpg");
        images.add("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=4079480355,3869162889&fm=200&gp=0.jpg");
        setBean(images);
        form_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TrainActivity.this, CityListSelectActivity.class);
                startActivityForResult(intent, CityListSelectActivity.CITY_SELECT_RESULT_FRAG);
                click = true;
            }
        });
        to_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TrainActivity.this, CityListSelectActivity.class);
                startActivityForResult(intent, CityListSelectActivity.CITY_SELECT_RESULT_FRAG);
                click = false;
            }
        });
        swap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tmptext = form_text.getText()+"";
                form_text.setText(to_text.getText()+"");
                to_text.setText(tmptext);
            }
        });
        selectdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerView pvTime = new TimePickerBuilder(TrainActivity.this, new OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                        String time = format.format(date);
                        times = time;
                        selectdate.setText(time);
                        Toast.makeText(TrainActivity.this, time, Toast.LENGTH_SHORT).show();
                    }
                }).build();
                pvTime.show();

            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MorphingButton.Params circle = MorphingButton.Params.create()
                        .duration(500)
                        .cornerRadius(180) // 56 dp
                        .width(180) // 56 dp
                        .height(180) // 56 dp
                        .color(R.color.liji_material_red_500) // normal state color
                        .colorPressed(R.color.liji_material_red_700) // pressed state color
                        .icon(R.drawable.ic_done);
                search.morph(circle);

                Intent ticket = new Intent(TrainActivity.this,TicketActivity.class);
                startActivity(ticket);

                ticket.putExtra("to",to);
                ticket.putExtra("from",form);
                ticket.putExtra("date",times);
                startActivityForResult(ticket,1);
            }
        });
        try {
            Intent intent = getIntent();
            name = intent.getStringExtra("username");
        }catch (Exception e){
            name = "default";
        }
        switch (name) {
            case "123456":
                String[] local = new String[]{"哈尔滨","黑龙江"};
                Random ran = new Random(1);
                int number = ran.nextInt(2);
                like_1.setText(local[number]);
                break;
            case"yxy":
                like_2.setText("成都");
                break;
            case "justin":
                like_3.setText("洛杉矶");
                break;
            case "default":
                break;

        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CityListSelectActivity.CITY_SELECT_RESULT_FRAG) {
            if (resultCode == RESULT_OK) {
                if (data == null) {
                    return;
                }
                Bundle bundle = data.getExtras();

                CityInfoBean cityInfoBean = (CityInfoBean) bundle.getParcelable("cityinfo");


                if (null == cityInfoBean) {
                    return;
                }
                if(click==true){
                    form_text.setText(""+cityInfoBean.getName());
                    form = cityInfoBean.getName()+"";
                }
                if(click==false){
                    to_text.setText(""+cityInfoBean.getName());
                    to = cityInfoBean.getName()+"";
                }



            }
        }
    }

    private void setBean(final ArrayList beans) {
        mBanner.setPages(new CustomBanner.ViewCreator<String>() {
            @Override
            public View createView(Context context, int position) {
                ImageView imageView = new ImageView(context);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                return imageView;
            }

            @Override
            public void updateUI(Context context, View view, int position, String entity) {
                Glide.with(context).load(entity).into((ImageView) view);
            }
        }, beans).startTurning(2000);
    }
}
