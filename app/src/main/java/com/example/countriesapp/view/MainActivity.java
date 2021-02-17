package com.example.countriesapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.countriesapp.R;
import com.example.countriesapp.model.CountryModel;
import com.example.countriesapp.viewmodel.ListViewmodel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.recyclerView)
    RecyclerView countriesList;
    @BindView(R.id.list_error)
    TextView error;
    @BindView(R.id.loading_view)
    ProgressBar loading;
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refresh;

    private ListViewmodel viewmodel;
    private CountryListAdapter adapter = new CountryListAdapter(new ArrayList<>());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        viewmodel = ViewModelProviders.of(this).get(ListViewmodel.class);
        viewmodel.referesh();

        countriesList.setLayoutManager(new LinearLayoutManager(this));
        countriesList.setAdapter(adapter);

        refresh.setOnRefreshListener(() -> {
            viewmodel.referesh();
            refresh.setRefreshing(false);
        });
        
        observeViewModel();
    }

    private void observeViewModel() {
        viewmodel.countries.observe(this, countryModels -> {
            if(countryModels != null)
            {
                countriesList.setVisibility(View.VISIBLE);
                adapter.updateCountries(countryModels);
            }

        });

        viewmodel.countryLoadError.observe(this, isError -> {
            if(isError != null)
                error.setVisibility(isError? View.VISIBLE:View.GONE);
        });

        viewmodel.loading.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isLoading) {
                if(isLoading != null)
                    loading.setVisibility(isLoading? View.VISIBLE:View.GONE);
                if(isLoading)
                {
                    countriesList.setVisibility(View.GONE);
                    error.setVisibility(View.GONE);
                }
            }
        });
    }
}