package com.example.chai.cocktails.repository;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.example.chai.cocktails.interfaces.ListingService;
import com.example.chai.cocktails.models.apiresponsewrappers.DrinkListingAPIResonse;
import com.example.chai.cocktails.models.apiresponsewrappers.NameListingAPIResponse;
import com.example.chai.cocktails.models.wrapperpojos.DrinkDetailListing;
import com.example.chai.cocktails.models.wrapperpojos.DrinkListing;
import com.example.chai.cocktails.utils.Constants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CocktailRepository {
    private static CocktailRepository cocktailRepository;

    private ListingService listingService;

    private MutableLiveData<NameListingAPIResponse> nameListingApiResponse = new MutableLiveData<>();
    private MutableLiveData<DrinkListingAPIResonse> drinkListingAPIResonse = new MutableLiveData<>();

    public CocktailRepository() {
        listingService = ListingService.retrofit.create(ListingService.class);
    }

    public static synchronized CocktailRepository getInstance() {
        if (cocktailRepository == null) {
            cocktailRepository = new CocktailRepository();
        }
        return cocktailRepository;
    }


    public MutableLiveData<NameListingAPIResponse> getData(final String filter) {
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
                    nameListingApiResponse.postValue(new NameListingAPIResponse(response.body().getDrinks()));
                } else {
                    int statusCode = response.code();
                    nameListingApiResponse.postValue(new NameListingAPIResponse(statusCode));
                }
            }

            @Override
            public void onFailure(Call<DrinkListing> call, Throwable t) {
                nameListingApiResponse.postValue(new NameListingAPIResponse(t));
            }


        });

        return nameListingApiResponse;

    }


    public MutableLiveData<DrinkListingAPIResonse> getData(String type, String name) {
        ListingService webService =
                ListingService.retrofit.create(ListingService.class);
        Log.d("Testing", Constants.getUrlByFilterAndName(type, name));
        Call<DrinkDetailListing> call = webService.filterByMentioned(Constants.getUrlByFilterAndName(type, name));
        call.enqueue(new Callback<DrinkDetailListing>() {
            @Override
            public void onResponse(Call<DrinkDetailListing> call, Response<DrinkDetailListing> response) {
                if (response.isSuccessful()) {
                    drinkListingAPIResonse.postValue(new DrinkListingAPIResonse(response.body().getDrinks()));
                } else {
                    int responseCode = response.code();
                    drinkListingAPIResonse.postValue(new DrinkListingAPIResonse(responseCode));
                }
            }

            @Override
            public void onFailure(Call<DrinkDetailListing> call, Throwable t) {
                drinkListingAPIResonse.postValue(new DrinkListingAPIResonse(t));
            }


        });

        return drinkListingAPIResonse;

    }


}
