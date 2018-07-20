package com.example.chai.cocktails.viewmodels;

import android.app.Activity;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.example.chai.cocktails.interfaces.ListingService;
import com.example.chai.cocktails.models.DrinkDetail;
import com.example.chai.cocktails.models.DrinkDetailListing;
import com.example.chai.cocktails.utils.Constants;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DrinkDetailListingViewModel extends ViewModel {

    public MutableLiveData<List<DrinkDetail>> drinksObservable;
    String type;
    String name;

    public DrinkDetailListingViewModel() {
        drinksObservable = new MutableLiveData<>();
    }

    public void fetchUrlDetails(Activity activity) {
        if (activity.getIntent() != null) {
            type = activity.getIntent().getStringExtra("type");
            name = activity.getIntent().getStringExtra("categoryName");
        }
    }

    public void getData() {
        ListingService webService =
                ListingService.retrofit.create(ListingService.class);
        Call<DrinkDetailListing> call = webService.filterByMentioned(Constants.getUrlByFilterAndName(type, name));
        call.enqueue(new Callback<DrinkDetailListing>() {
            @Override
            public void onResponse(Call<DrinkDetailListing> call, Response<DrinkDetailListing> response) {
                if (response.isSuccessful()) {
                    drinksObservable.postValue(response.body().getDrinks());
                } else {
                    Log.d("MainActivity", "Response empty");
                }
            }

            @Override
            public void onFailure(Call<DrinkDetailListing> call, Throwable t) {
                Log.d("error", "" + call);
            }


        });

    }
}
