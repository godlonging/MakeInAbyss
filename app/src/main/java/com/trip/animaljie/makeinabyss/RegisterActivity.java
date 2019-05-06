package com.trip.animaljie.makeinabyss;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class RegisterActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener,CompoundButton.OnCheckedChangeListener,View.OnClickListener{

    public RadioGroup rg_gender;
    public RadioButton rb_gender1;
    public RadioButton rb_gender2;
    public RadioGroup rg_department;
    public RadioButton rb_department1;
    public RadioButton rb_department2;
    public RadioButton rb_department3;
    public RadioButton rb_department4;
    public RadioGroup rg_consume;
    public RadioButton rb_consume1;
    public RadioButton rb_consume2;
    public RadioButton rb_consume3;
    public RadioButton rb_consume4;
    public RadioGroup rg_grade;
    public RadioButton rb_grade1;
    public RadioButton rb_grade2;
    public RadioButton rb_grade3;
    public RadioButton rb_grade4;
    public CheckBox cb_meal;
    public CheckBox cb_shopping;
    public CheckBox cb_study;
    public CheckBox cb_amusement;
    public CheckBox cb_internet;
    public  CheckBox cb_loveconsume;
    public CheckBox cb_otherconsume;
    public EditText et_suggest;
    public Button bt_submit;
    public String gender;
    public String grade;
    public String suggestion;
    public String consume;
    public String department;

    ArrayList mainConsume=new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        initview();
    }
    public void initview(){
        //性别
        rg_gender=(RadioGroup)findViewById(R.id.rg_gender);
        rb_gender1=(RadioButton)findViewById(R.id.rb_man);
        rb_gender2=(RadioButton)findViewById(R.id.rb_woman);
//        学院
        rg_department=(RadioGroup)findViewById(R.id.rg_department);
        rb_department1=(RadioButton)findViewById(R.id.rb_department1);
        rb_department2=(RadioButton)findViewById(R.id.rb_department2);
        rb_department3=(RadioButton)findViewById(R.id.rb_department3);
        rb_department4=(RadioButton)findViewById(R.id.rb_department4);
//消费
        rg_consume=(RadioGroup)findViewById(R.id.rg_consume);
        rb_consume1=(RadioButton)findViewById(R.id.rb_consume1);
        rb_consume2=(RadioButton)findViewById(R.id.rb_consume2);
        rb_consume3=(RadioButton)findViewById(R.id.rb_consume3);
        rb_consume4=(RadioButton)findViewById(R.id.rb_consume4);
//年级
        rg_grade=(RadioGroup)findViewById(R.id.rg_grade);
        rb_grade1=(RadioButton)findViewById(R.id.rb_grade1);
        rb_grade2=(RadioButton)findViewById(R.id.rb_grade2);
        rb_grade3=(RadioButton)findViewById(R.id.rb_grade3);
        rb_grade4=(RadioButton)findViewById(R.id.rb_grade4);
//        主要消费
        cb_meal=(CheckBox)findViewById(R.id.cb_mainconsume_a);
        cb_shopping=(CheckBox)findViewById(R.id.cb_mainconsume_b);
        cb_study=(CheckBox)findViewById(R.id.cb_mainconsume_c);
        cb_amusement=(CheckBox)findViewById(R.id.cb_mainconsume_d);
        cb_internet=(CheckBox)findViewById(R.id.cb_mainconsume_e);
        cb_loveconsume=(CheckBox)findViewById(R.id.cb_mainconsume_f);
        cb_otherconsume=(CheckBox)findViewById(R.id.cb_mainconsume_g);

//        建议
        et_suggest=(EditText)findViewById(R.id.et_suggest);
        bt_submit=(Button)findViewById(R.id.btn_submit);
        //bt_exit=findViewById(R.id.btn_exit);
        bt_submit.setOnClickListener(this);
        //bt_exit.setOnClickListener(this);


        rg_gender.setOnCheckedChangeListener(this);
        rg_department.setOnCheckedChangeListener(this);
        rg_consume.setOnCheckedChangeListener(this);
        rg_grade.setOnCheckedChangeListener(this);

        cb_shopping.setOnCheckedChangeListener(this);
        cb_meal.setOnCheckedChangeListener(this);
        cb_otherconsume.setOnCheckedChangeListener(this);
        cb_study.setOnCheckedChangeListener(this);
        cb_amusement.setOnCheckedChangeListener(this);
        cb_loveconsume.setOnCheckedChangeListener(this);
        cb_internet.setOnCheckedChangeListener(this);

    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        int checkboxId=compoundButton.getId();
        //Toast.makeText(this,"checkboxId:"+checkboxId,Toast.LENGTH_LONG).show();
        switch (checkboxId){
            case R.id.cb_mainconsume_a:
                mainConsume.add(cb_meal.getText().toString());
                // Toast.makeText(this,"你选择了"+cb_meal.getText().toString()+"check:"+isChecked,Toast.LENGTH_LONG).show();
                break;
            case R.id.cb_mainconsume_b:
                mainConsume.add(cb_shopping.getText().toString());
                // Toast.makeText(this,"你选择了"+cb_shopping.getText().toString()+"check:"+isChecked,Toast.LENGTH_LONG).show();
                break;
            case R.id.cb_mainconsume_c:
                mainConsume.add(cb_study.getText().toString());
                // Toast.makeText(this,"你选择了"+cb_shopping.getText().toString()+"check:"+isChecked,Toast.LENGTH_LONG).show();
                break;
            case R.id.cb_mainconsume_d:
                mainConsume.add(cb_amusement.getText().toString());
                // Toast.makeText(this,"你选择了"+cb_shopping.getText().toString()+"check:"+isChecked,Toast.LENGTH_LONG).show();
                break;
            case R.id.cb_mainconsume_e:
                mainConsume.add(cb_internet.getText().toString());
                //Toast.makeText(this,"你选择了"+cb_shopping.getText().toString()+"check:"+isChecked,Toast.LENGTH_LONG).show();
                break;
            case R.id.cb_mainconsume_f:
                mainConsume.add(cb_loveconsume.getText().toString());
                // Toast.makeText(this,"你选择了"+cb_shopping.getText().toString()+"check:"+isChecked,Toast.LENGTH_LONG).show();
                break;
            case R.id.cb_mainconsume_g:
                mainConsume.add(cb_otherconsume.getText().toString());
                // Toast.makeText(this,"你选择了"+cb_shopping.getText().toString()+"check:"+isChecked,Toast.LENGTH_LONG).show();
                break;
        }
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.btn_submit) {
            suggestion = String.valueOf(et_suggest.getText().toString().trim());
            if (gender == null || department == null || grade == null || consume == null || mainConsume.size() == 0) {
                Toast.makeText(this, "您有未填写的项目！", Toast.LENGTH_SHORT).show();
            } else {
                if (TextUtils.isEmpty(et_suggest.getText())) {
                    Toast.makeText(this, "请填写您的建议", Toast.LENGTH_SHORT).show();
                } else {
                }

                //进行页面的跳转和数据的传递
                Intent intent = new Intent(this, MainActivity.class);
                String name = intent.getStringExtra("username");
                intent.putExtra("username",name);
                intent.putExtra("gender",gender);
                intent.putExtra("department",department);
                intent.putExtra("grade",grade);
                intent.putExtra("consume",consume);
                startActivity(intent);
            }
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (i){
            case R.id.rb_man:
                gender=rb_gender1.getText().toString();
                //Toast.makeText(this,"你选择了"+rb_gender1.getText().toString(),Toast.LENGTH_LONG).show();
                break;
            case R.id.rb_woman:
                gender=rb_gender2.getText().toString();
                // Toast.makeText(this,"你选择了"+rb_gender2.getText().toString(),Toast.LENGTH_LONG).show();
                break;

            case R.id.rb_department1:
                department=rb_department1.getText().toString();
                // Toast.makeText(this,"你选择了"+rb_department1.getText().toString(),Toast.LENGTH_LONG).show();
                break;
            case R.id.rb_department2:
                department=rb_department2.getText().toString();
                //Toast.makeText(this,"你选择了"+rb_department2.getText().toString(),Toast.LENGTH_LONG).show();
                break;
            case R.id.rb_department3:
                department=rb_department3.getText().toString();
                //Toast.makeText(this,"你选择了"+rb_department3.getText().toString(),Toast.LENGTH_LONG).show();
                break;
            case R.id.rb_department4:
                department=rb_department4.getText().toString();
                //Toast.makeText(this,"你选择了"+rb_department4.getText().toString(),Toast.LENGTH_LONG).show();
                break;

            case R.id.rb_consume1:
                consume=rb_consume1.getText().toString();
                //Toast.makeText(this,"你选择了"+rb_consume1.getText().toString(),Toast.LENGTH_LONG).show();
                break;
            case R.id.rb_consume2:
                consume=rb_consume2.getText().toString();
                //Toast.makeText(this,"你选择了"+rb_consume2.getText().toString(),Toast.LENGTH_LONG).show();
                break;
            case R.id.rb_consume3:
                consume=rb_consume3.getText().toString();
                //Toast.makeText(this,"你选择了"+rb_consume3.getText().toString(),Toast.LENGTH_LONG).show();
                break;
            case R.id.rb_consume4:
                consume=rb_consume4.getText().toString();
                /// Toast.makeText(this,"你选择了"+rb_consume4.getText().toString(),Toast.LENGTH_LONG).show();
                break;

            case R.id.rb_grade1:
                grade=rb_grade1.getText().toString();
                // Toast.makeText(this,"你选择了"+rb_grade1.getText().toString(),Toast.LENGTH_LONG).show();
                break;
            case R.id.rb_grade2:
                grade=rb_grade2.getText().toString();
                //Toast.makeText(this,"你选择了"+rb_grade2.getText().toString(),Toast.LENGTH_LONG).show();
                break;
            case R.id.rb_grade3:
                grade=rb_grade3.getText().toString();
                // Toast.makeText(this,"你选择了"+rb_grade3.getText().toString(),Toast.LENGTH_LONG).show();
                break;
            case R.id.rb_grade4:
                grade=rb_grade4.getText().toString();
                //Toast.makeText(this,"你选择了"+rb_grade4.getText().toString(),Toast.LENGTH_LONG).show();
                break;
        }


    }
}
