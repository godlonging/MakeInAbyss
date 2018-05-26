package com.trip.animaljie.makeinabyss.WeatherAdapter;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.trip.animaljie.makeinabyss.BuildConfig;
import com.trip.animaljie.makeinabyss.R;

import github.vatsal.easyweather.Helper.TempUnitConverter;
import github.vatsal.easyweather.Helper.WeatherCallback;
import github.vatsal.easyweather.WeatherMap;
import github.vatsal.easyweather.retrofit.models.Weather;
import github.vatsal.easyweather.retrofit.models.WeatherResponseModel;

import static com.trip.animaljie.makeinabyss.BuildConfig.OWM_API_KEY;
import static github.vatsal.easyweather.Helper.TempUnitConverter.*;

public class Weather_fragment1 extends Fragment {
    public final String APP_ID = BuildConfig.OWM_API_KEY;
    String city = null;
    private TextView weatherTitle;
    private ImageButton refresh;
    private ImageView weatherIcon;
    private TextView location;
    private TextView condition;
    private TextView temp;
    private TextView tvHumidity;
    private TextView tvPressure;
    private TextView tvWind;
    private TextView tvWindDeg;
    private EditText input;
    private TextView go;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.weatherfragment_layout1,container,false);
        refresh = view.findViewById(R.id.refresh);
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadweather(city);
            }
        });
        weatherIcon = view.findViewById(R.id.weather_icon);
        location = view.findViewById(R.id.location);
        condition = view.findViewById(R.id.condition);
        temp = view.findViewById(R.id.temp);
        tvHumidity = view.findViewById(R.id.tvHumidity);
        tvPressure = view.findViewById(R.id.tvPressure);
        tvWind = view.findViewById(R.id.tvWind);
        tvWindDeg = view.findViewById(R.id.tvWindDeg);
        input = view.findViewById(R.id.et_city);
        go = view.findViewById(R.id.tv_go);
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            city = input.getText().toString().trim();
            loadweather(city);
            Toast.makeText(getActivity(),city+"",Toast.LENGTH_SHORT).show();

            }
        });

        return view;
    }
    private void loadweather(String city){
        WeatherMap weatherMap = new WeatherMap(getActivity(),APP_ID);
        weatherMap.getCityWeather(city, new WeatherCallback() {
            @Override
            public void success(WeatherResponseModel weatherResponseModel) {
                populateWeather(weatherResponseModel);
            }

            @Override
            public void failure(String s) {
                System.out.println(s);
            }
        });
    }
    private void populateWeather(WeatherResponseModel responseModel){
        Weather weather[] = responseModel.getWeather();
        condition.setText(weather[0].getMain());
        temp.setText(convertToCelsius(responseModel.getMain().getTemp()).intValue() + " °C");
        location.setText(responseModel.getName());
        tvHumidity.setText(responseModel.getMain().getHumidity() + "%");
        tvPressure.setText(responseModel.getMain().getPressure() + " hPa");
        tvWind.setText(responseModel.getWind().getSpeed() + "m/s");
        tvWindDeg.setText(responseModel.getWind().getDeg() + "°");

        String link = weather[0].getIconLink();
        Picasso.with(getActivity()).load(link).into(weatherIcon);
    }
}
