package com.example.chai.cocktails.views.activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.chai.cocktails.R;
import com.example.chai.cocktails.adapters.DrinkDetailListAdapter;
import com.example.chai.cocktails.models.pojos.DrinkDetail;
import com.example.chai.cocktails.viewmodels.DrinkDetailListingViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DrinkDetailListingActivity extends AppCompatActivity {

    @BindView(R.id.detailedList)
    RecyclerView detailedList;

    DrinkDetailListingViewModel viewModel;
    DrinkDetailListAdapter adapter;

    List<DrinkDetail> drinksList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink_detail_listing);
        getSupportActionBar().hide();
        ButterKnife.bind(this);


        viewModel = ViewModelProviders.of(this)
                .get(DrinkDetailListingViewModel.class);

        viewModel.fetchUrlDetails(this);
        drinksList = new ArrayList<>();
        loadData();
        initViewModel();
        viewModel.getData();


    }

    private void initViewModel() {
        viewModel.drinksObservable.observe(this, new Observer<List<DrinkDetail>>() {
            @Override
            public void onChanged(@Nullable List<DrinkDetail> drinks) {
                drinksList.clear();
                drinksList.addAll(drinks);
                if (adapter == null) {
                    loadData();
                } else {
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }

    private void loadData() {
        LinearLayoutManager layoutManager =
                new LinearLayoutManager(this);
        detailedList.setLayoutManager(layoutManager);
        adapter =
                new DrinkDetailListAdapter(drinksList, this);
        detailedList.setAdapter(adapter);
    }


}
