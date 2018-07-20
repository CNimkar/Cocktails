package com.example.chai.cocktails.viewmodels;

import android.app.Activity;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.example.chai.cocktails.interfaces.ListingService;
import com.example.chai.cocktails.models.pojos.DrinkFullDetail;
import com.example.chai.cocktails.models.wrapperpojos.DrinkFullDetailWrapper;
import com.example.chai.cocktails.utils.Constants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DrinkFullDetailViewModel extends ViewModel {

    public MutableLiveData<DrinkFullDetail> drinkObservable;
    String id;

    public DrinkFullDetailViewModel() {
        drinkObservable = new MutableLiveData<>();
        getData();
    }

    public void fetchId(Activity activity) {
        if (activity.getIntent() != null) {
            id = activity.getIntent().getStringExtra("id");
        }
    }

    public void getData() {
        ListingService webService =
                ListingService.retrofit.create(ListingService.class);
        Call<DrinkFullDetailWrapper> call = webService.getDrinkById(Constants.getUrlById(id));
        call.enqueue(new Callback<DrinkFullDetailWrapper>() {
            @Override
            public void onResponse(Call<DrinkFullDetailWrapper> call, Response<DrinkFullDetailWrapper> response) {
                if (response.isSuccessful()) {
                    drinkObservable.postValue(response.body().getDrinks().get(0));
                } else {
                    Log.d("MainActivity", "Response empty");
                }
            }

            @Override
            public void onFailure(Call<DrinkFullDetailWrapper> call, Throwable t) {
                Log.d("error", "" + call);
            }


        });

    }
}
