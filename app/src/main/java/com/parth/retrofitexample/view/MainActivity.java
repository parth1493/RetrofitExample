package com.parth.retrofitexample.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.parth.retrofitexample.R;
import com.parth.retrofitexample.adapter.CountryAdapter;
import com.parth.retrofitexample.model.Info;
import com.parth.retrofitexample.model.Result;
import com.parth.retrofitexample.service.GetCountryDataService;
import com.parth.retrofitexample.service.RetrofitInstance;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Result> results;
    private static final String TAG = "MainActivity";
    private RecyclerView recyclerView;
    private CountryAdapter countryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getCountries();
    }

    private Object getCountries() {

        GetCountryDataService getCountryDataService = RetrofitInstance.getService();

        Call<Info> call = getCountryDataService.getResult();

        call.enqueue(new Callback<Info>() {
            @Override
            public void onResponse(Call<Info> call, Response<Info> response) {

                Info info = response.body();

                if(info != null && info.getResult() !=null){
                    results = (ArrayList<Result>) info.getResult();

                    for(Result r:results){
                        Log.i(TAG, "onResponse: "+ r.getName());
                    }
                    viewData();
                }
            }

            @Override
            public void onFailure(Call<Info> call, Throwable t) {

            }
        });

        return results;
    }

    private void viewData() {

        recyclerView = findViewById(R.id.rv_countries_list);
        countryAdapter = new CountryAdapter(results);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(countryAdapter);
        
    }
}
