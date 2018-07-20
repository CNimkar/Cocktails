package com.example.chai.cocktails.viewmodels;

import android.app.Activity;
import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.chai.cocktails.interfaces.ListingService;
import com.example.chai.cocktails.models.DrinkFullDetail;
import com.example.chai.cocktails.models.DrinkFullDetailWrapper;
import com.example.chai.cocktails.utils.Constants;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DrinkFullDetailViewModel extends AndroidViewModel {

    String id;
    public MutableLiveData<DrinkFullDetail> drinkObservable;

    public void fetchId(Activity activity){
        if (activity.getIntent() != null) {
            id = activity.getIntent().getStringExtra("id");
        }
    }

    public DrinkFullDetailViewModel(@NonNull Application application) {
        super(application);
        drinkObservable = new MutableLiveData<>();
        getData();
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
