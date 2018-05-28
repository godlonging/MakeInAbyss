package com.trip.animaljie.makeinabyss;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.trip.animaljie.makeinabyss.PayAdapter.PayFragment;

public class PayActivity extends AppCompatActivity {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        button = findViewById(R.id.btn_pay);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PayFragment payFragment = new PayFragment();
                payFragment.show(getFragmentManager(),"payFragment");
            }
        });
    }
}
