package com.example.chai.cocktails.viewmodels;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.chai.cocktails.repository.CocktailRepository;
import com.example.chai.cocktails.models.Drink;
import com.example.chai.cocktails.models.NameListingAPIResponse;

import java.util.List;

public class MainViewModel extends ViewModel {

    public MutableLiveData<List<Drink>> categoryObservable;
    public MutableLiveData<List<Drink>> glassObservable;
    public MutableLiveData<List<Drink>> ingredientObservable;
    public MutableLiveData<NameListingAPIResponse> nameListingAPIResponse;
    CocktailRepository repository = CocktailRepository.getInstance();

    public MainViewModel() {
        categoryObservable = new MutableLiveData<>();
        glassObservable = new MutableLiveData<>();
        ingredientObservable = new MutableLiveData<>();
        nameListingAPIResponse = new MutableLiveData<>();
    }

    public void getData(String filter){
        nameListingAPIResponse = repository.getData(filter);
    }


}

/*
*
* public MutableLiveData<List<Drink>> categoryObservable;
    public MutableLiveData<List<Drink>> glassObservable;
    public MutableLiveData<List<Drink>> ingredientObservable;

    public MainViewModel() {
        categoryObservable = new MutableLiveData<>();
        glassObservable = new MutableLiveData<>();
        ingredientObservable = new MutableLiveData<>();
    }

    public void getData(final String filter) {
        ListingService webService =
                ListingService.retrofit.create(ListingService.class);
        Call<DrinkListing> call;

        switch (filter) {
            case Constants.FILTER_CATEGORY:
                call = webService.listDrinksByCategory();
                break;
            case Constants.FILTER_GLASS:
                call = webService.listDrinksByGlass();
                break;
            case Constants.FILTER_INGREDIENTS:
                call = webService.listDrinksByIngredient();
                break;
            default:
                call = webService.listDrinksByCategory();
        }

        call.enqueue(new Callback<DrinkListing>() {
            @Override
            public void onResponse(Call<DrinkListing> call, Response<DrinkListing> response) {
                if (response.isSuccessful()) {
                    switch (filter) {
                        case Constants.FILTER_CATEGORY:
                            categoryObservable.postValue(response.body().getDrinks());
                            break;
                        case Constants.FILTER_GLASS:
                            glassObservable.postValue(response.body().getDrinks());
                            break;
                        case Constants.FILTER_INGREDIENTS:
                            ingredientObservable.postValue(response.body().getDrinks());
                            break;
                        default:
                            categoryObservable.postValue(response.body().getDrinks());
                    }

                } else {
                    Log.d("MainActivity", "Response empty");
                }
            }

            @Override
            public void onFailure(Call<DrinkListing> call, Throwable t) {
                Log.d("error", "" + call);
            }


        });

    }*/